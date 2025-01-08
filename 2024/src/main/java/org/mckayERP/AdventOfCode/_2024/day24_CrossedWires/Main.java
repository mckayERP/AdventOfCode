package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

public class Main
{
    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        long output = circuit.run();
        System.out.println("Part 1: The decimal number output is " + output);
    }
}
