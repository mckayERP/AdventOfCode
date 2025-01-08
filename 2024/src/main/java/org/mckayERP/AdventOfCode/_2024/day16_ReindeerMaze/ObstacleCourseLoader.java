package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.AbstractMapLoader;
import org.mckayERP.AdventOfCode.utilities.MapLoader;
import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;

public class ObstacleCourseLoader extends AbstractMapLoader implements MapLoader
{

    @Override
    public ObjectMap createMap()
    {
        return new MazeMap();
    }

    @Override
    public int dealWithSymbol(String symbol, int row, int col, ObjectMap map)
    {
        MapObject mo = null;
        switch (symbol) {
            case "#" -> mo = new Wall(row, col, map);
            case "S" -> mo = new Start(row, col, map);
            case "E" -> mo = new Goal(row, col, map);
        }
        return 1;
    }

}
