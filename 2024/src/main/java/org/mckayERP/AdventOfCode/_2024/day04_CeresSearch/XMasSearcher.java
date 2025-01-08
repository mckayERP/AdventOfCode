package org.mckayERP.AdventOfCode._2024.day04_CeresSearch;

public class XMasSearcher
{

    int rowCount = 0;
    int colCount = 0;

    int countOffset = 0;
    char[][] grid;

    String[] searchPattern;

    public XMasSearcher() {
        searchPattern = new String[]{
                "MSMS",
                "MMSS",
                "SSMM",
                "SMSM"};
    }
    public void setStringArray(String[] strings)
    {
        rowCount = strings.length;
        colCount = strings[0].length();

        grid = new char[rowCount][colCount];
        for(int row = 0; row< rowCount; row ++) {
            grid[row] = strings[row].toCharArray();
        }

    }


    public int countOfXMas()
    {
        int count = 0;

        for (int row = 1; row < rowCount-1 ; row++)
        {
            for (int col = 1; col < colCount - 1; col++)
            {
                if (grid[row][col] == 'A')
                {
                    String pattern = "" + grid[row - 1][col - 1] + grid[row - 1][col + 1] + grid[row + 1][col - 1] + grid[row + 1][col + 1];
                    for (int i = 0; i < 4; i++)
                    {
                        if (pattern.equals(searchPattern[i]))
                            count++;
                    }
                }
            }
        }
        return count;
    }
}
