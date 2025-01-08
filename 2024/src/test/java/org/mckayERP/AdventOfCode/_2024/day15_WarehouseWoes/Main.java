package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main
{
    public static void main(String[] args) {
        WarehouseLoader whl = new WarehouseLoader();
        whl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        Warehouse wh = whl.getWarehouse();

        Robot robot = whl.getRobot();
        robot.setInstructions(whl.getInstructions());
        robot.runInstructions();

        System.out.println("Part 1: the sum of the GPS coordinates is " + wh.getSumOfGPSCoords());

        whl = new WarehouseLoader(true);
        whl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        wh = whl.getWarehouse();

        robot = whl.getRobot();
        robot.setInstructions(whl.getInstructions());
        robot.runInstructions();

        System.out.println("Part 2: the sum of the GPS coordinates is " + wh.getSumOfGPSCoords());

    }
}
