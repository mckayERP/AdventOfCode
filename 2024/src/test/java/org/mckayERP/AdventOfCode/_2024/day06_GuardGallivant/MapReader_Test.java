package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapReader_Test
{

    String[] input;

    @BeforeEach
    public final void setup() {
        input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
    }

    @Test
    public final void readGridTest() {
        WarehouseMapReader reader = new WarehouseMapReader(input);
        XYMap<Integer> map = reader.getXYMap();
        assertEquals(1, map.getValueAt(0,4));
    }

    @Test
    public final void givenTheInputMap_canFindGuardsPosition() {
        WarehouseMapReader reader = new WarehouseMapReader(input);
        assertEquals(4, reader.getGuardsPosition().getCol());
        assertEquals(6, reader.getGuardsPosition().getRow());
    }

}
