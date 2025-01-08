package org.mckayERP.AdventOfCode._2024.day04_CeresSearch;

public class WordSearcher
{

    int rowCount = 0;
    int colCount = 0;

    int countOffset = 0;
    char[][] grid;

    char[] searchString;
    public void setStringArray(String[] strings)
    {
        rowCount = strings.length;
        colCount = strings[0].length();

        grid = new char[rowCount][colCount];
        for(int row = 0; row< rowCount; row ++) {
            grid[row] = strings[row].toCharArray();
        }

    }

    public void setSearchString(String s)
    {
        this.searchString = s.toCharArray();
        countOffset = searchString.length;
    }

    public int countOfSearchString()
    {
        int count = 0;
        int searchStringLength = searchString.length;
        int searchStringIndex = 0;

        for (Direction direction : Direction.values())
        {
            for (int row = 0; row < rowCount ; row++)
            {
                for (int col = 0; col < colCount; col++)
                {
                    if (grid[row][col] == searchString[0])
                    {
                        searchStringIndex = 1;
                        int nextCharRow = row + getRowCharIncrement(direction);
                        int nextCharCol = col + getColCharIncrement(direction);
                        while (searchStringIndex < searchStringLength
                                && (0 <= nextCharRow && nextCharRow < rowCount)
                                && (0 <= nextCharCol && nextCharCol < colCount)
                                && grid[nextCharRow][nextCharCol] == searchString[searchStringIndex])
                        {
                            searchStringIndex++;
                            if (searchStringIndex == searchStringLength)
                            {
                                count++;
                            }
                            nextCharRow += getRowCharIncrement(direction);
                            nextCharCol += getColCharIncrement(direction);
                        }
                    }
                }
            }
        }
        return count;
    }

    public int getStartingRow(Direction direction)
    {
        int start = switch (direction)
        {
            case BTT -> rowCount-1;
            case DDR, DDL -> rowCount - countOffset - 1;
            case DUR, DUL -> countOffset - 1;
            default -> 0;
        };
        return limitToRowBounds(start);
    }

    public int limitToRowBounds(int end)
    {
        if (end < 0)
            end = 0;
        if (end >= rowCount)
            end = rowCount-1;
        return end;
    }

    public int getRowCharIncrement(Direction direction) {
        return switch (direction)
        {
            case BTT, DUL, DUR -> -1;
            case TTB, DDR, DDL -> 1;
            default -> 0;
        };
    }

    public int getColCharIncrement(Direction direction) {
        return switch (direction)
        {
            case RTL, DUL, DDL -> -1;
            case LTR, DDR, DUR -> 1;
            default -> 0;
        };
    }

}
