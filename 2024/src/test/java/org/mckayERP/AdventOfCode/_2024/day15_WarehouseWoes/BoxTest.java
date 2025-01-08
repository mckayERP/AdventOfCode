package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BoxTest
{
    @Test
    public final void testConstructor() {
        MapObject box = new Box(1,1, new Warehouse());
        assertEquals( 1, box.getPosition().getCol());
    }

    @Test
    public final void testCantMove() {
        Warehouse wh = new Warehouse();
        WarehouseObject wall = new Wall(0, 1, wh);
        WarehouseObject box = new Box(1,1, wh);
        assertFalse(box.move(Direction.UP));
    }

    @Test
    public final void testGPSCoord() {
        WarehouseObject box = new Box(1,4, new Warehouse());
        assertEquals( 104, box.getGPSCoord());
    }

}
