package org.mckayERP.AdventOfCode._2023.day02_CubeConondrum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameParser
{
    List<Game> games = new ArrayList<>();
    public void parse(String[] inputs)
    {
        for (String input : inputs)
        {
            String[] gameString = input.split(":");
            String[] gameID = gameString[0].split("\\s");
            int ID = Integer.parseInt(gameID[1]);
            Game game = new Game(ID);
            games.add(game);

            String[] reveals = gameString[1].split(";");
            for (String reveal : reveals)
            {
                String[] cubes = reveal.split(",");
                for (String cube : cubes)
                {
                    if (cube.contains("blue"))
                    {
                        String[] parts = cube.trim().split("\\s");
                        int count = Integer.parseInt(parts[0].trim());
                        game.addBlue(count);
                    }
                    if (cube.contains("red"))
                    {
                        String[] parts = cube.trim().split("\\s");
                        int count = Integer.parseInt(parts[0].trim());
                        game.addRed(count);
                    }
                    if (cube.contains("green"))
                    {
                        String[] parts = cube.trim().split("\\s");
                        int count = Integer.parseInt(parts[0].trim());
                        game.addGreen(count);
                    }
                }
            }
        }
    }

    public Game getGame(int i)
    {
        return games.stream().filter(g -> g.getID() == i).findFirst().get();
    }

    public List<Game> getPossibleGames(int maxRed, int maxGreen, int maxBlue)
    {
        return games.stream().filter(g ->
            g.greenCount <= maxGreen && g.redCount <= maxRed && g.blueCount <= maxBlue
        ).toList();
    }

    public int getSumOfPossibleGameIDs(int maxRed, int maxGreen, int maxBlue)
    {
        return getPossibleGames(maxRed, maxGreen, maxBlue).stream().mapToInt(Game::getID).sum();
    }

    public int getSumOfPowers()
    {
        return games.stream().mapToInt(Game::getPower).sum();
    }
}
