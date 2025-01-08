package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest
{

    @Test
    public final void testEntry8IsNotPossible() {
        PatternsAndDesiredDesignReader reader;
        reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        TowelPatternMatcher matcher = new TowelPatternMatcher();
        matcher.setAvailablePatterns(reader.getPatterns());
        matcher.setDesiredDesigns(reader.getDesiredDesigns());
        assertEquals(134528335466L, matcher.canMatch(reader.getDesiredDesigns().get(0)));
        assertTrue(matcher.canMatch(reader.getDesiredDesigns().get(8)) == 0);
    }

    @Test
    public final void testSmallStrings() {
        PatternsAndDesiredDesignReader reader;
        reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        TowelPatternMatcher matcher = new TowelPatternMatcher();
        matcher.setAvailablePatterns(reader.getPatterns());
        matcher.setDesiredDesigns(reader.getDesiredDesigns());
        assertTrue(matcher.canMatch("wgu") == 0);

    }
}
