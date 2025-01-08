package org.mckayERP.AdventOfCode._2024.day09_DiskFragmenter;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(Main.class, "input.txt"));
        reader.move();
        System.out.println("Part 1: the checksum is " + reader.getChecksum());

        reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(Main.class, "input.txt"));
        reader.diskCleanup();
        System.out.println("Part 2: the checksum is " + reader.getChecksum());
    }
}
