package org.mckayERP.AdventOfCode._2024.day20_RaceConditions;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;
import org.mckayERP.AdventOfCode._2024.day18_RAMRun.MazeSolver;

import java.util.*;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;
import static org.mckayERP.AdventOfCode.utilities.Direction.UP;

public class RaceMazeSolver extends MazeSolver
{
    public RaceMazeSolver(ObjectMap map)
    {
        super((MazeMap) map);
    }

    @Override
    public int getMoveCost(MoveType moveType)
    {
        return  1;
    }

    public int getManhattanDistanceBetween(Position start, Position end)
    {
        return Math.abs(end.getRow() - start.getRow()) + Math.abs(end.getCol() - start.getCol());
    }

    public List<Direction> getStartingDirectionsBetween(Position start, Position end)
    {
        int distance = getManhattanDistanceBetween(start, end);
        if (distance == 0)
            return null;

        List<Direction> possibleDirections = new ArrayList<>();

        if (end.getCol() - start.getCol() > 0)
            possibleDirections.add(RIGHT);
        if (end.getCol() - start.getCol() < 0)
            possibleDirections.add(LEFT);

        if (end.getRow() - start.getRow() > 0)
            possibleDirections.add(DOWN);
        if (end.getRow() - start.getRow() < 0)
            possibleDirections.add(UP);

        return possibleDirections;
    }

    public Map<Integer, List<Cheat>> findCheats(int minTimeSeparation, int maxDistance)
    {
        Map<Integer, List<Cheat>> cheats = new TreeMap<>();
        List<Position> path = getSuccessfulPaths().get(getBestPathCost()).get(0);
        if (minTimeSeparation >= path.size())
            throw new IllegalArgumentException("The minTimeSeparation is larger than the path length!");
        for (int i=0; i<(path.size()-minTimeSeparation); i++) {
            for (int j=i+minTimeSeparation; j < path.size(); j++) {
                Position p1 = path.get(i);
                Position p2 = path.get(j);
                int distance = getManhattanDistanceBetween(p1, p2);
                if (distance >= 2  && distance <= maxDistance)
                {
                    int cost = getCostMap().getValueAt(p2) - getCostMap().getValueAt(p1) - distance;
                    if (cost > 0)
                    {
                        List<Cheat> cl = cheats.getOrDefault(cost, new ArrayList<>());
                        cl.add(new Cheat(p1, p2, cost));
                        cheats.putIfAbsent(cost, cl);
                    }
                }
            }
        }
        return cheats;
    }

}
