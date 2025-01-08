package org.mckayERP.AdventOfCode._2024.day18_RAMRun;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RAMMapLoaderTest
{

    @Test
    public final void testConstructor() {
        RAMMapLoader loader = new RAMMapLoader();
        loader.setNumberOfRowsAndColumns(7,7);
        loader.readObjectCoordinates(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        loader.dealWithSymbol("S", 0,0,loader.getMap());
        loader.dealWithSymbol("E", 6,6,loader.getMap());
        assertEquals("#", loader.getMap().getContentAt(new Position(4,5)).getSymbol());
        assertEquals("S", loader.getMap().getContentAt(new Position(0,0)).getSymbol());
        assertEquals("E", loader.getMap().getContentAt(new Position(6,6)).getSymbol());

    }

    @Test
    public final void testSimpleInput() {
        RAMMapLoader loader = new RAMMapLoader();
        loader.setNumberOfRowsAndColumns(7,7);
        loader.readObjectCoordinates(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        loader.dealWithSymbol("S", 0,0,loader.getMap());
        loader.dealWithSymbol("E", 6,6,loader.getMap());
        ((MazeMap) loader.getMap()).initialize(12);
        RAMMazeSolver solver = new RAMMazeSolver(loader.getMap());
        int cost = solver.findBestPath() -1;  // Solver includes the first position.
        assertEquals(22, cost);
    }

    @Test
    public final void testPart1Input() {
        RAMMapLoader loader = new RAMMapLoader();
        loader.setNumberOfRowsAndColumns(71,71);
        loader.readObjectCoordinates(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        ObjectMap map = new MazeMap();
        loader.getMap().getContents().forEach(map::add);
        loader.dealWithSymbol("S", 0,0,loader.getMap());
        loader.dealWithSymbol("E", 70,70,loader.getMap());
        ((MazeMap) loader.getMap()).initialize(1024);
        RAMMazeSolver solver = new RAMMazeSolver(loader.getMap());
        int cost = solver.findBestPath() - 1;
        solver.printMapAndBestPath(null);
        assertEquals(292, cost);
    }

    @Test
    public final void testPart2Input()
    {
        RAMMapLoader loader = new RAMMapLoader();
        loader.setNumberOfRowsAndColumns(71, 71);
        loader.readObjectCoordinates(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        ObjectMap map = new MazeMap();
        loader.getMap().getContents().forEach(map::add);
        loader.dealWithSymbol("S", 0, 0, loader.getMap());
        loader.dealWithSymbol("E", 70, 70, loader.getMap());
        ((MazeMap) loader.getMap()).initialize(1024);
        RAMMazeSolver solver = new RAMMazeSolver(loader.getMap());
        Map<Integer, List<List<Position>>> successfulPaths;

        boolean hasASolution = true;
        Position lastDrop = null;
        while (hasASolution)
        {
            solver.findBestPath();
            successfulPaths = solver.getSuccessfulPaths();
            if (successfulPaths.isEmpty())
            {
                hasASolution = false;
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

        assertEquals(new Position(44,58), lastDrop);
    }

}
