package org.mckayERP.AdventOfCode._2023.day04_ScratchCards;

import org.mckayERP.AdventOfCode.utilities.stringManipulation.AOCStrings;

import java.util.ArrayList;
import java.util.List;

public class CardReader
{
    List<Card> cards = new ArrayList<>();
    public void read(String[] input)
    {
        for (String line : input)
        {
            String[] cardString = line.split("\\|");
            String[] idAndNumbers = cardString[0].split(":");
            String[] ids = idAndNumbers[0].split("\\s+");

            int id = Integer.parseInt(ids[1].trim());
            Integer[] winningNumbers = AOCStrings.extractIntegers(idAndNumbers[1]);
            Integer[] myNumbers = AOCStrings.extractIntegers(cardString[1]);
            Card card = new Card(id, winningNumbers, myNumbers);
            cards.add(card);
        }
    }

    public int getSumOfCardValue()
    {
        return cards.stream().mapToInt(Card::getValue).sum();
    }


    public int getTotalCardsWon() {
        for (int i = 0; i < cards.size(); i++ ) {
            Card card = cards.get(i);
            int numberOfWinningNumbers = card.getNumberOfWinningNumbers();
            if (numberOfWinningNumbers > 0) {
                for (int j = i+1; j<cards.size() && j<=i+numberOfWinningNumbers ; j++) {
                    Card nextCard = cards.get(j);
                    nextCard.incrementInstanceCount(card.instanceCount);
                }
            }
        }
        return cards.stream().mapToInt(Card::getInstanceCount).sum();
    }
}
