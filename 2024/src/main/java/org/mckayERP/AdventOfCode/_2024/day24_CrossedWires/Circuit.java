package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

public class Circuit implements PropertyChangeListener
{

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    Map<String, Wire> wires = new HashMap<>();

    List<Gate> gates = new ArrayList<>();

    TreeMap<String, Wire> outputs = new TreeMap<>();
    TreeMap<String, Wire> inputx = new TreeMap<>();
    TreeMap<String, Wire> inputy = new TreeMap<>();
    private List<Gate> affectedGates = new ArrayList<>();


    public void inputCircuit(String[] input)
    {
        for(String line : input) {
            if (line.contains(":"))
                addWireFromInput(line);
            else if (line.contains("->"))
                wireGate(line);
        }

        identifyAndLoadInputAndOutputWires();
    }

    public int getNumberOfBits() {
        return inputx.size();
    }

    public long run() {

        start();

        return getZ();
    }

    void start()
    {
        pcs.firePropertyChange(PropertyNames.START,null, null);
    }

    void stop()
    {
        pcs.firePropertyChange(PropertyNames.STOP,null, null);
    }

    public long getZ()
    {
        return readNumberFromWireset(outputs);
    }

    private static long readNumberFromWireset(TreeMap<String, Wire> wireSet)
    {
        final long[] number = {0};
        wireSet.descendingMap().values().stream() 
                .map(Wire::getValue)
                .forEach(b -> { // ordered MSB first
                    number[0] = number[0] << 1;
                    number[0] += b ? 1:0;
                }
        );
        return number[0];
    }

    public long setY(long y)
    {
        return setValueOnWireset(y, inputy);
    }

    public long setX(long x)
    {
        return setValueOnWireset(x, inputx);
    }

    private long setValueOnWireset(long value, TreeMap<String, Wire> wireSet)
    {
        final long[] number = {value};
        wireSet.entrySet().stream()
                .map(Map.Entry::getValue)
                .forEach(w -> { // LSB first
                            boolean b = (number[0] & 1) == 1;
                            w.setValue(b);
                            number[0] = number[0] >> 1;
                        }
                );
        return readNumberFromWireset(wireSet);
    }

    public long add(long x, long y) {

        return 0;
    }


    private void identifyAndLoadInputAndOutputWires()
    {
        for (String wireName : wires.keySet()) {
            if (wireName.startsWith("z"))
            {
                Wire outputWire = wires.get(wireName);
                outputs.put(wireName, outputWire);
            }
            if (wireName.startsWith("x"))
            {
                Wire input = wires.get(wireName);
                inputx.put(wireName, input);
            }
            if (wireName.startsWith("y"))
            {
                Wire input = wires.get(wireName);
                inputy.put(wireName, input);
            }
        }
    }

    private void wireGate(String line)
    {
        String[] parts = line.split("\s");
        String wire1Name = parts[0].trim();
        String wire2Name = parts[2].trim();
        String gateType = parts[1].trim();
        String outputWireName = parts[4].trim();

        Gate gate = new Gate(GateType.valueOf(gateType));
        gate.setID(gates.size());
        gate.addPropertyChangeListener(this);
        gate.connectInputs(addWire(wire1Name), addWire(wire2Name));
        gate.connectOutput(addWire(outputWireName));
        gates.add(gate);
    }

    private Wire addWireFromInput(String line)
    {
        String[] parts = line.split(":");
        String wireName = parts[0].trim();
        Integer initialValue = Integer.parseInt(parts[1].trim());
        return addWire(wireName, initialValue);
    }

    private Wire addWire(String wireName)
    {
        return addWire(wireName, (Integer) null);
    }

