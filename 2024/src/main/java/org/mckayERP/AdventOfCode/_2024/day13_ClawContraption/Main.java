package org.mckayERP.AdventOfCode._2024.day13_ClawContraption;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer();
        player.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: The total cost to win is " + player.getTotalCost());
        System.out.println("Part 2: The total cost to win is " + player.getTotalCost(true));

    }
}
