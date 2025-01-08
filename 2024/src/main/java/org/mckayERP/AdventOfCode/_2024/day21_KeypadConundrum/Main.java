package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");
        RobotController controller = new RobotController();
        long totalComplexity = controller.getTotalComplexity(input, 2);
        System.out.println("Part 1: Total complexity is " + totalComplexity);
        totalComplexity = controller.getTotalComplexity(input, 25);
        System.out.println("Part 2: With 26 directional keyboards, total complexity is " + totalComplexity);
    }
}
