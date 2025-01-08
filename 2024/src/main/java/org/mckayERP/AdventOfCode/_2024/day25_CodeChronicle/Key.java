package org.mckayERP.AdventOfCode._2024.day25_CodeChronicle;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Key
{
    int[] keyHeights;
    public Key(String[] keySpec)
    {
        readKeySpec(keySpec);
    }

    public Key(int[] keyHeights)
    {
        this.keyHeights = keyHeights;
    }

    private void readKeySpec(String[] keySpec)
    {
        keyHeights = new int[5];
        Arrays.fill(keyHeights, 5);
        keySpec[0] = "";
        for (int i=1; i<keySpec.length-1; i++) {
            for (int j=0; j<5; j++) {
                if (".".equals(keySpec[i].substring(j,j+1)))
                    keyHeights[j]--;
            }
        }

    }

    public String getHeightString()
    {
        return Arrays.stream(keyHeights).mapToObj(i -> "" + i).collect(Collectors.joining(","));
    }

    public int[] getKeyHeights()
    {
        return keyHeights;
    }
}
