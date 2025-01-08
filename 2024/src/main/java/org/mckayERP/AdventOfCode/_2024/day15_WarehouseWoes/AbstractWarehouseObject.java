package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.Objects;

import static org.mckayERP.AdventOfCode.utilities.Direction.RIGHT;

public abstract class AbstractWarehouseObject implements WarehouseObject, MapObject
{
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractWarehouseObject that = (AbstractWarehouseObject) o;
        return doubleWidth == that.doubleWidth && Objects.equals(wh, that.wh) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(wh, position, doubleWidth);
    }

    public Position initialPosition;

    @Override
    public Position getPosition()
    {
        return position;
    }

    Warehouse wh;
    Position position;

    int widthSupplement = 1;
    boolean doubleWidth = false;

    public AbstractWarehouseObject(int row, int col, Warehouse wh)
    {
        this(row, col, false, wh);
    }

    public AbstractWarehouseObject(int row, int col, boolean doubleWidth, Warehouse wh)
    {
        this.doubleWidth = doubleWidth;
        this.wh = wh;
        if (wh.doubleWidth && !doubleWidth)
            throw new IllegalArgumentException("Warehouse is using double width but the object is created without it.");
        position = new Position(row, col);
        initialPosition = position.clone();
        if (wh.spaceEmpty(position, getWidth(), null))
            wh.add(this);
        else
            throw new IllegalArgumentException("Warehouse spot is occupied " + position);
    }

    @Override
    public boolean move(Direction dir)
    {
        Position targetPosition = position.clone().move(dir);
        initialPosition = position.clone();
        if (wh.doubleWidth)
        {
            boolean canMove = false;
            switch (dir) {
                case LEFT -> canMove = wh.spaceEmpty(targetPosition, 2, dir)
                        || wh.spaceEmpty(targetPosition, 1, dir)
                        || ((WarehouseObject) wh.getContentAt(targetPosition)).move(dir);
                case UP, DOWN ->
                {
                    if (wh.spaceEmpty(targetPosition, 1, dir) && wh.spaceEmpty(targetPosition.clone().move(RIGHT), 1, dir))
                        canMove = true;
                    else
                    {
                        WarehouseObject who1 = (WarehouseObject) wh.getContentAt(targetPosition);
                        WarehouseObject who2 = (WarehouseObject) wh.getContentAt(targetPosition.clone().move(RIGHT));
                        if (who1 != null && who1.equals(who2))
                            who2 = null;
                        canMove = (who1 == null || who1.move(dir)) && (who2 == null || who2.move(dir));
                    }
                }
                case RIGHT ->
                {
                    if (wh.spaceEmpty(targetPosition.clone().move(RIGHT), 1, dir))
                        canMove = true;
                    else
                    {
                        WarehouseObject who2 = (WarehouseObject) wh.getContentAt(targetPosition.clone().move(RIGHT));
                        canMove = who2 == null || who2.move(dir);
                    }
                }
            }
            if (canMove) {
                position = targetPosition;
            }
            return canMove;

        } else
        {
            if (wh.spaceEmpty(targetPosition, getWidth(), dir)
                    || ((WarehouseObject)wh.getContentAt(targetPosition)).move(dir))
            {
                initialPosition = targetPosition;
                position = targetPosition;
                return true;
            }
        }
        return false;
    }

    public int getGPSCoord()
    {
//        if (doubleWidth) {
//            int distanceToClosestLREdge = Math.min(position.getCol(), wh.maxCol-position.getCol());
//            int distanceToClosestTBEdge = Math.min(position.getRow(), wh.maxRow-position.getRow());
//            return  distanceToClosestTBEdge*100 + distanceToClosestLREdge;
//        }
//        else
            return position.getRow() * 100 + position.getCol();
    }

    @Override
    public abstract String getSymbol();

    public int getWidth()
    {
        return doubleWidth ? 2 : 1;
    }

    public void rollback() {
        this.position = initialPosition.clone();
    }

    @Override
    public String toString() {
        return this.position.toString();
    }

    public void save() {
        this.initialPosition = position.clone();
    }
}
