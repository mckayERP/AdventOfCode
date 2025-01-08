package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WallTest
{
    @Test
    public final void testConstructor() {
        Warehouse wh = new Warehouse();
        MapObject wall = new Wall(1,1, wh);
        assertEquals( 1, wall.getPosition().getCol());
    }

    @Test
    public final void testCantMove() {
        Warehouse wh = new Warehouse();
        WarehouseObject wall = new Wall(1,1, wh);
        assertFalse(wall.move(Direction.UP));
    }

    @Test
    public final void testPrintIcon() {
        Warehouse wh = new Warehouse();
        MapObject wall = new Wall(1,1, wh);
        assertEquals("#", wall.getSymbol());
    }

}
