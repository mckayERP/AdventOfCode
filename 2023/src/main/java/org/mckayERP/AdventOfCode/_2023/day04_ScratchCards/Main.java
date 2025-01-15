package org.mckayERP.AdventOfCode._2023.day04_ScratchCards;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args)
    {
        CardReader reader = new CardReader();
        reader.read(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: the sum of the card value is " + reader.getSumOfCardValue());
        System.out.println("Part 2: the total number of cards won is " + reader.getTotalCardsWon());
    }
}
