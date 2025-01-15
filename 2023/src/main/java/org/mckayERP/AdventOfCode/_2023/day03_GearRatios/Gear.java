package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import java.util.ArrayList;
import java.util.List;

public class Gear
{
    List<SchematicNumber> numbers = new ArrayList<>();
    public Gear(Symbol asterix)
    {
        if (asterix.adjacentNumbers.size() != 2)
            throw new IllegalStateException("Gears can only be adjacent to two numbers!");
        numbers = asterix.getAdjacentNumbers();
    }

    public int getGearRatio() {
        return numbers.get(0).getValue() * numbers.get(1).getValue();
    }
}
