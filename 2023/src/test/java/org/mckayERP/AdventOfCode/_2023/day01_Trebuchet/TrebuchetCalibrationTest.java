package org.mckayERP.AdventOfCode._2023.day01_Trebuchet;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrebuchetCalibrationTest
{

    @Test
    public final void testConstructor()
    {
        TrebuchetCalibration uut = new TrebuchetCalibration();
    }

    @Test
    public final void givenStringInput_canFindsumOfValues() {
        String[]  inputs = new String[] {
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet",
        };

        TrebuchetCalibration uut = new TrebuchetCalibration();
        assertEquals(142, uut.getSumOfCalibrationValues(inputs));
    }

    @Test
    public final void givenASetOfInputsWithWordNumbers_CanConvertToNumbers() {
        String[]  inputs = new String[] {
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        };

        TrebuchetCalibration uut = new TrebuchetCalibration();
        assertEquals(281, uut.getSumOfExpandedCalibrationValues(inputs));

    }

    @Test
    public final void givenASetOfInputsWithWordNumbersAtEnd_CanConvertToNumbers() {
        String[]  inputs = new String[] {
                "twoeighthree",
                "nineighthreefonite",
                "three4fournineone",
                "athree4fournineightg",
                "sevenine",
                "twone",
                "eightwo"
        };

        TrebuchetCalibration uut = new TrebuchetCalibration();
        assertEquals(23+93+31+38+79+21+82, uut.getSumOfExpandedCalibrationValues(inputs));

    }

}