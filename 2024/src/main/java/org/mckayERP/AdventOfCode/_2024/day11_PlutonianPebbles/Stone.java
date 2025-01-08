package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

public class Stone
{
    long value;

    long multiplier;

    public Stone(long value, long multiplier)
    {
        this.value = value;
        this.multiplier = multiplier;
    }

    public long getMultiplier()
    {
        return multiplier;
    }

    public Long getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return value + ":" + multiplier;
    }
}
