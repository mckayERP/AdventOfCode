package org.mckayERP.AdventOfCode._2024.day22_MonkeyMarket;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        String[] inputs = ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt");
        SecretNumberGenerator generator = new SecretNumberGenerator();
        System.out.println("Part 1: The total sum of the 2000th secret is : " + generator.getTotalSum(inputs, 2000));
        System.out.println("Part 2: The maximum sum of bananas we can get is : " + generator.getMaximumNumberOfBananas());
    }
}
