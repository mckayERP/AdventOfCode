package org.mckayERP.AdventOfCode._2024.day18_RAMRun;

import org.mckayERP.AdventOfCode.utilities.*;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.PointState;

import java.util.*;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class MazeSolver
{

    private int bestPathCost = Integer.MAX_VALUE;
    
    public MazeSolver()
    {

    }

    public XYMap<MapObject> getObjectMap()
    {
        return objectMap;
    }

    public XYMap<Integer> getCostMap()
    {
        return costMap;
    }

    private MapObject goal;

    private MapObject start;
    private XYMap<Integer> costMap = null;
    private XYMap<MapObject> objectMap = null;

    LinkedList<PointState> pointsInQueue = new LinkedList<>();

    private final Map<Integer, List<List<Position>>> successfulPaths = new LinkedHashMap<>();
    private final List<Position> pointsLookedAt = new ArrayList<>();

    AOCLogger logger = AOCLogger.get();

    @SuppressWarnings("unchecked")
    public MazeSolver(MazeMap map)
    {
        objectMap = map.objectMap;
        Integer[][] costs = new Integer[objectMap.getRowCount() + 1][objectMap.getColCount()+1];
        for (int row=0; row<=objectMap.getRowCount(); row++) {
            for (int col=0; col<=objectMap.getColCount(); col++)
            {
                costs[row][col] = Integer.MAX_VALUE;
            }
        }
        costMap = new XYMap<>(costs);
        setStart(map.getStart());
        setGoal(map.getGoal());
    }

    public Map<Integer, List<List<Position>>> getSuccessfulPaths()
    {
        return successfulPaths;
    }


    public enum MoveType
    {
        MOVE, TURN_LEFT, TURN_RIGHT;
    }

    public int getMoveCost(MoveType moveType)
    {
        int costOfMove = 1;
        int costOfTurnAndMove = 1001;
        return  (moveType == MoveType.MOVE) ? costOfMove : costOfTurnAndMove;

    }

    Direction startDirection = RIGHT;

    public void setGoal(MapObject goal)
    {
        if (goal == null)
            throw new NullPointerException("Goal can't be null");
        this.goal = goal;
    }

    ;

    public void setStart(MapObject start)
    {
        if (start == null)
            throw new NullPointerException("Start can't be null");
        this.start = start;
    }

    public int getBestPathCost()
    {
        return successfulPaths.keySet().stream().sorted().findFirst().orElse(Integer.MAX_VALUE);
    }

    public int findBestPath()
    {
        successfulPaths.clear();
        bestPathCost = Integer.MAX_VALUE;
        pointsLookedAt.clear();
        costMap.setAllPositionsTo(Integer.MAX_VALUE);
        int cost = 0;
        Position startPosition = start.getPosition();
        LinkedList<Position> path = new LinkedList<>();
        PointState thisPointState = new PointState(startPosition, startDirection,
                    0, cost, path);
        thisPointState.isStart = true;

        pointsInQueue.add(thisPointState);

        searchPaths();

        return bestPathCost;
    }


    public void searchPaths() {

        while (!pointsInQueue.isEmpty())
        {

            PointState ps = getLowestCostPointStateFromQueue();
            updatePointStateToNextPosition(ps);
            LinkedList<Position> path = new LinkedList<>(ps.path);
            path.add(ps.position);
            if (ps.position.equals(goal.getPosition()))
            {
                costMap.setValueAt(goal.getPosition(), ps.costOfThisPath+ps.costOfThisMove);
                addToSuccessfulPaths(path, ps.costOfThisPath+ps.costOfThisMove);
            } else
            {
                for (MoveType move : MoveType.values())
                {
                    Direction nextDirection = getNewDirection(ps.direction, move);
                    if (canMoveForward(ps, nextDirection) && costIsWithinReason(ps, move, nextDirection))
                    {
                        pointsLookedAt.add(ps.position.move(nextDirection));
                        preparePointStateForNextMoveAndAddToQueue(ps, move, nextDirection, path);
                    }
                }
            }
        }
    }

    private PointState getLowestCostPointStateFromQueue()
    {
        pointsInQueue.sort(Comparator.comparingInt(ps -> ps.costOfThisPath + ps.costOfThisMove));
        PointState ps = pointsInQueue.removeFirst();
        return ps;
    }

    private boolean costIsWithinReason(PointState ps, MoveType move, Direction nextDirection)
    {
        Position testPosition = ps.position.move(nextDirection);
        return ps.costOfThisPath + getMoveCost(move) < costMap.getValueAt(testPosition)
                && ps.costOfThisPath + getMoveCost(move) <= bestPathCost;
    }

    private void preparePointStateForNextMoveAndAddToQueue(PointState ps, MoveType move, Direction nextDirection,
                                                           LinkedList<Position> path)
    {
        PointState psClone = new PointState(ps.position, nextDirection, getMoveCost(move), ps.costOfThisPath, path);
        pointsInQueue.add(psClone);
        costMap.setValueAt(psClone.position, psClone.costOfThisPath+ psClone.costOfThisMove);
    }

    private void addToSuccessfulPaths(List<Position> path, Integer score)
    {

        List<List<Position>> pathsAtThatScore = successfulPaths
                .computeIfAbsent(score, k -> new ArrayList<>());
        pathsAtThatScore.add(path.stream().toList());
        bestPathCost = getBestPathCost();
    }

    private void updatePointStateToNextPosition(PointState ps)
    {
        if (ps.isStart)
            return;
        ps.position = ps.position.move(ps.direction);
        ps.costOfThisPath = ps.costOfThisPath + ps.costOfThisMove;
    }
    private Direction getNewDirection(Direction currentDirection, MoveType move)
    {
        Direction newDirection = currentDirection;
        switch (move)
        {
            case TURN_LEFT ->
            {
                switch (currentDirection)
                {
                    case UP ->
                    {
                        newDirection = LEFT;
                    }
                    case DOWN ->
                    {
                        newDirection = RIGHT;
                    }
                    case LEFT ->
                    {
                        newDirection = DOWN;
                    }
                    case RIGHT ->
                    {
                        newDirection = UP;
                    }
                }
            }
            case TURN_RIGHT ->
            {
                switch (currentDirection)
                {
                    case UP ->
                    {
                        newDirection = RIGHT;
                    }
                    case DOWN ->
                    {
                        newDirection = LEFT;
                    }
                    case LEFT ->
                    {
                        newDirection = UP;
                    }
                    case RIGHT ->
                    {
                        newDirection = DOWN;
                    }
                }
            }
        }
        return newDirection;
    }

    private boolean canMoveForward(PointState ps, Direction currentDirection)
    {
        Position testPosition = ps.position.move(currentDirection);
        if (!objectMap.isOnMap(testPosition))
            return false;
        if (ps.path.contains(testPosition))
            return false;
        if (pointsLookedAt.contains(testPosition))
            return false;
        MapObject blockingObject = objectMap.getValueAt(testPosition);
        boolean atGoal = testPosition.equals(goal.getPosition());
        return blockingObject == null || atGoal;
    }

    public long getCountOfPointsOnBestPath()
    {

        if (successfulPaths.isEmpty())
            return 0;

        List<Integer> sortedScores = new ArrayList<>(successfulPaths.keySet());
        Collections.sort(sortedScores);
        Integer bestScore = sortedScores.get(0);

        List<List<Position>> bestPaths = successfulPaths.get(bestScore);
        List<Position> pointsOnBestPaths = new ArrayList<>();
        bestPaths.forEach(pointsOnBestPaths::addAll);
        return pointsOnBestPaths.stream().distinct().count();

    }

    public void printMapAndBestPath(Position currentPosition)
    {
        if (!AOCLogger.isJUnitTest())
            return;

        logger.logln("");
        List<Position> pointsOnBestPaths = new ArrayList<>();
        if (!successfulPaths.isEmpty())
        {
            List<List<Position>> bestPaths = successfulPaths.get(getBestPathCost());
            bestPaths.forEach(pointsOnBestPaths::addAll);
        }
        for (int row = 0; row < objectMap.getRowCount(); row++)
        {
            for (int col = 0; col < objectMap.getColCount(); col++)
            {
                MapObject mapObject = objectMap.getValueAt(new Position(row, col));
                if (mapObject == null)
                {
                    Position point = new Position(row, col);
                    if (point.equals(currentPosition))
                    {
                        logger.log("Y");
                    } else
                    {
                        if (pointsOnBestPaths.contains(new Position(row, col)))
                        {
                            logger.log("O");
                        } else
                        {
                            if (pointsLookedAt.contains(new Position(row, col)))
                            {
                                logger.log("X");
                            } else
                                logger.log(".");
                        }
                    }
                } else
                {
                    String symbol = mapObject.getSymbol();
                    logger.log(symbol);
                    col += symbol.length() - 1;
                }
            }
            logger.logln("");
        }
        logger.logln("");
    }

}
