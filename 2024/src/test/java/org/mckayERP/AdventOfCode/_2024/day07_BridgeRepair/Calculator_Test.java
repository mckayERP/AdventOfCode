package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mckayERP.AdventOfCode._2024.day07_BridgeRepair.OperatorType.ADD;
import static org.mckayERP.AdventOfCode._2024.day07_BridgeRepair.OperatorType.MULTIPLY;

public class Calculator_Test
{
    @Test
    public final void testCalculations() {
        Calculator calc = new Calculator();
        calc.setValues(new int[] {11, 6, 16, 20});
        calc.setOperations(new OperatorType[] {ADD, MULTIPLY, ADD});
        assertEquals(292, calc.calc());
    }

    @Test
    public final void testMod3Generation() {

        int numOfOps = 4;
        int numbOfCombs = BigDecimal.valueOf(3).pow(numOfOps).intValue();
        for (int i = 0; i<numbOfCombs; i++) {
            for (int j=0; j<numOfOps; j++) {
                int denominator = BigDecimal.valueOf(3).pow(j).intValue();
                System.out.print("" + Math.floorMod(i/denominator, 3));
            }
            System.out.println();
        }
    }
}
