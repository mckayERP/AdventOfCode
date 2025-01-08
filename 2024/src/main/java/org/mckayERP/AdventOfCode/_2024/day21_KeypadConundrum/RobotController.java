package org.mckayERP.AdventOfCode._2024.day21_KeypadConundrum;

import org.mckayERP.AdventOfCode.utilities.AOCLogger;

import java.util.*;
import java.util.stream.Collectors;

public class RobotController
{
    AOCLogger logger = AOCLogger.get();
    /**
     * Dictionary, maps lowest level patterns to higher levels, 1 through 3. 
     */
    Map<String, Map<Integer, String>> dictionary = new HashMap<>();

    Map<String, Map<Integer, Long>> lengthLookup = new HashMap<>();

    List<SequenceManager> managers = new ArrayList<>();

    public RobotController() {
        generateDictionary();
        lengthLookup = new HashMap<>();
        managers = new ArrayList<>();
    }

    public void addKeypad(Keypad keypad)
    {
            managers.add(new SequenceManager(keypad));
    }

    public List<String> getSequencesToType(String seqString)
    {
        List<String> sequencesToType = new ArrayList<>();
        sequencesToType.add(seqString);
        managers.forEach(keypad -> {
            List<String> outputSequences = new ArrayList<>();
            sequencesToType.forEach(input-> {
                outputSequences.addAll(keypad.type(input));
            });
            sequencesToType.clear();
            sequencesToType.addAll(outputSequences);
        });
        sequencesToType.sort(Comparator.comparingInt(String::length));
        int length = sequencesToType.get(0).length();
        printSequence(sequencesToType.get(0));
        return sequencesToType.stream().filter(s -> s.length() == length).collect(Collectors.toList());
    }

    public String addShortestSequenceToDictionary(String inputSequence, int numOfDirectionalKeypadsToLookAhead) {

        List<String> firstListOfOptions = getSingleStepKeypadOutputOptionsForAnInput(inputSequence);
        int level = 1;
        String bestOutput = getOptionWithShortestOutputLookingAhead(numOfDirectionalKeypadsToLookAhead, firstListOfOptions);
        addToDictionaryAtLevel(inputSequence, level, bestOutput);
        return dictionary.get(inputSequence).get(level);

    }

    private void addToDictionaryAtLevel(String inputSequence, int level, String bestOutput)
    {
        Map<Integer, String> levelMap = dictionary.getOrDefault(inputSequence, new HashMap<Integer, String>());
        levelMap.put(level, bestOutput);
        dictionary.put(inputSequence, levelMap);
    }

    private String getFromDictionaryAtLevel(String inputSequence, int level)
    {
        Map<Integer, String> nullMap = new HashMap<>();
        nullMap.putIfAbsent(level, null);
        return dictionary.getOrDefault(inputSequence, nullMap).getOrDefault(level, null);
    }

    private static List<String> getSingleStepKeypadOutputOptionsForAnInput(String inputSequence)
    {
        List<String> numberPadOutputs = new ArrayList<>();
        if (!inputSequence.equals("A") && !"<>^v".contains(inputSequence.substring(0,1)))
        {
            SequenceManager numKeypadManager = new SequenceManager(new NumericKeypad());
            String firstKey = inputSequence.substring(0, 1);
            KeypadKey startingKey = numKeypadManager.keypad.getKey(firstKey);
            numKeypadManager.keypad.setCurrentKey(startingKey);
            numKeypadManager.start = startingKey;
            numberPadOutputs = numKeypadManager.type(inputSequence.substring(1, 2));
        }
        else
        {
            SequenceManager directionalKeypadManager = new SequenceManager(new DirectionalKeypad());
            numberPadOutputs = directionalKeypadManager.type(inputSequence);
        }
        return numberPadOutputs;
    }

    private String getOptionWithShortestOutputLookingAhead(int numOfDirectionalKeypadsToLookAhead, List<String> inputOptions)
    {
        TreeMap<Integer, String> outputLengthToInput = new TreeMap<>();
        List<String> stringsToType = new ArrayList<>();
        List<String> outputSequences = new ArrayList<>();
        List<SequenceManager> directionalManagers = new ArrayList<>();
        for (int i = 0; i< 4; i++)
            directionalManagers.add(new SequenceManager(new DirectionalKeypad()));
        inputOptions.forEach(numPadInput -> {
            stringsToType.clear();
            stringsToType.add(numPadInput);
            directionalManagers.forEach(keypad ->
            {
                outputSequences.clear();
                stringsToType.forEach(input ->
                {
                    String output = getFromDictionaryAtLevel(input, 1);
                    if (output != null)
                        outputSequences.add(output);
                    else
                        outputSequences.addAll(keypad.type(input));
                });
                stringsToType.clear();
                stringsToType.addAll(outputSequences);
            });
            outputSequences.sort(Comparator.comparingInt(String::length));
            String shortestOutputSequence = outputSequences.get(0);
            outputLengthToInput.putIfAbsent(shortestOutputSequence.length(), numPadInput);
        });
        String bestOutput = outputLengthToInput.get(outputLengthToInput.firstKey());
        return bestOutput;
    }

