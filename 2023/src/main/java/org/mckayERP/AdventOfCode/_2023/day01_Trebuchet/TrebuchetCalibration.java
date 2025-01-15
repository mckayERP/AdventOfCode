package org.mckayERP.AdventOfCode._2023.day01_Trebuchet;

import org.mckayERP.AdventOfCode.utilities.AOCLogger;
import org.mckayERP.AdventOfCode.utilities.stringManipulation.AOCStrings;

import java.util.ArrayList;
import java.util.List;

public class TrebuchetCalibration
{
    AOCLogger logger = AOCLogger.get();

    Object[][] directConversions = {
            {"1",       1 },
            {"2",       2 },
            {"3",       3 },
            {"4",       4 },
            {"5",       5 },
            {"6",       6 },
            {"7",       7 },
            {"8",       8 },
            {"9",       9 }
    };

    Object[][] extendedConversions = {
            {"1",       1 },
            {"2",       2 },
            {"3",       3 },
            {"4",       4 },
            {"5",       5 },
            {"6",       6 },
            {"7",       7 },
            {"8",       8 },
            {"9",       9 },
            {"one",     1 },
            {"two",     2 },
            {"three",   3 },
            {"four",    4 },
            {"five",    5 },
            {"six",     6 },
            {"seven",   7 },
            {"eight",   8 },
            {"nine",    9 }
    };

    public int getSumOfCalibrationValues(String[] inputs)
    {
        int sum = 0;
        int calibration = 0;
        for (String input : inputs) {
            Integer[] digits = convertNumberString(input, directConversions);
            calibration = digits[0]*10 + digits[digits.length-1];
            sum += calibration;
        }
        return sum;
    }

    public int getSumOfExpandedCalibrationValues(String[] inputs) {
        int sum = 0;
        int calibration = 0;
        for (String input : inputs) {
            Integer[] digits = convertNumberString(input, extendedConversions);
            calibration = digits[0]*10 + digits[digits.length-1];
            sum += calibration;
        }
        return sum;

    }

    private Integer[] convertNumberString(String input, Object[][] conversions)
    {
        logger.log(input + " -> ");
        List<Integer> output = new ArrayList<>();
        output.add(findFirstDigit(input, conversions));
        output.add(findLastDigit(input, conversions));
        logger.logln("");
        Integer[] a = new Integer[output.size()];
        return output.toArray(a);
    }

    private Integer findLastDigit(String input, Object[][] conversionsStringToInt )
    {
        int endPosition = input.length();
        int endOffset = 0;
        boolean hasLast = false;
        while ((endOffset < input.length()) && !hasLast)
        {
            for (Object[] convert : conversionsStringToInt)
            {
                if (input.substring(0, endPosition - endOffset).endsWith((String) convert[0]))
                {
                    logger.log("" + convert[1]);
                    return (Integer) convert[1];
                }
            }
            endOffset++;
        }
        return 0;
    }

    private Integer findFirstDigit(String input, Object[][] conversionsStringToInt)
    {
        int position = 0;
        boolean hasFirst = false;
        while ((position < input.length()) && !hasFirst)
        {
            for (Object[] convert : conversionsStringToInt)
            {
                if (input.substring(position).startsWith((String) convert[0]))
                {
                    logger.log("" + convert[1]);
                    return (Integer) convert[1];
                }
            }
            position++;
        }
        return 0;
    }
}
