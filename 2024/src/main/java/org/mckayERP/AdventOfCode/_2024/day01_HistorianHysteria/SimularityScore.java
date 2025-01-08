package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import java.util.List;

public class SimularityScore
{
    public long score(List<List<Integer>> lists)
    {

        List<Integer> listOne = lists.get(0);
        List<Integer> listTwo = lists.get(1);

        long simularityScore = 0;
        for (Integer a : listOne)
        {
            long count = (listTwo.stream().filter(a::equals).count());
            long score = a * count;
            simularityScore += score;
        }
        return simularityScore;

    }
}
