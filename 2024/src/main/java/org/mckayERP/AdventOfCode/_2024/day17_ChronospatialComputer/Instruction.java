package org.mckayERP.AdventOfCode._2024.day17_ChronospatialComputer;

public class Instruction
{
    int opcode;

    public Instruction(int opcode, int operand)
    {
        this.opcode = opcode;
        this.operand = operand;
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

    int operand;
}
