package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class LineParser_Test
{
    LineParser parser;

    @BeforeEach
    public final void setup() {
        parser = new LineParser();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public final void givenAnNullOrEmptyString_throwsException(String line){
            assertThrows(IllegalArgumentException.class, () -> {
                parser.parse(line);
            });
    }

    @Test
    public final void givenALineWIthNoDigits_returnsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("aasdbasdf");
        });
    }

    @Test
    public final void givenALineWithOneDigit_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("1");
        });
    }

    @Test
    public final void givenALineWithTwoDigits_returnsTheDigits() {
            int[] result = parser.parse("1 2");
            assertEquals(1, result[0]);
            assertEquals(2, result[1]);
    }

    @Test
    public final void givenALineWithTwoLargeDigits_returnsTheDigits() {
        int[] result = parser.parse("77710   11556");
        assertEquals(77710, result[0]);
        assertEquals(11556, result[1]);
    }

}
