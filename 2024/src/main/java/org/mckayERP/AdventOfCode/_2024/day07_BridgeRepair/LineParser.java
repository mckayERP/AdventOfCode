package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

public class LineParser
{
    long expectedResult = 0;
    int [] values;
    public void parse(String input)
    {
        if (input.contains(":"))
        {
            String[] numbers = input.split(":");
            String firstNumber = numbers[0];
            expectedResult = Long.parseLong(firstNumber);
            String[] valueStrings = numbers[1].trim().split("\\D+");
            int i = 0;
            values = new int[valueStrings.length];
            for (String value : valueStrings)
                values[i++] = Integer.parseInt(value);


        }
    }

    public long getExpectedResult()
    {
        return expectedResult;
    }

    public int[] getValues()
    {
        return values;
    }
}
