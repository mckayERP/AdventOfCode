package org.mckayERP.AdventOfCode._2024.day07_BridgeRepair;

public class CalibrationFinder
{


    Solver solver;
    String[] input;

    public CalibrationFinder() {
        this(false);
    }
    public CalibrationFinder(boolean withConcatenation) {

        this.solver = new Solver(withConcatenation);
    }

    public void setInput(String[] input)
    {
        this.input = input;
    }

    public long getCalibrationResult()
    {
        long sum = 0;
        LineParser parser = new LineParser();
        for (String line : input)
        {
            parser.parse(line);
            long result = parser.getExpectedResult();
            int[] values = parser.getValues();
            boolean ok = solver.setResult(result).setValues(values).canBeSolved();
            if (ok)
                sum += result;
        }
        return sum;
    }
}
