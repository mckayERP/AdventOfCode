package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.ObjectMap;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.Objects;

public abstract class AbstractMapObject implements MapObject
{
    public Position initialPosition;

    ObjectMap map;

    Position position;

    public AbstractMapObject(int row, int col, ObjectMap map) {
        this.map = map;
        position = new Position(row, col);
        initialPosition = position.clone();
        map.add(this);
    }

    public AbstractMapObject()
    {

    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public abstract String getSymbol();

    @Override
    public String toString() {
        return this.position.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractMapObject that = (AbstractMapObject) o;
        return Objects.equals(map, that.map) && position.equals(position);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(map, position);
    }

}
