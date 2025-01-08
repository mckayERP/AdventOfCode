package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode._2024.day24_CrossedWires.Gate;
import org.mckayERP.AdventOfCode._2024.day24_CrossedWires.GateType;
import org.mckayERP.AdventOfCode._2024.day24_CrossedWires.Wire;

import static org.junit.jupiter.api.Assertions.*;

public class GateTest
{

    @Test
    public final void testGateConstructor() {
        Gate gate = new Gate(GateType.XOR);
    }

    @Test
    public final void testGateWiring() {
        Gate gate = new Gate(GateType.XOR);
        assertThrows(RuntimeException.class, gate::setValue);
    }

    @Test
    public final void testGateConnectionsNull() {

        Gate gate = new Gate(GateType.XOR);
        Wire x00 = new Wire();
        Wire x01 = new Wire();
        Wire fst = new Wire();
        gate.connectInputs(x00,x01);
        gate.connectOutput(fst);
        gate.setValue();
        assertNull(fst.getValue());

    }

    @Test
    public final void testGateXOR() {

        Gate gate = new Gate(GateType.XOR);
        Wire x00 = new Wire();
        Wire x01 = new Wire();
        Wire fst = new Wire();
        gate.connectInputs(x00,x01);
        gate.connectOutput(fst);
        x00.started = true;
        x01.started = true;
        fst.started = true;
        x00.setValue(1);
        x01.setValue(0);

        assertEquals(1 ^ 0, fst.getValue() ? 1: 0);

    }

    @Test
    public final void testGateAND() {

        Gate gate = new Gate(GateType.AND);
        Wire x00 = new Wire(true);
        Wire x01 = new Wire(false);
        Wire fst = new Wire();
        gate.connectInputs(x00,x01);
        gate.connectOutput(fst);
        gate.setValue();
        assertEquals(false, fst.getValue());
        x01.setValue(true);
        gate.setValue();
        assertEquals(true, fst.getValue());
    }

    @Test
    public final void testGateOR() {

        Gate gate = new Gate(GateType.OR);
        Wire x00 = new Wire(false);
        Wire x01 = new Wire(false);
        Wire fst = new Wire();
        gate.connectInputs(x00,x01);
        gate.connectOutput(fst);
        gate.setValue();
        assertEquals(false, fst.getValue());
        x01.setValue(true);
        gate.setValue();
        assertEquals(true, fst.getValue());
        x00.setValue(true);
        gate.setValue();
        assertEquals(true, fst.getValue());
    }

}
