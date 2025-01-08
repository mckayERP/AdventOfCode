package org.mckayERP.AdventOfCode._2024.day24_CrossedWires;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Wire implements PropertyChangeListener
{
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    Boolean binaryValue = null;

    boolean started = false;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    String name;

    List<Gate> gates = new ArrayList<>();
    public Wire() {}

    public Wire(int value) {
        setValue(value);
    }
    public Wire(Boolean value)
    {
        binaryValue = value;
    }

    public Wire(String wireName) {
        setName(wireName);
    }
    public Wire(String wireName, int initialValue)
    {
        setName(wireName);
        setValue(initialValue);
    }

    public Boolean getValue()
    {
        return binaryValue;
    }

    public int getValueAsInt()
    {
        return binaryValue ? 1:0;
    }

    public void setValue(Integer value) {
        if (value == null)
            setValue((Boolean) null);
        else if (value <0 || value > 1)
            throw new IllegalArgumentException("Wire value can only be set by 1 or 0.");
        else setValue(value == 1);
    }

    public void setValue(Boolean newValue)
    {
        Boolean oldValue = binaryValue;
        binaryValue = newValue;
        if (started)
            this.pcs.firePropertyChange(PropertyNames.WIRE_VALUE_CHANGED, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(PropertyNames.WIRE_VALUE_CHANGED, listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals(PropertyNames.START)) {
            started = true;
            if (this.binaryValue != null)
                this.pcs.firePropertyChange(PropertyNames.WIRE_VALUE_CHANGED, (Boolean) null, binaryValue);
        }
        if(evt.getPropertyName().equals(PropertyNames.STOP)) {
            started = false;
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}
