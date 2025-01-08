package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import java.util.*;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class SequenceManager
{

    Keypad keypad;

    KeypadKey start;
    KeypadKey goal;

    private int bestPathCost = Integer.MAX_VALUE;
    private XYMap<Map<Direction, Integer>> costMap = null;
    private XYMap<Direction> directionXYMap = null;
    LinkedList<PointState> pointsInQueue = new LinkedList<>();
    private final Map<Integer, List<List<Position>>> successfulPaths = new LinkedHashMap<>();
    private final List<Position> pointsLookedAt = new ArrayList<>();
    private Map<Integer, List<List<String>>> possibleSequences;

    public SequenceManager(Keypad keypad)
    {
        setKeypad(keypad);
        initializeMaps();
    }

    public void setKeypad(Keypad keypad)
    {
        this.keypad = keypad;
    }

    public int getSequenceLengthFor(String fromKeySymbol, String toKeySymbol)
    {
        return keypad.getManhattanDistance(fromKeySymbol, toKeySymbol);
    }

    public int getSequenceLengthFor(String keySymbol)
    {
        return keypad.getManhattanDistanceFromCurrent(keySymbol);
    }

    public Map<Integer, List<List<String>>> getSequenceListFor(String currentKey, String nextKey)
    {
        possibleSequences = new TreeMap<>();
        goal = keypad.getKey(nextKey);
        start = keypad.getKey(currentKey);
        keypad.setCurrentKey(start);
        int leftRightDistance = goal.getPosition().getCol() - start.getPosition().getCol();
        int upDownDistance = goal.getPosition().getRow() - start.getPosition().getRow();
        String leftright = "";
        if (leftRightDistance != 0)
            leftright = (leftRightDistance>0?">":"<").repeat(Math.abs(leftRightDistance));
        String upDown = "";
        if (upDownDistance != 0)
            upDown = (upDownDistance>0?"v":"^").repeat(Math.abs(upDownDistance));
        String sequence = leftright+upDown;
        if (leftRightDistance > 0)
            sequence = upDown+leftright;

        String keySymbol = "";
        try {
            keySymbol = applySequence(start,new String[] {sequence + "A"});
        }
        catch (RuntimeException e) {
            sequence = upDown+leftright;
            if (leftRightDistance > 0)
                sequence = leftright+upDown;

            keySymbol = applySequence(start, new String[] {sequence + "A"});
        }

        if (!keySymbol.equals(nextKey))
            throw new RuntimeException("Key sequence does not generate the correct keystroke. Expected " + nextKey + " but generated " + keySymbol);
        List<String> sequenceList = new ArrayList<>();
        sequenceList.add(sequence);
        List<List<String>> sequences = new ArrayList<>();
        sequences.add(sequenceList);
        possibleSequences.put(1,sequences);
//        bestPathCost = Integer.MAX_VALUE;
//        initializeMaps();
//        findBestPath();
        return possibleSequences;
    }

    private void initializeMaps()
    {
        HashMap<Direction, Integer>[][] costs = new HashMap[keypad.keyMap.getRowCount()][keypad.keyMap.getColCount()];
        Direction[][] directions = new Direction[keypad.keyMap.getRowCount()][keypad.keyMap.getColCount()];
        for (int row = 0; row < keypad.keyMap.getRowCount(); row++)
        {
            for (int col = 0; col < keypad.keyMap.getColCount(); col++)
            {
                costs[row][col] = new HashMap<>();
                directions[row][col] = null;
            }
        }
        costMap = new XYMap<>(costs);
        directionXYMap = new XYMap<>(directions);

    }

    public int getMoveCost()
    {
        return 1;

    }

    public int findBestPath()
    {
        int cost = 0;

        Position startPosition = start.getPosition();
        LinkedList<Position> path = new LinkedList<>();
        LinkedList<String> keySequence = new LinkedList<>();
        PointState thisPointState = new PointState(startPosition, UP, 0, cost, path, keySequence);
        thisPointState.isStart = true;

        pointsInQueue.add(thisPointState);

        searchPaths();

        return bestPathCost;
    }

    public void searchPaths()
    {

        while (!pointsInQueue.isEmpty())
        {

            PointState ps = getLowestCostPointStateFromQueue();
            updatePointStateToNextPosition(ps);
            LinkedList<Position> path = new LinkedList<>(ps.path);
            LinkedList<String> keySequence = new LinkedList<>(ps.keySequence);
            if (path.contains(ps.position))
                continue;

            path.add(ps.position);
            if (!ps.isStart)
                keySequence.add(convertDirectionToKey(ps.direction));
            if (ps.position.equals(goal.getPosition()))
            {
                addToSuccessfulPaths(path, ps.costOfThisPath);
                addToSuccessfulSequences(keySequence, ps.costOfThisPath);
            } else
            {
                for (Direction nextDirection : Direction.values())
                {
                    if (canMoveForward(ps.position, nextDirection) && costIsWithinReason(ps, nextDirection))
                    {
                        preparePointStateForNextMoveAndAddToQueue(ps, nextDirection, path, keySequence);
                    }
                }
            }
        }
    }

    private String convertDirectionToKey(Direction direction)
    {
        return switch (direction)
        {
            case LEFT -> "<";
            case DOWN -> "v";
            case UP -> "^";
            case RIGHT -> ">";
        };
    }

    private Direction convertKeyToDirection(String key)
    {
        if (!"<>^v".contains(key))
            throw new RuntimeException("Key does not represent a Directional Keypad key. " + key);

        return switch (key)
        {
            case "<" -> LEFT;
            case "v"-> DOWN;
            case "^" -> UP;
            case ">" -> RIGHT;
            default -> throw new RuntimeException("Unknown key " + key);
        };
    }

    private PointState getLowestCostPointStateFromQueue()
    {
        pointsInQueue.sort(Comparator.comparingInt(ps -> ps.costOfThisPath + ps.costOfThisMove));
        PointState ps = pointsInQueue.removeFirst();
        return ps;
    }

    private void updatePointStateToNextPosition(PointState ps)
    {
        if (ps.isStart)
            return;
        ps.position = ps.position.move(ps.direction);
        ps.costOfThisPath = ps.costOfThisPath + ps.costOfThisMove;
    }

    private void preparePointStateForNextMoveAndAddToQueue(PointState ps, Direction nextDirection, LinkedList<Position> path, LinkedList<String> keySequence)
    {
        PointState psClone = new PointState(ps.position, nextDirection, getMoveCost(), ps.costOfThisPath, path, keySequence);
        pointsInQueue.add(psClone);
        pointsLookedAt.add(psClone.position);
        costMap.getValueAt(psClone.position).putIfAbsent(psClone.direction, psClone.costOfThisPath + psClone.costOfThisMove);
        directionXYMap.setValueAt(psClone.position, ps.direction);
    }

    private void addToSuccessfulPaths(List<Position> path, Integer score)
    {

        List<List<Position>> pathsAtThatScore = successfulPaths.computeIfAbsent(score, k -> new ArrayList<>());
        pathsAtThatScore.add(path.stream().toList());
        bestPathCost = getBestPathCost();

    }

    private void addToSuccessfulSequences(List<String> keySequence, Integer score)
    {

        List<List<String>> keySequencesAtThatScore = possibleSequences.computeIfAbsent(score, k -> new ArrayList<>());
        keySequencesAtThatScore.add(keySequence.stream().toList());

    }

    public int getBestPathCost()
    {
        return successfulPaths.keySet().stream().sorted().findFirst().orElse(Integer.MAX_VALUE);
    }

    public int getShortestSequence()
    {
        return possibleSequences.keySet().stream().sorted().findFirst().orElse(Integer.MAX_VALUE);
    }

    private boolean canMoveForward(Position currentPosition, Direction currentDirection)
    {
        Direction dir = currentDirection == null ? UP : currentDirection;
        Position testPosition = currentPosition.move(dir);
        if (!keypad.keyMap.isOnMap(testPosition))
            return false;
        KeypadKey blockingObject = keypad.keyMap.getValueAt(testPosition);
        return !(blockingObject instanceof NullKey);
    }

    private boolean costIsWithinReason(PointState ps, Direction nextDirection)
    {
        int costAtThisPosition = costMap.getValueAt(ps.position).getOrDefault(nextDirection, Integer.MAX_VALUE);
        return ps.costOfThisPath <= costAtThisPosition && (ps.costOfThisPath + getMoveCost()) <= bestPathCost;
    }

    public List<String> type(String keys)
    {
        List<String> shortestSequences = new ArrayList<>();
        shortestSequences.add("");
        List<String> newShortestSequences = new ArrayList<>();
        for (int k = 0; k < keys.length(); k++)
        {
            newShortestSequences.clear();
            List<List<String>> sequenceList = getSequenceForAndType(keys.substring(k, k + 1));
            shortestSequences.forEach(seq -> {;
                sequenceList.forEach(list -> {
                    StringBuilder sequenceString = new StringBuilder(seq);
                    list.forEach(sequenceString::append);
                    sequenceString.append("A");
                    newShortestSequences.add(sequenceString.toString());
                });
            });
            shortestSequences.clear();
            shortestSequences.addAll(newShortestSequences);
        }
        return shortestSequences;
    }

    private List<List<String>> getSequenceForAndType(String key)
    {
        Map<Integer, List<List<String>>> sequenceList = getSequenceListFor(keypad.currentKey.symbol, key);
        int shortestSequenceLength = getShortestSequence();
        List<List<String>> sequence = sequenceList.get(shortestSequenceLength);
        keypad.setCurrentKey(keypad.getKey(key));
        return sequence;
    }

    public String applySequence(String[] keyseg) {
        return applySequence(keypad.getKey("A"), keyseg);
    }

    public String applySequence(KeypadKey startKey, String[] keyseg)
    {
        String output = "";
        keypad.setCurrentKey(startKey);
        Position currentPosition = keypad.getCurrentKey().getPosition();
        for (int ks=0; ks< keyseg.length; ks++) {
            String seq = keyseg[ks];
            String thisSeqOutput = "";
            for (int i=0; i<seq.length(); i++)
            {
                String key = String.valueOf(seq.charAt(i));
                if (key.equals("A"))
                {
                    keypad.setCurrentKey(keypad.getKey(currentPosition));
                    thisSeqOutput += keypad.getCurrentKey().getSymbol();
                    break;
                }
                Direction dir = convertKeyToDirection(key);
                if (canMoveForward(currentPosition, dir))
                    currentPosition = currentPosition.move(dir);
                else
                    throw new RuntimeException("Illegal move from key '" + keypad.getKey(currentPosition).getSymbol()
                            + "' in " + dir + " direction occurred at sequence '" + seq + "' ("+ ks + ")");
            }
            output += thisSeqOutput;
        }
        return output;
    }

}
