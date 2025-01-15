package org.mckayERP.AdventOfCode._2023.day02_CubeConondrum;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        GameParser parser = new GameParser();
        parser.parse(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: the sum of the ids of the possible games is " + parser.getSumOfPossibleGameIDs(12,13,14));
        System.out.println("Part 2: the sum of the powers of the games is " + parser.getSumOfPowers());
    }
}
