package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrailWalker_Test
{
    TrailheadFinder finder;
    XYMap<Integer> map;

    @BeforeEach
    public final void setup() {
        IntegerMapReader reader = new IntegerMapReader(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        map = reader.getXYMap();
        finder = new TrailheadFinder(map);
    }

    @Test
    public final void simpleCase() {

        String[] input = new String[] {
            "...0...",
            "...1...",
            "...2...",
            "6543456",
            "7.....7",
            "8.....8",
            "9.....9"
        };

        IntegerMapReader reader = new IntegerMapReader(input);
        map = reader.getXYMap();
        finder = new TrailheadFinder(map);
        TrailWalker walker = new TrailWalker(map, finder.getTrailheads());
        walker.walk();
        assertEquals(2, walker.getTotalScore());

    }

    @Test
    public final void simpleCase2() {

        String[] input = new String[] {
                "..90..9",
                "...1.98",
                "...2..7",
                "6543456",
                "765.987",
                "876....",
                "987...."
        };

        IntegerMapReader reader = new IntegerMapReader(input);
        map = reader.getXYMap();
        finder = new TrailheadFinder(map);
        TrailWalker walker = new TrailWalker(map, finder.getTrailheads());
        walker.walk();
        assertEquals(4, walker.getTotalScore());

    }

    @Test
    public final void trailwalkerTest() {
        TrailWalker walker = new TrailWalker(map, finder.getTrailheads());
        walker.walk();
        assertEquals(36, walker.getTotalScore());
        assertEquals(81, walker.getTotalRating());
    }

}
