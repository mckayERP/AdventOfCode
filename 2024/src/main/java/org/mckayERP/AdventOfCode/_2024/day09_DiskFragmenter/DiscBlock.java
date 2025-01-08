package org.mckayERP.AdventOfCode._2024.day09_DiskFragmenter;

public class DiscBlock
{
    public boolean isEmpty()
    {
        return empty;
    }

    public void setEmpty(boolean empty)
    {
        this.empty = empty;
    }

    boolean empty = true;
    public int getStartingIndex()
    {
        return startingIndex;
    }

    public void setStartingIndex(int startingIndex)
    {
        this.startingIndex = startingIndex;
    }

    public int getEndingIndex()
    {
        return endingIndex;
    }

    public void setEndingIndex(int endingIndex)
    {
        this.endingIndex = endingIndex;
    }

    public int getSize()
    {

        return endingIndex - startingIndex + 1;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    int startingIndex = -1;
    int endingIndex = -1;

    int size = 0;


}
