package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.util.Comparator;

public class WireComparator  implements Comparator<Wire>
{
    @Override
    public int compare(Wire w1, Wire w2)
    {
        return w1.getName().compareTo(w2.getName());
    }

}
