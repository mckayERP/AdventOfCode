package org.mckayERP.AdventOfCode._2024.day13_ClawContraption;

import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamePlayerTest
{
    @Test
    public final void testCanReadInput() {
        GamePlayer player = new GamePlayer();
        player.readInput(new String[] {"Button A: X+94, Y+34", "Button B: X+22, Y+67", "Prize: X=8400, Y=5400"});
        assertEquals(1, player.getNumberOfGames());
        assertEquals(94, player.getGame(0).ax);
    }

    @Test
    public final void testCanReadSimpleInput() {
        GamePlayer player = new GamePlayer();
        player.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "simpleInput.txt"));
        assertEquals(4, player.getNumberOfGames());
        assertEquals(26, player.getGame(1).ax);
        assertEquals(66, player.getGame(1).ay);
        assertEquals(67, player.getGame(1).bx);
        assertEquals(21, player.getGame(1).by);
        assertEquals(12748, player.getGame(1).px);
        assertEquals(12176, player.getGame(1).py);
    }

    @Test
    public final void testGamePlayWithOneGame() {
        GamePlayer player = new GamePlayer();
        player.readInput(new String[] {"Button A: X+94, Y+34", "Button B: X+22, Y+67", "Prize: X=8400, Y=5400"});
        assertEquals(280, player.getTotalCost());
    }

    @Test
    public final void testGamePlayWithNoWin() {
        GamePlayer player = new GamePlayer();
        player.readInput(new String[] {"Button A: X+26, Y+66", "Button B: X+67, Y+21", "Prize: X=12748, Y=12176"});
        assertEquals(0, player.getTotalCost());
    }

    @Test
    public final void testGamePlayTestInput() {
        GamePlayer player = new GamePlayer();
        player.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt"));
        assertEquals(480, player.getTotalCost());
    }

    @Test
    public final void testGamePlayPart1Input() {
        GamePlayer player = new GamePlayer();
        player.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(GamePlayer.class, "input.txt"));
        assertEquals(36571, player.getTotalCost());
    }

    @Test
    public final void testGame2PlayWithConversion() {
        GamePlayer player = new GamePlayer();
        player.readInput(new String[] {"Button A: X+26, Y+66", "Button B: X+67, Y+21", "Prize: X=12748, Y=12176"});
        assertEquals(459236326669L, player.getTotalCost(true));
    }

    @Test
    public final void testGamePlayPart2Input() {
        GamePlayer player = new GamePlayer();
        player.readInput(ResourceFileReader.getResourceFileAsArrayOfStrings(GamePlayer.class, "input.txt"));
        assertEquals(85527711500010L, player.getTotalCost(true));
    }



}
