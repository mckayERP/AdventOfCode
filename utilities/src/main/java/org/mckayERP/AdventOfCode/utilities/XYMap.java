package org.mckayERP.AdventOfCode.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class XYMap<T>
{
    T[][] map;

    public XYMap(T[][] mapdata) {
        this.map = mapdata;
    }

    public T[][] getMap() {
        return map;
    }

    public int getRowCount() {
        if (getMap() != null)
            return getMap().length;
        else
            return 0;
    }

    public int getColCount() {
        if (getMap() != null)
            return getMap()[0].length;
        else
            return 0;
    }

    public void printMap() {
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColCount(); col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
    }

    public void setValueAt(int row, int col, T value)
    {
        map[row][col] = (T) value;
    }

    public void setValueAt(Position p, T value) {
        setValueAt(p.getRow(), p.getCol(), value);
    }

    public T getValueAt(int row, int col)
    {
        if (isOnMap(new Position(row, col)))
            return map[row][col];
        else
            throw new RuntimeException("Not on map. " + new Position(row, col));
    }

    public T getValueAt(Position p)
    {
        return getValueAt(p.getRow(), p.getCol());
    }

    public boolean isOnMap(Position p)
    {
        return 0 <= p.getRow() && p.getRow() < getRowCount()
                && 0 <= p.getCol() && p.getCol() < getColCount();
    }

    public boolean isOnMap(int row, int col) {
        return isOnMap(new Position(row, col));
    }

    public Stream<Position> streamPositions() {
        List<Position> positions = new ArrayList<>();
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColCount(); col++) {
                positions.add(new Position(row, col));
            }
        }
        return positions.stream();
    }

    public void setAllPositionsTo(T value) {
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColCount(); col++) {
               setValueAt(row, col, value);
            }
        }
    }

}
