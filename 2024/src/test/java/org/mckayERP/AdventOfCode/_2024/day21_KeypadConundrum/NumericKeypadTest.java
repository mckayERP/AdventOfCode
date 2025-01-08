package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumericKeypadTest
{
    @Test
    public final void testConstructor() {
        NumericKeypad nkpad = new NumericKeypad();
        assertEquals(new Position(3,1), nkpad.getKey("0").getPosition());
        assertEquals(new Position(2,0), nkpad.getKey("1").getPosition());
        assertEquals(new Position(2,1), nkpad.getKey("2").getPosition());
        assertEquals(new Position(2,2), nkpad.getKey("3").getPosition());
        assertEquals(new Position(1,0), nkpad.getKey("4").getPosition());
        assertEquals(new Position(1,1), nkpad.getKey("5").getPosition());
        assertEquals(new Position(1,2), nkpad.getKey("6").getPosition());
        assertEquals(new Position(0,0), nkpad.getKey("7").getPosition());
        assertEquals(new Position(0,1), nkpad.getKey("8").getPosition());
        assertEquals(new Position(0,2), nkpad.getKey("9").getPosition());
        assertEquals(new Position(3,2), nkpad.getKey("A").getPosition());
    }
}
