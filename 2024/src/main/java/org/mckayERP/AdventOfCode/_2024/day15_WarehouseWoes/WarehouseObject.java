package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.Direction;

public interface WarehouseObject
{
    boolean move(Direction direction);

    int getGPSCoord();

    int getWidth();

    void rollback();

    void save();
}