    public Long getShortestSequenceLengthForNumberPadSequence(String numberPadSequence, int numberOfDirectionalKeypads)
    {

        if (numberPadSequence.isEmpty())
            throw new NullPointerException("numberPadSequence must not null or empty and have the form \"123A\".");

        if (numberPadSequence.length() != 4)
            throw new IllegalArgumentException("numberPadSequence must be a key sequence in the form \"123A\".");

        logger.logln("Initial sequence: " + numberPadSequence + " " + numberPadSequence.length()
                + " level " + numberOfDirectionalKeypads);

        if (dictionary.isEmpty())
            generateDictionary();

        String initialSequence =
                dictionary.get("A" + numberPadSequence.charAt(0)).get(1)
                + dictionary.get(numberPadSequence.substring(0,2)).get(1)
                + dictionary.get(numberPadSequence.substring(1,3)).get(1)
                + dictionary.get(numberPadSequence.substring(2,4)).get(1);

        Long length = findLength(initialSequence, numberOfDirectionalKeypads);
        logger.logln(" length: " + length);
        return length;

    }

    private Long findLength(String sequenceToSplit, int numberOfDirectionalKeypadsRemaining)
    {
        if (numberOfDirectionalKeypadsRemaining == 0)
            return (long) sequenceToSplit.length();

        Long length = lookupLength(sequenceToSplit, numberOfDirectionalKeypadsRemaining);
        if (length != null)
            return length;

        String dictionaryValue = getFromDictionaryAtLevel(sequenceToSplit, 1);
        if (dictionaryValue != null && numberOfDirectionalKeypadsRemaining > 1)
        {
            length = splitAtAAndFindLength(dictionaryValue, numberOfDirectionalKeypadsRemaining - 2);
        }
        else
            length = splitAtAAndFindLength(sequenceToSplit, numberOfDirectionalKeypadsRemaining-1);

        addLengthToLookup(sequenceToSplit, length, numberOfDirectionalKeypadsRemaining);

        return length;
    }

    private void addLengthToLookup(String sequence, Long length, int numberOfDirectionalKeypadsRemaining)
    {

        logger.logln("Adding length " + length + " for sequence " + sequence + " at depth " + numberOfDirectionalKeypadsRemaining);
        Map<Integer, Long> lengthsForNumberOfKeypadsRemaining = lengthLookup.getOrDefault(sequence, new HashMap<>());
        lengthsForNumberOfKeypadsRemaining.putIfAbsent(numberOfDirectionalKeypadsRemaining, length);
        lengthLookup.put(sequence, lengthsForNumberOfKeypadsRemaining);

    }

    private Long lookupLength(String sequence, int numberOfDirectionalKeypadsRemaining)
    {
        Long length = lengthLookup.getOrDefault(sequence, new HashMap<>())
                .getOrDefault(numberOfDirectionalKeypadsRemaining, null);

        if (length != null)
            logger.logln("At depth " + numberOfDirectionalKeypadsRemaining + " found length for sequence " + sequence + ":" + length);

        return length;
    }

    private Long splitAtAAndFindLength(String sequenceToSplit, int numberOfDirectionalKeypads)
    {
        String[] shortSequences = splitSequenceAtA(sequenceToSplit);
        Long length = Arrays.stream(shortSequences).mapToLong(input ->
        {
            String dictValue = getOrAddToDictionary(input);
            return findLength(dictValue, numberOfDirectionalKeypads);
        }).sum();
        return length;
    }

    private static String[] splitSequenceAtA(String sequenceToSplit)
    {
        String splittableSequence = sequenceToSplit.replace("A", "A,");
        splittableSequence = splittableSequence.substring(0,splittableSequence.length()-1);
        return Arrays.stream(splittableSequence.split(",")).map(String::trim).toArray(String[]::new);
    }

    private String getOrAddToDictionary(String input)
    {
        String dictValue = getFromDictionaryAtLevel(input,1);
        if (dictValue == null)
        {
            logger.logln("Sequence " + input + " not found in dictionary. Adding.");
            dictValue = addShortestSequenceToDictionary(input, 2);
        }
        return dictValue;
    }

