package org.mckayERP.AdventOfCode._2024.day13_ClawContraption;

import java.util.ArrayList;
import java.util.List;

public class GamePlayer
{

    long conversion = 10000000000000L;

    boolean withConversion = false;
    public Game getGame(int i)
    {
        return games.get(i);
    }

    public class Game {
        long ax, ay, bx, by, px, py;
    }

    List<Game> games = new ArrayList<>();

    public void readInput(String[] strings)
    {
        for (int i=0; i<strings.length; i=i+4) {

            String[] part1 = strings[i].split(",");
            String[] part2 = strings[i+1].split(",");
            String[] part3 = strings[i+2].split(",");

            Game game = new Game();
            game.ax = Integer.parseInt(part1[0].split("\\+")[1]);
            game.ay = Integer.parseInt(part1[1].split("\\+")[1]);
            game.bx = Integer.parseInt(part2[0].split("\\+")[1]);
            game.by = Integer.parseInt(part2[1].split("\\+")[1]);
            game.px = Integer.parseInt(part3[0].split("=")[1]);
            game.py = Integer.parseInt(part3[1].split("=")[1]);
            games.add(game);

        }

    }

    public long getCostOfGame(Game g) {
        long cf = 0;
        if(withConversion)
            cf = conversion;

        long b = ((g.py+cf)*g.ax - (g.px+cf)*g.ay)/(g.ax*g.by-g.ay*g.bx);
        long a = ((g.px+cf) -b*g.bx)/g.ax;

        if (!withConversion && (a > 100 || b > 100))
        {
            return 0;
        }

        long cost = 3*(a) + b;

        if (((g.px+cf) == a*g.ax + b*g.bx) && ((g.py+cf) == a*g.ay + b*g.by))
            return cost;
        else
            return 0;
    }

    public long getTotalCost() { return getTotalCost(false); }
    public long getTotalCost(boolean withConversion)
    {
        this.withConversion = withConversion;
        return games.stream().mapToLong(this::getCostOfGame).sum();
    }

    public long getNumberOfGames()
    {
        return games.size();
    }
}
