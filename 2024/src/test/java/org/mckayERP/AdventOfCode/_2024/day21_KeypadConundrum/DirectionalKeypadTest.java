package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionalKeypadTest
{
    @Test
    public final void testConstructor() {
        DirectionalKeypad keypad = new DirectionalKeypad();
        assertEquals(new Position(0,1), keypad.getKey("^").getPosition());
        assertEquals(new Position(0,2), keypad.getKey("A").getPosition());
        assertEquals(new Position(1,0), keypad.getKey("<").getPosition());
        assertEquals(new Position(1,1), keypad.getKey("v").getPosition());
        assertEquals(new Position(1,2), keypad.getKey(">").getPosition());
    }
}