    private String addLongerStringToDictionary(String sequenceToSplit)
    {
        String[] shortSequences = splitSequenceAtA(sequenceToSplit);
        String output = Arrays.stream(shortSequences).map(input ->
        {
            String dictValue = getOrAddToDictionary(input);
            return dictValue;

        }).collect(Collectors.joining(""));
        addToDictionaryAtLevel(sequenceToSplit, 1, output);
        return output;
    }

    public Long getComplexity(String pattern)
    {
        return getComplexity(pattern, 2);
    }

    public Long getTotalComplexity(String[] input)
    {
        return getTotalComplexity(input, 2);
    }

    public Long getTotalComplexity(String[] input, int numberOfDirectionalKeyboards)
    {
        Long sum = 0L;
        for(String pattern : input) {
            sum += getComplexity(pattern, numberOfDirectionalKeyboards);
        }
        return sum;
    }

    public Long getComplexity(String pattern, int numberOfDirectionalKeyboards)
    {
        int intPart = Integer.parseInt(pattern.substring(0,3));
        long seqLength = getShortestSequenceLengthForNumberPadSequence(pattern, numberOfDirectionalKeyboards);;
        return intPart*seqLength;
    }


    public void generateDictionary() {
        dictionary = new HashMap<>();
        generateDictionaryEntriesForTheNumericKeyboard();
        generateDictionaryEntriesForDirectionalKeyboard();
    }

    private void generateDictionaryEntriesForDirectionalKeyboard()
    {
        addShortestSequenceToDictionary("A", 2);
        String [] keys = new String[] {"<","^", "v", ">"};
        for (String key1 : keys) {
            addShortestSequenceToDictionary(key1 + "A", 2);
        }
        addShortestSequenceToDictionary("<<A", 2);
        addShortestSequenceToDictionary("<^A", 2);
        addShortestSequenceToDictionary("<vA", 2);
        addShortestSequenceToDictionary("^<A", 2);
        addShortestSequenceToDictionary("^>A", 2);
        addShortestSequenceToDictionary("^^A", 2);
        addShortestSequenceToDictionary("v<A", 2);
        addShortestSequenceToDictionary("vvA", 2);
        addShortestSequenceToDictionary("v>A", 2);
        addShortestSequenceToDictionary(">^A", 2);
        addShortestSequenceToDictionary(">vA", 2);
        addShortestSequenceToDictionary(">>A", 2);
        addShortestSequenceToDictionary("v<A", 2);
        addShortestSequenceToDictionary("^<<A", 2);
        addShortestSequenceToDictionary(">>^A", 2);
        addShortestSequenceToDictionary("v<<A", 2);
        addShortestSequenceToDictionary("vvvA",2);
    }

    private void generateDictionaryEntriesForTheNumericKeyboard()
    {
        for (int i=0; i<10; i++) {
            String keysToPress = "A" + i;
            addShortestSequenceToDictionary(keysToPress, 2);
            keysToPress = i + "A";
            addShortestSequenceToDictionary(keysToPress, 2);
            for (int j=0; j<10; j++) {
                if (i==j) continue;
                keysToPress = "" + i  + j;
                addShortestSequenceToDictionary(keysToPress, 2);
            }
        }
    }

    public Map<String, Map<Integer, String>> getDictionary()
    {
        return dictionary;
    }

    public void printSequence(String sequence) {

        logger.logln("Human:   " + sequence);
        String[] output = new String[] {sequence};
        final String[] spacedSequence = {sequence};
        new ArrayDeque<>(managers)
                .descendingIterator().forEachRemaining(keyboard -> {
            String description = (keyboard.keypad instanceof DirectionalKeypad) ? "Dir kbd" : "Num kbd";
            output[0] = keyboard.applySequence(splitSequenceAtA(output[0]));
            spacedSequence[0] = spaceByA(spacedSequence[0], output[0]);
            logger.logln(description + ": " + spacedSequence[0]);
        });
        logger.logln("String Length: " + sequence.length());
        logger.logln("");

    }

    private String spaceByA(String stringWithAs, String stringToSpace)
    {
        char[] out = " ".repeat(stringWithAs.length()).toCharArray();
        char[] in = stringToSpace.toCharArray();
        int j = 0;
        for (int i =0; i<stringWithAs.length(); i++) {
            char a = stringWithAs.charAt(i);
            if (a == 'A')
            {
                out[i] = in[j++];
            }
        }
        return String.valueOf(out);
    }


}
