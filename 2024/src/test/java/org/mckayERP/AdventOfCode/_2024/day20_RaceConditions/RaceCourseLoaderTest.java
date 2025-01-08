package org.mckayERP.AdventOfCode._2024.day20_RaceConditions;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;
import org.mckayERP.AdventOfCode._2024.day18_RAMRun.MazeSolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Very similar to Day 16
 */
public class RaceCourseLoaderTest
{
    @Test
    public final void testMapLoader() {

        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        map.print();

    }

    @Test
    public final void testSolveMaze() {

        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        MazeSolver solver = new RaceMazeSolver(map);
        int cost = solver.findBestPath();
        assertEquals(85, cost);
        assertEquals(85, solver.getCountOfPointsOnBestPath());

    }

}
