package org.mckayERP.AdventOfCode.utilities;

public class Bounds
{
    Position topLeft, bottomRight;
    public Bounds(Position topLeft, Position bottomRight)
    {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public boolean isWithinBounds(Position position)
    {
        return topLeft.getRow() <= position.getRow() && position.getRow() < bottomRight.getRow()
                && topLeft.getCol() <= position.getCol() && position.getCol() < bottomRight.getCol();
    }
}
