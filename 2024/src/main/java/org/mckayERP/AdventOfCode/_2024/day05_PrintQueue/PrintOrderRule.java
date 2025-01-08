package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

public class PrintOrderRule
{
    int beforePage;
    int afterPage;

    public PrintOrderRule(int before, int after)
    {
        if (before <= 0 || after <= 0)
            throw new IllegalArgumentException();

        setRule(before, after);
    }

    public void setRule(int before, int after) {
        beforePage = before;
        afterPage = after;
    }

    public boolean matches(int before, int after) {
        return before == beforePage && after == afterPage;
    }

}
