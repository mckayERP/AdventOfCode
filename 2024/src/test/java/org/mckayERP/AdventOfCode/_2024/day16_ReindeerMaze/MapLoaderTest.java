package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapLoaderTest
{
    @Test
    public final void testMapLoader() {

        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput1.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        map.print();

    }

    @Test
    public final void testSolveMaze1() {

        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput1.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        map.fillInDeadEnds();
        MazeSolver solver = new MazeSolver(map);
        int cost = solver.findBestPath();
        assertEquals(7036, cost);
        assertEquals(45, solver.getCountOfPointsOnBestPath());

    }

    @Test
    public final void testSolveMaze2() {

        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        map.fillInDeadEnds();
        MazeSolver solver = new MazeSolver(map);
        int cost = solver.findBestPath();
        assertEquals(11048, cost);
        assertEquals(64, solver.getCountOfPointsOnBestPath());

    }

    @Test
    public final void testSolveMaze3() {

        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput3.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        map.fillInDeadEnds();
        MazeSolver solver = new MazeSolver(map);
        int cost = solver.findBestPath();
        assertEquals(4014, cost);
        assertEquals(15, solver.getCountOfPointsOnBestPath());

    }

    @Test
    public final void testSolveMaze4() {

        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput4.txt"));
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        map.fillInDeadEnds();
        MazeSolver solver = new MazeSolver(map);
        int cost = solver.findBestPath();
        assertEquals(2010, cost);
        assertEquals(19, solver.getCountOfPointsOnBestPath());

    }

}
