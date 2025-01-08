package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {

        String [] input = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");
        CalibrationFinder finder = new CalibrationFinder();
        finder.setInput(input);
        long result = finder.getCalibrationResult();
        System.out.println("Part 1: the total calibration result = " + result);
        finder = new CalibrationFinder(true);
        finder.setInput(input);
        result = finder.getCalibrationResult();
        System.out.println("Part 2: the total calibration result with concatenation = " + result);
    }
}
