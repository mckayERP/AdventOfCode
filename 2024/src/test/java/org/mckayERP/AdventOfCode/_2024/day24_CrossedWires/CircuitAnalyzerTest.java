package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircuitAnalyzerTest
{

    CircuitAnalyzer analyzer;

    @BeforeEach
    public final void setup()
    {
        analyzer = new CircuitAnalyzer();
    }

    @Test
    public final void givenAnInputWireAndASingleGate_canFindAllAffectedGates()
    {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput1.txt"));
        analyzer.setCircuit(circuit);
        Set<Gate> affectedGates = analyzer.getAffectedGates(circuit.getWire("x00"));
        assertEquals(1, affectedGates.size());
        assertEquals("0:x00 AND y00 -> z00", affectedGates.toArray()[0].toString());
    }

    @Test
    public final void givenAnInputWireAndMultipleGates_canFindAllAffectedGates()
    {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        analyzer.setCircuit(circuit);
        TreeSet<Gate> affectedGates = (TreeSet<Gate>) analyzer.getAffectedGates(circuit.getWire("x00"));
        assertEquals(19, affectedGates.size());
        affectedGates.forEach(System.out::println);
    }

    @Test
    public final void givenAnManyInputWiresAndGates_canFindAllAffectedGates()
    {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        analyzer.setCircuit(circuit);
        Set<Gate> affectedGates = analyzer.getAffectedGates(circuit.getWire("x00"));
        assertEquals(134, affectedGates.size());
        assertTrue(affectedGates.stream().map(Gate::toString).collect(Collectors.joining("")).contains("x00 XOR y00 -> z00"));
    }

    @Test
    public final void givenAnOutputWireAndMultipleGates_canFindAllGatesAffecting()
    {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        analyzer.setCircuit(circuit);
        TreeSet<Gate> affectingGates = (TreeSet<Gate>) analyzer.getAffectingGates(circuit.getWire("z00"));
        assertEquals(7, affectingGates.size());
        affectingGates.forEach(System.out::println);
    }

    @Test
    public final void givenAnOutputWireAndManyGates_canFindAllGatesAffecting()
    {
        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        analyzer.setCircuit(circuit);
        TreeSet<Gate> affectingGates = (TreeSet<Gate>) analyzer.getAffectingGates(circuit.getWire("z45"));
        assertEquals(175, affectingGates.size());
        affectingGates.forEach(System.out::println);
    }

    @Test
    public final void givenTheTestInput2circuit_canIdentifyTheComplementOfTheAffectingInTheAffectedGates()
    {

        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        analyzer.setCircuit(circuit);
        TreeSet<Gate> affectedGates = (TreeSet<Gate>) analyzer.getAffectedGates(circuit.getWire("x00"));
        TreeSet<Gate> affectingGates = (TreeSet<Gate>) analyzer.getAffectingGates(circuit.getWire("z00"));

        System.out.println("Affected by x00 but not affecting z00");
        TreeSet<Gate> affectedNotAffecting = new TreeSet<>(affectedGates);
        affectedNotAffecting.removeAll(affectingGates);
        affectedNotAffecting.forEach(System.out::println);

        System.out.println("\nAffecting z00 but not affected by x00");
        TreeSet<Gate> affectingNotAffected = new TreeSet<>(affectingGates);
        affectingNotAffected.removeAll(affectedGates);
        affectingNotAffected.forEach(System.out::println);

        System.out.println("\nCommon gates to both x00 and z00");
        TreeSet<Gate> commonGates = new TreeSet<>(affectingGates);
        commonGates.retainAll(affectingGates);
        commonGates.forEach(System.out::println);

    }

    @Test
    public final void canVerifyThatZ00IsNotWorking()
    {

        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput3.txt"));
        circuit.run();
        analyzer.setCircuit(circuit);
        analyzer.isWorking(1,1);

        TreeSet<Gate> affectedGates = (TreeSet<Gate>) analyzer.getAffectedGates(circuit.getWire("x00"));
        TreeSet<Gate> affectingGates = (TreeSet<Gate>) analyzer.getAffectingGates(circuit.getWire("z00"));

        System.out.println("Affected by x00 but not affecting z00");
        TreeSet<Gate> affectedNotAffecting = new TreeSet<>(affectedGates);
        affectedNotAffecting.removeAll(affectingGates);
        affectedNotAffecting.forEach(System.out::println);

        System.out.println("\nAffecting z00 but not affected by x00");
        TreeSet<Gate> affectingNotAffected = new TreeSet<>(affectingGates);
        affectingNotAffected.removeAll(affectedGates);
        affectingNotAffected.forEach(System.out::println);

        System.out.println("\nCommon gates to both x00 and z00");
        TreeSet<Gate> commonGates = new TreeSet<>(affectingGates);
        commonGates.retainAll(affectingGates);
        commonGates.forEach(System.out::println);

    }

    @Disabled
    @Test
    public final void canVerifyThatZ00IsNotWorkingWithFullInput()
    {

        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        circuit.run();
        Set<Set<Wire>> repairedWires = new HashSet<>();
        analyzer.setCircuit(circuit);
        analyzer.lastTriedSwap.clear();
        while(!analyzer.isWorking(1, circuit.getNumberOfBits()))
        {
            int failedAtBit = analyzer.getFailedAtBitCount();
            if (failedAtBit < 0)
                break;

            Set<Set<Integer>> previouslyTriedSwaps = new HashSet<>();
            boolean notWorking = true;
            List<Gate> setOfNotWorkingGates = analyzer.affectedButNotWorkingGates.stream().collect(Collectors.toList());
            while (notWorking && setOfNotWorkingGates.size() > 0)
            {
                Gate g1 = setOfNotWorkingGates.get(0);
                System.out.println("Looking to swap wires with " + g1);
                for (Gate g2 : setOfNotWorkingGates)
                {
                    if (g1.equals(g2))
                        continue;

                    if (!analyzer.lastTriedSwap.isEmpty())
                    {
                        analyzer.undoSwap(analyzer.lastTriedSwap);
                        analyzer.lastTriedSwap.clear();
                    }
                    Set<Integer> candidate = new HashSet<>();
                    candidate.add(g1.getID());
                    candidate.add(g2.getID());
                    Set<Wire> swap;
                    try
                    {
                        swap = analyzer.swapOutputs(candidate);
                    }
                    catch (RuntimeException e) {
                        analyzer.lastTriedSwap.clear();
                        continue;
                    }
                    try
                    {
                        CircuitAnalyzer innerAnalyzer = new CircuitAnalyzer();
                        innerAnalyzer.setCircuit(circuit);
                        if (innerAnalyzer.isWorking(failedAtBit-1,failedAtBit) && innerAnalyzer.getFailedAtBitCount() < 0)
                        {
                            notWorking = false;
                            repairedWires.add(new HashSet<>(swap));
                            analyzer.lastTriedSwap.clear();
                            break;
                        }
                    }
                    catch (StackOverflowError ignoreDueToCircularLoop) {}  //TODO
                }
                System.out.println("Looked at all possible swaps  involving " + g1);
                setOfNotWorkingGates.remove(g1);
            }
        }

        TreeSet<Wire> finalSwapList = new TreeSet<>(new WireComparator());
        finalSwapList.addAll(analyzer.swaps.stream().flatMap(Collection::stream).collect(Collectors.toSet()));
        System.out.println(finalSwapList.stream().map(w -> w.getName()).collect(Collectors.joining(",")));

    }

    @Test
    public final void GateMapping() {

        Circuit circuit = new Circuit();
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));


        // An ugly manual process of finding the mistakes and correcting them by hand.  Ugh!
        Set<Set<Wire>> repairedWires = new HashSet<>();
        Set<Integer> candidate = new HashSet<>();
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("hbs")).getID());
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("kfp")).getID());
        repairedWires.add(new HashSet<>(swapOutputs(circuit, candidate)));

        candidate.clear();
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("z18")).getID());
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("dhq")).getID());
        repairedWires.add(new HashSet<>(swapOutputs(circuit, candidate)));

        candidate.clear();
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("pdg")).getID());
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("z22")).getID());
        repairedWires.add(new HashSet<>(swapOutputs(circuit, candidate)));

        candidate.clear();
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("jcp")).getID());
        candidate.add(circuit.getGateWithOutputWire(circuit.getWire("z27")).getID());
        repairedWires.add(new HashSet<>(swapOutputs(circuit, candidate)));

        Wire carryOutWire = null;

        for (int i = 0; i<circuit.getNumberOfBits(); i++)
        {
            Wire carryInWire = carryOutWire;

            // Inputs are wired properly
            // Implies the inputs are connected to an AND gate and an xOR gate
            int gateNumber = i;
            String formattedGateNumber = String.format("%02d", gateNumber);
            System.out.println("Examining the gates for input/out " + formattedGateNumber);
            System.out.println("The carry in wire is " + carryInWire);

            Wire xin = circuit.getWire("x" + formattedGateNumber);
            Wire yin = circuit.getWire("y" + formattedGateNumber);
            Set<Gate> firstLevelXGates = circuit.getGatesAffectedByWire(xin);
            Set<Gate> firstLevelYGates = circuit.getGatesAffectedByWire(yin);
            Gate xyXORGate = firstLevelXGates.stream().filter(g -> g.getGateType() == GateType.XOR).findFirst().get();
            Gate xyANDGate = firstLevelYGates.stream().filter(g -> g.getGateType() == GateType.AND).findFirst().get();
            Wire xyXOROutToZXORIn = xyXORGate.getOutputConnection();
            System.out.println("xyXORGate: " + xyXORGate);
            System.out.println("The xyXORGate connects to :");
                circuit.getGatesAffectedByWire(xyXORGate.getOutputConnection()).forEach(System.out::println);
            System.out.println("xyANDGate: " + xyANDGate);


            Wire zout = circuit.getWire("z" + formattedGateNumber);
            Set<Gate> firstLevelZGates = circuit.getGatesAffectingWire(zout);
            Gate zOutGate = firstLevelZGates.stream().findFirst().get();
            System.out.println("The gate connected to the Z output " + zOutGate);

            Gate carryAndGate = null;
            Set<Gate> carryInGates = new HashSet<>();
            Wire xyANDOutput = null;
            Wire carryAndOutput = null;
            Set<Gate> carryOutOrGates = new HashSet<>();
            Gate carryOutORGate = null;
            long count;

            if (gateNumber == 0)
            {
                carryOutWire = xyANDGate.getOutputConnection();
                System.out.println("The carry out wire is " + carryOutWire);
            } else
            {

                // Verify XOR and carryIn are inputs to the same and gate.
                carryInGates = circuit.getGatesAffectedByWire(carryInWire);
                carryAndGate = carryInGates.stream().filter(g -> g.getGateType() == GateType.AND).findFirst().get();
                System.out.println("The carry in AND gate is " + carryAndGate);

                // Verify the xyAND and the carryAnd outputs feed a single OR gate
                xyANDOutput = xyANDGate.getOutputConnection();
                System.out.println("The XY AND gate is " + xyANDGate);
                carryAndOutput = carryAndGate.getOutputConnection();
                carryOutOrGates = circuit.getGatesAffectedByWire(xyANDOutput);
                if (carryOutOrGates.isEmpty())
                {
                    System.out.println("The Carry out OR gate is not found connected to " + xyANDOutput);
                    System.out.println("Trying to find it using the carry AND gate output " + carryAndOutput);
                    carryOutOrGates = circuit.getGatesAffectedByWire(carryAndOutput);
                }
                carryOutORGate = carryOutOrGates.stream().findFirst().get();
                System.out.println("The carry out OR gate is " + carryOutORGate);
                carryOutWire = carryOutORGate.getOutputConnection();
            }


            if (gateNumber == 0)
            {
                Gate zOutXORGate = firstLevelZGates.stream().filter(g -> g.getGateType() == GateType.XOR).findFirst().get();
                Gate xyInXORGate = firstLevelXGates.stream().filter(g -> g.getGateType() == GateType.XOR).findFirst().get();
                assertEquals(zOutXORGate, xyInXORGate, "For the first gate set, the XY in XOR and the Z out XOR should be the same gate but are " + xyInXORGate + " and " + zOutXORGate);
            } else
            {
                // Verify XOR gates are connected
                zOutGate = firstLevelZGates.stream().filter(g -> g.getGateType() == GateType.XOR).findFirst().get();
                assertTrue(xyXOROutToZXORIn.equals(zOutGate.getInputOne()) || xyXOROutToZXORIn.equals(zOutGate.getInputTwo()),
                        "The output wire " + xyXOROutToZXORIn + " should be an input to " + zOutGate);
                assertTrue(carryInWire.equals(zOutGate.getInputOne()) || carryInWire.equals(zOutGate.getInputTwo()),
                        "The carryout wire " + carryInWire + " should be an input to " + zOutGate);
            }

            assertEquals(firstLevelXGates, firstLevelYGates, "The X and Y wires do not connect to the same gates.  This is an assumption.  Tilt!!");
            assertEquals(2, firstLevelXGates.size(), "The first level gates should be 2 in size but found " + firstLevelXGates.size());
            assertEquals(1, firstLevelXGates.stream().filter(g -> g.getGateType() == GateType.AND).count(), "In the first level gates there should be only one AND gate.");
            assertEquals(1, firstLevelXGates.stream().filter(g -> g.getGateType() == GateType.XOR).count(), "In the first level gates there should be only one OR gate.");
            assertEquals(1, firstLevelZGates.size(), "The Z output should be connected to only one gate.");
            assertEquals(1, firstLevelZGates.stream().filter(g -> g.getGateType() == GateType.XOR).count(), "The Z output should be connected to an XOR gate but is connected to " + zOutGate);
            if (gateNumber == 0)
            {
            }
            else {
                assertEquals(2, carryInGates.size(), "The Carry In wire " + carryInWire + " should be connected to two gates but found " + carryInGates.size());
                count = carryInGates.stream().filter(g -> g.getGateType() == GateType.AND).count();
                assertEquals(1, count, "The Carry In wire should be connected to one AND gate but found " + count);
                count = carryInGates.stream().filter(g -> g.getGateType() == GateType.XOR).count();
                assertEquals(1, count, "The Carry In wire " + carryInWire + " should be connected to one XOR gate but found " + count);

                assertTrue(xyXOROutToZXORIn.equals(carryAndGate.getInputOne()) || xyXOROutToZXORIn.equals(carryAndGate.getInputTwo()),
                        "The output of the xy XOR gate " + xyXOROutToZXORIn + " should be connected to the carry AND gate " + carryAndGate);
                assertTrue(carryInWire.equals(carryAndGate.getInputOne()) || carryInWire.equals(carryAndGate.getInputTwo()));


                assertEquals(1, carryOutOrGates.size(), "There should be one gate generating the carry out wire affected by the wire " + xyANDOutput);
                assertEquals(1, carryOutOrGates.stream().filter(g -> g.getGateType() == GateType.OR).count(), "There should be one gate generating the carry out wire affected by the wire " + xyANDOutput);
                assertTrue(xyANDOutput.equals(carryOutORGate.getInputOne())
                                || xyANDOutput.equals(carryOutORGate.getInputTwo()),
                        "The carry out OR gate should have an input connected to the " + xyANDOutput +
                                " wire but has connections " + carryOutORGate);
                assertTrue(carryAndOutput.equals(carryOutORGate.getInputOne()) || carryAndOutput.equals(carryOutORGate.getInputTwo()));

            }
        }

        TreeSet<Wire> wires = new TreeSet<>(new WireComparator());
        repairedWires.forEach(wires::addAll);
        System.out.println("The repaired wires are " + wires.stream().map(Wire::getName).collect(Collectors.joining(",")));
    }

    public Set<Wire> swapOutputs(Circuit circuit, Set<Integer> gateIDs)
    {
        Gate g1 = circuit.getGate((Integer) gateIDs.toArray()[0]);
        Gate g2 = circuit.getGate((Integer) gateIDs.toArray()[1]);
        Wire w1 = g1.getOutputConnection();
        Wire w2 = g2.getOutputConnection();

        System.out.println("Swapping " + w1 + " and " + w2);
        circuit.stop();
        try
        {
            circuit.swapOutputs(g1, g2);
        }
        catch (RuntimeException e) {
            System.out.println("Swap not allowed - resetting output wires. " + e.getMessage());
            g1.connectOutput(w1);
            g2.connectOutput(w2);
            circuit.start();
            throw new RuntimeException("Swap not allowed. Left connections as is.");
        }
        circuit.start();

        Set<Wire> swap = new HashSet<>();

        swap.add(w1);
        swap.add(w2);
        return swap;

    }

}