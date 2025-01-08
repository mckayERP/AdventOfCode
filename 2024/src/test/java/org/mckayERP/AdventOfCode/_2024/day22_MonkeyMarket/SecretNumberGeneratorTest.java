package org.mckayERP.AdventOfCode._2024.day22_MonkeyMarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretNumberGeneratorTest
{
    SecretNumberGenerator generator;

    @BeforeEach
    public final void setup() {
        generator = new SecretNumberGenerator();
    }

    @Test
    public final void testConstructor() {
        long nextSecret = generator.getNextSecret(123);
        assertEquals(15887950L, nextSecret);
    }

    @Test
    public final void testExampleTwoTimes() {
        long nextSecret = generator.getNextSecret(123);
        nextSecret = generator.getNextSecret(nextSecret);
        assertEquals(16495136L, nextSecret);
    }

    @Test
    public final void testExampleTenTimes() {
        long nextSecret = generator.getXthSecret(123, 10);
        assertEquals(5908254L, nextSecret);
    }

    @Test
    public final void testExampleforPart1() {
        String[] inputs = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput.txt");
        assertEquals(37327623L, generator.getTotalSum(inputs, 2000));
    }

    @Test
    public final void testAddChanges() {
        Integer[] changes = new Integer[4];
        Arrays.fill(changes, null);
        generator.addToChanges(4, changes);
        assertEquals(4, changes[0]);
        generator.addToChanges(3, changes);
        assertEquals(3, changes[0]);
        assertEquals(4, changes[1]);
        generator.addToChanges(2, changes);
        generator.addToChanges(1, changes);
        generator.addToChanges(0, changes);
        assertEquals(0, changes[0]);
        assertEquals(1, changes[1]);
        assertEquals(2, changes[2]);
        assertEquals(3, changes[3]);
    }

    @Test
    public final void testSingleSecretStarting123AndTenGenerations() {
        generator.getTotalSum(new String[] {"123"}, 10);
        assertEquals(6, generator.getMaximumNumberOfBananas());
    }

    @Test
    public final void testExampleForPart2() {
        String[] inputs = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt");
        generator.getTotalSum(inputs, 2000);
        assertEquals(23, generator.getSumForChange("-2,1,-1,3"));
    }

    @Test
    public final void testGetMostBananasPart2() {
        String[] inputs = ResourceFileReader.getResourceFileAsArrayOfStrings(this.getClass(), "testInput2.txt");
        generator.getTotalSum(inputs, 2000);
        assertEquals(23, generator.getSumForChange("-2,1,-1,3"));
        assertEquals(23, generator.getMaximumNumberOfBananas());
    }

}
