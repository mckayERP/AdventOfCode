package org.mckayERP.AdventOfCode._2024.day25_CodeChronicle;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        SchematicReader reader = new SchematicReader();
        reader.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: the number of fits is " + reader.determineNumberOfFits());
    }
}
