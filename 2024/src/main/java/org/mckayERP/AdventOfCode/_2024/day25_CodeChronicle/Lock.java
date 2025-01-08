package org.mckayERP.AdventOfCode._2024.day25_CodeChronicle;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Lock
{
    public Lock(String[] lockSpec)
    {
        readLockSpec(lockSpec);
    }

    public Lock(int[] pinHeights)
    {
        this.pinHeights = pinHeights;
    }

    private void readLockSpec(String[] lockSpec)
    {
        pinHeights = new int[5];
        lockSpec[0] = "";
        for (int i=1; i<lockSpec.length; i++) {
            for (int j=0; j<5; j++) {
                if ("#".equals(lockSpec[i].substring(j,j+1)))
                    pinHeights[j]++;
            }
        }
    }

    public int[] getPinHeights()
    {
        return pinHeights;
    }

    public void setPinHeights(int[] pinHeights)
    {
        this.pinHeights = pinHeights;
    }

    int[] pinHeights;
    public String getPatternString()
    {
        return Arrays.stream(pinHeights).mapToObj(i -> "" + i).collect(Collectors.joining(","));
    }

    public boolean keyFits(Key key)
    {
        int[] keyHeights = key.getKeyHeights();
        for (int i=0; i<pinHeights.length; i++) {
            if (pinHeights[i] + keyHeights[i] > 5)
                return false;
        }
        return true;
    }
}
