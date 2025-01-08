package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.MapLoader;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;

import java.util.ArrayList;
import java.util.List;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class WarehouseLoader implements MapLoader
{
    String[] input;
    Warehouse wh;

    boolean doubleWidth = false;

    public WarehouseLoader() {}

    public WarehouseLoader(boolean doubleWidth) {
        this.doubleWidth = doubleWidth;
    }

    public Robot getRobot()
    {
        return robot;
    }

    public List<Direction> getInstructions()
    {
        return instructions;
    }

    Robot robot;

    List<Direction> instructions = new ArrayList<>();

    @Override
    public ObjectMap getMap()
    {
        return wh;
    }

    @Override
    public void readMapPattern(String[] input)
    {
        this.wh = new Warehouse(doubleWidth);
        this.input = input;
        int row = 0;
        for (String line : input) {
            int whCol = 0;
            for (int col = 0; col<line.length(); col++) {
                String symbol = line.substring(col, col+1);
                whCol += dealWithSymbol(symbol, row, whCol, wh);
            }
            row++;
        }
    }

    @Override
    public int dealWithSymbol(String symbol, int row, int col, ObjectMap wh)
    {
    WarehouseObject who = null;
    switch (symbol) {
        case "@" -> {robot = new Robot(row, col, doubleWidth, (Warehouse) wh);
            who = robot;}
        case "O" -> who = new Box(row, col, doubleWidth, (Warehouse)wh);
        case "#" -> who = new Wall(row, col, doubleWidth, (Warehouse) wh);
        case "<","^","v",">" -> instructions.add(getDirection(symbol));
    }
    if (doubleWidth)
        return 2;
    else
        return 1;

}

    private Direction getDirection(String dirSymbol)
    {
        return switch (dirSymbol) {
            case "^" -> UP;
            case "v" -> DOWN;
            case "<" -> LEFT;
            case ">" -> RIGHT;
            default -> throw new RuntimeException("Unknown direction symbol " + dirSymbol);
        };
    }

    public Warehouse getWarehouse()
    {
        return wh;
    }
}
