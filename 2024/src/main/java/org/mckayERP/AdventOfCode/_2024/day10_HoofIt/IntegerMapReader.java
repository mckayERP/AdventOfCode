package org.mckayERP.AdventOfCode._2024.day10_HoofIt;

import org.mckayERP.AdventOfCode.utilities.MapReader;
import org.mckayERP.AdventOfCode.utilities.XYMap;

public class IntegerMapReader implements MapReader<Integer>
{
    XYMap<Integer> map;
    int rowCount;
    int colCount;

    public IntegerMapReader(String[] input)
    {
        readInput(input);
    }

    @Override
    public void readInput(String[] input) {
        rowCount = input.length;
        colCount = input[0].length();
        Integer[][] mapData = new Integer[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                try
                {
                    mapData[row][col] = Integer.parseInt(String.valueOf((char) input[row].toCharArray()[col]));
                }
                catch (NumberFormatException e) {
                    mapData[row][col] = -1;
                }
            }
        }
        map = new XYMap<>(mapData);
    }

    @Override
    public XYMap<Integer> getXYMap() {
        return map;
    }

}
