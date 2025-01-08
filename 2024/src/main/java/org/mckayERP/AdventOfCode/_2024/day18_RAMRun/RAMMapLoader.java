package org.mckayERP.AdventOfCode._2024.day18_RAMRun;

import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.*;
import org.mckayERP.AdventOfCode.utilities.AbstractMapLoader;
import org.mckayERP.AdventOfCode.utilities.MapLoader;
import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;

public class RAMMapLoader extends AbstractMapLoader implements MapLoader
{

    public void setNumberOfRowsAndColumns(int rows, int cols) {
        ((MazeMap)getMap()).setMaxRow(rows-1);
        ((MazeMap) getMap()).setMaxCol(cols-1);
    }

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
