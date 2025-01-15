package org.mckayERP.AdventOfCode._2023.day03_GearRatios;

import org.mckayERP.AdventOfCode.utilities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchematicReader extends AbstractMapLoader implements MapLoader
{

    XYMap<MapObject> xyMap;

    List<SchematicNumber> partNumbers = new ArrayList<>();
    List<Gear> gears = new ArrayList<>();

    @Override
    public ObjectMap createMap()
    {
        return new SimpleObjectMap();
    }

    SchematicNumber number;
    List<SchematicNumber> numbers = new ArrayList<>();

    @Override
    public void readMapPattern(String[] input) {
        super.readMapPattern(input);
        ((SimpleObjectMap) getMap()).setMaxCol(input[0].length()-1);
        ((SimpleObjectMap) getMap()).setMaxRow(input.length-1);
    }
    public void initializeXYMap() {

        SimpleObjectMap map = (SimpleObjectMap) getMap();
        xyMap = new XYMap<>(new MapObject[map.getMaxRow()+1][map.getMaxCol()+1]);
        map.getContents().forEach(mo ->xyMap.setValueAt(mo.getPosition(), mo));

    }

    @Override
    public int dealWithSymbol(String symbol, int row, int col, ObjectMap map)
    {
        if (symbol.matches("\\d"))
        {
            Digit d = new Digit(symbol, new Position(row, col));
            map.add(d);
            if(number == null)
            {
                number = new SchematicNumber();
            }
            number.addDigit(d);
            d.setNumber(number);
        }
        else {
            if (number != null)
            {
                numbers.add(number);
                number = null;
            }
        }

        if (symbol.equals("."))
        {
            return 0;
        }

        if (symbol.matches("\\D"))
            map.add(new Symbol(symbol, new Position(row, col)));


        return 1;
    }

    public int findPartNumbers()
    {
        numbers.stream()
                .filter(n ->
                        n.digits.stream().anyMatch(this::isAdjacentToSymbol)
        ).forEach(n -> {
            if (!partNumbers.contains(n))
                partNumbers.add(n);
        });
        partNumbers.sort(Comparator.comparing(SchematicNumber::getValue));
        partNumbers.forEach(System.out::println);
        return partNumbers.size();
    }

    public int findGears()
    {
        getMap().getContents().stream()
                .filter(mo -> mo instanceof Symbol && ((Symbol) mo).getSymbol().equals("*"))
                .map(mo -> (Symbol) mo)
                .filter(this::isAdjacentToTwoNumbers)
                .forEach(asterix -> {
                        Gear gear = new Gear(asterix);
                        gears.add(gear);
                });
        return gears.size();
    }

    private boolean isAdjacentToTwoNumbers(Symbol symbol)
    {
        Position ds = symbol.getPosition();
        int countOfNumbers = 0;
        for (int rowOffset = -1; rowOffset<2; rowOffset++) {
            for (int colOffset = -1; colOffset<2; colOffset++) {
                if (rowOffset==0 && colOffset == 0)
                    continue;
                Position pToTest = new Position(ds.getRow()+rowOffset, ds.getCol()+colOffset);
                if (xyMap.isOnMap(pToTest))
                {
                    MapObject mo = xyMap.getValueAt(pToTest);
                    if (mo instanceof Digit) {
                        symbol.addAdjacentNumber(((Digit) mo).getNumber());
                    }
                }
            }
        }

        return symbol.getAdjacentNumbers().size() == 2;
    }

    private boolean isAdjacentToSymbol(Digit d)
    {
        Position dp = d.getPosition();
        for (int rowOffset = -1; rowOffset<2; rowOffset++) {
            for (int colOffset = -1; colOffset<2; colOffset++) {
                if (rowOffset==0 && colOffset == 0)
                    continue;
                Position pToTest = new Position(dp.getRow()+rowOffset, dp.getCol()+colOffset);
                if (xyMap.isOnMap(pToTest))
                {
                    MapObject mo = xyMap.getValueAt(pToTest);
                    if (mo instanceof Symbol)
                        return true;
                }
            }
        }

        for (int rowOffset = -1; rowOffset<2; rowOffset++) {
            for (int colOffset = -1; colOffset<2; colOffset++) {
                if (rowOffset==0 && colOffset == 0)
                    continue;
                Position pToTest = new Position(dp.getRow()+rowOffset, dp.getCol()+colOffset);
                if (xyMap.isOnMap(pToTest))
                {
                    MapObject mo = xyMap.getValueAt(pToTest);
                    if (mo instanceof Symbol)
                        return true;
                }
            }
        }
        return false;
    }

    public int getSumOfPartNumbers()
    {
        if (partNumbers.isEmpty())
            findPartNumbers();
        return partNumbers.stream().mapToInt(number -> number.getValue()).sum();
    }

    public int getSumOfGearRatios() {
        if (gears.isEmpty())
            findGears();
        return gears.stream().mapToInt(Gear::getGearRatio).sum();
    }
}
