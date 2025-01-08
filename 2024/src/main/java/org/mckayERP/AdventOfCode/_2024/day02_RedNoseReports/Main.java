package org.mckayERP.AdventOfCode._2024.day02_RedNoseReports;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args){

        String[] reports = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");

        RuleEngine rules = new RuleEngine();

        System.out.println("Part 1: Count is " + rules.countOfSafeReports(reports, false));
        System.out.println("Part 2: Count is " + rules.countOfSafeReports(reports, true));
    }

}
