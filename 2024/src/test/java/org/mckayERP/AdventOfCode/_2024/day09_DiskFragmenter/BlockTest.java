package org.mckayERP.AdventOfCode._2024.day09_DiskFragmenter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTest
{
    @Test
    public final void testConstructor() {
        DiscBlock block = new DiscBlock();
        block.setStartingIndex(1);
        block.setEndingIndex(2);
        block.setEmpty(true);
        assertEquals(2,block.getSize());
        assertTrue(block.isEmpty());

    }
}
