package org.mckayERP.AdventOfCode._2024.day18_RAMRun;

import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;

public class RAMMazeSolver extends MazeSolver
{
    public RAMMazeSolver(ObjectMap map)
    {
        super((MazeMap) map);
    }

    @Override
    public int getMoveCost(MoveType moveType)
    {
        return  1;
    }

}
