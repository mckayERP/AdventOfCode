package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: the total cost of fencing is " + mapper.getTotalPrice());
        System.out.println("Part 2: the total cost of fencing by Edge is " + mapper.getTotalPriceByEdge());
    }
}
