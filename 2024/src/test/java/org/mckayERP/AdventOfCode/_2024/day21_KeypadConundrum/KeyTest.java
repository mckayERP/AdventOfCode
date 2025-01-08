package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyTest
{
    @Test
    public final void  testKeyConstructor() {
        KeypadKey key = new KeypadKey("7");
        key.setPosition(new Position(0,0));
        assertEquals("7", key.getSymbol());
        assertEquals(new Position(0,0), key.getPosition());
    }
}
