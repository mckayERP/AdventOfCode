package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;

import java.util.ArrayList;
import java.util.List;

public class ProcessRunner
{

    int[] program;

    ThreeBitProcessor processor;

    public ProcessRunner() {
        processor = new ThreeBitProcessor();
    }

    public void readInput(Class clazz, String inputFileName) {
        String[] input = ResourceFileReader.getResourceFileAsArrayOfStrings(clazz, inputFileName);
        int regNum = 0;
        String regString = input[regNum++].split(":")[1].trim();
        processor.setRegA(Integer.parseInt(regString));
        regString = input[regNum++].split(":")[1].trim();
        processor.setRegB(Integer.parseInt(regString));
        regString = input[regNum++].split(":")[1].trim();
        processor.setRegC(Integer.parseInt(regString));

        String[] programStr = input[4].split(":")[1].trim().split(",");
        program = new int[programStr.length];
        for (int i = 0; i<programStr.length; i++) {
            program[i] = Integer.parseInt(programStr[i]);
        }

    }

    public void run()
    {
        processor.loadProgram(program);
        processor.run();
    }

    public String getOutput() {
        return processor.getOutput();
    }

    public long getCorrectInitialA() {

        List<Long> possibleRegAValues = new ArrayList<>();
        List<Long> newRegAValuesToAdd = new ArrayList<>();
        possibleRegAValues.add(0L);
        long regATestValue = 0L;

        for (int i = program.length-1; i>=0; i--)
        {
            for (Long regAValue : possibleRegAValues) {
                for (int j = 0; j < 8; j++)
                {
                    regATestValue = (regAValue << 3) + j;
                    processor.setRegA(regATestValue);
                    processor.setRegB(0);
                    processor.setRegC(0);
                    run();
                    int[] output = processor.getOutputAccumulator();

                    System.out.print("Setting regA=" + regATestValue);
                    System.out.print(" output=");
                    for (int k : output)
                        System.out.print(k + ",");
                    System.out.println();
                    boolean matches = processor.regA == 0;
                    for (int k = output.length - 1; k >=0; k--) {
                        matches = matches && output[k] == program[program.length - output.length + k];
                    }
                    if (matches)
                    {
                        System.out.println("Success! Found new regAValue for position " + i + " = " + regATestValue + " from seed " + regAValue);
                        newRegAValuesToAdd.add(regATestValue);
                    }
                }
            }
            possibleRegAValues.clear();
            possibleRegAValues.addAll(newRegAValuesToAdd);
            newRegAValuesToAdd.clear();
        }
        Long smallestRegAValue = possibleRegAValues.stream().sorted().findFirst().orElse(-1L);
        System.out.println("RegA value is " + smallestRegAValue);
        return smallestRegAValue;
    }

}
