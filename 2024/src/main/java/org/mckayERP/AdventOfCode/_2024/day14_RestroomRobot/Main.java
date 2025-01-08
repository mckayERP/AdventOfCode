package org.mckayERP.AdventOfCode._2024.day14_RestroomRobot;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        RobotTracker tracker = new RobotTracker();
        tracker.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        tracker.setGridLimits(103,101);
        tracker.move(100);
        System.out.println("Part 1: the product of the sum of robots in each quadrant is " + tracker.getCountInQuadrants());
        System.out.println("Part 2: the smallest Std Dev occurs at " + tracker.xmasTreeFinder());

    }

}
