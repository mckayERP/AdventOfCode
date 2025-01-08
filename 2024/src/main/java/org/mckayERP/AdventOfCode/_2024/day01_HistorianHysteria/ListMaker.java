package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListMaker
{
    public List<List<Integer>> make(String input) {
        String[] data = new String[] {input};
        return make(data);
    }
    public List<List<Integer>> make(String[] input)
    {
        if (input == null)
            return null;

        LineParser parser = new LineParser();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for (String line : input)
        {
            int[] lineResult;
            try
            {
                lineResult = parser.parse(line);
            } catch (Exception e)
            {
                return null;
            }

            firstList.add(lineResult[0]);
            secondList.add(lineResult[1]);
        }

        result.add(firstList);
        result.add(secondList);

        return result;
    }

    public List<List<Integer>> sort(List<List<Integer>> lists)
    {

        lists.forEach(list -> list.sort(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o1.compareTo(o2);
            }

        }));

        return lists;
    }
}
