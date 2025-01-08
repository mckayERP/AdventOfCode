package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SimularityScore_Test
{
    String[] data;
    DistanceFinder distanceFinder;

    @BeforeEach
    public void setup() {

        distanceFinder = new DistanceFinder();

        data = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");

    }

    @Test
    public final void givenTwoLists_calculatesTheSimularityScore() {
        ListMaker listMaker = new ListMaker();
        List<List<Integer>> lists = listMaker.sort(listMaker.make("1  1"));
        SimularityScore simularityScore = new SimularityScore();
        assertEquals(1, simularityScore.score(lists));
    }

    @Test
    public final void givenTwoListsWithLines_calculatesTheSimularityScore() {
        String inputString = """
                1  1\r
                2  1\r
                4  4\r
                5  4\r
                5  4\r
                """;
        // Result should be 1*2 + 2*0 + 4*3 = 14
        ListMaker listMaker = new ListMaker();
        List<List<Integer>> lists = listMaker.sort(listMaker.make(inputString.split(System.lineSeparator())));
        SimularityScore simularityScore = new SimularityScore();
        assertEquals(14, simularityScore.score(lists));
    }

    @Test
    public final void givenTheTestInput_theResultIs31() {
        ListMaker listMaker = new ListMaker();
        List<List<Integer>> lists = listMaker.sort(listMaker.make(data));
        SimularityScore simularityScore = new SimularityScore();
        assertEquals(31, simularityScore.score(lists));
    }
}
