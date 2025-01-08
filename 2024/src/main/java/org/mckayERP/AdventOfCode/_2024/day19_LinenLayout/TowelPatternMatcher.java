package org.mckayERP.AdventOfCode._2024.day19_LinenLayout;

import org.mckayERP.AdventOfCode.utilities.AOCLogger;

import java.util.*;

public class TowelPatternMatcher
{
    AOCLogger logger = AOCLogger.get();

    private List<String> desiredDesigns;

    private final Map<String, List<String>> patternsByFirstColour = new LinkedHashMap<>();
    private final Map<String, Long> countOfSolutions = new LinkedHashMap<>();

    private List<String> unmatchableStrings = new ArrayList<>();

    public void setAvailablePatterns(List<String> patterns)
    {
        patterns.forEach(p -> {
            String firstColour = p.substring(0,1);
            List<String> patternsWithThisFirstColour = patternsByFirstColour.getOrDefault(firstColour, new ArrayList<>());
            patternsWithThisFirstColour.add(p);
            patternsByFirstColour.putIfAbsent(firstColour, patternsWithThisFirstColour);
            patternsByFirstColour.get(firstColour).sort(Comparator.comparingInt(String::length).reversed());

        });
    }

    public void setDesiredDesigns(List<String> desiredDesigns)
    {
        this.desiredDesigns = desiredDesigns;
    }

    public List<String> getPatternsStartingWith(String colour)
    {
        return patternsByFirstColour.get(colour);
    }

    public long canMatch(String patternToMatch)
    {
        logger.logln("Matching " + patternToMatch);

        long thisCount = countOfSolutions.getOrDefault(patternToMatch, 0L);
        if (thisCount > 0)
            return thisCount;

        boolean knownCantMatch = unmatchableStrings.stream().anyMatch(unmatchable->unmatchable.equals(patternToMatch));
        if (knownCantMatch) {
            logger.logln(patternToMatch + " is known unmatchable.");
            return 0;
        }

        logger.log(patternToMatch + " -> ");
        String[] pattern = new String[]{patternToMatch};
        long[] countOfMatches = new long[] {0L};
        String colourToMatch = pattern[0].substring(0, 1);
        List<String> availablePatterns = getPatternsStartingWith(colourToMatch);
        if (availablePatterns == null)
        {
            logger.logln(" but there is nothing available that starts with the same colour.");
        } else
        {
            thisCount = availablePatterns.stream().filter(ap ->
            {
                if (ap.length() <= pattern[0].length() && pattern[0].startsWith(ap))
                {
                    logger.log(ap + " ");
                    String nextPattern = pattern[0].substring(ap.length());
                    if (nextPattern.isEmpty())
                    {
                        logger.logln( " found full match!");
                        countOfMatches[0]++;
                        return true;
                    }
                    else
                    {
                        long numberOfMatches = canMatch(nextPattern);
                        if (numberOfMatches == 0)
                        {
                            logger.log(pattern[0] + " <- ");
                        }
                        else {
                            countOfMatches[0] += numberOfMatches;
                        }
                        return numberOfMatches > 0;
                    }
                }
                return false;
            }).count();
            if (countOfMatches[0] == 0)
            {
                unmatchableStrings.add(pattern[0]);
                logger.logln(" no match.");
            }
            else {
                countOfSolutions.putIfAbsent(pattern[0], countOfMatches[0]);
            }
        }
        return countOfMatches[0];

    }

    public long getCountOfPossibleDesigns()
    {
        return desiredDesigns.stream().filter(d -> {
            if (canMatch(d) > 0) {
                System.out.println("CAN match " + d);
                return true;
            }
            else
            {
                System.out.println("CANNOT match " + d);
                return false;
            }
        }).count();
    }

    public long getNumberOfDifferentWaysToMakePossibleDesigns()
    {
        return desiredDesigns.stream().mapToLong(d -> canMatch(d)).sum();
    }

}
