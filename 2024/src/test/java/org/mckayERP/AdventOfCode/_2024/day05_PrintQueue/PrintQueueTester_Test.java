package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrintQueueTester_Test
{
    PrintQueueTester tester;
    String[] testInput;
    @BeforeEach
    public final void setup() {
        tester = new PrintQueueTester();
        testInput = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
    }

    @Test
    public final void givenAString_CanIdentifyARule() {
        String input = "47|53";
        assertTrue(tester.isRule(input));
    }

    @Test
    public final void givenAString_CanIdentifyNotARule() {
        String input = "47,53,29,75,33";
        assertFalse(tester.isRule(input));
    }

    @Test
    public final void givenRuleString_canCreateRule() {
        String input = "47|53";
        tester.parseRule(input);
        assertTrue(tester.getRuleEngine().matches(47,53));
    }

    @Test
    public final void givenTestInput_canMatch() {
        tester.loadRulesAndOrders(testInput);
        assertTrue(tester.getRuleEngine().matches(47,53));
        assertTrue(tester.getRuleEngine().matches(53,13));
        assertFalse(tester.getRuleEngine().matches(53,17));
    }

    @Test
    public final void givenAPrintList_canParcePages() {
        int[] pages = tester.parsePrintList("75,47,61,53,29");
        assertEquals(47, pages[1]);
    }
    @Test
    public final void givenTestInput_canTest_pagesToProductCanBeTestedTrue() {
        tester.loadRulesAndOrders(testInput);
        assertTrue(tester.test("75,47,61,53,29"));

    }

    @Test
    public final void givenTestInput_canTest_pagesToProductCanBeTestedFalse() {
        tester.loadRulesAndOrders(testInput);
        assertFalse(tester.test("75,97,47,61,53"));
    }

    @Test
    public final void givenShortTestInput_pagesToProductCanBeTestedFalse() {
        tester.loadRulesAndOrders(testInput);
        assertFalse(tester.test("61,13,29"));
    }

    @Test
    public final void givenASetOfPageNumbers_canFindTheMiddleNumber() {
        tester.loadRulesAndOrders(testInput);
        tester.test("75,47,61,53,29");
        assertEquals(61, tester.getSumOfMiddlePageNumbers());
    }

    @Test
    public final void givenTestInputs_canSumOfPages() {
        tester.loadRulesAndOrders(testInput);
        tester.testPrintOrders();
        assertEquals(143, tester.getSumOfMiddlePageNumbers());
    }

    @Test
    public final void givenMisorderedPageList_canCorrectIt() {
        tester.loadRulesAndOrders(testInput);
        tester.correct("75,97,47,61,53");
        List<int[]> orders = tester.getCorrectedOrders();
        assertEquals(97, orders.get(0)[0]);
        assertEquals(75, orders.get(0)[1]);
        assertEquals(47, orders.get(0)[2]);
        assertEquals(61, orders.get(0)[3]);
        assertEquals(53, orders.get(0)[4]);
        assertEquals(47, tester.getSumOfCorrectedMiddlePages());
    }

    @Test
    public final void givenMisorderedPageList2_canCorrectIt() {
        tester.loadRulesAndOrders(testInput);
        tester.correct("61,13,29");
        List<int[]> orders = tester.getCorrectedOrders();
        assertEquals(61, orders.get(0)[0]);
        assertEquals(29, orders.get(0)[1]);
        assertEquals(13, orders.get(0)[2]);
        assertEquals(29, tester.getSumOfCorrectedMiddlePages());
    }

    @Test
    public final void givenMisorderedPageList3_canCorrectIt() {
        tester.loadRulesAndOrders(testInput);
        tester.correct("97,13,75,29,47");
        List<int[]> orders = tester.getCorrectedOrders();
        assertEquals(97, orders.get(0)[0]);
        assertEquals(75, orders.get(0)[1]);
        assertEquals(47, orders.get(0)[2]);
        assertEquals(29, orders.get(0)[3]);
        assertEquals(13, orders.get(0)[4]);
        assertEquals(47, tester.getSumOfCorrectedMiddlePages());

    }

    @Test
    public final void givenInputList_SumOfCorrectionsIsOK() {
        tester.loadRulesAndOrders(testInput);
        tester.testPrintOrders();
        tester.correctBadPrintOrders();
        assertEquals(123, tester.getSumOfCorrectedMiddlePages());

    }

}
