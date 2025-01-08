package org.mckayERP.AdventOfCode._2024.day15_WarehouseWoes;

import org.mckayERP.AdventOfCode.utilities.MapObject;
import org.mckayERP.AdventOfCode.utilities.Direction;
import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.List;

public class Robot extends AbstractWarehouseObject implements WarehouseObject, MapObject
{

    List<Direction> instructions;

    public Robot(int row, int col, Warehouse wh)
    {
        super(row, col, wh);
    }

    public Robot(int row, int col, boolean doubleWidth, Warehouse wh)
    {
        super(row, col, doubleWidth, wh);
    }

    @Override
    public String getSymbol()
    {
        return "@";
    }

    public void setInstructions(List<Direction> instructions)
    {
        this.instructions = instructions;
    }

    public void runInstructions()
    {
        instructions.forEach(this::move);
    }

    @Override
    public boolean move(Direction dir)  // robot specific
    {
        boolean canMove = false;
        initialPosition = position.clone();
        Position targetPosition = position.clone().move(dir);
        if (wh.doubleWidth)
        {
            switch (dir) {
                case LEFT -> canMove = wh.spaceEmpty(targetPosition, 2, dir)
                        || wh.spaceEmpty(targetPosition, 1, dir)
                        || ((WarehouseObject) wh.getContentAt(targetPosition)).move(dir);
                case UP, DOWN, RIGHT ->
                {
                    if (wh.spaceEmpty(targetPosition, 1, dir))
                        canMove = true;
                    else
                    {
                        WarehouseObject who1 = (WarehouseObject) wh.getContentAt(targetPosition);
                        canMove = (who1 == null || who1.move(dir));
                    }
                }
            }
        } else
        {
            if (wh.spaceEmpty(targetPosition, getWidth(), dir) || ((WarehouseObject) wh.getContentAt(targetPosition)).move(dir))
            {
                canMove = true;
            }
        }

        if (canMove)
        {
            initialPosition = targetPosition;
            position = targetPosition;
            wh.save();
            return true;
        }
        else
            wh.rollback();
        return false;
    }

    @Override
    public int getWidth() {
        return 1;
    }
}
