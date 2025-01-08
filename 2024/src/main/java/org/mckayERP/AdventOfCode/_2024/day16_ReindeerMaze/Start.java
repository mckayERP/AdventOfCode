package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;

public class Start extends AbstractMapObject implements MapObject
{

    public Start(int row, int col, ObjectMap map)
    {
        super(row, col, map);
    }

    @Override
    public String getSymbol()
    {
        return "S";
    }

}
