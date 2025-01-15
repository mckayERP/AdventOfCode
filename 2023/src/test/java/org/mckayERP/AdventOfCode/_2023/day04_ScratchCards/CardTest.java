package org.mckayERP.AdventOfCode._2023.day04_ScratchCards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest
{

    @Test
    public final void testConstructor()
    {
        Card uut = new Card();
    }

    @Test
    public final void testCardCanCompareNumbers() {
        Card card = new Card();
        Set<Integer> winningNumbers = new HashSet<>(5);
        winningNumbers.addAll(Arrays.asList(41, 48, 83, 86, 17));
        card.setWiningNumbers(winningNumbers);
        Set<Integer> mynumbers = new HashSet<Integer>();
        mynumbers.addAll(Arrays.asList(83, 86,  6, 31, 17,  9, 48, 53));
        card.setMyNumbers(mynumbers);
        assertEquals(8, card.getValue());

    }

}