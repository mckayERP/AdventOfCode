package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CircuitAnalyzer
{
    Circuit circuit;

    Set<Gate> workingGates = new HashSet<>();
    Set<Gate> workingGatesPreviousToLastPass = new HashSet<>();
    Set<Gate> workingGatesLastPass = new HashSet<>();
    Set<Gate> affectedButNotWorkingGates = new HashSet<>();

    Set<Set<Wire>> swaps = new HashSet<>();
    Set<Set<Wire>> effectiveSwaps = new HashSet<>();
    Set<Wire> lastTriedSwap = new HashSet<>();

    private int failedAtBitCount = -1;
    public void setCircuit(Circuit circuit)
    {
        this.circuit = circuit;
    }

    public Set<Gate> getAffectedGates(Wire w1)
    {
        return circuit.getAffectedGates(w1);
    }

    public Set<Gate> getAffectingGates(Wire w1)
    {
        TreeSet<Gate> affectingGates = new TreeSet<>(new GateComparator());
        TreeSet<Gate> gateList = new TreeSet<>(new GateComparator());
        gateList.addAll(circuit.getGatesAffectingWire(w1));
        affectingGates.addAll(gateList);
        boolean done = false;
        while (!done) {
            Set<Wire> inputWires = new HashSet<>();
            gateList.forEach(g -> {
                inputWires.add(g.getInputOne());
                inputWires.add(g.getInputTwo());
            });
            gateList.clear();
            inputWires.forEach(w -> gateList.addAll(circuit.getGatesAffectingWire(w)));
            done = !affectingGates.addAll(gateList);
        }
        return affectingGates;

    }

    public boolean isWorking(int startingBit, int endingBit) {
        failedAtBitCount = -1;
        circuit.run();
        circuit.setX(0);
        circuit.setY(0);
        workingGatesPreviousToLastPass.clear();
        workingGatesLastPass.clear();
        workingGates.clear();
        affectedButNotWorkingGates.clear();

        long z = 0;
        long mask = 1;
        for (int bitCount=startingBit; bitCount<=endingBit; bitCount++)
        {
            mask = (1 << (bitCount+1)) - 1;
            long x0 = 0;
            long y0 = 0;
            long x1 = 1;
            long x1c = 1;
//            for(int i=1; i<bitCount; i=i+2) {
                x1 = (x1 << (bitCount-1));
                x1c = x1 >> 1;
//            }
            long y1 = x1;
            long y1c = x1c;

            long[] xs = new long[] {0, x1, x1+x1c,  0,      0,    x1,    x1+x1c,        x1,          x1+x1c};
            long[] ys = new long[] {0,  0,      0, y1, y1+y1c,    y1,        y1,    y1+y1c,          y1+y1c};
            long[] zs = new long[] {0, x1, x1+x1c, y1, y1+y1c, x1+y1, x1+x1c+y1, x1+y1+y1c,  x1+x1c+ y1+y1c};
            affectedButNotWorkingGates.clear();
            circuit.resetGatesAffected();
            for (int j=0; j<xs.length; j++)
            {
                circuit.setX(xs[j]);
                circuit.setY(ys[j]);
                long expected = zs[j];
                z = circuit.getZ();
                boolean working = (z & mask) == expected;
                if (!working)
                {
                    failedAtBitCount = bitCount;
                    System.out.println("Not working: x + y != z -> " + xs[j] + " + " + ys[j] + " = " + z);
                    System.out.println("Expected: x + y != z -> " + xs[j] + " + " + ys[j] + " = " + zs[j]);
                    System.out.println("z Masked -> " + ((z ^ mask) & mask));
                    System.out.println("Failed at bitcount " + failedAtBitCount);
                }
                else {
                    System.out.println("Working: x + y = z -> " + xs[j] + " + " + ys[j] + " = " + z);
                }
            }
            List<Gate> gatesAffected = circuit.getGatesAffected();
            gatesAffected.forEach(System.out::println);
            if (failedAtBitCount > 0)
            {
                affectedButNotWorkingGates.addAll(gatesAffected);
                String formated = String.format("%01d", bitCount);
                affectedButNotWorkingGates.addAll(getAffectingGates(circuit.getWire("z" + formated)));
                affectedButNotWorkingGates.removeAll(workingGates);
                affectedButNotWorkingGates.forEach(System.out::println);
                return false;
            }
            workingGatesPreviousToLastPass.addAll(workingGatesLastPass);
            workingGatesLastPass.addAll(gatesAffected);
            workingGates.addAll(workingGatesPreviousToLastPass);
        }
        return true;
    }

    public Set<Wire> swapOutputs(Set<Integer> gateIDs)
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
        lastTriedSwap = swap;
        return swap;

    }

    public void undoSwap(Set<Wire> swap)
    {
        swaps.remove(swap);
        Wire w1 = (Wire) swap.toArray()[0];
        Wire w2 = (Wire) swap.toArray()[1];

        Gate g1 = circuit.getGateWithOutputWire(w1);
        Gate g2 = circuit.getGateWithOutputWire(w2);

        try
        {
            g1.connectOutput(w2);
            g2.connectOutput(w1);
        }
        catch (IllegalStateException e) {
            g1.connectOutput(w1);
            g2.connectOutput(w2);
            throw new RuntimeException("Couldn't undo the swap! Left the connections as is.");
        }

        System.out.println("Reversing swap " + w1 + " and " + w2);

    }

    public int getFailedAtBitCount()
    {
        return failedAtBitCount;
    }
}
