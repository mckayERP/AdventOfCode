package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Trailhead
{
    Position start;

    List<Position> peaks = new ArrayList<>();

    int score = 0;
    int rating = 0;

    public Trailhead(Position position)
    {
        this.start = position;
    }

    @Override
    public String toString() {
        return ""+ start + " Score:" + score;
    }
}
