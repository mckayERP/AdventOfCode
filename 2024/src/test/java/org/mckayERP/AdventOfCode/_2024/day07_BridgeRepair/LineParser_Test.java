package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineParser_Test
{
    @Test
    public final void lineCanBeParsed() {
        String input = "21037: 9 7 18 13";
        LineParser parser = new LineParser();
        parser.parse(input);
        assertEquals(21037, parser.getExpectedResult());
        assertEquals(4, parser.getValues().length);
        assertEquals(9, parser.getValues()[0]);
        assertEquals(7, parser.getValues()[1]);
        assertEquals(18, parser.getValues()[2]);
        assertEquals(13, parser.getValues()[3]);
    }
}
