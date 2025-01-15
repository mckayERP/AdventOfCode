package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.mckayERP.AdventOfCode.utilities.AOCLogger;

import java.util.*;

public class ConversionMap
{
    
    static AOCLogger logger = AOCLogger.get();
    Set<Range> ranges = new TreeSet<Range>(new RangeComparator());

    public ConversionMap() {}

    public ConversionMap(Long[] input)
    {
        ranges.add(new Range(input));
    }

    public void fillRanges() {
        Range[] ra = ranges.toArray(new Range[0]);
        if (ra[0].getInputStart() > 0)
        {
            Range newRange = new Range(0L, 0L, ra[0].inputStart);
            ranges.add(newRange);
        }
    }

    public long convert(long input)
    {
        long output = input;
        for(Range range : ranges) {
            if (range.inRange(input))
                output = range.convertToDestination(input);
        }
        return output;
    }

    public void addConversionRange(long outputStart, long inputStart, long range)
    {
        Long[] rangeData = new Long[] {outputStart, inputStart, range};
        addConversionRange(rangeData);
    }

    public void addConversionRange(Long[] rangeData)
    {
        ranges.add(new Range(rangeData));
    }

    public ConversionMap flattenWith(ConversionMap map2)
    {
        return flattenWith(map2, false);
    }

