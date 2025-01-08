package org.mckayERP.AdventOfCode._2024.day23_LanParty;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.readInputList(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        System.out.println("Part 1: Count of tri connections starting with 't' is " + manager.getCountOfConnectionsWithComputersStartingWithT());
        System.out.println("Part 2: The password is : " + manager.getPasswordBasedOnSet(manager.getLargestSetOfConnectedComputers()));
    }
}
