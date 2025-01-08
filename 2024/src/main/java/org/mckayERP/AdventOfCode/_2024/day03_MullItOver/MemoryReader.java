package org.mckayERP.AdventOfCode._2024.day03_MullItOver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoryReader
{
    public MemoryReader setEnablersActive(boolean enablersActive)
    {
        this.enablersActive = enablersActive;
        return this;
    }

    boolean enablersActive = false;
    boolean enabled = true;
    public DataPoint[] parseString(String data)
    {
        Pattern p;
        if (enablersActive)
            p = Pattern.compile("[do\\(\\)|don't\\(\\)]?.*?mul\\((\\d+),(\\d+)\\)");
        else
            p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        Matcher m = p.matcher(data);
        List<DataPoint> list = new ArrayList<>();

        while(m.find()) {
            if (enablersActive)
            {
                if (m.group(0).contains("don't()"))
                    enabled = false;
                else if (m.group(0).contains("do()"))
                    enabled = true;
            }
            if (!enablersActive || enabled)
            {
                DataPoint d = new DataPoint();
                d.X = Integer.parseInt(m.group(1));
                d.Y = Integer.parseInt(m.group(2));
                list.add(d);
            }
        }
        return list.stream().filter(Objects::nonNull).toArray(DataPoint[]::new);
    }

    public long getProductSum(String data)
    {

        long sum = 0;
        DataPoint[] p = parseString(data);
        for (DataPoint dataPoint : p)
        {
            sum += (long) dataPoint.X * dataPoint.Y;
        }
        return sum;
    }
}
