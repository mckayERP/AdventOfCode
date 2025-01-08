package org.mckayERP.AdventOfCode.utilities;

import java.util.Objects;

public class Position implements Cloneable
{
    int row;
    int col;
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Position p = (Position) obj;

        return p.row == row && p.col == col;

    }


    @Override
    public int hashCode()
    {
        return Objects.hash(row, col);
    }

    @Override
    public Position clone()
    {
        try
        {
            Position clone = (Position) super.clone();
            clone.row = this.row;
            clone.col = this.col;
            return clone;
        } catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public int getRow()
    {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }
    public void setCol(int col)
    {
        this.col = col;
    }

    public Position move(Direction dir) {

        int row = getRow();
        int col = getCol();
        switch (dir) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> col--;
            case RIGHT -> col++;
            default -> throw new RuntimeException("Unknown direction");
        };

        return new Position(row, col);

    }

    public boolean isNextTo(Position p)
    {
        return Math.abs(this.row - p.row) <= 1 && Math.abs(this.col - p.col) <= 1;
    }
}
