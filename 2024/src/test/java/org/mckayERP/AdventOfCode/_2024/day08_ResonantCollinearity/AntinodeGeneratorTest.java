package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.Bounds;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AntinodeGeneratorTest
{

    Bounds mapBounds;

    @BeforeEach
    public final void setup() {
        mapBounds = new Bounds(new Position(0,0), new Position(12,12));
    }

    @Test
    public final void testConstructor() {

        Antenna a = new Antenna(new Position(3,4), "A");
        Antenna b = new Antenna(new Position(5, 5), "A");
        Antenna c = new Antenna(new Position(2, 7), "A");
        Antenna d = new Antenna(new Position(6, 3), "B");
        Antenna e = new Antenna(new Position(8, 6), "B");
        List<Antenna> antennaList = new ArrayList<>();
        antennaList.add(a);
        antennaList.add(b);
        antennaList.add(c);
        antennaList.add(d);
        antennaList.add(e);
        Bounds mapBounds = new Bounds(new Position(-10,-10), new Position(20,20));
        AntinodeGenerator generator = new AntinodeGenerator(antennaList, mapBounds);
        assertEquals(8, generator.getAntinodes().size());

    }

    @Test
    public final void testCounter() {

        Antenna a = new Antenna(new Position(3,4), "A");
        Antenna b = new Antenna(new Position(5, 5), "A");
        Antenna c = new Antenna(new Position(2, 7), "A");
        Antenna d = new Antenna(new Position(6, 3), "B");
        Antenna e = new Antenna(new Position(8, 6), "B");
        List<Antenna> antennaList = new ArrayList<>();
        antennaList.add(a);
        antennaList.add(b);
        antennaList.add(c);
        antennaList.add(d);
        antennaList.add(e);
        AntinodeGenerator generator = new AntinodeGenerator(antennaList, mapBounds);

        assertEquals(7, generator.getCountOnMap());

    }

    @Test
    public final void testWithFullTestInput() {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
        AntennaFinder finder = new AntennaFinder(input);
        AntinodeGenerator generator = new AntinodeGenerator(finder.getAntennas(), mapBounds);
        long count = generator.getCountOnMap();
        assertEquals(14, count);

    }

    @Test
    public final void testWithFullTestInputForPart2() {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
        AntennaFinder finder = new AntennaFinder(input);
        AntinodeGenerator generator = new AntinodeGenerator(finder.getAntennas(), mapBounds, true);
        long count = generator.getCountOnMap();
        assertEquals(34, count);

    }
}
