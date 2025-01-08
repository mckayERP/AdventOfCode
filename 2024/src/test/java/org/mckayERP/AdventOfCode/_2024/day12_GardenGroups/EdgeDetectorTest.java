package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeDetectorTest
{
    String[] singlePlotMap = {
            "A"
    };

    String[] doubleDownPlotMap = {
            "A",
            "A"
    };

    String[] doubleRightPlotMap = {
            "AA"
    };

    String[] doubleSquarePlotMap = {
            "AA",
            "AA"
    };

    String[] simpleMap = {
        "AAAA",
        "BBCD",
        "BBCC",
        "EEEC"
    };

    @Test
    public final void testHasEdgeOnRightSingle(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(singlePlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnRight(new Position(0,0)));
    }

    @Test
    public final void testHasEdgeOnLeftSingle(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(singlePlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnLeft(new Position(0,0)));
    }

    @Test
    public final void testHasEdgeOnTopSingle(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(singlePlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnTop(new Position(0,0)));
    }

    @Test
    public final void testHasEdgeOnBottomSingle(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(singlePlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnBottom(new Position(0,0)));
    }

    @Test
    public final void testCanFindFourEdges() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(singlePlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        detector.findEdges();
        assertEquals(4, regions.get(0).getEdgeCount());
    }

    @Test
    public final void testHasEdgeOnRightSimple(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertFalse(detector.hasEdgeOnRight(new Position(0,0)));
        assertFalse(detector.hasEdgeOnRight(new Position(0,1)));
        assertFalse(detector.hasEdgeOnRight(new Position(0,2)));
        assertTrue(detector.hasEdgeOnRight(new Position(0,3)));
    }

    @Test
    public final void testHasEdgeOnLeftSimple(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnLeft(new Position(0,0)));
    }

    @Test
    public final void testHasEdgeOnTopSimple(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnTop(new Position(0,0)));
    }

    @Test
    public final void testHasEdgeOnBottomSimple(){

        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        detector.setRegion(regions.get(0));
        assertTrue(detector.hasEdgeOnBottom(new Position(0,0)));
    }

    @Test
    public final void testCanFindRegionAEdges() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(doubleDownPlotMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        Region region = regions.stream().filter(r -> r.plantType.equals("A")).findFirst().get();
        assertEquals(4, region.getEdgeCount());
        assertEquals(2, region.plots.size());
    }
    @Test
    public final void testCanFindRegionAEdgesOnSimple() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        EdgeDetector detector = new EdgeDetector();
        Region region = regions.stream().filter(r -> r.plantType.equals("A")).findFirst().get();
        detector.setMap(regionMap);
        detector.setRegion(region);
        detector.findEdges();
        assertEquals(4, region.getEdgeCount());
        assertEquals(4, region.plots.size());
    }

    @Test
    public final void testCanFindRegionBOnSimple() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        Region region = regions.stream().filter(r -> r.plantType.equals("B")).findFirst().get();
        assertEquals(4, region.getEdgeCount());
        assertEquals(4, region.plots.size());
    }

    @Test
    public final void testCanFindRegionCOnSimple() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        Region region = regions.stream().filter(r -> r.plantType.equals("C")).findFirst().get();
        assertEquals(8, region.getEdgeCount());
        assertEquals(4, region.plots.size());
    }

    @Test
    public final void testCanFindRegionDOnSimple() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        Region region = regions.stream().filter(r -> r.plantType.equals("D")).findFirst().get();
        assertEquals(4, region.getEdgeCount());
        assertEquals(1, region.plots.size());
    }

    @Test
    public final void testCanFindRegionEOnSimple() {
        GardenPlotMapper mapper = new GardenPlotMapper();
        mapper.readInput(simpleMap);
        List<Region> regions = mapper.getRegions();
        XYMap<Integer> regionMap = mapper.getRegionMap();
        Region region = regions.stream().filter(r -> r.plantType.equals("E")).findFirst().get();
        assertEquals(4, region.getEdgeCount());
        assertEquals(3, region.plots.size());
    }

}
