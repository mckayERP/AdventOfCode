package org.mckayERP.AdventOfCode._2024.day02_RedNoseReports;

import java.util.Arrays;

public class RuleEngine
{
    static final int MAX_STEP_SIZE = 3;

    public Long countOfSafeReports(String[] reports, boolean withDampener) {

        return Arrays.stream(reports).filter(report -> test(report, withDampener)).count();

    }

    public boolean test(String data)
    {
        return test(data, false);
    }

    public boolean test(String data, boolean withDampener)
    {

        String[] s = data.split("\\D+");
        Integer[] numbers = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            numbers[i] = Integer.parseInt(s[i]);
        }

        boolean nonDampenedResult = runTest(numbers);
        if (nonDampenedResult)
            return true;
        else
            if (!withDampener)
                return false;

        for (int i = 0; i < numbers.length; i++){

            Integer[] dampenedNumbers = removeElement(numbers, i);
            if (runTest(dampenedNumbers))
                return true;

        }


        return false;
    }

    private Integer[] removeElement(Integer[] numbers, int indexToRemove)
    {
        Integer[] newNumbers = new Integer[numbers.length-1];
        int j = 0;
        for (int i=0; i < numbers.length; i++)
        {
            if (i != indexToRemove)
                newNumbers[j++] = numbers[i];
        }
        return newNumbers;
    }

    private boolean runTest(Integer[] numbers) {

        boolean increasing = true;
        boolean decreasing = true;
        boolean safeStepSize = true;
        int minStepSize = 1;
        int maxStepSize = 2;

        for (int i = 0; i < numbers.length-1; i++) {

            increasing = increasing && numbers[i] < numbers[i+1];
            decreasing = decreasing && numbers[i] > numbers[i+1];
            safeStepSize = safeStepSize & Math.abs(numbers[i] - numbers[i+1]) <= MAX_STEP_SIZE ;
        }

        return (increasing || decreasing) && safeStepSize;

    }
}
