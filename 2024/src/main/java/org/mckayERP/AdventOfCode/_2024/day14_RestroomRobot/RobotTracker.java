package org.mckayERP.AdventOfCode._2024.day14_RestroomRobot;

import org.mckayERP.AdventOfCode.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RobotTracker
{
    int maxRows;
    int maxColumns;

    List<Robot> robots = new ArrayList<>();
    public void setGridLimits(int rows, int cols)
    {
        maxRows = rows;
        maxColumns = cols;
    }

    public void move(Robot r, int seconds)
    {
        int x = Math.floorMod(r.currentPosition.getCol() + r.velocity.vx*seconds, maxColumns);
        r.currentPosition.setCol(x);

        int y = Math.floorMod(r.currentPosition.getRow() + r.velocity.vy*seconds, maxRows);
        r.currentPosition.setRow(y);
    }

    public void readInput(String[] strings)
    {
        for (String line : strings) {

            Pattern p = Pattern.compile("p=(\\d+),(\\d+)\\s*v=(-*\\d+),(-*\\d+)");
            Matcher m = p.matcher(line);
            if (m.find()) {
                int x = Integer.parseInt(m.group(1));
                int y = Integer.parseInt(m.group(2));
                int vx = Integer.parseInt(m.group(3));
                int vy = Integer.parseInt(m.group(4));
                robots.add(new Robot(new Position(y,x), new Velocity(vx,vy)));
            }
            else
                throw new IllegalArgumentException("Input line did not contain robot info.");

        }
        System.out.println("Tracker: read in " + robots.size() + " robots.");
        //printMap(false);
    }

    public List<Robot> getRobots()
    {
        return robots;
    }

    public void move(int moves)
    {
        for (Robot r : robots) {
            move(r, moves);
        }
        //printMap(false);
    }

    void printMap(boolean withQuadrants)
    {
        System.out.println();
        for(int row=0; row<maxRows; row++) {
            if (withQuadrants && row == maxRows/2)
                System.out.println();
            else
            {
                for (int col = 0; col < maxColumns; col++)
                {
                    int finalRow = row;
                    int finalCol = col;
                    long num = robots.stream().filter(r -> r.currentPosition.equals(new Position(finalRow, finalCol))).count();
                    if (withQuadrants && finalCol == maxColumns / 2)
                        System.out.print(" ");
                    else
                    {
                        if (num == 0)
                            System.out.print(".");
                        else
                            System.out.print("" + num);
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public long getCountInQuadrants()
    {

        //printMap(true);
        long q1 = robots.stream().filter(this::inQuadrant1).count();
        long q2 = robots.stream().filter(this::inQuadrant2).count();
        long q3 = robots.stream().filter(this::inQuadrant3).count();
        long q4 = robots.stream().filter(this::inQuadrant4).count();

        System.out.println("Robots on the map " + robots.stream().filter(r -> onTheMap(r)).count());
        System.out.println("Robots in Q1=" + q1 + " Q2="+q2 + " Q3=" + q3 + " Q4=" + q4);
        System.out.println("Total # of robots in quadrants " + (q1+q2+q3+q4));
        return q1 * q2 * q3 * q4;

    }

    private boolean onTheMap(Robot r)
    {
        return 0 <= r.currentPosition.getCol() && r.currentPosition.getCol() < maxColumns
                && 0 <= r.currentPosition.getRow() && r.currentPosition.getRow() < maxRows;
    }

    private boolean inQuadrant1(Robot r)
    {
        return r.currentPosition.getCol() < maxColumns/2
                &&  r.currentPosition.getRow() < maxRows/2;
    }

    private boolean inQuadrant2(Robot r)
    {
        return maxColumns/2 < r.currentPosition.getCol()
                && r.currentPosition.getRow() < maxRows/2;
    }

    private boolean inQuadrant3(Robot r)
    {
        return  r.currentPosition.getCol() < maxColumns/2
                && maxRows/2 < r.currentPosition.getRow();
    }
    private boolean inQuadrant4(Robot r)
    {
        return maxColumns/2 < r.currentPosition.getCol()
                && maxRows/2 < r.currentPosition.getRow();
    }

    public int xmasTreeFinder() {

        resetRobots();
        // int treeAtTime = smallestYStdDev();
        int treeAtTime = mostConnected();
        resetRobots();
        move(treeAtTime);
        printMap(false);
        return treeAtTime;
    }

    private int smallestYStdDev()
    {
        // Try for smallest sdev in y
        double smallestSDev = 1000000;
        int treeAtTime = -1;
        for (int i=1; i<1000000; i++) {
            move(1);
            double meanX = robots.stream().mapToDouble(r -> r.currentPosition.getCol()).sum()/robots.size();
            double sdevX = Math.sqrt(robots.stream().mapToDouble(r -> Math.pow(1.0*r.currentPosition.getCol() - meanX, 2)).sum()/robots.size());
            double meanY = robots.stream().mapToDouble(r -> r.currentPosition.getRow()).sum()/robots.size();
            double sdevY = Math.sqrt(robots.stream().mapToDouble(r -> Math.pow(1.0*r.currentPosition.getRow() - meanY, 2)).sum()/robots.size());
            double sdev = sdevX*sdevY;
            if (sdevY < smallestSDev) {
                smallestSDev = sdevY;
                treeAtTime = i;
            }
        }
        return treeAtTime;
    }

    private int mostConnected() {

        Long mostConnected = -1L;
        int treeAtTime = -1;
        for (int i=1; i<10000; i++) {
            move(1);
            Long connected = robots.stream().mapToLong(r -> {
                return robots.stream().filter(rn -> rn.nextTo(r)).count();
            }).sum();
            if (connected > mostConnected) {
                mostConnected = connected;
                treeAtTime = i;
            }
            System.out.println("After " + i + " times - connected count = " + connected + " most ever (" + mostConnected + ":" + treeAtTime + ")");
        }
        return treeAtTime;

    }
    private void resetRobots()
    {
        robots.forEach(Robot::resetPosition);
    }

}
