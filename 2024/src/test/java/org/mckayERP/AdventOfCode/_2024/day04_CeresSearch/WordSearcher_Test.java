package org.mckayERP.AdventOfCode._2024.day04_CeresSearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordSearcher_Test
{
    WordSearcher searcher;

    @BeforeEach
    public final void setup() {
        searcher = new WordSearcher();
    }

    @Test
    public final void givenAnArrayOfCharacters_canSearchTheArrayForWord() {

        searcher.setStringArray(new String[] {"MMMSXXMASM",
                                              "MSAMXMSMSA"});
        searcher.setSearchString("XMAS");
        assertEquals(2, searcher.countOfSearchString());

    }

    @Test
    public final void givenADirection_StartingRowIsDetermined() {
        Direction dir = Direction.LTR;
        searcher.setStringArray(new String[] {"MMMSXXMASM",
                                              "MSAMXMSMSA"});
        assertEquals(0, searcher.getStartingRow(dir));
    }

    @Test
    public final void givenTheTestInput_StartingRowIsDetermined() {
        Direction dir = Direction.LTR;
        searcher.setStringArray(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(),"testInput.txt"));
        searcher.setSearchString("XMAS");
        assertEquals(18, searcher.countOfSearchString());
    }

}
