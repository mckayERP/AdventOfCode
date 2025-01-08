package org.mckayERP.AdventOfCode._2024.day25_CodeChronicle;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchematicReaderTest

{

    @Test
    public final void testCanReadALock() {
        String[] lockInput = new String[] {
            "#####",
            ".####",
            ".####",
            ".####",
            ".#.#.",
            ".#...",
            "....."
        };

        SchematicReader reader = new SchematicReader();
        reader.readInput(lockInput);
        List<Lock> locks = reader.getLocks();
        Lock lock = locks.get(0);
        assertEquals("0,5,3,4,3",lock.getPatternString());
    }

    @Test
    public final void testCanReadAKey() {
        String[] keyInput = new String[] {
                ".....",
                "#....",
                "#....",
                "#...#",
                "#.#.#",
                "#.###",
                "#####"
        };

        SchematicReader reader = new SchematicReader();
        reader.readInput(keyInput);
        List<Key> keys = reader.getKeys();
        Key key = keys.get(0);
        assertEquals("5,0,2,1,3",key.getHeightString());
    }

    @Test
    public final void testCanReadTestInput() {
        SchematicReader reader = new SchematicReader();
        reader.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(2, reader.getLocks().size());
        assertEquals(3, reader.getKeys().size());
        List<Lock> locks = reader.getLocks();
        Lock lock = locks.get(0);
        assertEquals("0,5,3,4,3",lock.getPatternString());
        List<Key> keys = reader.getKeys();
        Key key = keys.get(0);
        assertEquals("5,0,2,1,3",key.getHeightString());
    }

    @Test
    public final void testCanDetermineIfThereIsOverlap() {
        Lock lock1 = new Lock(new int[] {0,5,3,4,3});
        Key key1 = new Key(new int[] {5,0,2,1,3});
        Key key2 = new Key(new int[] {4,3,4,0,2});
        Key key3 = new Key(new int[] {3,0,2,0,1});
        assertFalse(lock1.keyFits(key1));
        assertFalse(lock1.keyFits(key2));
        assertTrue(lock1.keyFits(key3));
    }

    @Test
    public final void testCanDetermineTheTotalNumberOfFits() {
        SchematicReader reader = new SchematicReader();
        reader.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(3, reader.determineNumberOfFits());
    }
}
