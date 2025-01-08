package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

public class Main
{

    public static void main(String[] args) {
        ProcessRunner runner = new ProcessRunner();
        runner.readInput(Main.class, "input.txt");
        runner.run();
        System.out.println("Part 1: the process output is " + runner.getOutput());


        System.out.println("Part 2: the process output is " + runner.getCorrectInitialA());
    }
}
