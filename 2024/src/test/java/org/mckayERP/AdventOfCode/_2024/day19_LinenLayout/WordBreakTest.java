package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

public class WordBreakTest
{
    public static boolean wordBreak(String s, List<String> dictionary) {
        // create a dp table to store results of subproblems
        // value of dp[i] will be true if string s can be segmented
        // into dictionary words from 0 to i.
        boolean[] dp = new boolean[s.length() + 1];

        // dp[0] is true because an empty string can always be segmented.
        dp[0] = true;

        for(int i = 0; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && dictionary.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public final void testWordSearch()
    {
        PatternsAndDesiredDesignReader reader;
        reader = new PatternsAndDesiredDesignReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(TowelPatternMatcherTest.class, "testInput.txt"));

        List<String> dict = reader.getPatterns();

        if (wordBreak("urwrggwgbrrwggwuubrrwwugbgbubwbuuugwbguggubrgwwrubub", dict))
        {
            System.out.println("Yes");
        } else
        {
            System.out.println("No");
        }
    }
}
