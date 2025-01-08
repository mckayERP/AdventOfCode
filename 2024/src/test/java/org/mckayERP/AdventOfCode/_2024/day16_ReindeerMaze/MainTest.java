package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class MainTest
{

    @Test
    public final void testInput()
    {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        int cost = solver.findBestPath();
        long count = solver.getCountOfPointsOnBestPath();
        System.out.println("Part 1: cost of the best path is " + cost);
        System.out.println("Part 2: count of points on a best path is " + count);
    }
}
