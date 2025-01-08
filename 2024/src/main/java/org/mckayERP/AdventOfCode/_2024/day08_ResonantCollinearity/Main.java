package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.Bounds;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{

    public static void main(String[] args) {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");
        AntennaFinder finder = new AntennaFinder(input);
        Bounds bounds = new Bounds(new Position(0,0), new Position(input.length, input[0].length()));
        AntinodeGenerator generator = new AntinodeGenerator(finder.getAntennas(), bounds);
        long count = generator.getCountOnMap();
        System.out.println("Part 1: The number of positions containing an antinodes is " + count);

        generator = new AntinodeGenerator(finder.getAntennas(), bounds, true);
        count = generator.getCountOnMap();
        System.out.println("Part 2: The number of positions containing an harmonic antinodes is " + count);

    }

}
