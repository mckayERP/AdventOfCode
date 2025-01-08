package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternsAndDesiredDesignReaderTest
{

    @Test
    public final void testConstructor() {
        PatternsAndDesiredDesignReader reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        List<String> patterns = reader.getPatterns();
        List<String> desiredDesigns = reader.getDesiredDesigns();
        assertEquals(8, patterns.size());
        assertEquals(8, desiredDesigns.size());
        assertEquals("brwrr", desiredDesigns.get(0));
        assertEquals("wr", patterns.get(1));
    }

}
