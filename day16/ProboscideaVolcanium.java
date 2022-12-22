// File for determining which valves to shut off in a volcano to relieve pressure
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Map;

public class ProboscideaVolcanium {
  public static void main(String[] args) throws FileNotFoundException {
    // Create the graph (with no nodes or edges)
    VolcanoGraph elephantVolcano = new VolcanoGraph();

    // Load the data in and populate the graph
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day16/valve_data_test.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day16/valve_data.txt";

    File volcano = new File(filePath);
    Scanner volcanoScanner = new Scanner(volcano);
    ArrayList<ArrayList<String>> volcanoArray = new ArrayList<>();

    while(volcanoScanner.hasNextLine()) {
      // Create an array to track the relevant data in the line
      ArrayList<String> lineArray = new ArrayList<>();
      String newLine = volcanoScanner.nextLine();
      // System.out.println(newLine);
      // Create the regex pattern matching
      Pattern valvePattern = Pattern.compile("[A-Z]{2}");
      Pattern ratePattern = Pattern.compile("\\d+");
      // Create the pattern matchers
      Matcher valveMatch = valvePattern.matcher(newLine);
      Matcher rateMatch = ratePattern.matcher(newLine);
      // These first two find the valve being added and its flow rate
      rateMatch.find();
      valveMatch.find();
      lineArray.add(valveMatch.group());
      lineArray.add(rateMatch.group());
      // Then, this finds the valves connected to this valve
      while(valveMatch.find()) {
        lineArray.add(valveMatch.group());
      }
      volcanoArray.add(lineArray);
      // Finally, at this point, add the valve to the graph (nodes will be added in a second pass)
      String origin = lineArray.get(0);
      int flowRate = Integer.parseInt(lineArray.get(1));
      elephantVolcano.addValve(origin, flowRate);
    }

    // Now, add the tunnels between each valve
    int tunnelLength = 1;
    for (ArrayList<String> valve : volcanoArray) {
      for (int i = 2; i< valve.size(); i++) {
        elephantVolcano.addTunnel(valve.get(0), valve.get(i), tunnelLength);
      }
    }

    // And, generate the adjacency matrix
    elephantVolcano.genAdjMatrix();

    // Print the graph to ensure it's set up correctly
    System.out.println(elephantVolcano);
    System.out.println(elephantVolcano.getValveList());

    // Now, check the adjacency matrix by printing it
    ArrayList<ArrayList<Integer>> printList = elephantVolcano.getAdjMatrix();
    for (ArrayList<Integer> line : printList) {
      for (Integer num : line) {
        System.out.print(num + "\t");
      }
      System.out.println("");
    }
    ArrayList<ArrayList<Integer>> distList = new ArrayList<>();
    distList = pairDists(elephantVolcano);
    for (ArrayList<Integer> line : distList) {
      for (Integer num : line) {
        System.out.print(num + "\t");
      }
      System.out.println("");
    }

    System.out.println(elephantVolcano.getAdjMatrix());

    // Create a simplified graph
    SimpleVolcanoGraph simpleElephantVolcano = new SimpleVolcanoGraph(elephantVolcano);
    System.out.println(simpleElephantVolcano);

    printList = simpleElephantVolcano.getAdjMatrix();
    for (ArrayList<Integer> line : printList) {
      for (Integer num : line) {
        System.out.print(num + "\t");
      }
      System.out.println("");
    }


    // Find the max flow rate in 30 mins (Part 1)
    String startValve = "AA";
    int timeLim = 30;
    HashMap<String, Integer> solutions = new HashMap<>();
    int result = findMaxFlow(startValve, timeLim, simpleElephantVolcano, solutions);
    // int result = findMaxFlow(startValve, timeLim, elephantVolcano);
    System.out.println("Max recorded flow, part 1, is: " + result);
    System.out.println(solutions.size());

    // Part 2 : an elephant can help, start at t = 26 minutes
    startValve = "AA";
    timeLim = 26;
    HashMap<String, Integer> part2Solutions = new HashMap<>();
    result = findMaxFlow(startValve, timeLim, simpleElephantVolcano, part2Solutions);
    // int result = findMaxFlow(startValve, timeLim, elephantVolcano);
    System.out.println("Max recorded flow, in 26 minutes, is: " + result);
    System.out.println(part2Solutions.size());
    int sharedSol = findSharedSolution(part2Solutions);
    System.out.println("Shared solution is: " + sharedSol);
  }


  // Method to find the shortest path between each pair of vertices using Floyd-Warshall
  public static ArrayList<ArrayList<Integer>> pairDists(VolcanoGraph graph) {
    ArrayList<ArrayList<Integer>> dist = graph.getAdjMatrix();
    for (int i = 0; i < dist.size(); i++) {
      dist.get(i).add(i, 0);
      dist.get(i).remove(i+1);
    }
    for (int k = 0; k < dist.size(); k++) {
      for (int i = 0; i < dist.size(); i++) {
        for (int j = 0; j < dist.size(); j++) {
          if (dist.get(i).get(k) == null || dist.get(k).get(j) == null) {
            continue;
          }
          else if (dist.get(i).get(j) == null ||
                  dist.get(i).get(j) > dist.get(i).get(k) + dist.get(k).get(j)) {
            dist.get(i).add(j, dist.get(i).get(k) + dist.get(k).get(j));
            dist.get(i).remove(j+1);
          }
        }
      }
    }
    return dist;
  }


