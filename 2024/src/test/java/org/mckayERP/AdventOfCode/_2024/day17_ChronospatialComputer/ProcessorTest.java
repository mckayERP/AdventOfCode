package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest
{
    ThreeBitProcessor processor;

    @BeforeEach
    public final void setup() {
        processor = new ThreeBitProcessor();
    }

    @Test
    public final void testInstructionPointerCanReadProgram() {
        processor.setInstructionPointer(1);
        assertEquals(1, processor.getInstructionPointer());
    }

    @Test
    public final void testInstructionPointCanReadInstructions() {
        processor.loadProgram(new int[] {0,1,2,3});
        processor.setInstructionPointer(0);
        assertTrue(processor.hasInstructions());
        Instruction instruction = processor.getNextInstruction();
        assertEquals(0, instruction.getOpcode());
        assertEquals(1, instruction.getOperand());
        instruction = processor.getNextInstruction();
        assertEquals(2, instruction.getOpcode());
        assertEquals(3, instruction.getOperand());
        assertFalse(processor.hasInstructions());
    }

    @Test
    public final void testProcessorCanReadLiteralOperandValues() {
        assertEquals(0,processor.getOperandValue(0, OperandType.LITERAL));
        assertEquals(1,processor.getOperandValue(1, OperandType.LITERAL));
        assertEquals(2,processor.getOperandValue(2, OperandType.LITERAL));
        assertEquals(3,processor.getOperandValue(3, OperandType.LITERAL));
        assertEquals(4,processor.getOperandValue(4, OperandType.LITERAL));
        assertEquals(5,processor.getOperandValue(5, OperandType.LITERAL));
        assertEquals(6,processor.getOperandValue(6, OperandType.LITERAL));
        assertEquals(7,processor.getOperandValue(7, OperandType.LITERAL));

    }

    @Test
    public final void testOperand4to8ReadsRegABCValues() {
        processor.setRegA(8);
        processor.setRegB(9);
        processor.setRegC(10);
        assertEquals(0,processor.getOperandValue(0, OperandType.COMBO));
        assertEquals(1,processor.getOperandValue(1, OperandType.COMBO));
        assertEquals(2,processor.getOperandValue(2, OperandType.COMBO));
        assertEquals(3,processor.getOperandValue(3, OperandType.COMBO));
        assertEquals(8,processor.getOperandValue(4, OperandType.COMBO));
        assertEquals(9,processor.getOperandValue(5, OperandType.COMBO));
        assertEquals(10,processor.getOperandValue(6, OperandType.COMBO));
    }

    @Test
    public final void testThrowsExceptionWhenOperandValueIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> processor.getOperandValue(7, OperandType.COMBO));
        assertThrows(IllegalArgumentException.class, () -> processor.getOperandValue(8, OperandType.COMBO));
    }

    @Test
    public final void check2ToThePower() {
        assertEquals(3, 19/5);
    }

    @Test
    public final void testSimpleProgram1() {
        processor.setRegC(9);
        processor.loadProgram(new int[] {2,6});
        processor.run();
        assertEquals(1, processor.getRegB());
    }

    @Test
    public final void testSimpleProgram2() {
        processor.setRegA(10);
        processor.loadProgram(new int[] {5,0,5,1,5,4});
        processor.run();
        assertEquals("0,1,2", processor.getOutput());
    }

    @Test
    public final void testSimpleProgram3() {
        processor.setRegA(2024);
        processor.loadProgram(new int[] {0,1,5,4,3,0});
        processor.run();
        assertEquals("4,2,5,6,7,7,7,7,3,1,0", processor.getOutput());
    }

    @Test
    public final void testSimpleProgram4() {
        processor.setRegB(29);
        processor.loadProgram(new int[] {1,7});
        processor.run();
        assertEquals(26, processor.getRegB());
    }

    @Test
    public final void testSimpleProgram5() {
        processor.setRegB(2024);
        processor.setRegC(43690);
        processor.loadProgram(new int[] {4,0});
        processor.run();
        assertEquals(44354, processor.getRegB());
    }

    @Test
    public final void testXOR3() {
        for (int i = 0; i<8; i++) {
            System.out.println(i + " ^ 3 = " + (i^3));
        }
    }

    @Test
    public final void testXOR5() {
        for (int i = 0; i<8; i++) {
            System.out.println(i + " ^ 5 = " + (i^5));
        }
    }

    @Test
    public final void testXOR3XOR5() {
        for (int i = 0; i<8; i++) {
            System.out.println(i + " ^3 ^ 5 = " + (i^3^5));
        }
    }

    @Test
    public final void testGetOutputZero() {
        processor.setRegA(53);
        processor.loadProgram(new int[] {2,4,1,3,7,5,1,5,0,3,4,1,5,5,3,0});
        processor.run();
        assertEquals("3,0", processor.getOutput());
    }
}
