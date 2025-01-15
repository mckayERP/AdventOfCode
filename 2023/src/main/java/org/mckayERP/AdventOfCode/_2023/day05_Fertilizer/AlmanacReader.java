package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.mckayERP.AdventOfCode.utilities.stringManipulation.AOCStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmanacReader
{
    List<Long> seeds = new ArrayList<>();
    List<ConversionMap> maps = new ArrayList<>();

    public void read(String[] input)
    {
        int i = 0;
        while (i < input.length) {
            String line = input[i++];
            if (line.startsWith("seeds")) {
                String[] seedsStrings = line.split(":");
                seeds = Arrays.stream(AOCStrings.extractLongs(seedsStrings[1])).toList();
                ++i;
            }
            else if (line.contains("map")) {
                line = input[i++];
                ConversionMap map = new ConversionMap();
                while (!line.trim().isEmpty() && i < input.length) {
                    map.addConversionRange(AOCStrings.extractLongs(line));
                    line = input[i++];
                }
                maps.add(map);
            }
        }
    }

    public List<Long> getSeedList()
    {
        return seeds;
    }

    public List<ConversionMap> getMaps()
    {
        return maps;
    }
}
