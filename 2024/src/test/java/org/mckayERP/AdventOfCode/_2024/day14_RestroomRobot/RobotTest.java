package org.mckayERP.AdventOfCode._2024.day14_RestroomRobot;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RobotTest
{
    @Test
    public final void testConstructor() {
        Robot robot = new Robot();
    }

    @Test
    public final void testConstructorWithPosition() {
        Robot robot = new Robot(new Position(1,2), new Velocity(2,-3));
    }

}
