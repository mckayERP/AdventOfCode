package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import java.util.ArrayList;
import java.util.List;

public class SchematicNumber implements Comparable
{
    int value = 0;

    String valueString = "";
    List<Digit> digits = new ArrayList<>();

    public void setValueString(String s)
    {
        valueString = s;
    }

    public String getValueString()
    {
        return valueString;
    }

    public void addDigit(Digit d)
    {
        digits.add(d);
        setValueString(getValueString() + d.getSymbol());
        value = Integer.parseInt(getValueString());
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValueString();
    }

    @Override
    public int compareTo(Object other)
    {
        return getValue() - ((SchematicNumber) other).getValue();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SchematicNumber))
            return false;

        if (((SchematicNumber) other).getValue() != getValue())
            return false;

        for (int i=0; i<digits.size(); i++) {
            if (!digits.get(i).getPosition().equals(((SchematicNumber) other).digits.get(i).getPosition()))
                return false;
        }
        return true;
    }
}
