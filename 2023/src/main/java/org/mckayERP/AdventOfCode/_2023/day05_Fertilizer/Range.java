package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class Range
{


    Long inputStart;
    Long outputStart;
    Long range;

    public Range(Long[] input) {
        outputStart = input[0];
        inputStart = input[1];
        range = input[2];
    }

    public Range(long inputStart, long outputStart, Long rangeLength)
    {
        this.inputStart = inputStart;
        this.outputStart = outputStart;
        this.range = rangeLength;
    }

    public static TreeSet<Range> flatten(Range thisRange, Range nextRange)
    {
        return null;
    }

    public boolean overlaps(Range nextRange)
    {
        long outputEnd = outputStart + range -1;
        long inputStart = nextRange.inputStart;
        long inputEnd = nextRange.inputStart + nextRange.range -1;

        return outputEnd >= inputStart && outputStart <= inputEnd;
    }

    public boolean inRange(long input)
    {
        return input >= inputStart && input <= inputStart + range - 1; // sourceStart
    }

    public long convertToDestination(long input)
    {
        Long offset = input - inputStart;
        return outputStart + offset;
    }
    public Long getInputStart()
    {
        return inputStart;
    }

    public void setInputStart(Long inputStart)
    {
        this.inputStart = inputStart;
    }

    public Long getOutputStart()
    {
        return outputStart;
    }

    public void setOutputStart(Long outputStart)
    {
        this.outputStart = outputStart;
    }

    public Long getRange()
    {
        return range;
    }

    public void setRange(Long range)
    {
        this.range = range;
    }

    @Override
    public String toString() {
        return this.inputStart + "->" + this.outputStart + " (" + this.range + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Range otherRange) {
            return otherRange.inputStart.equals(this.inputStart)
                    && otherRange.outputStart.equals((this.outputStart))
                    && otherRange.range.equals(this.range);
        }
        else
            return false;
    }
}
