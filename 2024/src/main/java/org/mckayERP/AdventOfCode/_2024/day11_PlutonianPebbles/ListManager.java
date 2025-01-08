package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import java.util.*;
import java.util.stream.Collectors;

public class ListManager
{
    List<Stone> stones = new ArrayList<>();
    public void setInitialArrangement(String s)
    {
        InputLineReader reader = new InputLineReader();
        stones = reader.read(s);
    }

    public Long blink(int times)
    {
        StoneTransformer transformer = new StoneTransformer();
        for (int blinkNum = 0; blinkNum < times; blinkNum++)
        {
            int i = 0;
            while (i < stones.size())
            {
                Stone[] output = transformer.transform(stones.get(i));
                if (output.length == 1)
                {
                    stones.remove(i);
                    stones.add(i++, output[0]);
                } else if (output.length == 2)
                {
                    stones.remove(i);
                    stones.add(i++, output[0]);
                    stones.add(i++, output[1]);
                }
            }
//            stones.sort(new Comparator<Stone>()
//            {
//                @Override
//                public int compare(Stone o1, Stone o2)
//                {
//                    Long v1 = o1.value;
//                    Long v2 = o2.value;
//                    int vComp = v1.compareTo(v2);
//                    if (vComp != 0)
//                        return vComp;
//
//                    Integer m1 = o1.multiplier;
//                    Integer m2 = o2.multiplier;
//                    return m1.compareTo(m2);
//                }
//            });

            stones = stones.stream()
                    .collect(Collectors.groupingBy(Stone::getValue, Collectors.summingLong(Stone::getMultiplier)))
                    .entrySet().stream()
                    .map(e -> new Stone(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());
        }
        return countStones();
    }

    public long countStones()
    {
        return stones.stream().mapToLong(s -> s.multiplier).sum();
    }

    public List<Stone> getStoneList()
    {
        return stones;
    }
}
