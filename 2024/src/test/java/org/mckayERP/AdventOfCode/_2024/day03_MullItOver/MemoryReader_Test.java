package org.mckayERP.AdventOfCode._2024.day03_MullItOver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MemoryReader_Test
{

    MemoryReader reader;

    @BeforeEach
    public final void setup() {
        reader = new MemoryReader();
    }

    @Test
    public final void testParsingOfCorrectString() {
        String testString = "asdfamul(3,4)asdf";
        DataPoint[] pairs = reader.parseString(testString);
        assertEquals(3, pairs[0].X);
        assertEquals(4, pairs[0].Y);
    }

    @ParameterizedTest
    @ValueSource(strings = {"mul(4*",
            "mul(6,9!",
            "?(12,34)",
            "mul ( 2 , 4 )"}
    )
    public final void givenIncorrectStrings_returnsZero(String testString) {
        DataPoint[] pairs = reader.parseString(testString);
        assertEquals(0,pairs.length);
    }

    @Test
    public final void givenLongStrings_returnsZero() {

        String testString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        DataPoint[] pairs = reader.parseString(testString);
        assertEquals(4,pairs.length);
        assertEquals(2, pairs[0].X);
        assertEquals(4, pairs[0].Y);
        assertEquals(5, pairs[1].X);
        assertEquals(5, pairs[1].Y);
        assertEquals(11, pairs[2].X);
        assertEquals(8, pairs[2].Y);
        assertEquals(8, pairs[3].X);
        assertEquals(5, pairs[3].Y);

    }

    @Test
    public final void givenTestInput_calculatesProductSum() {
        String input = ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput.txt");
        assertFalse(input.isEmpty());

        assertEquals(161, reader.getProductSum(input));
    }

    @Test
    public final void givenTestInputAndEnablersActive_calculatesProductSum() {
        String input = ResourceFileReader.getResourceFileAsString(this.getClass(), "testInput2.txt");
        assertFalse(input.isEmpty());
        assertEquals(48, reader.setEnablersActive(true).getProductSum(input));
    }

}
