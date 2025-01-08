package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class PathTracker implements Cloneable
{

    private List<PathPoint> path;
    private List<Position> spots;
    boolean loopDetected = false;
    public PathTracker() {
         path = new ArrayList<>();
         spots = new ArrayList<>();
    }

    public boolean addPathPoint(PathPoint p) {
        if (path.contains(p))
            loopDetected = true;
        else
        {
            path.add(p.clone());
            if (!spots.contains(p.position))
                spots.add(p.position.clone());
        }

        return loopDetected;
    }

    @Override
    public PathTracker clone()
    {
        try
        {
            PathTracker clone = (PathTracker) super.clone();
            clone.path = new ArrayList<>();
            for (PathPoint p : path)
                clone.addPathPoint(p);
            return clone;
        } catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }

    public int getPathSize()
    {
        return path.size();
    }

    public int getUniqueSize()
    {
        return spots.size();
    }

    public List<Position> getSpotsVisited()
    {
        return spots;
    }
}
