package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum.NumericKeypad;
import org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum.SequenceManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequenceManagerTest
{
    @Test
    public final void testConstructor() {
        SequenceManager manager = new SequenceManager(new NumericKeypad());
        Map<Integer, List<List<String>>> sequences = manager.getSequenceListFor("A","0");
        int bestSequenceLength = manager.getShortestSequence();
        assertEquals(1, manager.getSequenceLengthFor("A","0"));
        assertEquals(1, sequences.size());
        assertEquals("<", sequences.get(bestSequenceLength).get(0).stream().collect(Collectors.joining()));
    }

    @Test
    public final void testKeySequence() {
        SequenceManager manager = new SequenceManager(new NumericKeypad());
        assertEquals("<A", manager.type("0").get(0));
    }

    @Test
    public final void testTwoKeySequence() {
        SequenceManager manager = new SequenceManager(new NumericKeypad());
        assertEquals("<A^A", manager.type("02").get(0));
    }

    @Test
    public final void testLongerKeySequence() {
        SequenceManager manager = new SequenceManager(new NumericKeypad());
        List<String> result = manager.type("029A");
        assertEquals(1, result.size());
//        assertTrue(result.contains("<A^A>^^AvvvA"));
//        assertTrue(result.contains("<A^A^>^AvvvA"));
        assertTrue(result.contains("<A^A^^>AvvvA"));
    }

    @Test
    public final void testLongerKeySequence2() {
        SequenceManager manager = new SequenceManager(new NumericKeypad());
        List<String> result = manager.type("379A");
        assertEquals(1, result.size());
//        assertTrue(result.contains("^A^^<<A>>AvvvA"));
//        assertTrue(result.contains("^A^<^<A>>AvvvA"));
//        assertTrue(result.contains("^A^<<^A>>AvvvA"));
//        assertTrue(result.contains("^A<^^<A>>AvvvA"));
//        assertTrue(result.contains("^A<^<^A>>AvvvA"));
        assertTrue(result.contains("^A<<^^A>>AvvvA"));
    }

}
