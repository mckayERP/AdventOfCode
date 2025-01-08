package org.mckayERP.AdventOfCode._2024.day05_PrintQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrintQueueOrderRuleEngine_Test
{
    RuleEngine tester;

    @BeforeEach
    public final void setup() {
        tester = PrintQueueOrderRuleEngine.get();
    }

    @Test
    public final void givenARuleAndAPageNotAffected_ruleEngineDoesNotMatchRule() {
        tester.addRule(47, 53);
        assertFalse(tester.matches(75, 22));
    }

    @Test
    public final void givenARuleAndAPageAffected_ruleEngineDoesNotMatchRule() {
        tester.addRule(47, 53);
        assertFalse(tester.matches(75, 22));
    }

    @Test
    public final void givenARuleAndAPagesAffected_ruleEngineMatches() {
        tester.addRule(47, 53);
        assertTrue(tester.matches(47, 53));
    }

    @Test
    public final void givenSeveralRulesAndAPagesAffected_ruleEngineMatches() {
        tester.addRule(47, 53);
        tester.addRule(75, 22);
        tester.addRule(16, 49);
        assertTrue(tester.matches(75, 22));
    }

    @Test
    public final void givenSeveralRulesAndAPagesNotAffected_ruleEngineDoesntMatch() {
        tester.addRule(47, 53);
        tester.addRule(75, 22);
        tester.addRule(16, 49);
        assertFalse(tester.matches(47, 22));
    }

}
