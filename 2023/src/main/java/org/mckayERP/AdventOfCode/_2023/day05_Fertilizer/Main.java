package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.mckayERP.AdventOfCode._2023.day05_Fertilizer.AlmanacReader;
import org.mckayERP.AdventOfCode._2023.day05_Fertilizer.Converter;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.TreeSet;

public class Main
{
    public static void main(String[] args)
    {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        Converter converter = new Converter();
        converter.setMaps(reader.getMaps());
        converter.setSeedList(reader.getSeedList());
        System.out.println("Part 1: the lowest location number is " + converter.getLowestLocationNumber());

        converter.flattenMaps();
        converter.convertSeedsToRanges();
        TreeSet<Range> maskedRanges = converter.maskMaps();
        System.out.println("Part 2: the lowest location number that corresponds to the seed numbers is " + maskedRanges.first().outputStart);
    }
}
