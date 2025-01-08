package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode._2024.day24_CrossedWires.Wire;

import static org.junit.jupiter.api.Assertions.*;

public class WireTest
{

    @Test
    public final void testWireState() {

        Wire wire = new Wire();
        assertNull(wire.getValue());
        wire.setValue(Boolean.TRUE);
        assertEquals(true, wire.getValue());
        wire.setValue(false);
        assertFalse(wire.getValue());

    }

}
