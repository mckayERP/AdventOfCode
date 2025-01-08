package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceFinder_Test
{
    String[] input;
    DistanceFinder distanceFinder;

    @BeforeEach
    public void setup() {

        distanceFinder = new DistanceFinder();
        input = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");

    }

    @Test
    public final void givenPairsInExample_FindsDistance() {

        assertEquals(4, distanceFinder.withData(new String[] {"3   7"}).calculateDistance());
        assertEquals(6, distanceFinder.withData(new String[] {"9   3"}).calculateDistance());
        assertEquals(66154, distanceFinder.withData(new String[] {"77710   11556"}).calculateDistance());

    }

    @Test
    public final void givenTheTestInput_FindsDistance() {

        assertEquals(11, distanceFinder.withData(input).calculateDistance());

    }

}
