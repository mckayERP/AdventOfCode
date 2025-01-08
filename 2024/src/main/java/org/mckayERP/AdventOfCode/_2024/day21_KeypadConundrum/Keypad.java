package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import java.util.Map;
import java.util.TreeMap;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class Keypad
{

    public KeypadKey getCurrentKey()
    {
        return currentKey;
    }

    public void setCurrentKey(KeypadKey currentKey)
    {
        this.currentKey = currentKey;
    }

    protected KeypadKey currentKey;

    Map<String, KeypadKey> keys = new TreeMap<>();
    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getCols()
    {
        return cols;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    int rows, cols;
    XYMap<KeypadKey> keyMap;

    public void setSize(int rows, int cols)
    {
        setRows(rows);
        setCols(cols);
        keyMap = new XYMap<>(new KeypadKey[getRows()][getCols()]);
    }

    public void addKey(KeypadKey key)
    {
        addKey(key, key.getPosition());
    }

    public KeypadKey getKey(String symbol)
    {
        return keys.get(symbol);
    }

    public void addKey(KeypadKey key, Position position)
    {
        key.setPosition(position);
        if (keyMap.isOnMap(position))
        {
            if (keyMap.getValueAt(position) == null)
            {
                keyMap.setValueAt(position, key);
                keys.putIfAbsent(key.getSymbol(), key);
            }
        }
        else
            throw new IllegalArgumentException("Position is not on the keymap or the map has not been initialized.");
    }

    public int getManhattanDistance(String key1Symbol, String key2Symbol)
    {
        KeypadKey k1 = getKey(key1Symbol);
        KeypadKey k2 = getKey(key2Symbol);
        return getManhattanDistance(k1, k2);
    }

    private int getManhattanDistance(KeypadKey k1, KeypadKey k2)
    {
        return Math.abs(getVerticalDistance(k1, k2))
                + Math.abs(getHorizontalDistance(k1, k2));
    }

    public int getManhattanDistanceFromCurrent(String nextKeySymbol)
    {
        return getManhattanDistance(getCurrentKey(), getKey(nextKeySymbol));
    }

    public int getVerticalDistance(String nextKeySymbol)
    {
        return getVerticalDistance(currentKey, getKey(nextKeySymbol));
    }

    public int getHorizontalDistance(String nextKeySymbol)
    {
        return getHorizontalDistance(currentKey, getKey(nextKeySymbol));
    }

    int getVerticalDistance(KeypadKey k1, KeypadKey k2)
    {
        return k2.getPosition().getRow() - k1.getPosition().getRow();
    }

    int getHorizontalDistance(KeypadKey k1, KeypadKey k2)
    {
        return k2.getPosition().getCol() - k1.getPosition().getCol();
    }

    public Direction getVerticalDirection(String nextKeySymbol) {
        int vd = getVerticalDistance(nextKeySymbol);
        if (vd < 0)
            return UP;
        if (vd > 0)
            return DOWN;
        return null;
    }

    public Direction getHorizontalDirection(String nextKeySymbol) {
        int hd = getHorizontalDistance(nextKeySymbol);
        if (hd < 0)
            return LEFT;
        if (hd > 0)
            return RIGHT;
        return null;
    }

    public KeypadKey getKey(Position p)
    {
        return this.keyMap.getValueAt(p);
    }
}
