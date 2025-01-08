package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {

        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");
        WarehouseMapReader reader = new WarehouseMapReader(input);
        MapWalker walker = new MapWalker(reader.getXYMap(), reader.getGuardsPosition(), reader.getGuardsDirection(), false);

        System.out.println("Part 1: The number of spots visited is " + walker.walk());
        System.out.println("Part 2: The number of possible blocking positions is " + walker.findBlockingPositions());
    }
}
