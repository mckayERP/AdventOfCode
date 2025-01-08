package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalibrationFinderTest
{
    String [] input;

    @BeforeEach
    public final void setup() {
        input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
    }

    @Test
    public final void singleInputcalibrationFinderTest()
    {
        String[] single = new String[]{"190: 10 19"};
        CalibrationFinder finder = new CalibrationFinder();
        finder.setInput(single);
        long result = finder.getCalibrationResult();
        assertEquals(190, result);
    }

    @Test
    public final void singleInputWithLongcalibrationFinderTest()
    {
        String[] single = new String[]{"11230549368122: 713 99 991 43 37 24"};
        CalibrationFinder finder = new CalibrationFinder();
        finder.setInput(single);
        long result = finder.getCalibrationResult();
        assertEquals(0L, result);
    }

    @Test
    public final void fullInputCalibrationFinderTest()
    {
        CalibrationFinder finder = new CalibrationFinder();
        finder.setInput(input);
        long result = finder.getCalibrationResult();
        assertEquals(3749, result);
    }

    @Test
    public final void fullInputCalibrationFinderWithConcatenationTest()
    {
        CalibrationFinder finder = new CalibrationFinder(true);
        finder.setInput(input);
        long result = finder.getCalibrationResult();
        assertEquals(11387, result);
    }

}
