package org.mckayERP.AdventOfCode._2024.day06_GuardGallivant;

import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import static org.mckayERP.AdventOfCode._2024.day06_GuardGallivant.Direction.*;

public class MapWalker
{

    private final Position startingPosition;
    private final Direction startingDirection;
    Position currentPosition;

    PathPoint currentPos;
    XYMap<Integer> map;

    boolean loopDetected = false;
    Direction guardsDirection;

    PathTracker pathTracker = new PathTracker();

    boolean printDebug;

    public MapWalker(XYMap<Integer> map, Position guardsPosition, Direction guardsDirection)
    {
        this(map, guardsPosition, guardsDirection, true);
    }

    public MapWalker(XYMap<Integer> map, Position guardsPosition, Direction guardsDirection, boolean printDebug)
    {

        this.map = map;
        this.currentPosition = guardsPosition.clone();
        this.startingPosition = guardsPosition.clone();
        this.guardsDirection = guardsDirection;
        this.startingDirection = guardsDirection;
        this.currentPos = new PathPoint(startingPosition.clone(), startingDirection);
        this.pathTracker.addPathPoint(currentPos.clone());
        this.printDebug = printDebug;

    }


    public void turnRight() {
        guardsDirection =  turnRight(guardsDirection);
        currentPos.direction = guardsDirection;
    }

    public Direction turnRight(Direction currentDirection)
    {
        return switch(currentDirection) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public MapWalker setCurrentPosition(Position p)
    {
        currentPosition = p;
        currentPos.position = p;
        return this;
    }

    public boolean move()
    {
        int row = currentPos.position.getRow();
        int col = currentPos.position.getCol();

        switch (currentPos.direction) {
            case UP -> row--;
            case LEFT -> col --;
            case DOWN -> row++;
            case RIGHT -> col++;
        }
        if (map.isOnMap(row, col))
        {
            if (map.getValueAt(row,col) == 1 || map.getValueAt(row,col) == 8)
            {
                turnRight();
                return false;
            }
        }
        currentPosition.setRow(row);
        currentPosition.setCol(col);
        currentPos.position = currentPosition;
        return true;

    }

    public boolean isOnMap() {
        return map.isOnMap(currentPosition);
    }

    public int walk()
    {

        while (isOnMap() && !loopDetected)
        {

            move();
            if (isOnMap())
            {
                map.setValueAt(currentPosition, 2);
                loopDetected = pathTracker.addPathPoint(currentPos);
            }
        }

        return countPositionsVisited();
    }

    private void printMap()
    {
        if (!printDebug)
            return;


        for (Integer[] rows : map.getMap())
        {
            for (Integer pos : rows)
            {
                String mark = switch (pos) {
                    case 1 -> "#";
                    case 8 -> "O";
                    case 2 -> "x";
                    default -> ".";
                };
                System.out.print("" + mark);
            }
            System.out.println();
        }
        System.out.println();
    }


    private int countPositionsVisited()
    {
        int count = 0;
        for (Integer[] rows : map.getMap())
            for (Integer pos : rows)
                if (pos == 2)
                    count++;

        return count;
    }

    public MapWalker setGuardsDirection(Direction direction)
    {
        guardsDirection = direction;
        return this;
    }

    public int findBlockingPositions() {

        map.setValueAt(startingPosition,2);
        walk();
        if (printDebug)
            System.out.println("Path contains " + pathTracker.getPathSize() + " positions with " + pathTracker.getUniqueSize() + " unique spots.");
        XYMap initalMap = copyMap(map);
        int countOfLoopMakingPositions = 0;

        PathTracker initialPathTracker = pathTracker.clone();
        for (Position p : initialPathTracker.getSpotsVisited()) {
            if (!p.equals(startingPosition))
            {
                map.setValueAt(p, 8);
                clearPathFromMap();
                walk();
                if (isOnMap())
                {
                    if (loopDetected)
                    {
                        if (printDebug)
                            System.out.println("Found blocking position at " + currentPosition);
                        countOfLoopMakingPositions++;
                        printMap();
                    }
                }
                map = copyMap(initalMap);
                currentPosition = startingPosition.clone();
                guardsDirection = startingDirection;
                currentPos = new PathPoint(startingPosition, startingDirection);
                pathTracker = new PathTracker();
                pathTracker.addPathPoint(currentPos);
                loopDetected = false;
            }
        }
        return countOfLoopMakingPositions;
    }

    private XYMap copyMap(XYMap<Integer> map)
    {
        Integer[][] mapData = new Integer[map.getRowCount()][map.getColCount()];
        for (int row=0; row<map.getRowCount(); row++) {
            for (int col=0; col < map.getColCount(); col++) {
                mapData[row][col] = map.getValueAt(row, col);
            }
        }
        XYMap<Integer> newMap = new XYMap<>(mapData);
        return newMap;
    }

    private void clearPathFromMap()
    {
        if (printDebug) {
            for (int row = 0; row < map.getRowCount(); row++)
            {
                for (int col = 0; col < map.getColCount(); col++)
                {
                    if (map.getValueAt(row, col) == 2)
                        map.setValueAt(row, col, 0);
                }
            }
        }
    }


}
