package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

public class Operation
{
    private OperatorType operator;
    public Operation(OperatorType operatorType)
    {
        operator = operatorType;
    }

    public long calc(long a, long b) {
        return switch (operator) {
            case ADD -> a + b;
            case MULTIPLY -> a * b;
            case CONCATENATE -> concatenate(a,b);
            default -> throw new IllegalArgumentException("Unknown operation type; " + operator);
        };
    }

    private long concatenate(long a, long b)
    {
        return Long.parseLong("" + a + b);
    }
}
