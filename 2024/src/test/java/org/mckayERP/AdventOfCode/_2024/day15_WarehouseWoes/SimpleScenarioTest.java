package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleScenarioTest
{

    @Test
    public final void testSimpleScenario() {
        WarehouseLoader whl = new WarehouseLoader();
        whl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "smallTestInput.txt"));
        Warehouse wh = whl.getWarehouse();
        wh.print();

        Robot robot = whl.getRobot();
        robot.setInstructions(whl.getInstructions());
        robot.runInstructions();
        wh.print();

        assertEquals(2028, wh.getSumOfGPSCoords());
    }

    @Test
    public final void testSimpleDoubleScenario() {
        WarehouseLoader whl = new WarehouseLoader(true);
        whl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "smallDoubleInput.txt"));
        Warehouse wh = whl.getWarehouse();
        wh.print();

        Robot robot = whl.getRobot();
        robot.setInstructions(whl.getInstructions());
        robot.runInstructions();
        wh.print();

    }

}
