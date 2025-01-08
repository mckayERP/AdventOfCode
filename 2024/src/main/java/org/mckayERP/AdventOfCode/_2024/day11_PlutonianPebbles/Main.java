package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {

        ListManager manager = new ListManager();
//        manager.setInitialArrangement(ResourceFileReader.getResourceFileAsString(Main.class, "input.txt"));
//        System.out.println("Part 1: the number of stones is " + manager.blink(25));

        manager.setInitialArrangement(ResourceFileReader.getResourceFileAsString(Main.class, "input.txt"));
        System.out.println("Part 2: the number of stones is " + manager.blink(75));

    }
}
