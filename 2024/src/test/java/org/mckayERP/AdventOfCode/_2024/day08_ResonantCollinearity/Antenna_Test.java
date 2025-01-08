package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Antenna_Test
{

    @Test
    public final void testAntennaData() {
        Antenna a = new Antenna();
        a.setPosition(new Position(1,2));
        a.setType("@");
    }

    @Test
    public final void testAntennaList() {
        Antenna a = new Antenna();
        a.setPosition(new Position(1,2));
        a.setType("A");

        Antenna b = new Antenna();
        b.setPosition(new Position(5,10));
        b.setType("B");

        Antenna c = new Antenna(new Position(7,5), "A");

        List<Antenna> antennas = new ArrayList<>();
        antennas.add(a);
        antennas.add(b);
        antennas.add(c);

        assertEquals(2, antennas.stream().filter(ant -> ant.getType().equals("A")).count());
    }

}
