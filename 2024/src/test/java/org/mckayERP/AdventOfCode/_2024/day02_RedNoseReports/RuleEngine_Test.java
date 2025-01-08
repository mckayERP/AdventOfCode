package org.mckayERP.AdventOfCode._2024.day02_RedNoseReports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.*;

public class RuleEngine_Test
{
    final static boolean WITH_DAMPENER = true;
    RuleEngine rules;
    String[] data;

    @BeforeEach
    public void setup() {

        rules = new RuleEngine();
        data = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");

    }

    @Test
    public final void givenAStringOfNumbers_DeterminesIfIncreasing() {
        String test = "1 3 6 7 9";
        assertTrue(rules.test(test));
    }

    @Test
    public final void givenAStringOfNumbers_DeterminesIfDecreasing() {
        String test = "7 6 4 2 1";
        assertTrue(rules.test(test));
    }

    @Test
    public final void givenAStringOfNumbers_DeterminesNotAllDecreasing() {
        String test = "1 2 7 8 9";
        assertFalse(rules.test(test));
    }

    @Test
    public final void givenAnArrayOfStringOfNumbers_DeterminesCount() {
        int count = 0;
            for (String line : data)
                if (rules.test(line))
                    count++;

        assertEquals(2, count);
    }

    @Test
    public final void givenASetThatisSafeWithDampeneri_testIsTrue() {
        String test = "7 6 4 2 1";
        RuleEngine rules = new RuleEngine();
        assertTrue(rules.test(test, WITH_DAMPENER));
    }

    @Test
    public final void givenASetThatisUnsafe_determinesIfItCanBeDampened() {
        String test = "1 3 2 4 5";
        RuleEngine rules = new RuleEngine();
        assertTrue(rules.test(test, WITH_DAMPENER));

    }

    @Test
    public final void givenAnArrayOfStringOfNumbersWithDampening_DeterminesCount() {
        int count = 0;
        for (String line : data)
            if (rules.test(line, WITH_DAMPENER))
                count++;

        assertEquals(4, count);
    }
}
