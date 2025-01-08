package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        DistanceFinder distanceFinder = new DistanceFinder();

        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");

        ListMaker listMaker = new ListMaker();
        List<List<Integer>> lists = listMaker.sort(listMaker.make(input[0]));

        int result = distanceFinder.withLists(lists).calculateDistance();
        System.out.println("Part one: Distance calculated as " + result);

        SimularityScore simularityScore = new SimularityScore();
        long score = simularityScore.score(lists);
        System.out.println("Part two: Simularity score is " + score);

    }
}
