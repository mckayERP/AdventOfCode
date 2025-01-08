package org.mckayERP.AdventOfCode._2024.day25_CodeChronicle;

import java.util.ArrayList;
import java.util.List;

public class SchematicReader
{
    List<Lock> locks = new ArrayList<>();

    List<Key> keys = new ArrayList<>();

    public void readInput(String[] lockInput)
    {
        for (int i = 0; i<lockInput.length; i++) {
            if (lockInput[i].isEmpty())
                continue;
            if (lockInput[i].contains("#"))
            {
                String[] lockSpec = new String[7];
                for (int j = 0; j < 7; j++)
                {
                    lockSpec[j] = lockInput[i++];
                }
                locks.add(new Lock(lockSpec));
            }
            else if (lockInput[i].contains(".")) {
                String[] keySpec = new String[7];
                for (int j = 0; j < 7; j++)
                {
                    keySpec[j] = lockInput[i++];
                }
                keys.add(new Key(keySpec));

            }
        }


    }

    public List<Lock> getLocks()
    {
        return locks;
    }

    public List<Key> getKeys()
    {
        return keys;
    }

    public long determineNumberOfFits()
    {
        return locks.stream().mapToLong(lock -> keys.stream().filter(lock::keyFits).count()).sum();
    }
}
