package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Converter
{
    private List<ConversionMap> maps;
    private List<Long> seedList;
    Set<Range> seedRanges = new TreeSet<Range>(new RangeComparator());

    public void setMaps(List<ConversionMap> maps)
    {
        this.maps = maps;
    }

    public void setSeedList(List<Long> seedList)
    {
        this.seedList = seedList;
    }

    public long getLocationForSeed(Long seed)
    {
        Long input = seed;
        Long output = input;
        for (ConversionMap map : maps) {
            output = map.convert(input);
            input = output;
        }
        return output;
    }

    public long getLowestLocationNumber()
    {
        long lowestLocation = -1;
        for (Long seed : seedList) {
            if (lowestLocation < 0)
                lowestLocation = getLocationForSeed(seed);
            else
                lowestLocation = Math.min(lowestLocation, getLocationForSeed(seed));
        }
        return lowestLocation;
    }

    public void flattenMaps()
    {
        ConversionMap flattenedMap = maps.get(0);
        for (int i=1; i<maps.size()-1; i++) {
            ConversionMap nextMap = maps.get(i);
            flattenedMap = flattenedMap.flattenWith(nextMap);
        }
        maps.clear();
        maps.add(flattenedMap);
    }

    public TreeSet<Range> maskMaps()
    {
        ConversionMap seeds = new ConversionMap();
        seeds.ranges = seedRanges;
        TreeSet<Range> maskedRanges = seeds.mask(maps.get(0));
        System.out.println("Masked Ranges !!");
        maskedRanges.forEach(System.out::println);
        return maskedRanges;
    }

    public List<ConversionMap> getMaps()
    {
        return maps;
    }

    public void convertSeedsToRanges()
    {
        for (int i=0; i<seedList.size(); i++) {
            Range seed = new Range(seedList.get(i), seedList.get(i++), seedList.get(i));
            seedRanges.add(seed);
        }
    }
}
