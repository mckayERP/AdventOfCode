package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.mckayERP.AdventOfCode._2024.day10_HoofIt.IntegerMapReader;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.mckayERP.AdventOfCode._2024.day06_GuardGallivant.Direction.UP;

public class WarehouseMapReader extends IntegerMapReader
{
    Position guardsPosition = new Position(0,0);
    Direction guardsDirection;
    public WarehouseMapReader(String[] input)
    {
        super(input);
        int rowCount = getXYMap().getRowCount();
        int colCount = getXYMap().getColCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                char spot = input[row].toCharArray()[col];
                if (spot == '#')
                    getXYMap().setValueAt(row,col, 1);
                else
                {
                    if (spot == '^')
                    {
                        guardsPosition = new Position(row, col);
                        guardsDirection = UP;
                    }
                    getXYMap().setValueAt(row, col, 0);
                }
            }
        }
    }

    public Position getGuardsPosition()
    {
        return guardsPosition;
    }

    public Direction getGuardsDirection()
    {
        return guardsDirection;
    }
}
