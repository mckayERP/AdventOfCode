package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Region
{
    public Region() {
        plots = new ArrayList<>();
        plantType = "";
    }
    int regionCode;
    String plantType;
    List<Position> plots;

    List<Edge> edges = new ArrayList<>();

    public boolean contains(Position p)
    {
        return plots.contains(p);
    }

    public int getEdgeCount()
    {
        return edges.size();
    }
}
