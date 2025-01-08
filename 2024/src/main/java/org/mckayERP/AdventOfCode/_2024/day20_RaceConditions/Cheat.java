package org.mckayERP.AdventOfCode._2024.day20_RaceConditions;

import org.mckayERP.AdventOfCode.utilities.Position;

public class Cheat
{
    private final int costSavings;
    private Position start, end;

    public Position getStart()
    {
        return start;
    }

    public void setStart(Position start)
    {
        this.start = start;
    }

    public Position getEnd()
    {
        return end;
    }

    public void setEnd(Position end)
    {
        this.end = end;
    }

    public Cheat(Position start, Position end, int costSavings)
    {
        this.start = start;
        this.end = end;
        this.costSavings = costSavings;
    }

    public int getCostSavings()
    {
        return costSavings;
    }

    @Override
    public String toString() {
        return start + "->" + end + ":" + costSavings;
    }
}
