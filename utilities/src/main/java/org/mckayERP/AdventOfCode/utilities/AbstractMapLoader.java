package org.mckayERP.AdventOfCode.utilities;

public abstract class AbstractMapLoader implements MapLoader
{

    ObjectMap map = createMap();

    public void readMapPattern(String[] input)
    {


        int row = 0;

        for (String line : input) {
            for (int col = 0; col<line.length(); col++) {
                String symbol = line.substring(col, col+1);
                dealWithSymbol(symbol, row, col, map);
            }
            row++;
        }
    }

    public void readObjectCoordinates(String[] input) {
        for(String coords : input) {
            String[] coord = coords.split(",");
            int col = Integer.parseInt(coord[0].trim());
            int row = Integer.parseInt(coord[1].trim());
            dealWithSymbol("#", row, col, map);
        }
    }

    public ObjectMap getMap() {
        return map;
    }

    public abstract ObjectMap createMap();

    public abstract int dealWithSymbol(String symbol, int row, int col, ObjectMap map);
}
