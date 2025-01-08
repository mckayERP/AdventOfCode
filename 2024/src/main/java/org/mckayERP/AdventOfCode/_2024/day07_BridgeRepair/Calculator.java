package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

public class Calculator
{
    int[] values;

    Operation[] ops;
    public void setValues(int[] ints)
    {
        values = ints;
    }

    public void setOperations(OperatorType[] opTypes)
    {
        if (opTypes == null)
            throw new IllegalArgumentException("opType array can't be null");

        int i=0;
        ops = new Operation[opTypes.length];
        for (OperatorType opType : opTypes)
            ops[i++] = new Operation(opType);
    }

    public long calc()
    {
        long result = values[0];
        for (int i = 1; i < values.length; i++)
        {
            int b = values[i];
            result = ops[i-1].calc(result, b);
        }
        return result;
    }
}
