package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

import java.util.ArrayList;
import java.util.List;

public class ThreeBitProcessor
{

    static class ProcessorState {

        long regA;
        long regB;
        long regC;
        int[] program;

        @Override
        public String toString() {
            return ("Processor state:\n"
                    + " Reg A: " + regA + "\n"
                    + " Reg B: " + regB + "\n"
                    + " Reg C: " + regC + "\n"
                    + " Prog : " + program
            );
        }

    }

    private ProcessorState savedState;

    int[] program = new int[0];

    List<Integer> outputAccumulator = new ArrayList<>();

    long regA, regB, regC;

    public void saveProcessorState() {
        savedState = new ProcessorState();
        savedState.regA = regA;
        savedState.regB = regB;
        savedState.regC = regC;
        savedState.program = program;
    }

    public void restoreProcessorState() {

        if (savedState == null)
            return;

        regA = savedState.regA;
        regB = savedState.regB;
        regC = savedState.regC;
        program = savedState.program;

        System.out.println(savedState);

    }

    public long getRegA()
    {
        return regA;
    }

    public void setRegA(long regA)
    {
        this.regA = regA;
    }

    public long getRegB()
    {
        return regB;
    }

    public void setRegB(long regB)
    {
        this.regB = regB;
    }

    public long getRegC()
    {
        return regC;
    }

    public void setRegC(long regC)
    {
        this.regC = regC;
    }

    public long getInstructionPointer()
    {
        return instructionPointer;
    }

    public void setInstructionPointer(int instructionPointer)
    {
        this.instructionPointer = instructionPointer;
    }

    int instructionPointer = 0;


    public void loadProgram(int[] programData)
    {
        program = programData;
    }

    public boolean hasInstructions()
    {
        return instructionPointer < program.length;
    }

    public Instruction getNextInstruction()
    {
        return new Instruction(program[instructionPointer++], program[instructionPointer++]);
    }

    public int getOpcode()
    {
        return opcode;
    }

    public void setOpcode(int opcode)
    {
        this.opcode = opcode;
    }

    public int getOperand()
    {
        return operand;
    }

    public void setOperand(int operand)
    {
        this.operand = operand;
    }

    int opcode;
    int operand;
    public long getOperandValue(int operand, OperandType type)
    {
        return switch (type)
        {
            case LITERAL -> operand;
            case COMBO -> switch (operand)
            {
                case 0, 1, 2, 3 -> operand;
                case 4 -> getRegA();
                case 5 -> getRegB();
                case 6 -> getRegC();
                case 7 ->
                        throw new IllegalArgumentException("Operand 7 is reserved and should not appear in the program.");
                default -> throw new IllegalArgumentException("Unknown operand value " + operand);
            };
        };
    }

    private void instruction_adv() {
        long numerator = getRegA();
        long denominator = 1 << getOperandValue(getOperand(), OperandType.COMBO);
        long result = numerator / denominator;
        setRegA(result);
//        System.out.println("ADV: op0-" + getOperand() + " COMBO. RegA " + numerator + " -> " + getRegA() + " RegA / " + denominator + " 1<<" + getOperandValue(getOperand(), OperandType.COMBO));
    }

    private void instruction_bxl() {
        long initialRegB = getRegB();
        setRegB(initialRegB ^ getOperandValue(getOperand(), OperandType.LITERAL));
//        System.out.println("BXL: op1-" + getOperand() + " LITERAL. RegB " + initialRegB + " ->  RegB ^ operand(L) -> RegB ");
    }

    private void instruction_bst() {
        setRegB(getOperandValue(getOperand(), OperandType.COMBO) % 8);
//        System.out.println("BST: op2-" + getOperand() + " COMBO. Operand value " + getOperandValue(getOperand(), OperandType.COMBO) + " % 8 -> RegB: " + getRegB());
    }

    private void instruction_bxc() {
        long initialRegB = getRegB();
        setRegB(getRegB() ^ getRegC());
//        System.out.println("BXC: op4-" + getOperand() + " ignored. RegB " + initialRegB + " ^ regC" + getRegC() + " -> regB" + getRegB());
    }

    private void instruction_jnz() {
        if (getRegA() == 0)
        {
//            System.out.println("JNZ: op3-" + getOperand() + " LITERAL. Jump instruction. RegA == 0 - ignored." );
            return;
        }
//        System.out.println("JNZ: op3-" + getOperand() + " LITERAL. Jump instruction. RegA != 0. Instruction pointer set to " + getOperandValue(getOperand(), OperandType.LITERAL));
        setInstructionPointer(Long.valueOf(getOperandValue(getOperand(), OperandType.LITERAL)).intValue());
    }

    private void instruction_out() {
//        System.out.println("OUT: op5-" + getOperand() + " COMBO. Outputs the operand value %8: " + getOperandValue(getOperand(),OperandType.COMBO) % 8);
        outputAccumulator.add(Long.valueOf(getOperandValue(getOperand(),OperandType.COMBO) % 8).intValue());
    }
    private void instruction_bdv() {
        long numerator = getRegA();
        long denominator = 1L << getOperandValue(getOperand(), OperandType.COMBO);
        long result = numerator / denominator;
        setRegB(result);
//        System.out.println("BDV: op6-" + getOperand() + " COMBO. RegB  -> " + result + " = " + getRegA() + " / 1 << " + getOperandValue(getOperand(), OperandType.COMBO));

    }

    private void instruction_cdv() {
        long numerator = getRegA();
        long denominator = 1L << getOperandValue(getOperand(), OperandType.COMBO);
        long result = numerator / denominator;
        setRegC(result);
//        System.out.println("CDV: op7-" + getOperand() + " COMBO. RegC  -> " + result + " = " + getRegA() + " / 1 << " + getOperandValue(getOperand(), OperandType.COMBO));
    }

    public void run()
    {
        setInstructionPointer(0);
        outputAccumulator.clear();
        while (hasInstructions()) {
            Instruction inst = getNextInstruction();
            setOpcode(inst.getOpcode());
            setOperand(inst.getOperand());
//            System.out.println(this);
//            System.out.println("Opcode:" + opcode + " operand:" + operand);
            execute();
        }
    }

    private void execute()
    {
        switch (getOpcode()) {
          case 0 -> instruction_adv();
          case 1 -> instruction_bxl();
          case 2 -> instruction_bst();
          case 3 -> instruction_jnz();
          case 4 -> instruction_bxc();
          case 5 -> instruction_out();
          case 6 -> instruction_bdv();
          case 7 -> instruction_cdv();
        };
    }

    public int[] getOutputAccumulator() {
        return outputAccumulator.stream().mapToInt(i -> i).toArray();
    }

    public String getOutput()
    {
        StringBuilder outputString  = new StringBuilder();
        boolean dropComma = true;
        for (int output : outputAccumulator) {
            if (!dropComma)
            {
                outputString.append(",");
            }
            dropComma = false;
            outputString.append(output);
        }
        return outputString.toString();
    }

    @Override
    public String toString() {
        return ("Processor state:\n"
                + " Reg A: " + regA + "\n"
                + " Reg B: " + regB + "\n"
                + " Reg C: " + regC + "\n"
                + " Prog : " + program
        );
    }
}
