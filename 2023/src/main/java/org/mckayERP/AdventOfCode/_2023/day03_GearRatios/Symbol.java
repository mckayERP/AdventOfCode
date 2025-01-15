package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Symbol implements MapObject
{
    private final Position position;
    private final String symbol;

    List<SchematicNumber> adjacentNumbers = new ArrayList<>();
    public Symbol(String symbol, Position position)
    {
        this.symbol = symbol;
        this.position = position;
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public String getSymbol()
    {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + " (" + position + ")";
    }

    public List<SchematicNumber> getAdjacentNumbers()
    {
        return adjacentNumbers;
    }

    public void addAdjacentNumber(SchematicNumber n) {
        if (adjacentNumbers.contains(n))
            return;
        adjacentNumbers.add(n);
    }

}
