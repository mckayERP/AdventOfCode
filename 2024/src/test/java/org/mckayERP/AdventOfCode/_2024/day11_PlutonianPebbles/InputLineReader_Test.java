package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputLineReader_Test
{
    @Test
    public final void testConstructor() {

        InputLineReader reader = new InputLineReader();
        List<Stone> stones = reader.read("125 7");
        assertEquals(125, stones.get(0).value);
        assertEquals(1, stones.get(0).multiplier);
        assertEquals(7, stones.get(1).value);
        assertEquals(1, stones.get(1).multiplier);
    }

}
