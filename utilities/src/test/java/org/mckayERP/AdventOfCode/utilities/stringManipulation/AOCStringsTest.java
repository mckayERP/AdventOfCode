package org.mckayERP.AdventOfCode.utilities.stringManipulation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AOCStringsTest
{

    @ParameterizedTest
    @NullAndEmptySource
    public final void givenANullOrEmptyString_canExtractTheDigitsThrowsAnException(String input) {
        assertThrows(IllegalArgumentException.class, () ->{
            AOCStrings.extractSingleDigits(input);
        });
    }

    @Test
    public final void givenAStringWithNoDigits_returnsAnEmptyArray() {
        Integer[] digits = AOCStrings.extractSingleDigits("abcdef");
        assertEquals(0, digits.length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1abc2", "pqr3stu8vwx"})
    public final void givenAStringWithNumbersAndLetters_canExtractTheDigitsToArray(String input)
    {
        Integer[] digits = AOCStrings.extractSingleDigits(input);
        assertEquals(2, digits.length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1abcd", "a1bcd", "asdbd1"})
    public final void givenAStringWithOneNumbersAndLetters_canExtractTheSingleDigitToArray(String line)
    {
        Integer[] digits = AOCStrings.extractSingleDigits(line);
        assertEquals(1, digits.length);
        assertEquals(1, digits[0]);
    }

}
