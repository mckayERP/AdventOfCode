package org.mckayERP.AdventOfCode._2024.day14_RestroomRobot;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTrackerTest
{
    @Test
    public final void testConstructor() {
        RobotTracker tracker = new RobotTracker();
    }

    @Test
    public final void testDefineGrid() {
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(103, 101);
        assertEquals(103, tracker.maxRows);
        assertEquals(51, 103/2);
        assertEquals(101, tracker.maxColumns);
        assertEquals(50, 101/2);
        tracker.printMap(true);
    }

    @Test
    public final void testMovementAwayFromWall() {
        Robot robot = new Robot(new Position(4,2), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(7, 11);
        tracker.robots.add(robot);
        tracker.move(1);
        assertEquals(new Position(1, 4), robot.currentPosition);
    }

    @Test
    public final void testMovementAcrossTopWall() {
        Robot robot = new Robot(new Position(4,2), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(7, 11);
        tracker.robots.add(robot);
        tracker.move(2);
        assertEquals(new Position(5, 6), robot.currentPosition);
    }

    @Test
    public final void testMovementAcrossAfterThreeMoves() {
        Robot robot = new Robot(new Position(4,2), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(7, 11);
        tracker.robots.add(robot);
        tracker.move(3);
        assertEquals(new Position(2, 8), robot.currentPosition);
    }

    @Test
    public final void testMovementAcrossAfterFourMoves() {
        Robot robot = new Robot(new Position(4,2), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(7, 11);
        tracker.robots.add(robot);
        tracker.move(4);
        assertEquals(new Position(6, 10), robot.currentPosition);
    }

    @Test
    public final void testMovementAcrossAfterFiveMoves() {
        Robot robot = new Robot(new Position(4,2), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.setGridLimits(7, 11);
        tracker.robots.add(robot);
        tracker.move(5);
        assertEquals(new Position(3, 1), robot.currentPosition);
    }

    @Test
    public final void testMovementAcrossRightWall() {
        Robot robot = new Robot(new Position(6,10), new Velocity(2,-3));
        RobotTracker tracker = new RobotTracker();
        tracker.robots.add(robot);
        tracker.setGridLimits(7, 11);
        tracker.move(1);
        assertEquals(new Position(3, 1), robot.currentPosition);
    }

    @Test
    public final void robotTrackerCanReadInput() {
        RobotTracker tracker = new RobotTracker();
        tracker.readInput(new String[] {"p=0,4 v=3,-3"});
        assertEquals(new Position(4,0), tracker.getRobots().get(0).currentPosition);
    }

    @Test
    public final void testSimpleInput() {
        RobotTracker tracker = new RobotTracker();
        tracker.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "simpleInput.txt"));
        tracker.setGridLimits(7,11);
        tracker.move(100);
        assertEquals(12, tracker.getCountInQuadrants());

    }


}
