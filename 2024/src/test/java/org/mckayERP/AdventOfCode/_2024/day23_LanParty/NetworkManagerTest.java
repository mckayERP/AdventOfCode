package org.mckayERP.AdventOfCode._2024.day23_LanParty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NetworkManagerTest
{
    NetworkManager manager;

    @BeforeEach
    public final void setup() {
        manager = new NetworkManager();
    }

    @Test
    public final void testCanReadInputAndGenerateListOfComputers() {
        manager.readInputList(new String[] {"kh-tc", "qp-kh"});
        assertEquals(3, manager.getListOfComputers().size());
    }

    @Test
    public final void testCanFindConnectionsOfAComputer() {
        manager.readInputList(new String[] {"kh-tc", "qp-kh"});
        assertEquals(3, manager.getConnectionsOfComputer("kh").size());
        assertTrue(manager.getConnectionsOfComputer("kh").contains("tc"));
        assertTrue(manager.getConnectionsOfComputer("kh").contains("qp"));
        assertTrue(manager.getConnectionsOfComputer("kh").contains("kh"));
    }

    @Test
    public final void testReadSampleInputAndFindSetsOfThree() {
        manager.readInputList(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(12, manager.getListOfConnectComputerSets().size());
        assertEquals(7, manager.getCountOfConnectionsWithComputersStartingWithT());
    }

    @Test
    public final void testPart2LargestListOfConnectedComputers() {
        manager.readInputList(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        int size = manager.getLargestSetOfConnectedComputers().size();
        Set<String> set = manager.getLargestSetOfConnectedComputers();
        assertEquals(4, manager.getLargestSetOfConnectedComputers().size());
        assertEquals("co,de,ka,ta", manager.getPasswordBasedOnSet(set));
    }
}
