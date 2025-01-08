package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import java.math.BigDecimal;

import static org.mckayERP.AdventOfCode._2024.day07_BridgeRepair.OperatorType.ADD;
import static org.mckayERP.AdventOfCode._2024.day07_BridgeRepair.OperatorType.MULTIPLY;

public class Solver
{

    boolean includeConcatenation = false;

    public Solver() {

    }

    public Solver(boolean concatenate) {
        includeConcatenation = concatenate;
    }

    long expectedResult;
    int[] values;

    public Solver setResult(long result)
    {
        this.expectedResult = result;
        return this;
    }

    public Solver setValues(int[] ints)
    {
        this.values = ints;
        return this;
    }

    public boolean canBeSolved()
    {
        if (values.length == 1)
            return expectedResult == values[0];

        if (includeConcatenation)
            return solveWithConcatenation();
        else
            return solveWithoutConcatenation();
    }

    private boolean solveWithoutConcatenation()
    {
        Calculator c = new Calculator();
        c.setValues(values);
        int numOpsRequired = values.length -1;
        OperatorType[] opTypes = new OperatorType[numOpsRequired];
        int numberOfCombinations = 1 << numOpsRequired;
        for (int i = 0; i < numberOfCombinations; i++)
        {
            for (int j = 0; j < numOpsRequired; j++)
            {
                opTypes[j] = ((i >> j & 1) == 0) ? ADD : MULTIPLY;
            }
            c.setOperations(opTypes);
            if (expectedResult == c.calc())
                return true;
        }
        return false;
    }

    private boolean solveWithConcatenation()
    {
        Calculator c = new Calculator();
        c.setValues(values);
        int numOpsRequired = values.length -1;
        OperatorType[] opTypes = new OperatorType[numOpsRequired];
        int numberOfCombinations = BigDecimal.valueOf(3).pow(numOpsRequired).intValue();
        for (int i = 0; i < numberOfCombinations; i++)
        {
            for (int j=0; j<numOpsRequired; j++) {
                int denominator = BigDecimal.valueOf(3).pow(j).intValue();
                opTypes[j] = OperatorType.values()[Math.floorMod(i/denominator, 3)];
            }
            c.setOperations(opTypes);
            if (expectedResult == c.calc())
                return true;
        }
        return false;
    }

}
