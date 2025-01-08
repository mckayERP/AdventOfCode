package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerrainReader_Test
{
    @Test
    public final void testTerrainReader() {
        IntegerMapReader reader = new IntegerMapReader(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        reader.map.printMap();
        assertEquals(8, reader.getXYMap().getValueAt(0,0));
    }
}
