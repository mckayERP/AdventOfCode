package org.mckayERP.AdventOfCode._2024.day09_DiskFragmenter;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskmapReader_Test
{
    @Test
    public final void testConstructor() {
        DiskmapReader reader = new DiskmapReader();
        reader.read("12345");
        assertEquals(15, reader.getDiskSize());
        assertEquals("0..111....22222", reader.getDiskAsString());
    }

    @Test
    public final void testMoveOfBlocks()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read("12345");
        reader.move();
        assertEquals("022111222......", reader.getDiskAsString());
    }

    @Test
    public final void testMoveOfBlocksWithInput()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt"));
        reader.move();
        assertEquals("0099811188827773336446555566..............", reader.getDiskAsString());
    }

    @Test
    public final void testGetChecksupWithSample()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt"));
        reader.move();
        assertEquals(1928, reader.getChecksum());
    }

    @Test
    public final void testGetFirstEmptyBlock()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt"));
        assertEquals(3, reader.getFirstFreeBlock().getSize());
        assertEquals(2, reader.getLastFileBlock().getSize());
    }

    @Test
    public final void testDiskCleanupWithInput()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt"));
        reader.diskCleanup();
        assertEquals("00992111777.44.333....5555.6666.....8888..", reader.getDiskAsString());
    }

    @Test
    public final void testChecksumFollowingDiskCleanupWithInput()
    {
        DiskmapReader reader = new DiskmapReader();
        reader.read(ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt"));
        reader.diskCleanup();
        assertEquals(2858, reader.getChecksum());
    }

}
