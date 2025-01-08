package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.MapReader;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import java.util.ArrayList;
import java.util.List;

public class GardenPlotMapper implements MapReader
{
    XYMap<String> plantMap;
    XYMap<Integer> regionMap;

    public GardenPlotMapper()
    {
        this.regions = new ArrayList<>();
        this.plantTypes = new ArrayList<>();
    }

    public List<Region> getRegions()
    {
        return regions;
    }
    List<Region> regions;
    List<String> plantTypes;
    public void readInput(String[] input)
    {
        int rowCount = input.length;
        int colCount = input[0].length();
        String[][] data = new String[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String plantType = input[row].substring(col, col+1);
                data[row][col] = plantType;
            }
        }
        plantMap = new XYMap<>(data);
        mapRegions();
    }

    private void mapRegions()
    {
        regionMap = new XYMap<>(new Integer[plantMap.getRowCount()][plantMap.getColCount()]);
        int regionCode = 1;
        for (int row = 0; row < plantMap.getRowCount(); row++)
        {
            for (int col = 0; col < plantMap.getColCount(); col++)
            {
                Position thisPlot = new Position(row, col);
                String plantType = plantMap.getValueAt(thisPlot);
                if (!plantTypes.contains(plantType))
                    plantTypes.add(plantType);
                if (regionMap.getValueAt(thisPlot) == null) {
                    regionMap.setValueAt(thisPlot, regionCode);
                    Region region = new Region();
                    region.regionCode = regionCode++;
                    region.plantType = plantType;
                    region.plots.add(thisPlot);
                    mapRegion(region, thisPlot);
                    regions.add(region);
                }
            }
        }
        findEdges();
    }

    private void findEdges()
    {
        EdgeDetector detector = new EdgeDetector();
        detector.setMap(regionMap);
        regions.stream().forEach(r -> {
            detector.setRegion(r);
            detector.findEdges();
        });
    }

    private void mapRegion(Region region, Position thisPlot)
    {

        for (Direction dir : Direction.values())
        {
            mapRegionFromPlot(thisPlot, dir, region);
        }

    }

    private void mapRegionFromPlot(Position thisPlot, Direction dir, Region region)
    {
        int row = thisPlot.getRow();
        int col = thisPlot.getCol();
        switch (dir) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> col--;
            case RIGHT -> col++;
            default -> throw new RuntimeException("Unknown direction");
        };
        Position nextPosition = new Position(row, col);

        if (regionMap.isOnMap(nextPosition)
                && region.plantType.equals(plantMap.getValueAt(nextPosition))
                && !region.plots.contains(nextPosition)) {
            region.plots.add(nextPosition);
            regionMap.setValueAt(nextPosition, region.regionCode);
            mapRegion(region, nextPosition);
        }

    }

    public XYMap<String> getXYMap()
    {
        return plantMap;
    }

    public XYMap<String> getPlantMap()
    {
        return plantMap;
    }

    public XYMap<Integer> getRegionMap()
    {
        return regionMap;
    }

    public List<Position> getRegionPlots(int code)
    {
        return regions.stream().filter(r -> r.regionCode==code)
                .map(r -> r.plots).findAny().orElseGet(ArrayList::new);
    }

    private String getPlotRegion(Position p)
    {
        return plantMap.getValueAt(p);
    }
    public int getPerimeter(int code)
    {
        List<Position> plots = getRegionPlots(code);
        int perimeter = 0;
        int count = 0;
        for(Position plot : plots) {
            perimeter += getPlotExternalPerimeter(plot, code);
        }
        return perimeter;
    }

    private int getPlotExternalPerimeter(Position plot, int code)
    {
        int perimeter = 0;
        for (Direction dir : Direction.values()) {
            Position edge = plot.move(dir);
            if (!regionMap.isOnMap(edge) || regionMap.getValueAt(edge) != code)
                perimeter++;
        }
        return perimeter;
    }

    public int getRegionPrice(int i)
    {
        return getRegionSize(i) * getPerimeter(i);
    }

    public int getRegionSize(int i)
    {
        return getRegionPlots(i).size();
    }

    public int getTotalPrice()
    {
        int totalCost = 0;
        for (Region region : regions)
            totalCost += getRegionPrice(region.regionCode);
        return totalCost;
    }

    public int getTotalPriceByEdge()
    {
        int totalPrice = 0;
        for (Region region : regions)
            totalPrice += getRegionPriceByEdge(region.regionCode);
        return totalPrice;
    }

    public int getRegionPriceByEdge(int regionCode)
    {
        return getRegionSize(regionCode) * getNumberOfSides(regionCode);
    }

    private int getNumberOfSides(int regionCode)
    {
        return regions.stream().filter(r -> r.regionCode==regionCode).findFirst().get().edges.size();
    }
}
