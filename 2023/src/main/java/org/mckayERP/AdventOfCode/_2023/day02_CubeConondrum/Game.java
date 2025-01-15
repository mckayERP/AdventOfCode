package org.mckayERP.AdventOfCode._2023.day02_CubeConondrum;

public class Game
{
    int ID;
    int blueCount = 0;
    int redCount = 0;
    int greenCount = 0;

    public Game(int id)
    {
        this.ID = id;
    }

    public int getID()
    {
        return ID;
    }

    public int getGreenCount()
    {
        return greenCount;
    }

    public int getRedCount()
    {
        return redCount;
    }

    public int getBlueCount()
    {
        return blueCount;
    }

    public void addBlue(int count)
    {
        blueCount = Math.max(blueCount,count);
    }

    public void addRed(int count)
    {
        redCount = Math.max(redCount,count);
    }

    public void addGreen(int count)
    {
        greenCount = Math.max(greenCount,count);
    }

    public int getPower() {
        return redCount*greenCount*blueCount;
    }
}
