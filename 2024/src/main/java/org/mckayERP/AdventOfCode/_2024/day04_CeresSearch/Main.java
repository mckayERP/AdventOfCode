package org.mckayERP.AdventOfCode._2024.day04_CeresSearch;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {

        WordSearcher searcher = new WordSearcher();
        searcher.setStringArray(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class,"input.txt"));
        searcher.setSearchString("XMAS");
        System.out.println("Part 1: found xmas count: " + searcher.countOfSearchString());

        XMasSearcher xmasSearcher = new XMasSearcher();
        xmasSearcher.setStringArray(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class,"input.txt"));
        System.out.println("Part 2: found xmas count: " + xmasSearcher.countOfXMas());

    }
}
