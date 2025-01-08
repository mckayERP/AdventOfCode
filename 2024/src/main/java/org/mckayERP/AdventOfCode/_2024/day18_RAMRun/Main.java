package org.mckayERP.AdventOfCode._2024.day18_RAMRun;

import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) {
        RAMMapLoader loader = new RAMMapLoader();
        loader.setNumberOfRowsAndColumns(71,71);
        loader.readObjectCoordinates(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        ObjectMap map = new MazeMap();
        loader.getMap().getContents().forEach(map::add);
        loader.dealWithSymbol("S", 0,0,loader.getMap());
        loader.dealWithSymbol("E", 70,70,loader.getMap());
        ((MazeMap) loader.getMap()).initialize(1024);
        RAMMazeSolver solver = new RAMMazeSolver(loader.getMap());
        int cost = solver.findBestPath();
        solver.printMapAndBestPath(null);
        System.out.println("Part 1: the minimum number or steps required is " + cost);


        Map<Integer, List<List<Position>>> successfulPaths;
        boolean hasASolution = true;
        Position lastDrop = null;
        while (hasASolution)
        {
            solver.findBestPath();
            successfulPaths = solver.getSuccessfulPaths();
            if (successfulPaths.isEmpty())
            {
                break;
            }
            List<List<Position>> paths = new ArrayList<>();
            successfulPaths.values().forEach(paths::addAll);
            boolean stillOK = !successfulPaths.isEmpty();
            MazeMap mazeMap = (MazeMap) loader.getMap();

            while (mazeMap.hasNextToDrop() && stillOK)
            {
                lastDrop = mazeMap.dropNext();
                List<Integer> pathsToRemove = new ArrayList<>();
                if (lastDrop.equals(mazeMap.getStart().getPosition()) || lastDrop.equals(mazeMap.getGoal().getPosition()))
                {
                    hasASolution = false;
                    continue;
                }
                for (int i = 0; i < paths.size(); i++)
                {
                    if (paths.get(i).contains(lastDrop))
                        pathsToRemove.add(i);
                }
                pathsToRemove.forEach(i -> paths.remove(paths.get(i)));
                stillOK = !paths.isEmpty();

            }
        }
        System.out.println("Part 2: the blocking drop is at " + lastDrop.getCol() + "," + lastDrop.getRow());
    }

}
