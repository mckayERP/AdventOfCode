package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.mckayERP.AdventOfCode.utilities.Position;

public class PathPoint implements Cloneable
{
    Position position;
    Direction direction;

    public PathPoint(Position p, Direction d) {
        position = new Position(p.getRow(), p.getCol());
        direction = d;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;

        PathPoint op = (PathPoint) o;

        return op.position.equals(position) && op.direction == direction;
    }

    @Override
    public String toString() {
        return "" + position + direction;
    }

    @Override
    public PathPoint clone()
    {
        try
        {
            PathPoint clone = (PathPoint) super.clone();
            clone.position = new Position(this.position.getRow(), this.position.getCol());
            clone.direction = this.direction;
            return clone;
        } catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }
}
