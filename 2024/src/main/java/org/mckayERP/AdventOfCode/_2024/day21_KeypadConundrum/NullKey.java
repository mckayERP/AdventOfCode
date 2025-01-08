package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Position;

public class NullKey extends KeypadKey
{

    public NullKey(String symbol, Position position)
    {
        super(symbol, position);
    }

    public NullKey()
    {
        super("#");
    }

    @Override
    public void setSymbol(String ignored) {}

    @Override
    public String getSymbol()
    {
        return "#";
    }
}
