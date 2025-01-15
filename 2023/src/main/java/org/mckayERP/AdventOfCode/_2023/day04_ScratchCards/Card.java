package org.mckayERP.AdventOfCode._2023.day04_ScratchCards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Card
{
    int id;

    int instanceCount = 1;

    Set<Integer> winningNumbers = new HashSet<>();
    Set<Integer> myNumbers = new HashSet<>();

    public Card() {

    }
    public Card(int id, Integer[] winningNumbers, Integer[] myNumbers)
    {
        this.id = id;
        this.winningNumbers.addAll(Arrays.stream(winningNumbers).toList());
        this.myNumbers.addAll(Arrays.stream(myNumbers).toList());
    }

    public void setWiningNumbers(Set<Integer> winningNumbers)
    {
        this.winningNumbers = winningNumbers;
    }

    public void setMyNumbers(Set<Integer> myNumbers) {
        this.myNumbers = myNumbers;
    }

    public int getValue() {
        int number = getNumberOfWinningNumbers();
        if (number == 0)
            return 0;
        return 1 << (number-1);
    }

    public void incrementInstanceCount(int count)
    {
        instanceCount += count;
    }

    public int getInstanceCount()
    {
        return instanceCount;
    }

    @Override
    public String toString() {
        return "" + id + ":" + getValue()+ " (" + instanceCount + ")";
    }

    public int getNumberOfWinningNumbers()
    {
        Set<Integer> myWinningNumbers = new HashSet<>();
        myWinningNumbers.addAll(myNumbers);
        myWinningNumbers.retainAll(winningNumbers);
        return myWinningNumbers.size();
    }
}
