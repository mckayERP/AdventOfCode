package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.mckayERP.AdventOfCode.utilities.MapReader;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode.utilities.XYMap;

public class Main
{
    public static void main(String[] args) {

        MapReader<Integer> reader = new IntegerMapReader(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        XYMap<Integer> map = reader.getXYMap();
        TrailheadFinder finder = new TrailheadFinder(map);
        TrailWalker walker = new TrailWalker(map, finder.getTrailheads());
        walker.walk();
        System.out.println("Part 1: the total score is " + walker.getTotalScore());
        System.out.println("Part 2: the total rating is " + walker.getTotalRating());

    }
}
