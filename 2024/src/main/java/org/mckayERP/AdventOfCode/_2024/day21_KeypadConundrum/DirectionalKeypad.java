package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Position;

public class DirectionalKeypad extends Keypad
{
    public DirectionalKeypad() {
        setSize(2,3);
        KeypadKey keyUP = new KeypadKey("^");
        KeypadKey keyA = new KeypadKey("A");
        KeypadKey keyLEFT = new KeypadKey("<");
        KeypadKey keyDOWN = new KeypadKey("v");
        KeypadKey keyRIGHT = new KeypadKey(">");
        addKey( keyUP , new Position(0,1));
        addKey( keyA  , new Position(0,2));
        addKey( keyLEFT , new Position(1,0));
        addKey( keyDOWN , new Position(1,1));
        addKey( keyRIGHT, new Position(1,2));
        addKey( new NullKey(), new Position(0,0));
        setCurrentKey(keyA);
    }

}
