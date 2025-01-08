package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.mckayERP.AdventOfCode.utilities.Position;

public class Antenna
{
    Position position;

    String type;

    public Antenna() {}

    public Antenna(Position position, String type)
    {
        this.position = new Position(position.getRow(), position.getCol());
        this.type = type;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return type + position;
    }

}
