package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mckayERP.AdventOfCode.utilities.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class MazeSolverTest
{

    @Test
    public final void givenMoveType_CanDetermineCostOfMove() {
        MazeSolver solver = new MazeSolver();
        assertEquals(1, solver.getMoveCost(MazeSolver.MoveType.MOVE));
    }

    @ParameterizedTest
    @EnumSource(value = MazeSolver.MoveType.class, names = {"TURN_LEFT","TURN_RIGHT"})
    public final void givenTurnType_CanDetermineCostOfMove(MazeSolver.MoveType type) {
        MazeSolver solver = new MazeSolver();
        assertEquals(1001, solver.getMoveCost(type));
    }

    @Test
    public final void givenTheStartPositionWithOneHorizontalOption_fillsTheQueueWithOnePoint() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "######",
                "#S..E#",
                "######"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = spy(new MazeSolver(map));
        doNothing().when(solver).searchPaths();
        solver.findBestPath();
        assertEquals(1, solver.pointsInQueue.size());
        PointState ps = solver.pointsInQueue.removeFirst();
        assertEquals(Direction.RIGHT, ps.direction);
        assertTrue(ps.isStart);
    }

    @Test
    public final void givenTheStartPositionWithOneVerticalOption_fillsTheQueueWithOnePoint() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "######",
                "#.#.E#",
                "#...##",
                "#S#..#",
                "######"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = spy(new MazeSolver(map));
        doNothing().when(solver).searchPaths();
        solver.findBestPath();
        assertEquals(1, solver.pointsInQueue.size());
        PointState ps = solver.pointsInQueue.removeFirst();
        assertEquals(Direction.RIGHT, ps.direction);
    }

    @Test
    public final void givenAStartNextToTheGoal_SolverDoesNotAddToQueue() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "####",
                "#SE#",
                "####"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = spy(new MazeSolver(map));
        solver.findBestPath();
        assertEquals(0, solver.pointsInQueue.size());

    }

    @Test
    public final void givenAStartNextToTheGoal_BestPathCostIsOne() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "####",
                "#SE#",
                "####"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(1,solver.findBestPath());
        assertEquals(2, solver.getCountOfPointsOnBestPath());
    }

    @Test
    public final void givenAPathOfThreePoints_BestPathCostAndCountWork() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "#####",
                "#S.E#",
                "#####"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(2,solver.findBestPath());
        assertEquals(3, solver.getCountOfPointsOnBestPath());
    }

    @Test
    public final void givenAVerticalPathOfThreePoints_BestPathCostAndCountWork() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "###",
                "#E#",
                "#.#",
                "#S#",
                "###"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(1002,solver.findBestPath());
        assertEquals(3, solver.getCountOfPointsOnBestPath());
    }

    @Test
    public final void givenAPathWithACorner_BestPathCostAndCountWork() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "####",
                "##E#",
                "#..#",
                "#S##",
                "####"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(3003,solver.findBestPath());
        assertEquals(4, solver.getCountOfPointsOnBestPath());
    }

    @Test
    public final void givenTwoPaths_BestPathCostAndCountWork() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "#####",
                "#..E#",
                "#.#.#",
                "#S..#",
                "#####"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(1004,solver.findBestPath());
        assertEquals(5, solver.getCountOfPointsOnBestPath());
    }

    @Test
    public final void givenTwoEqualPaths_BestPathCostAndCountWork() {
        ObstacleCourseLoader ocl = new ObstacleCourseLoader();
        ocl.readMapPattern(new String[] {
                "######",
                "#...E#",
                "#.#.##",
                "#S..##",
                "######"
        });
        MazeMap map = (MazeMap) ocl.getMap();
        map.initialize();
        MazeSolver solver = new MazeSolver(map);
        assertEquals(2005,solver.findBestPath());
        assertEquals(9, solver.getCountOfPointsOnBestPath());
    }

}
