package org.mckayERP.AdventOfCode._2024.day08_ResonantCollinearity;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Antinode_Test
{
    @Test
    public final void testConstructor() {

        Antenna a = new Antenna(new Position(3,4), "A");
        Antenna b = new Antenna(new Position(5, 5), "A");
        Antinode antinode = new Antinode(a,b);

        assertEquals(7, antinode.position.getRow());
        assertEquals(6, antinode.position.getCol());
    }
}