    public ConversionMap flattenWith(ConversionMap map2, boolean mask)
    {

        ConversionMap outputMap = new ConversionMap();

        TreeSet<Range> flattenedRanges = new TreeSet<>(new RangeComparator());
        flattenedRanges.addAll(ranges);
        TreeSet<Range> unmatchedRanges = new TreeSet<>(new RangeComparator());
        unmatchedRanges.addAll(map2.ranges);
        TreeSet<Range> tempHoldingRanges = new TreeSet<>(new RangeComparator());
        List<Range> obscuredRanges = new ArrayList<>();
        Range nextRange = null;
        while (!unmatchedRanges.isEmpty()) {
            TreeSet<Range> loopingRangeSet = new TreeSet<>(new RangeComparator());
            loopingRangeSet.addAll(flattenedRanges);
            nextRange = unmatchedRanges.first();
            for (Range thisRange : loopingRangeSet) {
                if (unmatchedRanges.isEmpty())
                    break;
                if (thisRange.overlaps(nextRange)) {
                    logger.logln(thisRange + " overlaps " + nextRange);
                    flattenedRanges.remove(thisRange);
                    unmatchedRanges.remove(nextRange);
                    long outputStart = thisRange.outputStart;
                    long outputEnd = thisRange.outputStart + thisRange.range - 1;
                    long inputStart = nextRange.inputStart;
                    long inputEnd = nextRange.inputStart + nextRange.range -1;
                    if (outputStart <= inputStart && outputEnd <= inputEnd) {
                        long startOffset = inputStart - outputStart;
                        long midOffset = thisRange.range-startOffset;
                        long endOffset = nextRange.range + startOffset - thisRange.range;
                        if (startOffset != 0) {
                            Range startRange = new Range(thisRange.inputStart, outputStart, startOffset);
                            addToSet(flattenedRanges, startRange, obscuredRanges, "Start", "flattened ranges.");
                        }
                        Range midRange = new Range(thisRange.inputStart+startOffset, nextRange.outputStart, midOffset);
                        addToSet(tempHoldingRanges, midRange, obscuredRanges, "Mid", "temp holding ranges.");
                        if (endOffset != 0 )
                        {
                            Range endRange = new Range(nextRange.inputStart + midOffset, nextRange.outputStart + midOffset, endOffset);
                            addToSet(unmatchedRanges, endRange, null, "End", "unmatched ranges.");
                        }
                    }
                    else if (outputStart <= inputStart && outputEnd > inputEnd) {
                        long startOffset = inputStart - outputStart;
                        long midOffset = nextRange.range;
                        long endOffset = thisRange.range - startOffset - midOffset;
                        if (startOffset != 0) {
                            Range startRange = new Range(thisRange.inputStart, outputStart, startOffset);
                            addToSet(flattenedRanges, startRange, obscuredRanges, "Start", "flattened ranges.");
                        }
                        Range midRange = new Range(thisRange.inputStart + startOffset, nextRange.outputStart, midOffset);
                        addToSet(tempHoldingRanges, midRange, obscuredRanges, "Mid", "temp holding ranges.");
                        Range endRange = new Range(thisRange.inputStart + startOffset + midOffset, thisRange.outputStart+startOffset+midOffset, endOffset);
                        addToSet(flattenedRanges, endRange, obscuredRanges, "End", "flattened ranges.");
                    }
                    else if (outputStart > inputStart && outputEnd <= inputEnd) {
                        long startOffset = outputStart-inputStart;
                        long midOffset = thisRange.range;
                        long endOffset = nextRange.range - startOffset - thisRange.range;
                        Range startRange = new Range(nextRange.inputStart, nextRange.outputStart, startOffset);
                        addToSet(unmatchedRanges, startRange, obscuredRanges, "Start", "unmatched ranges.");
                        Range midRange = new Range(thisRange.inputStart, nextRange.outputStart+startOffset, midOffset);
                        addToSet(tempHoldingRanges, midRange, obscuredRanges, "Mid", "temp holding ranges.");
                        if (endOffset != 0)
                        {
                            Range endRange = new Range(nextRange.inputStart + startOffset + midOffset, nextRange.outputStart + startOffset + midOffset, endOffset);
                            addToSet(unmatchedRanges, endRange, null, "End", "unmatched ranges.");
                        }
                    }
                    else { // outputStart > inputStart && outputEnd > inputEnd
                        long startOffset = outputStart - inputStart;
                        long midOffset = nextRange.range - startOffset;
                        long endOffset = thisRange.range - midOffset;
                        Range startRange = new Range(nextRange.inputStart, nextRange.outputStart, startOffset);
                        addToSet(unmatchedRanges, startRange, obscuredRanges, "Start", "unmatched ranges.");
                        Range midRange = new Range(thisRange.inputStart, nextRange.outputStart + startOffset, midOffset);
                        Range endRange = new Range(thisRange.inputStart + midOffset, thisRange.outputStart+midOffset, endOffset);
                        addToSet(tempHoldingRanges, midRange, obscuredRanges, "Mid", "temp holding ranges.");
                        addToSet(flattenedRanges, endRange, obscuredRanges, "End", "flattened ranges.");
                    }
                    nextRange = null;
                    break;
                }
            }
            if (nextRange != null)
            {
                unmatchedRanges.remove(nextRange);
                if (mask) {
                    logger.logln(nextRange + " not masked. Removed from list.");
                }
                else
                {
                    logger.logln(nextRange + " not affected. Moved to flattened list.");
                    tempHoldingRanges.add(nextRange);
                }
            }
        }
        if (!obscuredRanges.isEmpty())
        {
            logger.logln("!! Obscured ranges !!");
            obscuredRanges.forEach(or -> logger.logln(or.toString()));
            logger.logln("");
        }
        outputMap.ranges.addAll(flattenedRanges);
        outputMap.ranges.addAll(tempHoldingRanges);
        logger.logln("** Flattened ranges after this map: ");
        outputMap.ranges.forEach(r -> logger.logln(r.toString()));
        return outputMap;
    }

    private static void addToSet(TreeSet<Range> tempHoldingRanges, Range range, List<Range> obscuredRanges, String rangeName, String rangeType)
    {
        logger.logln("  " + rangeName + " range "+ range + " added to " + rangeType);
        if (!tempHoldingRanges.add(range))
        {
            logger.logln("  Range obscured!!");
            addToObscured(range, obscuredRanges);
        }
    }

    private static void addToObscured(Range range, List<Range> obscuredRanges)
    {
        if (obscuredRanges != null)
            obscuredRanges.add(range);
    }

    public TreeSet<Range> mask(ConversionMap mapToBeMasked)
    {
        TreeSet<Range> maskedRanges = new TreeSet<>(new RangeComparator(true));
        maskedRanges.addAll(flattenWith(mapToBeMasked, true).ranges);
        return maskedRanges;
    }
}
