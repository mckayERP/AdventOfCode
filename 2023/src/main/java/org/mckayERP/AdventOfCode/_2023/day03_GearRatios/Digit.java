package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;

public class Digit implements MapObject
{
    private final Position position;
    int value;

    SchematicNumber parentNumber = null;

    public Digit(String symbol, Position position)
    {
        this.value = Integer.parseInt(symbol);
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
        return "" + value;
    }

    public void setNumber(SchematicNumber number)
    {
        parentNumber = number;
    }

    public SchematicNumber getNumber() {
        return parentNumber;
    }
}
