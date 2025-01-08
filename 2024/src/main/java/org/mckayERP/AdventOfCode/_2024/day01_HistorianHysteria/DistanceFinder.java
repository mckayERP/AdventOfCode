package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import java.util.List;

public class DistanceFinder
{
    List<Integer> listOne;
    List<Integer> listTwo;

    public DistanceFinder withData(String[] input)
    {
        ListMaker listMaker = new ListMaker();
        List<List<Integer>> list = listMaker.sort(listMaker.make(input));
        listOne = list.get(0);
        listTwo = list.get(1);
        return this;
    }

    public int calculateDistance()
    {
        int sum = 0;

        for (int i=0; i<listOne.size(); i++) {
            sum += Math.abs(listOne.get(i) - listTwo.get(i));
        }

        return sum;
    }

    public DistanceFinder withLists(List<List<Integer>> lists)
    {

        listOne = lists.get(0);
        listTwo = lists.get(1);
        return this;

    }
}
