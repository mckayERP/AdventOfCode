package org.mckayERP.AdventOfCode.utilities;

import java.util.List;

public interface ObjectMap
{
    void add(MapObject who);

    List<MapObject> getContents();

    boolean spaceEmpty(Position p);

    MapObject getContentAt(Position p);

    void print();
}
