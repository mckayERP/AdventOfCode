package org.mckayERP.AdventOfCode._2024.day04_CeresSearch;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMasSearch_Test
{

    @Test
    public final void givenTheTestInput_findsNine() {

        XMasSearcher searcher = new XMasSearcher();
        searcher.setStringArray(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(9,searcher.countOfXMas());

    }
}
