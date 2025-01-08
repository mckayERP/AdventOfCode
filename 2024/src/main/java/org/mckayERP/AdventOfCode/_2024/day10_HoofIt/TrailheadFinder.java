package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import java.util.ArrayList;
import java.util.List;

public class TrailheadFinder
{
    XYMap<Integer> map;

    List<Trailhead> trailheads;
    public TrailheadFinder(XYMap<Integer> map)
    {
        this.map = map;
        trailheads = new ArrayList<>();
        findTrailheads();
    }

    private void findTrailheads()
    {
        for (int row = 0; row < map.getRowCount(); row++) {
            for (int col = 0 ; col < map.getColCount(); col++) {
                if (map.getValueAt(row, col) == 0)
                {
                    Trailhead trailhead = new Trailhead(new Position(row, col));
                    trailheads.add(trailhead);
                }
            }
        }
    }

    public List<Trailhead> getTrailheads()
    {
        return trailheads;
    }
}
