package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlmanacReaderTest
{
    @Test
    public final void testConstructor() {
        AlmanacReader reader = new AlmanacReader();
    }

    @Test
    public final void testCanReadAlmanac() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
    }

    @Test
    public final void testReaderCanReadSeeds() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(4, reader.getSeedList().size());
    }

    @Test
    public final void testReaderCanReadSeedToSoilMap() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(4, reader.getSeedList().size());
    }

    @Test
    public final void testReaderCanReadSeedInput() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(7, reader.getMaps().size());
    }

}
