package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class AntennaFinder
{
    List<Antenna> antennas;
    int rowCount;
    int colCount;

    public AntennaFinder(String[] input)
    {
        rowCount = input.length;
        colCount = input[0].length();
        antennas = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                char spot = input[row].toCharArray()[col];
                if (spot != '.')
                {
                    antennas.add(new Antenna(new Position(row, col), String.valueOf(spot)));
                }
            }
        }
    }

    public List<Antenna> getAntennas() {
        return antennas;
    }

}
