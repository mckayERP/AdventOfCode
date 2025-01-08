package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.MapObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTest
{
    @Test
    public final void testRobotConstructor() {

        WarehouseObject robot = new Robot(1, 1, new Warehouse());

    }

    @Test
    public final void testSymbol() {
        MapObject robot = new Robot(1, 1, new Warehouse());
        assertEquals("@", robot.getSymbol());

    }
}
