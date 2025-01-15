package org.mckayERP.AdventOfCode.utilities.stringManipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of string utilities that help extract data from a string.
 */
public class AOCStrings
{
    /**
     * Extracts single digits to an array of Integers. Consecutive digits eg. "ab123cda" will be
     * treated a individual numbers eg. {1,2,3}
     * Will throw an IllegalArgumentException if the input string is empty or null.
     * The output array will be empty if no digits were found.
     * @param line
     * @return
     */
    public static Integer[] extractSingleDigits(String line)
    {
        if (line == null || line.isEmpty())
            throw new IllegalArgumentException("Line can't be null or empty.");

        Pattern p = Pattern.compile("\\D*(\\d)\\D*");
        Matcher m = p.matcher(line);
        List<Integer> result = new ArrayList<>();
        while (m.find()) {
            for (int col=0; col < m.groupCount(); col++)
            {
                result.add(Integer.parseInt(m.group(col+1)));
            }
        }

        Integer[] a = new Integer[result.size()];
        return result.toArray(a);
    }

    /**
     * Extracts integers from a string to an array of Integers. Consecutive digits eg. "ab123cda" will be
     * treated as a single integer 123.
     * Will throw an IllegalArgumentException if the input string is empty or null.
     * The output array will be empty if no digits were found.
     * @param line
     * @return and array of Integer
     */
    public static Integer[] extractIntegers(String line)
    {
        if (line == null || line.isEmpty())
            throw new IllegalArgumentException("Line can't be null or empty.");

        Pattern p = Pattern.compile("\\D*(\\d+)\\D*");
        Matcher m = p.matcher(line);
        List<Integer> result = new ArrayList<>();
        while (m.find()) {
            for (int col=0; col < m.groupCount(); col++)
            {
                result.add(Integer.parseInt(m.group(col+1)));
            }
        }

        Integer[] a = new Integer[result.size()];
        return result.toArray(a);
    }

    /**
     * Extracts Long numbers from a string to an array of Longs. Consecutive digits eg. "ab123cda" will be
     * treated as a single long 123.
     * Will throw an IllegalArgumentException if the input string is empty or null.
     * The output array will be empty if no digits were found.
     * @param line
     * @return an array of Long values
     */
    public static Long[] extractLongs(String line)
    {
        if (line == null || line.isEmpty())
            throw new IllegalArgumentException("Line can't be null or empty.");

        Pattern p = Pattern.compile("\\D*(\\d+)\\D*");
        Matcher m = p.matcher(line);
        List<Long> result = new ArrayList<>();
        while (m.find()) {
            for (int col=0; col < m.groupCount(); col++)
            {
                result.add(Long.parseLong(m.group(col+1)));
            }
        }

        Long[] a = new Long[result.size()];
        return result.toArray(a);
    }

}
