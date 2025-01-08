package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest
{
    @Test
    public final void testConstructor() {
        Warehouse wh = new Warehouse();
    }

    @Test
    public final void testWarehouseContents() {
        Warehouse wh = new Warehouse();
        WarehouseObject wall = new Wall(1,1, wh);
        assertEquals(1, wh.getContents().size());
    }

    @Test
    public final void testSpaceEmpty() {
        Warehouse wh = new Warehouse();
        WarehouseObject wall = new Wall(1,1, wh);
        assertTrue(wh.spaceEmpty(new Position(1,2), 1, null));
        assertFalse(wh.spaceEmpty(new Position(1,1), 1, null));
    }

    @Test
    public final void testGetContentsWhenEmptyReturnsNull() {
        Warehouse wh = new Warehouse();
        WarehouseObject wall = new Wall(1,1, wh);
        assertNull(wh.getContentAt(new Position(1,2)));
    }

}
