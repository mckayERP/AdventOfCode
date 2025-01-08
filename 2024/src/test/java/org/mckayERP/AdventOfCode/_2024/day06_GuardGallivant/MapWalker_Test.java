package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mckayERP.AdventOfCode._2024.day06_GuardGallivant.Direction.*;

public class MapWalker_Test
{
    String[] input;

    MapWalker walker;

    @BeforeEach
    public final void setup() {
        input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
        WarehouseMapReader reader = new WarehouseMapReader(input);
        walker = new MapWalker(reader.getXYMap(), reader.getGuardsPosition(), reader.getGuardsDirection());
    }

    @Test
    public final void givenADirection_whenObstacleHit_newDirectionIsCorrect() {

        Direction newDirection = walker.turnRight(UP);
        assertEquals(RIGHT, newDirection);
    }

    @Test
    public final void givenADirectionAndPosition_WhenMove_newPositionIsCorrect() {

        Direction currentDirection = UP;
        Position p = new Position(1,1);
        assertTrue(walker.setCurrentPosition(p).setGuardsDirection(currentDirection).move());
        assertEquals(new Position(0,1), walker.currentPosition);
    }

    @Test
    public final void givenAPositionOffTheGrid_isOnMapReturnsFalse() {

        Position p = new Position(1,20);
        walker.setCurrentPosition(p);
        assertFalse(walker.isOnMap());

    }

    @Test
    public final void whenWalkingTheMap_countOfPositionsVisitedEquals41() {
        assertEquals(41, walker.walk());
    }

    @Test
    public final void countOfPossibleBlockingPositionsEquals6() {
        assertEquals(41, walker.walk());
        assertEquals(6, walker.findBlockingPositions());
    }

}
