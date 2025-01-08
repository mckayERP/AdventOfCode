package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Operation_Test
{
    @Test
    public final void doOperationsMultiply_test() {
        Operation mult = new Operation(OperatorType.MULTIPLY);
        assertEquals(6, mult.calc(2,3));
    }

    @Test
    public final void doOperationsAdd_test() {
        Operation add = new Operation(OperatorType.ADD);
        assertEquals(5, add.calc(2,3));
    }

    @Test
    public final void calcWiththreeValues() {
        long expectedResult = 3267;
        int[] values = new int[] {81, 40, 27};
        Operation[] ops = new Operation[] {new Operation(OperatorType.ADD), new Operation(OperatorType.MULTIPLY)};

        long result = values[0];
        for (int i = 1; i < values.length; i++)
        {
            int b = values[i];
            result = ops[i-1].calc(result, b);
        }
        assertEquals(expectedResult, result);
    }

    @Test
    public final void checkConcatenationResult() {
        Operation concat = new Operation(OperatorType.CONCATENATE);
        assertEquals(867, concat.calc(86, 7));
    }
}
