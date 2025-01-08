package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import java.util.ArrayList;
import java.util.List;

public class PrintQueueOrderRuleEngine implements RuleEngine
{

    List<PrintOrderRule> rules = new ArrayList<>();
    static PrintQueueOrderRuleEngine engine;

    public static RuleEngine get() {
        engine = new PrintQueueOrderRuleEngine();
        return engine;
    }


    @Override
    public void addRule(int before, int after)
    {
        rules.add(new PrintOrderRule(before, after));
    }

    @Override
    public boolean matches(int beforePage, int afterPage)
    {
        return rules.stream().anyMatch(r -> r.matches(beforePage, afterPage));
    }

}
