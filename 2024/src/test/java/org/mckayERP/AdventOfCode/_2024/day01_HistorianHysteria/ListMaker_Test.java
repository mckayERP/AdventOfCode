package org.mckayERP.AdventOfCode._2024.day01_HistorianHysteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListMaker_Test
{
    ListMaker listMaker;
    String[] data;

    @BeforeEach
    public void setup() {
        listMaker = new ListMaker();

        data = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");

    }

    @Test
    public final void givenNull_returnsNull() {
        assertNull(listMaker.make((String) null));
    }

    @Test
    public final void givenAListWithNoNumbers_returnsNull() {
        assertNull(listMaker.make("""
                                    asd asfa
                                    asdf asdfa
                                    asdfa asf
                                    """
        ));
    }

    @Test
    public final void givenAListWithTwoNumbers_returnsArrayOfLists() {
        Object result = listMaker.make("1 2");
        assertInstanceOf(List.class, result);
        assertInstanceOf(List.class, ((List) result).get(0));
        assertInstanceOf(List.class, ((List) result).get(1));
        assertEquals(2, ((List) result).size());
        assertEquals(1, ((List<List>) result).get(0).get(0));
        assertEquals(2, ((List<List>) result).get(1).get(0));
    }

    @Test
    public final void givenAListWithSeveralLines_returnsArrayOfLists() {

        List<List<Integer>> result = listMaker.make(data);
        List<Integer> listOne = result.get(0);
        List<Integer> listTwo = result.get(1);
        assertEquals(2, result.size());
        assertEquals(6, listOne.size());
        assertEquals(6, listTwo.size());
        assertEquals(2, listOne.get(2));
        assertEquals(5, listTwo.get(2));

    }

    @Test
    public final void givenAListOfLists_sortsTheListsReturningSortedLists() {

        List<List<Integer>> result = listMaker.sort(listMaker.make(data));
        List<Integer> listOne = result.get(0);
        List<Integer> listTwo = result.get(1);
        assertEquals(1, listOne.get(0));
        assertEquals(2, listOne.get(1));
        assertEquals(3, listOne.get(2));
        assertEquals(3, listOne.get(3));
        assertEquals(3, listOne.get(4));
        assertEquals(4, listOne.get(5));

        assertEquals(3, listTwo.get(0));
        assertEquals(3, listTwo.get(1));
        assertEquals(3, listTwo.get(2));
        assertEquals(4, listTwo.get(3));
        assertEquals(5, listTwo.get(4));
        assertEquals(9, listTwo.get(5));

    }
}
