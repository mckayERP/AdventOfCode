package org.mckayERP.AdventOfCode._2024.day03_MullItOver;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args){

        String data = ResourceFileReader.getResourceFileAsString(Main.class, "input.txt");
        MemoryReader reader = new MemoryReader();

        System.out.println("Part 1: the memory product sum is " + reader.getProductSum(data));
        System.out.println("Part 2: the memory product sum is " + reader.setEnablersActive(true).getProductSum(data));

    }

}
