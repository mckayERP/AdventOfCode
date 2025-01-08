package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Position;

public class KeypadKey
{
    String symbol;

    public KeypadKey(String symbol, Position position)
    {
        setSymbol(symbol);
        setPosition(position);
    }

    public KeypadKey(String symbol)
    {
        setSymbol(symbol);
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    Position position;


    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    @Override
    public String toString() {return symbol + ":" + position;}
}
