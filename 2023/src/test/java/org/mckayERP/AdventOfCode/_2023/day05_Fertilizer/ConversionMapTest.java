package org.mckayERP.AdventOfCode._2023.day05_Fertilizer;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversionMapTest
{
    @Test
    public final void testMapConstructor() {
        ConversionMap map = new ConversionMap();
    }

    @Test
    public final void testMapCanBeSet() {
        ConversionMap map = new ConversionMap();
        map.addConversionRange(50, 98, 2);
        assertEquals(50, map.convert(98));
        assertEquals(51, map.convert(99));
        assertEquals(100, map.convert(100));
        assertEquals(101, map.convert(101));
    }

    @Test
    public final void testNumberNotInRangeIsPassedThrough() {
        ConversionMap map = new ConversionMap();
        assertEquals(10, map.convert(10));
    }

    @Test
    public final void testMultipleRangesInMap() {
        ConversionMap map = new ConversionMap();
        map.addConversionRange(50, 98, 2);
        map.addConversionRange(52, 50, 48);
        assertEquals(52, map.convert(50));
        assertEquals(55, map.convert(53));
        assertEquals(50, map.convert(98));
        assertEquals(51, map.convert(99));
        assertEquals(100, map.convert(100));
        assertEquals(101, map.convert(101));

    }

    @Test
    public final void testCanflattenTwoNonOverlappingMaps() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 60, 10);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(2, flattenMap.ranges.size());
    }

    @Test
    public final void testCanFlattenExactlyOverlappingMaps()
    {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 50, 10);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(1, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(30, rangeOne.outputStart);
        assertEquals(10, rangeOne.range);
    }


    @Test
    public final void testCanFlattenTwoOverlappingMaps() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 55, 10);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(3, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        Range rangeThree = flattenMap.ranges.toArray(new Range[0])[2];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(50, rangeOne.outputStart);
        assertEquals(5, rangeOne.range);
        assertEquals(15, rangeTwo.inputStart);
        assertEquals(30, rangeTwo.outputStart);
        assertEquals(5, rangeTwo.range);
        assertEquals(60, rangeThree.inputStart);
        assertEquals(35, rangeThree.outputStart);
        assertEquals(5, rangeThree.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsWithSameOutputEndInputEnd() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 55, 5);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(2, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(50, rangeOne.outputStart);
        assertEquals(5, rangeOne.range);
        assertEquals(15, rangeTwo.inputStart);
        assertEquals(30, rangeTwo.outputStart);
        assertEquals(5, rangeTwo.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsWhereTheFirstFullyOverlapsTheSecond() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 20);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 55, 10);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(3, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        Range rangeThree = flattenMap.ranges.toArray(new Range[0])[2];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(50, rangeOne.outputStart);
        assertEquals(5, rangeOne.range);
        assertEquals(15, rangeTwo.inputStart);
        assertEquals(30, rangeTwo.outputStart);
        assertEquals(10, rangeTwo.range);
        assertEquals(25, rangeThree.inputStart);
        assertEquals(65, rangeThree.outputStart);
        assertEquals(5, rangeThree.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsWhereTheSecondFullyOverlapsTheFirst() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 45, 20);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(3, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        Range rangeThree = flattenMap.ranges.toArray(new Range[0])[2];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(35, rangeOne.outputStart);
        assertEquals(10, rangeOne.range);
        assertEquals(45, rangeTwo.inputStart);
        assertEquals(30, rangeTwo.outputStart);
        assertEquals(5, rangeTwo.range);
        assertEquals(60, rangeThree.inputStart);
        assertEquals(45, rangeThree.outputStart);
        assertEquals(5, rangeThree.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsWhereTheSecondJustOverlapsTheFirst() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(50, 10, 10);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(30, 31, 20);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(3, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        Range rangeThree = flattenMap.ranges.toArray(new Range[0])[2];
        assertEquals(10, rangeOne.inputStart);
        assertEquals(49, rangeOne.outputStart);
        assertEquals(1, rangeOne.range);
        assertEquals(11, rangeTwo.inputStart);
        assertEquals(51, rangeTwo.outputStart);
        assertEquals(9, rangeTwo.range);
        assertEquals(31, rangeThree.inputStart);
        assertEquals(30, rangeThree.outputStart);
        assertEquals(19, rangeThree.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsFromTestInput() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(52, 50, 48);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(37, 52, 2);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(2, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        assertEquals(50, rangeOne.inputStart);
        assertEquals(37, rangeOne.outputStart);
        assertEquals(2, rangeOne.range);
        assertEquals(52, rangeTwo.inputStart);
        assertEquals(54, rangeTwo.outputStart);
        assertEquals(46, rangeTwo.range);
    }

    @Test
    public final void testCanFlattenTwoOverlappingMapsFromTestInput2() {
        ConversionMap map1 = new ConversionMap();
        map1.addConversionRange(39, 0, 15);
        ConversionMap map2 = new ConversionMap();
        map2.addConversionRange(1, 0, 69);
        ConversionMap flattenMap = map1.flattenWith(map2);
        assertEquals(2, flattenMap.ranges.size());
        Range rangeOne = flattenMap.ranges.toArray(new Range[0])[0];
        Range rangeTwo = flattenMap.ranges.toArray(new Range[0])[1];
        assertEquals(0, rangeOne.inputStart);
        assertEquals(40, rangeOne.outputStart);
        assertEquals(15, rangeOne.range);
        assertEquals(54, rangeTwo.inputStart);
        assertEquals(55, rangeTwo.outputStart);
        assertEquals(15, rangeTwo.range);
    }


    @Test
    public final void givenSampleInput_canFlattenMultipleRanges() {
        AlmanacReader reader = new AlmanacReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        Converter converter = new Converter();
        converter.setMaps(reader.getMaps());
        converter.flattenMaps();
    }

}
