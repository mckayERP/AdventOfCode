package org.mckayERP.AdventOfCode._2024.day14_RestroomRobot;

import org.mckayERP.AdventOfCode.utilities.Position;

public class Robot
{
    Position initialPosition;
    Position currentPosition;
    Velocity velocity;

    public Robot() {}
    public Robot(Position position, Velocity velocity)
    {
        this.currentPosition = position;
        this.initialPosition = position.clone();
        this.velocity = velocity;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void resetPosition() {
        currentPosition = initialPosition.clone();
    }

    public boolean nextTo(Robot r)
    {
        return this.currentPosition.isNextTo(r.currentPosition);
    }
}
