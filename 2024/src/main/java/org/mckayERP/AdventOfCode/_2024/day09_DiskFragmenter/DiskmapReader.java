package org.mckayERP.AdventOfCode._2024.day09_DiskFragmenter;

import java.util.ArrayList;
import java.util.List;

public class DiskmapReader
{
    String diskMap = null;
    int[] disk;

    int lastFreeBlock = -1;
    private int lastBlock = -1;
    private int firstFreeBlock = 0;
    private int diskSize = -1;

    List<DiscBlock> fileBlocks = new ArrayList<>();
    List<DiscBlock> emptyBlocks = new ArrayList<>();

    public void read(String diskMap)
    {
        this.diskMap = diskMap;
        read();
    }

    public int getDiskSize() {
        if (diskSize == -1)
        {
            try
            {
                diskSize = diskMap.chars().map(c -> Integer.parseInt(String.valueOf((char) c))).sum();
            }
            catch (NumberFormatException e) {
                System.out.println("Number format exception " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return diskSize;
    }

    private void read()
    {
        if (diskMap == null || diskMap.isEmpty())
            throw new RuntimeException("Diskmap is not initialized.");

        int size = getDiskSize();
        int fileAndSpaceCount = diskMap.length();
        int fileID = 0;
        lastFreeBlock = 0;
        lastBlock = -1;
        disk = new int[size];
        for (int i=0; i<fileAndSpaceCount; i=i+2) {
            int fileSize = Integer.parseInt(diskMap.substring(i,i+1));
            DiscBlock fileBlock = new DiscBlock();
            fileBlock.setEmpty(false);
            fileBlock.setStartingIndex(lastFreeBlock);
            for(int j=0; j<fileSize; j++)
            {
                lastBlock = lastFreeBlock++;
                disk[lastBlock] = fileID;
            }
            fileBlock.setEndingIndex(lastBlock);
            fileBlocks.add(fileBlock);
            if (i+2 < fileAndSpaceCount)
            {
                int blankSpaceSize = Integer.parseInt(diskMap.substring(i + 1, i + 2));
                if (blankSpaceSize > 0)
                {
                    DiscBlock emptyBlock = new DiscBlock();
                    emptyBlock.setEmpty(true);
                    emptyBlock.setStartingIndex(lastFreeBlock);
                    for (int j = 0; j < blankSpaceSize; j++)
                    {
                        if (firstFreeBlock == 0)
                            firstFreeBlock = lastFreeBlock;
                        disk[lastFreeBlock++] = -1;
                    }
                    emptyBlock.setEndingIndex(lastFreeBlock-1);
                    emptyBlocks.add(emptyBlock);
                }
            }
            fileID++;
        }
    }

    String getDiskAsString()
    {
        String diskString = "";
        for(int block : disk) {
            if (block == -1)
                diskString += ".";
            else
                diskString += block;
        }

        return diskString;
    }

    public void move()
    {
        findFirstFreeBlock();
        while (firstFreeBlock < lastBlock) {
            disk[firstFreeBlock] = disk[lastBlock];
            disk[lastBlock] = -1;
            findLastUsedBlock();
            findFirstFreeBlock();
        }

    }

    private void findFirstFreeBlock()
    {
        while (disk[firstFreeBlock] != -1 && firstFreeBlock<diskSize)
        {
                firstFreeBlock++;
        }

    }

    private void findLastUsedBlock()
    {
        while (disk[lastBlock] == -1 && lastBlock>= 0)
        {
            lastBlock--;
        }

    }

    public long getChecksum()
    {
        long checkSum = 0;
        for (int i=0; i<diskSize; i++) {
            if (disk[i] > -1)
                checkSum += (long) i *disk[i];
        }
        return checkSum;
    }

    public DiscBlock getFirstFreeBlock() {
        return emptyBlocks.get(0);
    }

    public DiscBlock getLastFileBlock()
    {
        return fileBlocks.get(fileBlocks.size() -1);
    }

    public void diskCleanup() {

        for (int block = fileBlocks.size()-1; block>0; block --)
        {
            DiscBlock lastFile = fileBlocks.get(block);
            int lastFileSize = lastFile.getSize();
            boolean moved = false;
            for (int slot = 0; slot < emptyBlocks.size(); slot ++) {
                DiscBlock empty = emptyBlocks.get(slot);
                int availableSize = empty.getSize();
                int startingIndex = empty.getStartingIndex();
                if (startingIndex > lastFile.getStartingIndex())
                    break;
                if (lastFileSize <= availableSize )
                {
                    moved = true;
                    for (int i = 0; i < lastFileSize; i++)
                    {
                        disk[startingIndex + i] = disk[lastFile.startingIndex + i];
                        disk[lastFile.startingIndex + i] = -1;
                    }
                    lastFile.setStartingIndex(startingIndex);
                    lastFile.setEndingIndex(startingIndex + lastFileSize -1);
                    break;
                }
            }
            if(moved)
                recalculateEmptyBlocks();
        }
    }

    private void recalculateEmptyBlocks()
    {
        emptyBlocks = new ArrayList<>();
        boolean looking = true;
        DiscBlock empty = new DiscBlock();
        for (int i=0; i<diskSize; i++) {
            if (disk[i] == -1 && looking) {
                empty.setEmpty(true);
                empty.setStartingIndex(i);
                looking = false;
            }
            if (disk[i] != -1 && !looking) {
                empty.setEndingIndex(i-1);
                emptyBlocks.add(empty);
                empty = new DiscBlock();
                looking = true;
            }
        }
        if (empty.getStartingIndex() > -1)
        {
            empty.setEndingIndex(diskSize - 1);
            emptyBlocks.add(empty);
        }

    }
}
