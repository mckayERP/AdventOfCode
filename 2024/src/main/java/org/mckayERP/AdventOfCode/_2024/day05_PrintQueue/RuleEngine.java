package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

public interface RuleEngine
{
    void addRule(int before, int after);
    boolean matches(int beforePage, int afterPage);

}
