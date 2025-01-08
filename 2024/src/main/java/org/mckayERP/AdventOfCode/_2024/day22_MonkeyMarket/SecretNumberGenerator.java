package org.mckayERP.AdventOfCode._2024.day22_MonkeyMarket;

import java.util.*;

public class SecretNumberGenerator
{

    Map<String, Integer> mapOfChangesToSum = new HashMap();
    public long getNextSecret(long secret)
    {
        long step1Result = prune(mix(secret*64, secret));
        long step2Result = prune(mix(step1Result >> 5, step1Result));;
        long step3Result = prune(mix(2048*step2Result, step2Result));
        return step3Result;
    }

    public long mix(long secret, long value) {
        return secret ^ value;
    }

    public long prune(long secret) {
        return secret % 16777216;
    }

    public long getXthSecret(long initialSecret, int x)
    {
        List<String> changeStringsSeen = new ArrayList<>();
        Integer[] changes = new Integer[4];
        long xthSecretNumber = initialSecret;
        for (int i=0; i<x; i++) {
            int previousLastDigit = (int) xthSecretNumber % 10;
            xthSecretNumber = getNextSecret(xthSecretNumber);
            int nextLastDigit = (int) xthSecretNumber % 10;
            int change = nextLastDigit - previousLastDigit;
            addToChanges(change, changes);
            if (i >= 3)
            {
                String changeString = convertChangesToString(changes);
                if (!changeStringsSeen.contains(changeString))
                {
                    changeStringsSeen.add(changeString);
                    mapOfChangesToSum.put(changeString, mapOfChangesToSum.getOrDefault(changeString, 0) + nextLastDigit);
                }
            }
        }
        return xthSecretNumber;
    }

    public void addToChanges(int change, Integer[] changes)
    {
        for (int i = changes.length-1; i>0; i--) {
            changes[i] = changes[i-1];
        }
        changes[0] = change;
    }

    public String convertChangesToString(Integer[] changes) {
        return "" + changes[3] + "," + changes[2] + "," + changes[1] + "," + changes[0];
    }

    public long getTotalSum(String[] inputs, int numberToGenerate)
    {
        long[] initialSecrets = new long[inputs.length];
        int counter = 0;
        for (String input : inputs) {
            initialSecrets[counter++] = Long.parseLong(input.trim());
        }
        long[] finalSecrets = new long[initialSecrets.length];
        counter = 0;
        for (long secret : initialSecrets) {
            finalSecrets[counter++] = getXthSecret(secret, numberToGenerate);
        }

        return Arrays.stream(finalSecrets).sum();

    }

    public Integer getSumForChange(String changeString)
    {
        return mapOfChangesToSum.getOrDefault(changeString, null);
    }

    public int getMaximumNumberOfBananas()
    {
        return mapOfChangesToSum.values().stream().max(Integer::compareTo).get();
    }

}
