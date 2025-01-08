package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum.DirectionalKeypad;
import org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum.NumericKeypad;
import org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum.RobotController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RobotControllerTest
{
    RobotController controller;

    @BeforeEach
    public final void setup() {
        controller = new RobotController();
    }

    @Test
    public final void testConstructorAndOneKeypad() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        List<String> results = controller.getSequencesToType("029A");
        assertEquals(1, results.size());
        assertTrue(results.contains("<A^A^^>AvvvA"));
    }

    @Test
    public final void testConstructorAndTwoKeypads() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> results = controller.getSequencesToType("029A");
        assertEquals(1, results.size());
        assertTrue(controller.getSequencesToType("029A").contains("v<<A>>^A<A>A<AAv>A^A<vAAA^>A"));
    }

    @Test
    public final void testConstructorAndThreeKeypads() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> results = controller.getSequencesToType("029A");
        assertEquals(68, results.get(0).length());
    }

    @Test
    public final void testConstructorAndThreeKeypads379() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        assertEquals(64, controller.getSequencesToType("379A").get(0).length());
    }

    @Test
    public final void testOneKeypad379A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        assertTrue(controller.getSequencesToType("379A").contains("^A<<^^A>>AvvvA"));
    }

    @Test
    public final void testTwoKeypads379A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("379A");
        assertEquals(1, result.size());
        assertTrue(result.contains("<A>Av<<AA>^AA>AvAA^A<vAAA^>A"));
    }

    @Test
    public final void testThreeKeypads379A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("379A");
        assertTrue(result.contains("v<<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA^>AA<A>Av<<A>A^>AAA<Av>A^A"));
    }

    @Test
    public final void testOneKeypads0() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        List<String> result = controller.getSequencesToType("0");
        assertEquals(1, result.size());
        assertTrue(result.contains("<A"));
    }

    @Test
    public final void testTwoKeypads0() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("0");
        assertEquals(1, result.size());
        assertTrue(result.contains("v<<A>>^A"));
    }

    @Test
    public final void testThreeKeypads0() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("0");
        assertEquals(18, result.get(0).length());
        assertTrue(result.contains("<vA<AA>>^AvAA<^A>A"));
    }

    @Test
    public final void testOneKeypads179A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        List<String> result = controller.getSequencesToType("179A");
        assertTrue(result.contains("^<<A^^A>>AvvvA"));
    }

    @Test
    public final void testTwoKeypads179A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("179A");
        assertEquals("<Av<AA>>^A<AA>AvAA^A<vAAA^>A", result.get(0));
    }

    @Test
    public final void testThreeKeypads179A() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("179A");
        assertEquals(68, result.get(0).length());
        assertTrue(result.contains("v<<A>>^A<vA<A>>^AAvAA<^A>Av<<A>>^AAvA^A<vA^>AA<A>Av<<A>A^>AAA<Av>A^A"));
    }

    @Test
    public final void testThreeKeypadsLength179A() {
        RobotController controller = new RobotController();
        Long length = controller.getShortestSequenceLengthForNumberPadSequence("179A", 2);
        assertEquals(68, length);
    }

    @Test
    public final void test4KeypadsLength179A() {
        RobotController controller = new RobotController();
    }

    @Test
    public final void testOrderingWithOneDirectionalKeypadsToAvoidNullKey() {
        RobotController controller = new RobotController();
        controller.addKeypad(new DirectionalKeypad());
        assertEquals("v<<A>A^>A", controller.getSequencesToType("<vA").get(0));
        assertEquals("<vA<A>>^A", controller.getSequencesToType("v<A").get(0));
    }

    @Test
    public final void testOrderingWithTwoDirectionalKeypadsToAvoidNullKey() {
        RobotController controller = new RobotController();
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        List<String> result = controller.getSequencesToType("<vA");
        assertEquals(21, result.get(0).length());
        assertTrue(result.contains("<vA<AA>>^AvA^A<Av>A^A"));
        result = controller.getSequencesToType("v<A");
        assertEquals(25, result.get(0).length());
        assertTrue(result.contains("v<<A>A^>Av<<A>>^AvAA<^A>A"));
    }

    @Test
    public final void testComplexityAndThreeKeypads() {
        RobotController controller = new RobotController();
        assertEquals(68*29, controller.getComplexity("029A"));
    }

    @Test
    public final void testLengthAndThreeKeypads980A() {
        RobotController controller = new RobotController();
        assertEquals(60, controller.getShortestSequenceLengthForNumberPadSequence("980A", 2));
    }

    @Test
    public final void testComplexityAndThreeKeypads980A() {
        RobotController controller = new RobotController();
        assertEquals(60*980, controller.getComplexity("980A"));
    }

    @Test
    public final void testComplexityAndThreeKeypads179A() {
        RobotController controller = new RobotController();
        assertEquals(68*179, controller.getComplexity("179A"));
    }

    @Test
    public final void testComplexityAndThreeKeypads456() {
        RobotController controller = new RobotController();
        assertEquals(64*456, controller.getComplexity("456A"));
    }

    @Test
    public final void testComplexityAndThreeKeypads379() {
        RobotController controller = new RobotController();
        assertEquals(64*379, controller.getComplexity("379A"));
    }

    @Test
    public final void testTotalComplexityAndThreeKeypads() {
        String[] input = new String[] {
          "029A",
          "980A",
          "179A",
          "456A",
          "379A"
        };
        RobotController controller = new RobotController();
        assertEquals(126384, controller.getTotalComplexity(input));
    }

    @Test
    public final void testTotalComplexityAnd25Keypads() {
        String[] input = new String[] {
                "029A",
                "980A",
                "179A",
                "456A",
                "379A"
        };
        RobotController controller = new RobotController();
        assertEquals(154115708116294L, controller.getTotalComplexity(input, 25));
    }

    @Test
    public final void testScoresAsKeyboardsAreAdded() {
        RobotController controller = new RobotController();
        assertEquals(12, controller.getShortestSequenceLengthForNumberPadSequence("029A", 0));
        assertEquals(28, controller.getShortestSequenceLengthForNumberPadSequence("029A", 1));
        assertEquals(68, controller.getShortestSequenceLengthForNumberPadSequence("029A", 2));
        assertEquals(164, controller.getShortestSequenceLengthForNumberPadSequence("029A", 3));
        assertEquals(404, controller.getShortestSequenceLengthForNumberPadSequence("029A", 4));
        assertEquals(998, controller.getShortestSequenceLengthForNumberPadSequence("029A", 5));
        assertEquals(2482, controller.getShortestSequenceLengthForNumberPadSequence("029A", 6));
        assertEquals(6166, controller.getShortestSequenceLengthForNumberPadSequence("029A", 7));
        assertEquals(15340, controller.getShortestSequenceLengthForNumberPadSequence("029A", 8));
        assertEquals(38154, controller.getShortestSequenceLengthForNumberPadSequence("029A", 9));
        assertEquals(94910, controller.getShortestSequenceLengthForNumberPadSequence("029A", 10));
        assertEquals(236104, controller.getShortestSequenceLengthForNumberPadSequence("029A", 11));
        assertEquals(587312, controller.getShortestSequenceLengthForNumberPadSequence("029A", 12));
        assertEquals(1461046, controller.getShortestSequenceLengthForNumberPadSequence("029A", 13));
        assertEquals(3634472, controller.getShortestSequenceLengthForNumberPadSequence("029A", 14));
        assertEquals(9041286, controller.getShortestSequenceLengthForNumberPadSequence("029A", 15));
        assertEquals(22491236, controller.getShortestSequenceLengthForNumberPadSequence("029A", 16));
        assertEquals(55949852, controller.getShortestSequenceLengthForNumberPadSequence("029A", 17));
        assertEquals(139182252, controller.getShortestSequenceLengthForNumberPadSequence("029A", 18));
        assertEquals(346233228, controller.getShortestSequenceLengthForNumberPadSequence("029A", 19));
        assertEquals(861298954, controller.getShortestSequenceLengthForNumberPadSequence("029A", 20));
        assertEquals(2142588658, controller.getShortestSequenceLengthForNumberPadSequence("029A", 21));
        assertEquals(5329959430L, controller.getShortestSequenceLengthForNumberPadSequence("029A", 22));
        assertEquals(13258941912L, controller.getShortestSequenceLengthForNumberPadSequence("029A", 23));
        assertEquals(32983284966L, controller.getShortestSequenceLengthForNumberPadSequence("029A", 24));
        assertEquals(82050061710L, controller.getShortestSequenceLengthForNumberPadSequence("029A", 25));
    }

    @Test
    public final void testScoresWith25Keyboards() {
        RobotController controller = new RobotController();
        assertEquals(82050061710L, controller.getShortestSequenceLengthForNumberPadSequence("029A", 25));
    }

    @Test
    public final void testTotalComplexityWith25Keyboards() {
        RobotController controller = new RobotController();
        assertEquals(29*82050061710L, controller.getTotalComplexity(new String[] {"029A"}, 25));
    }


    @Test
    public final void testScoresWith25Keyboards670A() {
        RobotController controller = new RobotController();
        assertEquals(84248089344L, controller.getShortestSequenceLengthForNumberPadSequence("670A", 25));
    }

    @Test
    public final void testScoresWith25Keyboards974A() {
        RobotController controller = new RobotController();
        assertEquals(85006969638L, controller.getShortestSequenceLengthForNumberPadSequence("974A", 25));
    }

    @Test
    public final void testScoresWith25Keyboards638A() {
        RobotController controller = new RobotController();
        assertEquals(86475783010L, controller.getShortestSequenceLengthForNumberPadSequence("638A", 25));
    }

    @Test
    public final void testScoresWith25Keyboards319A() {
        RobotController controller = new RobotController();
        assertEquals(82050061712L, controller.getShortestSequenceLengthForNumberPadSequence("319A", 25));
    }

    @Test
    public final void testScoresWith25Keyboards508A() {
        RobotController controller = new RobotController();
        assertEquals(86475783012L, controller.getShortestSequenceLengthForNumberPadSequence("508A", 25));
    }


    @Test
    public final void testPrintOfKeyboardPatterns() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.printSequence("<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A");
    }

    @Test
    public final void testThrowsExceptionWhenPatternViolatesBlankKey() {
        RobotController controller = new RobotController();
        controller.addKeypad(new NumericKeypad());
        controller.addKeypad(new DirectionalKeypad());
        controller.addKeypad(new DirectionalKeypad());
        assertThrows(RuntimeException.class, () -> {
            controller.printSequence("v<<A^>>AvA^Av<A<AA^>>AAvA^<A>AAvA^Av<A^>AA<A>Av<<A>A^>AAAvA^<A>A");
        });
    }

    @Test
    public final void testTotalComplexityAnd25KeypadsWithPuzzleInput() {
        String[] input = new String[] {
                "670A",
                "974A",
                "638A",
                "319A",
                "508A"
        };
        RobotController controller = new RobotController();
        assertEquals(264518225304496L, controller.getTotalComplexity(input, 25));
    }

}
