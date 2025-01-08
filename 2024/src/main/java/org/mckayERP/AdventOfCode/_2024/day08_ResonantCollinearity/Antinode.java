package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.mckayERP.AdventOfCode.utilities.Position;

public class Antinode
{
    Position position;
    Antenna a;
    Antenna b;


    public Antinode() {}
    public Antinode(Antenna a, Antenna b)
    {
        if (! a.getType().equals(b.getType()))
            throw new IllegalArgumentException("Both antennae must have the same type");

        this.a = a;
        this.b = b;
        position = new Position(a.getPosition().getRow() + 2*(b.getPosition().getRow() - a.getPosition().getRow()),
                a.getPosition().getCol() + 2*(b.getPosition().getCol() - a.getPosition().getCol()));
    }

    public Position getPosition()
    {
        return position;
    }

    @Override
    public String toString() {
        return position + " (" + a + "->" + b + ")";
    }

}
