package org.mckayERP.AdventOfCode._2024.day11_PlutonianPebbles;

public class StoneTransformer
{
    public Stone[] transform(Stone inputStone)
    {
        Stone[] output = {null};
        if (inputStone.value == 0L)
            output = new Stone[] {new Stone(1L, inputStone.multiplier)};
        else {
            String numberAsString = (""+ inputStone.value);
            if (numberAsString.length() % 2 == 0) {
                int mid = numberAsString.length()/2;
                output = new Stone[] {
                        new Stone(Long.parseLong(numberAsString.substring(0,mid)), inputStone.multiplier),
                        new Stone(Long.parseLong(numberAsString.substring(mid)), inputStone.multiplier)
                };
            }
            else {
                output = new Stone[] { new Stone(inputStone.value*2024L, inputStone.multiplier)};
            }
        }
        return output;
    }
}
