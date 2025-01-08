package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        PatternsAndDesiredDesignReader reader;
        reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        TowelPatternMatcher matcher = new TowelPatternMatcher();
        matcher.setAvailablePatterns(reader.getPatterns());
        matcher.setDesiredDesigns(reader.getDesiredDesigns());
        System.out.println("Part 1: there are " + matcher.getCountOfPossibleDesigns() + " possible designs");
        System.out.println("Part 2: there are " + matcher.getNumberOfDifferentWaysToMakePossibleDesigns() + " ways to make the designs" );
    }

}
