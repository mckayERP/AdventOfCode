package org.mckayERP.AdventOfCode._2023.day04_ScratchCards;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardReaderTest
{
    @Test
    public final void testConstructor() {
        CardReader reader = new CardReader();
    }

    @Test
    public final void testCanReadTestInput() {
        CardReader reader = new CardReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(13, reader.getSumOfCardValue());
    }

    @Test
    public final void testCanDetermineInstances() {
        CardReader reader = new CardReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(30, reader.getTotalCardsWon());
    }
}
