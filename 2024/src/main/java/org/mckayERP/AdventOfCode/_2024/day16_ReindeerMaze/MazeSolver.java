package org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze;

import org.mckayERP.AdventOfCode.utilities.*;

import java.util.*;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class MazeSolver
{

    private int bestPathCost = Integer.MAX_VALUE;

    public MazeSolver()
    {

    }

    private MapObject goal;

    private MapObject start;
    private XYMap<Map<Direction, Integer>> costMap = null;
    private XYMap<MapObject> objectMap = null;

    LinkedList<PointState> pointsInQueue = new LinkedList<>();

    private final Map<Integer, List<List<Position>>> successfulPaths = new LinkedHashMap<>();
    private final List<Position> pointsLookedAt = new ArrayList<>();

    AOCLogger logger = AOCLogger.get();

    @SuppressWarnings("unchecked")
    public MazeSolver(MazeMap map)
    {
        objectMap = map.objectMap;
        HashMap<Direction, Integer>[][] costs = new HashMap[objectMap.getRowCount() + 1][objectMap.getColCount()+1];
        for (int row=0; row<=objectMap.getRowCount(); row++) {
            for (int col=0; col<=objectMap.getColCount(); col++)
            {
                costs[row][col] = new HashMap<>();
            }
        }
        costMap = new XYMap<>(costs);
        setStart(map.getStart());
        setGoal(map.getGoal());
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
        int cost = 0;
        Position startPosition = start.getPosition();
        LinkedList<Position> path = new LinkedList<>();
        PointState thisPointState = new PointState(startPosition, startDirection,
                    0, cost, path);
        thisPointState.isStart = true;

        pointsInQueue.add(thisPointState);

        searchPaths();

        printMapAndBestPath(startPosition);

        return bestPathCost;
    }


    public void searchPaths() {

        while (!pointsInQueue.isEmpty())
        {

            PointState ps = getLowestCostPointStateFromQueue();
            updatePointStateToNextPosition(ps);
            LinkedList<Position> path = new LinkedList<>(ps.path);
            if (path.contains(ps.position))
                continue;

            path.add(ps.position);
            if (ps.position.equals(goal.getPosition()))
            {
                addToSuccessfulPaths(path, ps.costOfThisPath);
            } else
            {
                for (MoveType move : MoveType.values())
                {
                    Direction nextDirection = getNewDirection(ps.direction, move);
                    if (canMoveForward(ps.position, nextDirection) && costIsWithinReason(ps, move, nextDirection))
                    {
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
        return ps.costOfThisPath < costMap.getValueAt(ps.position).getOrDefault(nextDirection, Integer.MAX_VALUE)
                && ps.costOfThisPath + getMoveCost(move) <= bestPathCost;
    }

    private void preparePointStateForNextMoveAndAddToQueue(PointState ps, MoveType move, Direction nextDirection,
                                                           LinkedList<Position> path)
    {
        PointState psClone = new PointState(ps.position, nextDirection, getMoveCost(move), ps.costOfThisPath, path);
        pointsInQueue.add(psClone);
        pointsLookedAt.add(psClone.position);
        costMap.getValueAt(psClone.position).putIfAbsent(psClone.direction, psClone.costOfThisPath+ psClone.costOfThisMove);
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

    private boolean canMoveForward(Position currentPosition, Direction currentDirection)
    {
        Position testPosition = currentPosition.move(currentDirection);
        if (!objectMap.isOnMap(testPosition))
            return false;
        MapObject blockingObject = objectMap.getValueAt(testPosition);
        boolean atGoal = currentPosition.move(currentDirection).equals(goal.getPosition());
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

    private void printMapAndBestPath(Position currentPosition)
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
