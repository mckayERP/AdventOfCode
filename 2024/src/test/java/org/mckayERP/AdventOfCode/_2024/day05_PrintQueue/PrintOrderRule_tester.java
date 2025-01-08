package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrintOrderRule_tester
{
    @Test
    public final void constructor(){
        PrintOrderRule rule = new PrintOrderRule(1,2);
    }

    @Test
    public final void givenValueOfZero_throwsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            PrintOrderRule rule = new PrintOrderRule(0,0);
        });
    }

    @Test
    public final void givenNegativeValue_throwsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            PrintOrderRule rule = new PrintOrderRule(0,-1);
        });
    }

    @Test
    public final void givenPages_canMatch() {
        PrintOrderRule rule = new PrintOrderRule(47,53);
        rule.setRule(47,53);
        assertTrue(rule.matches(47,53));
    }
}
