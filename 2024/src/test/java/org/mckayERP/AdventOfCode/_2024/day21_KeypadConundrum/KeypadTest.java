package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KeypadTest
{

    Keypad keypad;

    @BeforeEach
    public final void setup() {
        keypad = new Keypad();
        keypad.setSize(4,3);
        KeypadKey key7 = new KeypadKey("7");
        KeypadKey key8 = new KeypadKey("8");
        KeypadKey key9 = new KeypadKey("9");
        KeypadKey key4 = new KeypadKey("4");
        KeypadKey key5 = new KeypadKey("5");
        KeypadKey key6 = new KeypadKey("6");
        KeypadKey key1 = new KeypadKey("1");
        KeypadKey key2 = new KeypadKey("2");
        KeypadKey key3 = new KeypadKey("3");
        KeypadKey key0 = new KeypadKey("0");
        KeypadKey keyA = new KeypadKey("A");
        keypad.addKey(key7, new Position(0,0));
        keypad.addKey( key8, new Position(0,1));
        keypad.addKey( key9, new Position(0,2));
        keypad.addKey( key4, new Position(1,0));
        keypad.addKey( key5, new Position(1,1));
        keypad.addKey( key6, new Position(1,2));
        keypad.addKey( key1, new Position(2,0));
        keypad.addKey( key2, new Position(2,1));
        keypad.addKey( key3, new Position(2,2));
        keypad.addKey( key0, new Position(3,1));
        keypad.addKey( keyA, new Position(3,2));
        keypad.setCurrentKey(keyA);
    }
    @Test
    public final void testKeypadConstructor() {
        Keypad keypad = new Keypad();
        keypad.setSize(4,3);
        KeypadKey key = new KeypadKey("7", new Position(0,0));
        keypad.addKey(key);
        assertEquals(new Position(0,0), keypad.getKey("7").getPosition());
    }

    @Test
    public final void testFullKeypad() {
        assertEquals(new Position(0,0), keypad.getKey("7").getPosition());
    }

    @Test
    public final void testManhattanDistance() {
        assertEquals(3, keypad.getManhattanDistance("3", "4"));
    }


    @Test
    public final void testSequenceDistances() {
        assertEquals(-3, keypad.getVerticalDistance("9"));
    }
}
