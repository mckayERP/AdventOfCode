package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AntennaFinder_Test
{

    @Test
    public final void  canReadTestInput() {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
        AntennaFinder finder = new AntennaFinder(input);
        assertEquals(7, finder.getAntennas().size());
    }
}
