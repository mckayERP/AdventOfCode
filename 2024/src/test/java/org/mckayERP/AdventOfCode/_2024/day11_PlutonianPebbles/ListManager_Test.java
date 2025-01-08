package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListManager_Test
{
    @Test
    public final void givenAListOfStonesAndOneBlink_AppliesRulesToList() {
        ListManager manager = new ListManager();
        manager.setInitialArrangement("125 17");
        assertEquals(3, manager.blink(1));
    }

    @Test
    public final void givenAListOfStonesAndTwo_AppliesRulesToList() {
        ListManager manager = new ListManager();
        manager.setInitialArrangement("125 17");
        assertEquals(4, manager.blink(2));
    }

    @Test
    public final void givenAListOfStonesAndThree_AppliesRulesToList() {
        ListManager manager = new ListManager();
        manager.setInitialArrangement("125 17");
        assertEquals(5, manager.blink(3));
    }

    @Test
    public final void givenAListOfStonesAndSixBlinks_AppliesRulesToList() {
        ListManager manager = new ListManager();
        manager.setInitialArrangement("125 17");
        assertEquals(22, manager.blink(6));
    }

    @Test
    public final void givenAListOfStonesAnd25Blinks_AppliesRulesToList() {
        ListManager manager = new ListManager();
        manager.setInitialArrangement("125 17");
        assertEquals(55312, manager.blink(25));
    }

}
