package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest
{
    @Test
    public final void solverTest() {

        Solver solver = new Solver();
        boolean result = solver.setResult(292)
                .setValues(new int[] {11,6,16,20})
                .canBeSolved();
        assertTrue(result);

    }
}
