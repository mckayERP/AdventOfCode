package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.util.Comparator;

public class GateComparator implements Comparator<Gate>

{
    @Override
    public int compare(Gate g1, Gate g2)
    {
        return Integer.compare(g1.getID(), g2.getID());
    }
}
