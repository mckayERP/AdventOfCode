package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args)
    {
        SchematicReader reader = new SchematicReader();
        reader.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        reader.initializeXYMap();
        System.out.println("Part 1: the sum of the part numbers is " + reader.getSumOfPartNumbers());
        System.out.println("Part 2: the sum of the gear ratios is " + reader.getSumOfGearRatios());

    }
}