  // Method to find the maximum flow rate given a starting valve and time limit
  public static int findMaxFlow(String startingValve, int timeLimit, VolcanoGraph graph, HashMap<String, Integer> solutions) {
    if (!graph.getValves().containsKey(startingValve)) {
      System.out.println("Valve not in Volcano, stopping analysis.");
      return -1;
    }

    ArrayList<String> openedNodes = new ArrayList<>();
    ArrayList<Integer> openedTimes = new ArrayList<>();
    // Call the recursive function
    int maxVal = recursiveMaxFlow(startingValve, 0, timeLimit, 0, graph, openedNodes, openedTimes, solutions);
    return maxVal;
  }

  // This method returns the maximum amount of flow in the remaining time up to
  // a limit from a given valve configuration, current time, and maximum time.
  private static int recursiveMaxFlow(
  String currentValve,
  int currentTime,
  int maxTime,
  int currentTotalFlow,
  VolcanoGraph graph,
  ArrayList<String> visitedNodes,
  ArrayList<Integer> visitedTimes,
  HashMap<String, Integer> solutions
  ) {
    // First, the base case - we have opened all nodes
    if (graph.allValvesOpen()) {
      // System.out.println("Here.");
      // System.out.println(visitedNodes);
      return (maxTime - currentTime) * graph.getMaxFlowRate();
    }
    // Otherwise, move to each node in this node's connections and open it, then
    // repeat
    ArrayList<TunnelEdge> tunnelList = graph.getTunnels().get(currentValve);
    int checkBelow = 0;
    int maxBelow = 0;
    // Iterate through the tunnels, checking each one
    for (TunnelEdge tunnel : tunnelList) {
      String destValve = tunnel.getDest().getValveName();
      // System.out.println(destValve);
      if (!destValve.equals("AA") && !visitedNodes.contains(destValve)) {
        // Calculate the time to travel to the node, as well as the flow along that path
        int travelTime = tunnel.getWeight();
        if (currentTime + travelTime + 1 > maxTime) {
          int upToTime = maxTime - currentTime;
          checkBelow = upToTime * (graph.getCurrentFlowRate());
          int totalFlow = currentTotalFlow + checkBelow;
          String nodesTraveled = valveOrderToString(visitedNodes);
          addToSolutions(nodesTraveled, totalFlow, solutions);
        } else {
          int travelFlow = ((travelTime + 1) * graph.getCurrentFlowRate());
          // Add this node to the visited nodes, as well as the
          visitedNodes.add(destValve);
          visitedTimes.add(currentTime + travelTime + 1);
          graph.openValve(destValve);
          // System.out.println(visitedNodes);
          // System.out.println(visitedTimes);
          checkBelow = travelFlow + recursiveMaxFlow(destValve, currentTime + travelTime + 1, maxTime, currentTotalFlow + travelFlow, graph, visitedNodes, visitedTimes, solutions);
          graph.closeValve(destValve);
          visitedNodes.remove(visitedNodes.size() - 1);
          visitedTimes.remove(visitedTimes.size() - 1);
        }
        if (checkBelow > maxBelow) {
          maxBelow = checkBelow;
        }
      }
    }
    return maxBelow;
  }

  private static String valveOrderToString(ArrayList<String> openedNodes) {
    String returnString = "";
    ArrayList<String> sortedNodes = (ArrayList)openedNodes.clone();
    Collections.sort(sortedNodes);
    for (String valve : sortedNodes) {
      returnString += valve;
    }
    return returnString;
  }

  private static HashSet<String> valveOrderToSet(String valves) {
    HashSet<String> valveSet = new HashSet<>();
    for (int i = 0; i < valves.length()/2; i++) {
      valveSet.add(valves.substring(2*i, 2*i + 2));
    }
    return valveSet;
  }

  private static void addToSolutions(String valvesVisited, int total, HashMap<String, Integer> solutionMap) {
    int current = solutionMap.getOrDefault(valvesVisited, -1);
    if (total > current) {
      solutionMap.put(valvesVisited, total);
    }
  }

  private static int findSharedSolution(HashMap<String, Integer> solutions) {
    int maxSharedVal = 0;
    HashMap<String,Integer> solutionsCopy = new HashMap<String,Integer>(solutions);
    for (HashMap.Entry<String,Integer> entryOut : solutions.entrySet()) {
      HashSet<String> outerSet = valveOrderToSet(entryOut.getKey());
      for (HashMap.Entry<String,Integer> entryIn : solutionsCopy.entrySet()) {
        HashSet<String> innerSet = valveOrderToSet(entryIn.getKey());
        if (Collections.disjoint(innerSet, outerSet)) {
          // System.out.println(innerSet);
          // System.out.println(outerSet);
          // System.out.println("");
          int sum = entryIn.getValue() + entryOut.getValue();
          if (sum > maxSharedVal) {
            maxSharedVal = sum;
          }
        }
      }
    }
    return maxSharedVal;
  }
}
