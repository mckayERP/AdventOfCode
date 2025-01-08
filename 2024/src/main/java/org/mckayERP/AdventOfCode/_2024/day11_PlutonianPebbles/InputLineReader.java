package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import java.util.ArrayList;
import java.util.List;

public class InputLineReader
{

    public List<Stone> read(String s)
    {
        List<Stone> list = new ArrayList<>();

        String[] seeds = s.split("\\D+");
        for (String seed:seeds) {
            Stone stone = new Stone(Long.parseUnsignedLong(seed), 1);
            list.add(stone);
        }
        return list;
    }
}
