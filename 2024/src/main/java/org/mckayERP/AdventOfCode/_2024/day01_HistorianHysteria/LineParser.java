package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineParser
{
    public int[] parse(String line)
    {
        if (line == null || line.isEmpty())
            throw new IllegalArgumentException("Line can't be null or empty.");

        Pattern p = Pattern.compile("\\D*(\\d+)\\D*(\\d+)\\D*");
        Matcher m = p.matcher(line);
        int[] result = {-1,-1};
        if (m.find()) {
            for (int col=0; col < 2; col++)
            {
                result[col] = Integer.parseInt(m.group(col+1));
            }
        }
        else
            throw new IllegalArgumentException("Input line did not contain two numbers.");

        return result;
    }
}
