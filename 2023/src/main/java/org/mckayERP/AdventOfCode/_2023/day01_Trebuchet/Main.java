package org.mckayERP.AdventOfCode._2023.day01_Trebuchet;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {

        TrebuchetCalibration calibration = new TrebuchetCalibration();
        System.out.println(calibration.getSumOfCalibrationValues(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt" )));
        System.out.println(calibration.getSumOfExpandedCalibrationValues(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt" )));
    }
}
