package org.mckayERP.AdventOfCode._2024.day12_GardenGroups;

import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;
import org.mckayERP.AdventOfCode.utilities.XYMap;

import static org.mckayERP.AdventOfCode.utilities.Direction.*;

public class EdgeDetector
{
    XYMap<Integer> map;
    Region region;

    public void setMap(XYMap<Integer> map)
    {
        this.map = map;
    }

    public void findEdges()
    {
        region.plots.stream().filter(p -> isOnEdge(p)).forEach(p -> findEdge(p));
    }

    private void findEdge(Position p)
    {
        if (!isOnEdge(p))
            return;

        for (Direction dir : Direction.values())
        {
            findEdge(dir, p);
        }

    }


    private void findEdge(Direction dir, Position p)
    {
        Direction edgeDirection = dir;
        Direction startDirection = edgeDirection == LEFT || edgeDirection == RIGHT ? UP : LEFT;
        Direction endDirection = edgeDirection == LEFT || edgeDirection == RIGHT ? DOWN : RIGHT;
        if(! hasEdgeOn(edgeDirection, p))
            return;

        if (foundOnEdge(p, edgeDirection))
            return;

        Position start = p.clone();
        Edge edge = new Edge();
        edge.edgeDirection = edgeDirection;
        while(hasEdgeOn(edgeDirection, start)
                && !hasEdgeOn(startDirection, start)
                && hasEdgeOn(edgeDirection, start.move(startDirection))) {
            start = start.move(startDirection);
        }
        edge.start = start;
        edge.positions.add(start);
        Position end = start.clone();
        while(hasEdgeOn(edgeDirection, end)
                && !hasEdgeOn(endDirection, end)
                && hasEdgeOn(edgeDirection, end.move(endDirection))) {
            end = end.move(endDirection);
            edge.positions.add(end.clone());
        }
        edge.end = end;
        region.edges.add(edge);

    }


    private void findRightEdge(Position p)
    {

        if(! hasEdgeOnRight(p))
            return;

        if (foundOnEdge(p, RIGHT))
            return;

        Position start = p.clone();
        Edge edge = new Edge();
        edge.edgeDirection = RIGHT;
        while(hasEdgeOn(RIGHT, start) && !hasEdgeOn(UP, start) && hasEdgeOn(RIGHT, start.move(UP))) {
            start = start.move(UP);
        }
        edge.start = start;
        edge.positions.add(start);
        Position end = start.clone();
        while(hasEdgeOn(RIGHT, end) && !hasEdgeOn(DOWN, end) && hasEdgeOn(RIGHT, end.move(DOWN))) {
            end = end.move(DOWN);
            edge.positions.add(end.clone());
        }
        edge.end = end;
        region.edges.add(edge);

    }

    private boolean foundOnEdge(Position p, Direction dir)
    {
        return region.edges.stream().anyMatch(edge ->
            edge.positions.contains(p)
            && edge.edgeDirection == dir
        );
    }

    private boolean isOnEdge(Position p)
    {
        return hasEdgeOnRight(p) || hasEdgeOnLeft(p)
                || hasEdgeOnTop(p) || hasEdgeOnBottom(p);
    }

    public void setRegion(Region region)
    {
        this.region = region;
    }


    public boolean hasEdgeOnRight(Position position)
    {
        Direction dir = Direction.RIGHT;
        return hasEdgeOn(dir, position);
    }

    private boolean hasEdgeOn(Direction dir, Position position)
    {
        Position newPos = position.move(dir);
        return !map.isOnMap(newPos) || !region.contains(newPos);
    }

    public boolean hasEdgeOnLeft(Position position)
    {
        return hasEdgeOn(LEFT, position);
    }

    public boolean hasEdgeOnTop(Position position)
    {
        return hasEdgeOn(Direction.UP, position);
    }

    public boolean hasEdgeOnBottom(Position position)
    {
        return hasEdgeOn(DOWN, position);
    }

}
