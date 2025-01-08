package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.MapReader;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrailheadFinder_Test
{
    @Test
    public final void testConstructor() {
        MapReader<Integer> reader = new IntegerMapReader(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        TrailheadFinder finder = new TrailheadFinder(reader.getXYMap());
        List<Trailhead> trailheads = finder.getTrailheads();
        assertEquals(9, trailheads.size());
    }
}
