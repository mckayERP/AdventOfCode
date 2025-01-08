package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SimpleObjectMap implements ObjectMap
{

    List<MapObject> map = new ArrayList<>();
    int maxRow;

    public int getMaxRow()
    {
        return maxRow;
    }

    public void setMaxRow(int maxRow)
    {
        this.maxRow = maxRow;
    }

    public int getMaxCol()
    {
        return maxCol;
    }

    public void setMaxCol(int maxCol)
    {
        this.maxCol = maxCol;
    }

    int maxCol;

    @Override
    public void add(MapObject mapObject)
    {
        Position p = mapObject.getPosition();
        if (!spaceEmpty(p))
        {
            throw new RuntimeException("Map space is not empty " + p);
        }
        if (p.getRow() > maxRow)
            maxRow = p.getRow();
        if (p.getCol() > maxCol)
            maxCol = p.getCol();
        map.add(mapObject);
    }

    @Override
    public List<MapObject> getContents()
    {
        return map;
    }

    private Stream<MapObject> streamAtPosition(Position p) {
        return map.stream().filter(mo ->
                mo.getPosition().equals(p));
    }
    @Override
    public boolean spaceEmpty(Position p)
    {
        return streamAtPosition(p).findAny().isEmpty();
    }

    @Override
    public MapObject getContentAt(Position p)
    {

        return streamAtPosition(p).findFirst().orElse(null);
    }

    @Override
    public void print() {
        System.out.println();
        for(int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                MapObject mapObject = getContentAt(new Position(row, col));
                if (mapObject == null)
                    System.out.print(".");
                else
                {
                    String symbol = mapObject.getSymbol();
                    System.out.print(symbol);
                    col += symbol.length()-1;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
