package org.mckayERP.AdventOfCode.utilities;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ResourceFileReader
{


    public static String[] getResourceFileAsArrayOfStrings(Class<?> clazz, String fileName)
    {
        return getResourceFileAsString(clazz, fileName).split(System.lineSeparator());
    }

    public static String getResourceFileAsString(Class<?> clazz, String fileName)
    {
        String[] input = new String[]{null};
        Optional.ofNullable(clazz.getResource(fileName))
                .ifPresent(testData -> {
                    try {
                        input[0] = IOUtils.toString(testData, StandardCharsets.UTF_8);
                    } catch (IOException ex) {
                        System.out.println("Couldn't read the resource " + fileName + " for class " + clazz.getName());
                    }
                });
        return input[0];
    }
}
