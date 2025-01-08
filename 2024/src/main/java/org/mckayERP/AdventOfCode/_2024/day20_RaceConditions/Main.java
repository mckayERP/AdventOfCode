package org.mckayERP.AdventOfCode._2024.day20_RaceConditions;

import org.mckayERP.AdventOfCode.utilities.ResourceFileReader;
import org.mckayERP.AdventOfCode._2024.day16_ReindeerMaze.MazeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) {
        RaceCourseLoader rcl = new RaceCourseLoader();
        rcl.readMapPattern(ResourceFileReader.getResourceFileAsArrayOfStrings(Main.class, "input.txt"));
        MazeMap map = (MazeMap) rcl.getMap();
        map.initialize();
        RaceMazeSolver solver = new RaceMazeSolver(map);
        solver.findBestPath();
        Map<Integer, List<Cheat>> cheats = solver.findCheats(2,2);
        List<Cheat> combinedList = new ArrayList<>();
        cheats.entrySet().stream().filter(e -> e.getKey() >= 100).map(Map.Entry::getValue).forEach(combinedList::addAll);
        System.out.println("Part 1: there are " + combinedList.size() + " cheats that save at least 100 picoseconds.");

        cheats = solver.findCheats(2,20);
        combinedList = new ArrayList<>();
        cheats.entrySet().stream().filter(e -> e.getKey() >= 100).map(Map.Entry::getValue).forEach(combinedList::addAll);
        System.out.println("Part 2: Using updated rules, there are " + combinedList.size() + " cheats that save at least 100 picoseconds.");

    }
}
