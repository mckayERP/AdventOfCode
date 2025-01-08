package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.Bounds;

import java.util.ArrayList;
import java.util.List;

public class AntinodeGenerator
{
    private final Bounds bounds;
    List<Antinode> antinodeList;

    public AntinodeGenerator(List<Antenna> antennaList, Bounds mapBounds) {
        this(antennaList, mapBounds, false);
    }

    public AntinodeGenerator(List<Antenna> antennaList, Bounds mapBounds, boolean withHarmonics)
    {
        this.bounds = mapBounds;
        antinodeList = new ArrayList<>();
        for (Antenna a : antennaList) {
            for (Antenna b : antennaList) {
                if (!withHarmonics)
                {
                    createNonHarmonicAntinode(a, b);
                }
                else {
                    createHarmonicAntiNodes(a,b);
                }
            }
        }
    }

    private void createHarmonicAntiNodes(Antenna a, Antenna b)
    {
        if (!a.equals(b) && a.getType().equals(b.getType()))
        {
            int i = 0;
            boolean offMap = false;
            while (!offMap)
            {
                i++;
                Position position = new Position(a.getPosition().getRow() + i * (b.getPosition().getRow() - a.getPosition().getRow()), a.getPosition().getCol() + i * (b.getPosition().getCol() - a.getPosition().getCol()));
                if (bounds.isWithinBounds(position))
                {
                    Antinode newAntinode = new Antinode();
                    newAntinode.position = position;
                    newAntinode.a = a;
                    newAntinode.b = b;
                    antinodeList.add(newAntinode);
                } else
                    offMap = true;
            }
        }
    }

    private void createNonHarmonicAntinode(Antenna a, Antenna b)
    {
        if (!a.equals(b) && a.getType().equals(b.getType()))
        {
            Antinode aa = new Antinode(a, b);
            if (bounds.isWithinBounds(aa.position))
                antinodeList.add(new Antinode(a, b));
        }
    }

    public List<Antinode> getAntinodes()
    {
        return antinodeList;
    }

    public long getCountOnMap()
    {
        return antinodeList.stream()
                .filter(n -> bounds.isWithinBounds(n.getPosition()))
                .map(Antinode::getPosition)
                .distinct().count();
    }

}
