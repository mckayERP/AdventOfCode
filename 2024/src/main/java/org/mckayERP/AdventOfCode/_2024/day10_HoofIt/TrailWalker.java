package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;


import java.util.List;

public class TrailWalker
{
    XYMap<Integer> map;

    List<Trailhead> trailheads;

    public TrailWalker(XYMap<Integer> map, List<Trailhead> trailheads)
    {
        this.map = map;
        this.trailheads = trailheads;
    }

    public void walk()
    {
        for (Trailhead trailhead : trailheads) {
            Position currentPosition = trailhead.start.clone();
            walk(currentPosition, trailhead);

        }
    }

    private void walk(Position currentPosition, Trailhead trailhead)
    {
        if (terrainHeightAtPosition(currentPosition) == 9)
        {
            trailhead.rating++;
            if (!trailhead.peaks.contains(currentPosition))
            {
                trailhead.score++;
                trailhead.peaks.add(currentPosition);
            }
        }
        else
        {
            for (Direction dir : Direction.values())
            {
                go(dir, currentPosition, trailhead);
            }
        }
    }

    private void go(Direction dir, Position currentPosition, Trailhead trailhead)
    {

        int row = currentPosition.getRow();
        int col = currentPosition.getCol();
        switch (dir) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> col--;
            case RIGHT -> col++;
            default -> throw new RuntimeException("Unknown direction");
        };

        Position nextPosition = new Position(row, col);

        if (map.isOnMap(nextPosition) && terrainHeightAtPosition(nextPosition) - terrainHeightAtPosition(currentPosition) == 1)
        {
            walk(nextPosition, trailhead);
        }
    }



    private int terrainHeightAtPosition(Position currentPosition)
    {
        return map.getValueAt(currentPosition);
    }

    public int getTotalScore()
    {
        return trailheads.stream().mapToInt(th -> th.score).sum();
    }

    public int getTotalRating()
    {
        return trailheads.stream().mapToInt(th -> th.rating).sum();
    }
}
