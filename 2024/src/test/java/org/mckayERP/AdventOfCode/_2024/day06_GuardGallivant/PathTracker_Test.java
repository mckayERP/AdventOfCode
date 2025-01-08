package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class PathTracker_Test
{
    @Test
    public final void givenAPath_canAddPoints() {

        Position pos = new Position(1, 1);
        PathPoint p = new PathPoint(pos, Direction.UP);

        PathTracker tracker = new PathTracker();
        assertFalse(tracker.addPathPoint(p));
    }

    @Test
    public final void givenAPath_canSamePositionButDifferentDirection() {

        Position pos1 = new Position(1, 1);
        PathPoint p1 = new PathPoint(pos1, Direction.UP);
        PathPoint p2 = new PathPoint(pos1, Direction.LEFT);

        PathTracker tracker = new PathTracker();
        assertFalse(tracker.addPathPoint(p1));
        assertFalse(tracker.addPathPoint(p2));
    }

    @Test
    public final void givenAPath_loopDetectedIfSamePointAndDirectionAdded() {

        Position pos1 = new Position(1, 1);
        PathPoint p1 = new PathPoint(pos1, Direction.UP);
        PathPoint p2 = new PathPoint(pos1, Direction.LEFT);
        PathPoint p3 = new PathPoint(pos1, Direction.UP);

        PathTracker tracker = new PathTracker();
        assertFalse(tracker.addPathPoint(p1));
        assertFalse(tracker.addPathPoint(p2));
        assertTrue(tracker.addPathPoint(p3));
    }

    @Test
    public final void giveAPathPoint_TheDirectionAndPositionAreImmutable() {
        Position pos1 = new Position(1, 1);
        Direction d = Direction.UP;
        PathPoint p1 = new PathPoint(pos1, d);
        d = Direction.DOWN;
        pos1.setCol(2);
        assertEquals(Direction.UP, p1.direction);
        assertEquals(1, p1.position.getCol());

    }

}
