package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;

public class Wall extends AbstractWarehouseObject implements WarehouseObject, MapObject
{

    public Wall(int row, int col, Warehouse wh)
    {
        super(row, col, wh);
    }

    public Wall(int row, int col, boolean doubleWidth, Warehouse wh)
    {
        super(row, col, doubleWidth, wh);
    }

    @Override
    public boolean move(Direction direction)
    {
        return false;
    }

    @Override
    public String getSymbol()
    {
        return doubleWidth ? "##" : "#";
    }
}
