package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TowelPatternMatcherTest
{
    static PatternsAndDesiredDesignReader reader;

    TowelPatternMatcher matcher;

    @BeforeAll
    public static void setupReader() {
        reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(TowelPatternMatcherTest.class, "testInput.txt"));
    }

    @BeforeEach
    public final void setup() {
        matcher = new TowelPatternMatcher();
        matcher.setAvailablePatterns(reader.getPatterns());
        matcher.setDesiredDesigns(reader.getDesiredDesigns());
    }

    @Test
    public final void matcherCanFindPatternsThatStartWithAColour() {
        List<String> bPatterns = matcher.getPatternsStartingWith("b");
        assertEquals(3, bPatterns.size());
    }

    @Test
    public final void matcherCanFindPatternsThatMatchDesign() {

        assertTrue(matcher.canMatch("brwrr") > 0);
        assertTrue(matcher.canMatch("bggr") > 0);
        assertTrue(matcher.canMatch("gbbr") > 0);
        assertTrue(matcher.canMatch("rrbgbr") > 0);
        assertTrue(matcher.canMatch("bwurrg") > 0);
        assertTrue(matcher.canMatch("brgr") > 0);
        assertTrue(matcher.canMatch("brrbwu") > 0);
    }

    @Test
    public final void matcherCantFindImpossibleDesignPatterns() {

        assertTrue(matcher.canMatch("ubwu") == 0);
        assertTrue(matcher.canMatch("bbrgwb") == 0);
    }

    @Test
    public final void testGetCountOfPossibleDesigns() {
        assertEquals(6, matcher.getCountOfPossibleDesigns());
    }

    @Test
    public final void testCountOfVariations() {
        assertEquals(2, matcher.canMatch("brwrr"));
        assertEquals(1, matcher.canMatch("bggr"));
        assertEquals(4, matcher.canMatch("gbbr"));
        assertEquals(6, matcher.canMatch("rrbgbr"));
        assertEquals(1, matcher.canMatch("bwurrg"));
        assertEquals(2, matcher.canMatch("brgr"));
        assertTrue(matcher.canMatch("brrbwu") > 0);
    }

}
