package org.mckayERP.AdventOfCode._2023.day02_CubeConondrum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamerParserTest
{
    @Test
    public final void testConstructor() {
        GameParser parser = new GameParser();
    }

    @Test
    public final void canDetectGames() {
        String[] input = new String[] {"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"};
        GameParser parser = new GameParser();
        parser.parse(input);
        Game game = parser.getGame(1);
        assertEquals(1, game.getID());
    }

    @Test
    public final void canDetectCubes() {
        String[] input = new String[] {"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"};
        GameParser parser = new GameParser();
        parser.parse(input);
        Game game = parser.getGame(1);
        assertEquals(6, game.getBlueCount());
        assertEquals(4, game.getRedCount());
        assertEquals(2, game.getGreenCount());
    }

    @Test
    public final void canInputMultipleGames() {
        String[] input = new String[] {
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        };
        GameParser parser = new GameParser();
        parser.parse(input);
        Game game = parser.getGame(5);
        assertEquals(2, game.getBlueCount());
        assertEquals(6, game.getRedCount());
        assertEquals(3, game.getGreenCount());
    }

    @Test
    public final void canInputMultipleGamesAndDetectPossibleGames() {
        String[] input = new String[] {
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        };
        GameParser parser = new GameParser();
        parser.parse(input);
        List<Game> possibleGames = parser.getPossibleGames(12, 13, 14);
        assertEquals(3, possibleGames.size());
        assertEquals(8, parser.getSumOfPossibleGameIDs(12,13,14));
    }

    @Test
    public final void canCalculateThePowerOfAGame() {
        String[] input = new String[] {
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        };
        GameParser parser = new GameParser();
        parser.parse(input);
        List<Game> possibleGames = parser.getPossibleGames(12, 13, 14);
        assertEquals(48, parser.getGame(1).getPower());
        assertEquals(12, parser.getGame(2).getPower());
        assertEquals(1560, parser.getGame(3).getPower());
        assertEquals(630, parser.getGame(4).getPower());
        assertEquals(36, parser.getGame(5).getPower());
        assertEquals(2286, parser.getSumOfPowers());
    }

}
