package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import java.util.ArrayList;
import java.util.List;

public class PrintQueueTester
{

    long sumOfMiddlePages = 0;
    long sumOfCorrectedMiddlePages = 0;
    PrintQueueOrderRuleEngine engine;

    List<String> printOrders = new ArrayList<>();
    List<String> badPrintOrders = new ArrayList<>();

    List<int[]> correctedOrders = new ArrayList<>();
    public PrintQueueTester(){
        engine = new PrintQueueOrderRuleEngine();
    };

    public void loadRulesAndOrders(String[] inputs) {
        for (String input : inputs) {
            if (isRule(input))
                parseRule(input);
            else
                if (isPrintOrder(input)) {
                    printOrders.add(input);
                }
        }
    }


    public void parseRule(String input)
    {
        String[] pages = input.split("\\|");
        int before = Integer.parseInt(pages[0]);
        int after = Integer.parseInt(pages[1]);
        engine.addRule(before,after);
    }

    public PrintQueueOrderRuleEngine getRuleEngine()
    {
        return engine;
    }

    public boolean isRule(String input)
    {
        return input.contains("|");
    }

    public boolean isPrintOrder(String input)
    {
        return input.contains(",");
    }

    public int[] parsePrintList(String list)
    {
        String pages[] = list.split(",");
        int[] pageNumbers = new int[pages.length];
        for (int i=0; i<pages.length; i++)
            pageNumbers[i] = Integer.parseInt(pages[i]);

        return pageNumbers;
    }

    public boolean test(String printList)
    {
        int [] pageNumbers = parsePrintList(printList);

        for (int i = 0; i<pageNumbers.length; i++) {
            boolean okbefore = true;
            boolean okAfter = true;
            for (int j = 0; j<i; j++) {
                okbefore = okbefore && engine.matches(pageNumbers[j], pageNumbers[i]);
            }
            for (int j = i+1; j<pageNumbers.length; j++) {
                okAfter = okAfter && engine.matches(pageNumbers[i], pageNumbers[j]);
            }
            if (!okbefore || !okAfter)
            {
                badPrintOrders.add(printList);
                return false;
            }
        }
        sumOfMiddlePages += pageNumbers[pageNumbers.length/2];
        return true;
    }

    public long getSumOfCorrectedMiddlePages()
    {
        return sumOfCorrectedMiddlePages;
    }

    public boolean correct(String printList)
    {
        int [] pageNumbers = parsePrintList(printList);
        boolean outOfOrder = true;

        while (outOfOrder)
        {
            outOfOrder = false;
            for (int i = 0; i < pageNumbers.length; i++)
            {
                boolean okbefore = true;
                boolean okAfter = true;
                for (int j = 0; j < i; j++)
                {
                    if (!engine.matches(pageNumbers[j], pageNumbers[i]))
                    {
                        swapPages(pageNumbers, j, i);
                        outOfOrder = true;
                        break;
                    }
                }
                if (outOfOrder)
                    break;

                for (int j = i + 1; j < pageNumbers.length; j++)
                {
                    if(!engine.matches(pageNumbers[i], pageNumbers[j]))
                    {
                        swapPages(pageNumbers, j, i);
                        outOfOrder = true;
                        break;
                    }
                }
            }
        }
        correctedOrders.add(pageNumbers);
        sumOfCorrectedMiddlePages += pageNumbers[pageNumbers.length/2];
        return true;
    }

    private static void swapPages(int[] pageNumbers, int j, int i)
    {
        int tempPage = pageNumbers[j];
        pageNumbers[j] = pageNumbers[i];
        pageNumbers[i] = tempPage;
    }

    public long getSumOfMiddlePageNumbers()
    {
        return sumOfMiddlePages;
    }

    public void testPrintOrders()
    {
        printOrders.forEach(this::test);
    }

    public List<int[]> getCorrectedOrders()
    {
        return correctedOrders;
    }

    public void correctBadPrintOrders()
    {
        badPrintOrders.forEach(this::correct);
    }
}
