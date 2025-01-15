package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.*;

public class MazeMap extends SimpleObjectMap implements ObjectMap
{

    XYMap<Integer> costMap;
    public XYMap<MapObject> objectMap;
    Goal goal = null;
    Start start = null;
    private int nextMapPoint;
    private int lastMapPoint;

    public void initialize()
    {
        initialize(getMap().size());
    }

    public void initialize(int numberOfPoints) {
        goal = getGoal();
        start = getStart();

        initializePathMap(numberOfPoints);
    }

    public Goal getGoal() {
        if (goal == null)
            goal = (Goal) getContents().stream().filter(mo -> mo.getSymbol().equals("E")).findFirst().orElse(null);
        return goal;
    }
    public Start getStart() {
        if (start == null)
            start = (Start) getContents().stream().filter(mo -> mo.getSymbol().equals("S")).findFirst().orElse(null);
        return start;
    }

    public void initializePathMap(int numberOfPoints) {

        costMap = new XYMap<>(new Integer[getMaxRow()+1][getMaxCol()+1]);
        objectMap = new XYMap<>(new MapObject[getMaxRow()+1][getMaxCol()+1]);

        costMap.setAllPositionsTo(Integer.MAX_VALUE);
        objectMap.setAllPositionsTo(null);

        getMap().stream().limit(numberOfPoints).forEach(mo ->objectMap.setValueAt(mo.getPosition(), mo));
        nextMapPoint = numberOfPoints;
        lastMapPoint = getMap().size()-1;

    }

    public void fillInDeadEnds() {

        boolean deadendsExist = true;
        while(deadendsExist)
        {
            deadendsExist = false;
            for (int row = 0; row < getMaxRow(); row++)
            {
                for (int col = 0; col < getMaxCol(); col++)
                {
                    Position p = new Position(row, col);
                    if (notStartOrGoal(p))
                    {
                        if (isDeadEnd(p))
                        {
                            deadendsExist = true;
                            System.out.println("Found dead end " + p);
                            objectMap.setValueAt(p.getRow(), p.getCol(), new Wall(p.getRow(), p.getCol(), this));
                        }
                    }
                }
            }
        }
        print();
    }

    public boolean isDeadEnd(Position p) {
        if (objectMap.getValueAt(p) == null) {
            int count = 0;
            for (Direction dir :Direction.values()) {
                Position testPos = p.move(dir);
                if (!(objectMap.getValueAt(p) == null) && notStartOrGoal(testPos))
                    count ++;
            }
            return count >= 3;
        }
        else
            return false;
    }

    public boolean notStartOrGoal(Position p) {
        return !p.equals(goal.position) && !p.equals(start.position);
    }

    public Position dropNext()
    {

        MapObject mo = getMap().get(nextMapPoint++);
        objectMap.setValueAt(mo.getPosition(), mo);
        return mo.getPosition();

    }

    public boolean hasNextToDrop()
    {
        return nextMapPoint<=lastMapPoint;
    }
}
