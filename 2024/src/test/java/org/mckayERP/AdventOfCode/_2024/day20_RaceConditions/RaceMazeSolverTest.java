package org.mckayERP.AdventOfCode._2024.day20_RaceConditions;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;
import org.mckayERP.AdventOfCode._2024.day18_RAMRun.MazeSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceMazeSolverTest
{
    @Test
    public final void testCheatsDetected() {
        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        MazeSolver solver = new RaceMazeSolver(map);
        int cost = solver.findBestPath();
        List<Position> path = solver.getSuccessfulPaths().get(cost).get(0);
        assertEquals(85, path.size());

        Position start = new Position(1,8);
        Position end = new Position(1,9);
        Cheat cheat = new Cheat(start, end, 2);

    }

    @Test
    public final void testDistancesCanBeMeasured() {

        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        RaceMazeSolver solver = new RaceMazeSolver(map);
        int cost = solver.findBestPath();
        List<Position> path = solver.getSuccessfulPaths().get(cost).get(0);
        assertEquals(85, path.size());

        Position start = new Position(1,7);
        Position end = new Position(1,9);
        assertEquals(2, solver.getManhattanDistanceBetween(start, end));
        assertEquals(Direction.RIGHT, solver.getStartingDirectionsBetween(start, end).get(0));
        Map<Integer, List<Cheat>> cheats = solver.findCheats(2,2);
        List<Cheat> combinedList = new ArrayList<>();
        cheats.entrySet().stream().filter(e -> e.getKey() <= 100).map(Map.Entry::getValue).forEach(combinedList::addAll);
        assertEquals(44, combinedList.size());
    }

    @Test
    public final void testDistancesCanBeMeasuredPart2() {

        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        RaceMazeSolver solver = new RaceMazeSolver(map);
        int cost = solver.findBestPath();
        List<Position> path = solver.getSuccessfulPaths().get(cost).get(0);
        assertEquals(85, path.size());

        Map<Integer, List<Cheat>> cheats = solver.findCheats(2,20);
        List<Cheat> combinedList = new ArrayList<>();
        cheats.entrySet().stream().filter(e -> e.getKey() >= 50).map(Map.Entry::getValue).forEach(combinedList::addAll);
        assertEquals(285, combinedList.size());
    }

}
