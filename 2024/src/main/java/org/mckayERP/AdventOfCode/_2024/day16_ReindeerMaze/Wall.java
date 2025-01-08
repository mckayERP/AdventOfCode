package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;

public class Wall extends AbstractMapObject implements MapObject
{

    public Wall(int row, int col, ObjectMap map)
    {
        super(row, col, map);
    }

    public Wall()
    {
        super();
    }

    @Override
    public String getSymbol()
    {
        return "#";
    }

}
