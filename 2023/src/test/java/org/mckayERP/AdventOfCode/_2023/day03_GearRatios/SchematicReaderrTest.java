package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchematicReaderrTest
{

    @Test
    public final void testConstructor()
    {
        SchematicReader uut = new SchematicReader();
    }

    @Test
    public final void canReadTestInput() {
        SchematicReader reader = new SchematicReader();
        reader.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        reader.getMap().print();
    }

    @Test
    public final void canFindNumbers() {
        SchematicReader reader = new SchematicReader();
        reader.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        reader.initializeXYMap();
        assertEquals(8, reader.findPartNumbers());
    }

    @Test
    public final void canSumPartNumbers() {
        SchematicReader reader = new SchematicReader();
        reader.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        reader.initializeXYMap();
        assertEquals(4361, reader.getSumOfPartNumbers());

    }

    @Test
    public final void canSumGearNumbers() {
        SchematicReader reader = new SchematicReader();
        reader.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        reader.initializeXYMap();
        assertEquals(467835, reader.getSumOfGearRatios());
    }
}