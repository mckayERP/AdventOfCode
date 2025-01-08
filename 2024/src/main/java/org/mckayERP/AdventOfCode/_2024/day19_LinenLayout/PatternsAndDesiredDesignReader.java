package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import java.util.Arrays;
import java.util.List;

public class PatternsAndDesiredDesignReader
{
    private String patternString;
    private String[] desiredDesigns;

    public void read(String[] input)
    {
        patternString = input[0];
        desiredDesigns = Arrays.copyOfRange(input,2, input.length);
    }

    public List<String> getPatterns()
    {
        return Arrays.stream(patternString.split(",")).map(String::trim).toList();
    }

    public List<String> getDesiredDesigns() {
        return Arrays.stream(desiredDesigns).map(String::trim).toList();
    }
}
