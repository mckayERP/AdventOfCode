package org.mckayERP.AdventOfCode.utilities;

public interface MapReader<T>
{

    void readInput(String[] input);

    XYMap<T> getXYMap();

}
