package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main
{
    public static void main(String[] args) {

        PrintQueueTester tester = new PrintQueueTester();
        tester.loadRulesAndOrders(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "testInput.txt"));
        tester.testPrintOrders();
        System.out.println("Part 1: The sum of middle pages is " + tester.getSumOfMiddlePageNumbers());
        tester.correctBadPrintOrders();
        System.out.println("Part 2: the sum of the middle pages of the corrected orders is " +
                tester.getSumOfCorrectedMiddlePages());

    }
}
