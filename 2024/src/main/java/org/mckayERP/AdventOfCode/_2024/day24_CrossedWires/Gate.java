package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Gate implements PropertyChangeListener
{
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private int ID = -1;
    private final GateType gateType;
    private Wire inputOne = null;
    private Wire inputTwo = null;
    private Wire output = null;

    public Gate(GateType type)
    {
        gateType = type;
    }

    public void connectInputs(Wire w1, Wire w2)
    {

        if (w1.equals(output) || w2.equals(output))
            throw new IllegalStateException("Can't attach a wire to both the input and output of the same gate.");

        inputOne = w1;
        inputTwo = w2;
        inputOne.addPropertyChangeListener(this);
        inputTwo.addPropertyChangeListener(this);
    }

    public void connectOutput(Wire wOut)
    {
        if (wOut.equals(inputOne) || wOut.equals(inputTwo))
            throw new IllegalStateException("Can't attach a wire to both the input and output of the same gate.");

        output = wOut;
        if (inputOne != null && inputTwo != null)
            setValue();
    }

    public void setValue() {

        if (inputOne == null || inputTwo == null || output == null)
            throw new RuntimeException("Gate not wired properly.");

        Boolean oldValue = output.getValue();
        if (inputOne.getValue() == null || inputTwo.getValue() == null)
            output.setValue((Boolean) null);
        else {
            switch (gateType) {
                case XOR ->
                {
                    output.setValue(Boolean.logicalXor(inputOne.getValue(), inputTwo.getValue()));
                }
                case OR ->
                {
                    output.setValue(Boolean.logicalOr(inputOne.getValue(), inputTwo.getValue()));
                }
                case AND ->
                {
                    output.setValue(Boolean.logicalAnd(inputOne.getValue(), inputTwo.getValue()));
                }
            }
        }
        this.pcs.firePropertyChange(PropertyNames.GATE_VALUE_CHANGED, oldValue, output.getValue());

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(PropertyNames.GATE_VALUE_CHANGED, listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if ((evt.getSource().equals(inputOne) || evt.getSource().equals(inputTwo)) && evt.getPropertyName().equals(PropertyNames.WIRE_VALUE_CHANGED))
            setValue();
    }

    public Wire getOutputConnection()
    {
        return output;
    }

    @Override
    public String toString() {
        if (inputOne == null && inputTwo == null && output == null)
            return "<Not connected>";
        return ID + ":" +inputOne + " " + gateType + " " + inputTwo + " -> " + output;
    }

    public Wire getInputOne()
    {
        return inputOne;
    }

    public Wire getInputTwo()
    {
        return inputTwo;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public GateType getGateType()
    {
        return gateType;
    }
}
