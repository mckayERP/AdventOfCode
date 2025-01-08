package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

public class Box extends AbstractWarehouseObject implements WarehouseObject
{
    public Box(int row, int col, Warehouse wh)
    {
        super(row, col, wh);
    }

    public Box(int row, int col, boolean doubleWidth, Warehouse wh)
    {
        super(row, col, doubleWidth, wh);
    }


    @Override
    public String getSymbol()
    {
        return doubleWidth? "[]" : "O";
    }

}
