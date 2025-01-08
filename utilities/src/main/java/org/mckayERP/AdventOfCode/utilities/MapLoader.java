package org.mckayERP.AdventOfCode.utilities;

public interface MapLoader
{
    ObjectMap getMap();
    void readMapPattern(String[] input);

    int dealWithSymbol(String symbol, int row, int col, ObjectMap map);
}
