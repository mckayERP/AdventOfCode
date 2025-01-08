package org.mckayERP.AdventOfCode._2024.day23_LanParty;

import java.util.*;
import java.util.stream.Collectors;

public class NetworkManager
{
    List<String> computerList = new ArrayList<>();
    Map<String, Set<String>> connectionMap = new HashMap<>();

    TreeMap<Integer, Map<String, Set<String>>> mapSizeToConnectionSets = new TreeMap<>();
    Set<Set<String>> triConnectedComputers = new HashSet<>();
    public void readInputList(String[] input)
    {
        for(String pair : input) {
            String[] computers = pair.split("-");
            for (String computer : computers)
            {
                if (!computerList.contains(computer))
                    computerList.add(computer);
            }
            addComputerPairToConnectionMap(computers);

        }
        fillListOfTriConnectedComputers();
        mapSizeOfConnectionSets();

    }

    private void mapSizeOfConnectionSets()
    {
        connectionMap.forEach((computer, connections) -> {
            int size = connections.size();
            Map<String, Set<String>> setsOfThisSize = mapSizeToConnectionSets.getOrDefault(size, new HashMap<>());
            setsOfThisSize.put(computer, connections);
            mapSizeToConnectionSets.put(size, setsOfThisSize);
        });
    }

    private void addComputerPairToConnectionMap(String[] computers)
    {
        Set<String> connections = connectionMap.getOrDefault(computers[0], new HashSet<>(32));
        connections.add(computers[0]);
        connections.add(computers[1]);
        connectionMap.put(computers[0], connections);
        connections = connectionMap.getOrDefault(computers[1], new HashSet<>(32));
        connections.add(computers[0]);
        connections.add(computers[1]);
        connectionMap.put(computers[1], connections);
    }

    private void fillListOfTriConnectedComputers()
    {
        for (String computer1 : computerList) {
            for (String computer2 : connectionMap.get(computer1)) {
                if (computer2.equals(computer1))
                    continue;
                for (String computer3 : connectionMap.get(computer1)) {
                    if (computer2.equals(computer3) || computer1.equals(computer3))
                        continue;
                    for (String computer3Connection : connectionMap.get(computer3)) {
                        if (computer3Connection.equals(computer1))
                            continue;
                        if (computer3Connection.equals(computer2)) {
                            Set<String> connection = new HashSet<>();
                            connection.add(computer1);
                            connection.add(computer2);
                            connection.add(computer3);
                            addTriConnectionIfAbsent(connection);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void addTriConnectionIfAbsent(Set<String> connection)
    {
        boolean alreadyIncluded = false;
        for (Set<String> savedConnection : triConnectedComputers) {
            alreadyIncluded = savedConnection.containsAll(connection);
            if (alreadyIncluded)
                break;
        }
        if (!alreadyIncluded)
            triConnectedComputers.add(connection);
    }

    public List<String> getListOfComputers()
    {
        return computerList;
    }

    public Set<String> getConnectionsOfComputer(String computer)
    {
        return connectionMap.getOrDefault(computer, new HashSet<>());
    }

    public Set<Set<String>> getListOfConnectComputerSets()
    {
        return triConnectedComputers;
    }

    public int getCountOfConnectionsWithComputersStartingWithT()
    {
        int count = 0;
        for (Set<String> connection : triConnectedComputers) {
            for (String computer : connection)
            {
                if (computer.startsWith("t"))
                {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public Set<String> getLargestSetOfConnectedComputers() {

        int maxSizeFound = 3;
        Set<String> maxSet = null;
        Map<Set<String>, Set<String>> intersections = new HashMap<>();
        Map<String, Set<String>> mostConnected = getMostConnectedComputers();
        mostConnected.forEach((computer, connections) -> {
            Set<String> intersectionID = new HashSet<>();
            intersectionID.add(computer);
            intersections.put(intersectionID, connections);
        });
        boolean done = false;
        int sizeOfSets = 0;
        while(!done)
        {
            done = true;
            Set<Set<String>> sets = new HashSet<>(intersections.keySet());
            sizeOfSets++;
            for (Set<String> setA : sets)
            {
                if (setA.size() != sizeOfSets)
                    continue;
                List<String> rankingOfConnectionsBySize = getRankingOfComputersBySizeOfTheirConnections(intersections.get(setA));
                for (String computerB : rankingOfConnectionsBySize)
                {
                    if (setA.contains(computerB))
                        continue;
                    Set<String> intersectionIdentifier = new HashSet<>();
                    intersectionIdentifier.addAll(setA);
                    intersectionIdentifier.add(computerB);
                    Set<String> intersectionSet = new HashSet<>(intersections.get(setA));
                    intersectionSet.retainAll(connectionMap.get(computerB));
                    if (intersectionSet.size() < maxSizeFound)
                        continue;
                    done = false;
                    intersections.put(intersectionIdentifier, intersectionSet);
                    boolean selfReferential = intersectionIdentifier.equals(intersectionSet);
                    if (selfReferential && intersectionSet.size() > maxSizeFound)
                    {
                        maxSizeFound = intersectionSet.size();
                        maxSet = intersectionSet;
                    }
                }
            }
        }
        return maxSet;
    }

    private List<String> getRankingOfComputersBySizeOfTheirConnections(Set<String> computers)
    {

        return computers.stream()
                .sorted((b,c) -> Integer.compare(connectionMap.get(c).size(), connectionMap.get(b).size())).collect(Collectors.toList());
    }

    private Map<String, Set<String>> getMostConnectedComputers()
    {
        return mapSizeToConnectionSets.lastEntry().getValue();
    }

    public String getPasswordBasedOnSet(Set<String> setToConvert)
    {
        return setToConvert.stream().sorted(String::compareTo).collect(Collectors.joining(","));
    }
}
