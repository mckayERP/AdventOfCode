package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import java.util.Comparator;
import java.util.Objects;

public class RangeComparator implements Comparator<Range>
{
    boolean basedOnOutput = false;

    public RangeComparator() {
        basedOnOutput = false;
    }

    public RangeComparator(boolean basedOnOutput) {
        this.basedOnOutput = basedOnOutput;
    }


    @Override
    public int compare(Range r1, Range r2)
    {
        if (r1.equals(r2))
            return 0;
        if (basedOnOutput)
        {
                return r1.outputStart.compareTo(r2.outputStart);
        }
        else
            return r1.inputStart.compareTo(r2.inputStart);
    }

}
