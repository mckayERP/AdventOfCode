package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class WarehouseLoaderTest
{
    @Test
    public final void testConstructor() {
        WarehouseLoader whl = new WarehouseLoader();
    }

    @Test
    public final void testCanParseInput() {
        WarehouseLoader whl = new WarehouseLoader();
        whl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "smallTestInput.txt"));
        Warehouse wh = whl.getWarehouse();
        wh.print();
        assertEquals(LEFT, whl.instructions.get(0));
        assertEquals(15, whl.instructions.size());
    }

}
