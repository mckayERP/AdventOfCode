package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

import static org.mckayERP.AdventOfCode.utilities.Direction.LEFT;
import static org.mckayERP.AdventOfCode.utilities.Direction.RIGHT;

public class Warehouse implements ObjectMap
{

    int maxRow = -1, maxCol = -1;

    boolean doubleWidth = false;
    List<MapObject> conents = new ArrayList<>();

    public Warehouse() {}

    public Warehouse(boolean doubleWidth) {
        this.doubleWidth = doubleWidth;
    }

    @Override
    public void add(MapObject who)
    {
        Position p = who.getPosition();
        int width = ((WarehouseObject) who).getWidth();
        if (!spaceEmpty(p, ((WarehouseObject) who).getWidth(), null))
        {
            throw new RuntimeException("Warehouse space is not empty " + p);
        }
        if (p.getRow() > maxRow)
            maxRow = p.getRow();
        if (p.getCol()+(width-1) > maxCol)
            maxCol = p.getCol()+(width-1);
        conents.add(who);
    }

    @Override
    public List<MapObject> getContents()
    {
        return conents;
    }

    @Override
    public boolean spaceEmpty(Position p)
    {
        return getContentAt(p) == null;
    }


    public boolean spaceEmpty(Position p, int width, Direction dir)
    {
        if (width == 1  || dir == null)
            return spaceEmpty(p);
        else
        {
            boolean empty = true;
            switch (dir) {
                case UP, DOWN -> empty = getContentAt(p) == null && getContentAt(p.clone().move(LEFT)) == null && getContentAt(p.clone().move(RIGHT)) == null;
                case LEFT, RIGHT -> empty = getContentAt(p.clone()) == null;
            };
            return empty;
        }
    }

    @Override
    public MapObject getContentAt(Position p)
    {
        if (!doubleWidth)
            return conents.stream().filter(c -> c.getPosition().getCol() == p.getCol()
                    && c.getPosition().getRow() == p.getRow() ).findAny().orElse(null);
        else
            return conents.stream().filter(c -> (c.getPosition().getCol() == p.getCol() || c.getPosition().getCol()
                    +((WarehouseObject) c).getWidth()-1 == p.getCol()) && c.getPosition().getRow() == p.getRow()).findAny().orElse(null);
    }

    @Override
    public void print() {
        System.out.println();
        for(int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                WarehouseObject who = (WarehouseObject) getContentAt(new Position(row, col));
                if (who == null)
                    System.out.print(".");
                else
                {
                    String symbol = ((MapObject) who).getSymbol();
                    System.out.print(symbol);
                    col += symbol.length()-1;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSumOfGPSCoords()
    {
        return conents.stream().filter(who -> who.getSymbol().equals("O") || who.getSymbol().equals("[]")).mapToInt(who -> ((WarehouseObject) who).getGPSCoord()).sum();
    }

    public void rollback()
    {
        conents.forEach(c -> ((WarehouseObject) c).rollback());
    }

    public void save()
    {
        conents.forEach(c -> ((WarehouseObject) c).save());
    }
}
