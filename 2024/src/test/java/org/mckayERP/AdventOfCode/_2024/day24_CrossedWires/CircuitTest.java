package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mckayERP.AdventOfCode._2024.day24_CrossedWires.GateType.XOR;

public class CircuitTest
{

    Circuit circuit;

    @BeforeEach
    public final void setup() {
        circuit = new Circuit();
    }

    @Test
    public final void testReadingInput() {
        circuit.inputCircuit(new String[] {
            "x00: 1",
            "x01: 1",
            "x02: 1",
            "y00: 0",
            "y01: 1",
            "y02: 0",
            "",
            "x00 AND y00 -> z00",
            "x01 XOR y01 -> z01",
            "x02 OR y02 -> z02"
        });
        long output = circuit.run();
        assertEquals(4, output);
    }

    @Test
    public final void testExampleInput() {
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        long output = circuit.run();
        assertEquals(2024, output);
    }

    @Test
    public final void testSetAndReadValues() {
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        assertEquals(7, circuit.setX(7));
        assertEquals(4, circuit.setY(4));
    }

    @Test
    public final void givenASystemOfGatesAndWires_canTestTheSystem() {
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        boolean working = circuit.testSystem();
        assertFalse(working);
    }

    @Test
    public final void givenAPairOfGates_canSwapWires() {
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt"));
        Gate gate1 = circuit.gates.get(0);
        Gate gate2 = circuit.gates.get(1);
        Wire w1 = gate1.getOutputConnection();
        Wire w2 = gate2.getOutputConnection();
        circuit.swapOutputs(gate1, gate2);
        assertEquals(w1, gate2.getOutputConnection());
        assertEquals(w2, gate1.getOutputConnection());
    }

    @Test
    public final void givenAGate_whenAttachingAWireToBothInputAndOutput_throwsException() {
        Gate gate = new Gate(XOR);
        Wire w1 = new Wire("w1");
        Wire w2 = new Wire("w2");
        gate.connectInputs(w1, w2);
        assertThrows(IllegalStateException.class, () -> {
            gate.connectOutput(w1);
        });
    }

    @Test
    public final void givenAGate_whenAttachingAWireToBothOutputAndInput_throwsException() {
        Gate gate = new Gate(XOR);
        Wire w1 = new Wire("w1");
        Wire w2 = new Wire("w2");
        gate.connectOutput(w1);
        assertThrows(IllegalStateException.class, () -> {
            gate.connectInputs(w1, w2);
        });
    }

    @Test
    public final void givenACircuit_whenAnInputWireValueIsChanged_theAffectedGatesAreListed() {
        circuit.inputCircuit(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput3.txt"));
        circuit.run();
        circuit.getWire("x00").setValue((Boolean) null);
        circuit.getWire("y00").setValue((Boolean) null);
        circuit.resetGatesAffected();
        circuit.getWire("x00").setValue(0);
        circuit.getWire("y00").setValue(0);
        assertEquals(1, circuit.getWire("z00").getValueAsInt());
        List<Gate> gatesAffected = circuit.getGatesAffected();
        assertEquals(1, gatesAffected.size());
        assertEquals(circuit.getWire("x00"), gatesAffected.get(0).getInputOne());
        assertEquals(circuit.getWire("y00"), gatesAffected.get(0).getInputTwo());
    }
}
