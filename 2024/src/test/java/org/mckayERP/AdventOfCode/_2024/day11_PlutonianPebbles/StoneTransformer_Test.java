package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoneTransformer_Test
{
    @Test
    public final void givenAStoneNumberZero_transformsTheNumberCorrectly() {

        Stone firstStone = new Stone(0L, 1);
        StoneTransformer transformer = new StoneTransformer();
        Stone[] stones = transformer.transform(firstStone);
        assertEquals(1, stones.length);
        assertEquals(1, stones[0].value);
        assertEquals(1, stones[0].multiplier);

    }

    @Test
    public final void givenAStoneWithEvenNumberOfDigits_transformsToTwoStones() {

        Stone inputStone = new Stone(28676032L, 1);
        StoneTransformer transformer = new StoneTransformer();
        Stone[] stones = transformer.transform(inputStone);
        assertEquals(2, stones.length);
        assertEquals(2867L, stones[0].getValue());
        assertEquals(6032L, stones[1].getValue());

    }

    @Test
    public final void givenAStoneWithTwoDigits_transformsToTwoStones() {

        Stone inputStone = new Stone(17L, 1);
        StoneTransformer transformer = new StoneTransformer();
        Stone[] stones = transformer.transform(inputStone);
        assertEquals(2, stones.length);
        assertEquals(1L, stones[0].getValue());
        assertEquals(7L, stones[1].getValue());

    }

    @Test
    public final void givenAStoneNotZeroAndNotEvenDigits_transformsByMultiplication() {

        Stone inputStone = new Stone(125L, 1);
        StoneTransformer transformer = new StoneTransformer();
        Stone[] stones = transformer.transform(inputStone);
        assertEquals(1, stones.length);
        assertEquals(125L * 2024L, stones[0].getValue());

    }
}
