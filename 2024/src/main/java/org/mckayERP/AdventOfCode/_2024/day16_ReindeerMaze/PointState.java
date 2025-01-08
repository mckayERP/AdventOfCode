package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class PointState implements Cloneable
{

    public PointState(Position p, Direction dir, Integer costOfThisMove, Integer costOfThisPath, LinkedList<Position> path)
    {

        this.position = p;
        this.direction = dir;
        this.costOfThisMove = costOfThisMove;
        this.costOfThisPath = costOfThisPath;
        this.path = path;
        this.isStart = false;
    }


    public Position position;
    public Direction direction;
    public Integer costOfThisMove;
    public Integer costOfThisPath;

    public boolean isStart = false;

    public LinkedList<Position> path;

    @Override
    public String toString()
    {
        return "" + position + direction;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointState that = (PointState) o;
        return Objects.equals(position, that.position) && direction == that.direction && Objects.equals(costOfThisMove, that.costOfThisMove) && Objects.equals(costOfThisPath, that.costOfThisPath);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(position, direction, costOfThisMove, costOfThisPath, path);
    }

    @Override
    public PointState clone()
    {
        try
        {
            PointState clone = (PointState) super.clone();
            clone.position = position.clone();
            clone.direction = direction;
            clone.costOfThisMove = costOfThisMove;
            clone.costOfThisPath = costOfThisPath;
            clone.path = new LinkedList<>(new ArrayList<>(path));
            clone.isStart = false;
            return clone;
        } catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }
}
