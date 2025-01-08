package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class gardenPlotMapperTest
{
    GardenPlotMapper mapper;

    @BeforeEach
    public final void setup() {
        mapper = new GardenPlotMapper();
        mapper.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
    }

    @Test
    public final void testPlantMap() {
        assertEquals("R", mapper.getPlantMap().getValueAt(0,0));
        mapper.getPlantMap().printMap();
    }

    @Test
    public final void testRegionMap() {
        assertEquals(1, mapper.getRegionMap().getValueAt(0,0));
        mapper.getRegionMap().printMap();
    }

    @Test
    public final void testRegionCodes() {
        assertEquals(11, mapper.getRegions().size());
    }

    @Test
    public final void readRegionsTest() {
        assertEquals(12, mapper.getRegionSize(1));
    }

    @Test
    public final void getRegionPerimeter() {
        assertEquals(18, mapper.getPerimeter(1));
    }

    @Test
    public final void getRegionPrice() {
        assertEquals(216, mapper.getRegionPrice(1));
    }

    @Test
    public final void testTestScenario() {
        assertEquals(1930, mapper.getTotalPrice());
    }

    @Test
    public final void testPart2RegionRScenario() {
        assertEquals(120, mapper.getRegionPriceByEdge(1));
    }

    @Test
    public final void testSimpleExample() {
        String[] simpleMap = {
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC"
        };
        mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        assertEquals(80, mapper.getTotalPriceByEdge());
    }

    @Test
    public final void testSimpleExample2() {
        String[] simpleMap = {
                "OOOOO",
                "OXOXO",
                "OOOOO",
                "OXOXO",
                "OOOOO"
        };
        mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        assertEquals(436, mapper.getTotalPriceByEdge());
    }

    @Test
    public final void testSimpleExample3() {
        String[] simpleMap = {
                "EEEEE",
                "EXXXX",
                "EEEEE",
                "EXXXX",
                "EEEEE"
        };
        mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        assertEquals(12, mapper.regions.get(0).edges.size());
        assertEquals(236, mapper.getTotalPriceByEdge());
    }

    @Test
    public final void testPart2Scenario() {
        assertEquals(1206, mapper.getTotalPriceByEdge());
    }
}


