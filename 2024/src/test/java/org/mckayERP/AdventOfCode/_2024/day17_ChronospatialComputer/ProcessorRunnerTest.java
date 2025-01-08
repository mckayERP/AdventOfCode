package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessorRunnerTest
{

    @Test
    public final void processorCanReadInput() {
        ProcessRunner runner = new ProcessRunner();
        runner.readInput(this.getClass(), "testInput.txt");
        assertEquals(729, runner.processor.getRegA());
        assertEquals(0, runner.processor.getRegB());
        assertEquals(0, runner.processor.getRegC());
    }

    @Test
    public final void processorCanRunSimpleExample() {
        ProcessRunner runner = new ProcessRunner();
        runner.readInput(this.getClass(), "testInput.txt");
        runner.run();
        assertEquals("4,6,3,5,6,3,5,2,1,0", runner.getOutput());
    }

    @Test
    public final void processorCanRunTestExample() {
        ProcessRunner runner = new ProcessRunner();
        runner.readInput(this.getClass(), "testInput.txt");
        runner.run();
        assertEquals("4,6,3,5,6,3,5,2,1,0", runner.getOutput());
    }


    @Test
    public final void processorPart2() {
        ProcessRunner runner = new ProcessRunner();
        runner.readInput(Main.class, "input.txt");
        Long initialA = runner.getCorrectInitialA();
        runner.processor.setRegA(initialA);
        runner.processor.setRegB(0);
        runner.processor.setRegC(0);
        runner.run();
        assertEquals("2,4,1,3,7,5,1,5,0,3,4,1,5,5,3,0", runner.getOutput());
    }

}