    private Wire addWire(String wireName, Integer initialValue)
    {
        Wire wire = wires.getOrDefault(wireName, new Wire(wireName));
        if (initialValue != null)
            wire.setValue(initialValue);
        removePropertyChangeListener(wire);
        addPropertyChangeListener(wire);
        wires.putIfAbsent(wireName, wire);
        return wires.get(wireName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(PropertyNames.START, listener);
        this.pcs.addPropertyChangeListener(PropertyNames.STOP, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(PropertyNames.START, listener);
        this.pcs.removePropertyChangeListener(PropertyNames.STOP, listener);
    }


    public boolean testSystem()
    {
        long x = 0;
        long y = 0;
        x = initializeX(x);
        y = initializeY(y);
        start();
        if (getZ() != x + y) // All zero
            return false;

        stop();
        x = Integer.MAX_VALUE;
        x = initializeX(x);
        start();
        if (getZ() != x + y) // X+y all bits on x
            return false;

        stop();
        x = 0;
        x = initializeX(x);
        y = Integer.MAX_VALUE;
        y = initializeY(y);
        start();
        if (getZ() != x + y) // X+y all bits on x
            return false;

        return true;
    }

    private long initializeX(long v)
    {
        return setX(setCorrectNumberOfBits(v, inputx));
    }
    private long initializeY(long v)
    {
        return setY(setCorrectNumberOfBits(v, inputy));
    }

    private long setCorrectNumberOfBits(long v, TreeMap<String, Wire> input)
    {
        long x = 0;
        int numberOfBits = input.size();
        for (int i=0; i<numberOfBits; i++) {
            x = (x << 1) + (v & 1);
            v = v >> 1;
        }
        return x;
    }

    public void swapOutputs(Gate gate1, Gate gate2)
    {
        Wire w1 = gate1.getOutputConnection();
        Wire w2 = gate2.getOutputConnection();

        if (getAffectedGates(w2).contains(gate1))
            throw new IllegalStateException("Swap would cause a feedback loop! " + w2 + " affects " + gate1);
        if (getAffectedGates(w1).contains(gate2))
            throw new IllegalStateException("Swap would cause a feedback loop! " + w1 + " affects " + gate2);

        gate1.connectOutput(w2);
        gate2.connectOutput(w1);
    }

    public Wire getWire(String name)
    {
        return wires.get(name);
    }

    public List<Gate> getGatesAffected()
    {
        return affectedGates;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if ((evt.getSource() instanceof Gate) && evt.getPropertyName().equals(PropertyNames.GATE_VALUE_CHANGED)) {
            Gate gate = (Gate) evt.getSource();
            if (!affectedGates.contains(gate))
                affectedGates.add(gate);
        }
    }

    public void resetGatesAffected()
    {
        affectedGates.clear();
    }

    public void getGatesConnectedToWire(Wire w1)
    {
    }

    public Set<Gate> getGatesAffectedByWire(Wire w1)
    {
        return gates.stream()
                .filter(g -> g.getInputOne().equals(w1) || g.getInputTwo().equals(w1))
                .collect(Collectors.toCollection(() -> new TreeSet<Gate>(new GateComparator())));
    }

    public Set<Gate> getGatesAffectingWire(Wire w)
    {
        return gates.stream()
                .filter(g -> g.getOutputConnection().equals(w))
                .collect(Collectors.toCollection(() -> new TreeSet<Gate>(new GateComparator())));
    }

    public Gate getGate(int gateID)
    {
        return gates.stream().filter(g -> g.getID()==gateID).findFirst().get();
    }

    public Gate getGateWithOutputWire(Wire w1)
    {
        return gates.stream().filter(g -> g.getOutputConnection().equals(w1)).findFirst().get();
    }

    public Set<Gate> getAffectedGates(Wire w1)
    {
        TreeSet<Gate> affectedGates = new TreeSet<Gate>(new GateComparator());
        TreeSet<Gate> gateList = new TreeSet<Gate>(new GateComparator());
        gateList.addAll(getGatesAffectedByWire(w1));
        affectedGates.addAll(gateList);
        while (!gateList.isEmpty())
        {
            Set<Wire> outputWires = new HashSet<>();
            gateList.forEach(g ->
            {
                outputWires.add(g.getOutputConnection());
            });
            gateList.clear();
            outputWires.forEach(w -> gateList.addAll(getGatesAffectedByWire(w)));
            affectedGates.addAll(gateList);
        }
        return affectedGates;
    }
}
