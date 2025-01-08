package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Position;

public class NumericKeypad extends Keypad
{
    public NumericKeypad() {
        setSize(4,3);
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
        addKey( key7, new Position(0,0));
        addKey( key8, new Position(0,1));
        addKey( key9, new Position(0,2));
        addKey( key4, new Position(1,0));
        addKey( key5, new Position(1,1));
        addKey( key6, new Position(1,2));
        addKey( key1, new Position(2,0));
        addKey( key2, new Position(2,1));
        addKey( key3, new Position(2,2));
        addKey( new NullKey(), new Position(3,0));
        addKey( key0, new Position(3,1));
        addKey( keyA, new Position(3,2));
        setCurrentKey(keyA);
    }
}
