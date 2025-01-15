package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest
{

    @Test
    public final void testConstructor()
    {
        Converter uut = new Converter();
    }

    @Test
    public final void testConverterCanConvertSeedsToLocation() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        Converter converter = new Converter();
        converter.setMaps(reader.getMaps());
        converter.setSeedList(reader.getSeedList());
        assertEquals(82, converter.getLocationForSeed(79L));
    }

    @Test
    public final void testCanFindLowestLocation() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        Converter converter = new Converter();
        converter.setMaps(reader.getMaps());
        converter.setSeedList(reader.getSeedList());
        assertEquals(35, converter.getLowestLocationNumber());
    }

    @Test
    public final void testCanFindLowestSeedGroup() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        Converter converter = new Converter();
        converter.setMaps(reader.getMaps());
        converter.setSeedList(reader.getSeedList());
        converter.flattenMaps();
        TreeSet<Range> outputSortedRanges = new TreeSet<>(new RangeComparator(true));
        outputSortedRanges.addAll(converter.getMaps().get(0).ranges);
        for (Range range : outputSortedRanges) {
            System.out.println(range);
        }
        converter.convertSeedsToRanges();
        TreeSet<Range> maskedRanges = converter.maskMaps();
        assertEquals(46, maskedRanges.first().outputStart);
    }
}
