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

public class ProboscideaVolcanium {
  public static void main(String[] args) throws FileNotFoundException {
    // Create the graph (with no nodes or edges)
    VolcanoGraph elephantVolcano = new VolcanoGraph();

    // Load the data in and populate the graph
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day16/valve_data_test.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day16/valve_data.txt";

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

    // Print the graph to ensure it's set up correctly
    System.out.println(elephantVolcano);

    // Find the max flow rate in 30 mins
    String startValve = "AA";
    int timeLim = 30;
    int result = findMaxFlow(startValve, timeLim, elephantVolcano);

    System.out.println("Max recorded flow is: " + result);


  }


  // Method to find the maximum flow rate given a starting valve and time limit
  public static int findMaxFlow(String startingValve, int timeLimit, VolcanoGraph graph) {
    if (!graph.getValves().containsKey(startingValve)) {
      System.out.println("Valve not in Volcano, stopping analysis.");
      return -1;
    }


    // Maps a valve to a map of amounts of time left to a map of valve configs which are mapped to a max flow
    // Fundamentally, you give the outer map your position and it returns possible times left.
    // You then give that map your current time left, and it returns possible valve configs
    // You then give it your current config, and it gives you the max flow based on all these
    // We will be checking this map at the top of the iteration to see if the value exists,
    // and if it does, simply returning that.  If it doesn't, we'll add it at the end of the loop
    HashMap<String, HashMap<Integer, HashMap<String, Integer>>> valveMaxFlows = new HashMap<>();

    // Call the recursive function
    int maxVal = recursiveMaxFlow(startingValve, 0, timeLimit, 0, graph, valveMaxFlows);
    return maxVal;
  }

  // private static int recursiveMaxFlow(
  // String currentValve,
  // int remainingTime,
  // VolcanoGraph graph,
  // HashMap<String, HashMap<Integer, HashMap<HashMap<String, Boolean>, Integer>>> resultMap) {
  //   // First, the base case - if time is out, we can't have any flow
  //   if (remainingTime == 0) {
  //     return 0;
  //   }
  //   // Second, check to see if we already have a maximum assigned for this config
  //   HashMap<String,Boolean> currentValveStatus = graph.getValvesOpen();
  //   if(
  //   resultMap != null &&
  //   resultMap.containsKey(currentValve) &&
  //   resultMap.get(currentValve) != null &&
  //   resultMap.get(currentValve).containsKey(remainingTime) &&
  //   resultMap.get(currentValve).get(remainingTime) != null &&
  //   resultMap.get(currentValve).get(remainingTime).containsKey(currentValveStatus)
  //   ) {
  //     System.out.println("Using dynamic programming!");
  //     return resultMap.get(currentValve).get(remainingTime).get(currentValveStatus);
  //   }
  //   // Otherwise, proceed to recursive calls
  //   // Option 1: sit at this node (can maybe delete this one?)
  //   int maxFlow = recursiveMaxFlow(currentValve, remainingTime - 1, graph, resultMap);
  //
  //   // Option 2: If the valve we are at is closed, open it
  //   if (!graph.getValveStatus(currentValve)) {
  //     // Open the valve
  //     graph.openValve(currentValve);
  //     // Call the function again, with time less by 1
  //     int checkFlow = recursiveMaxFlow(currentValve, remainingTime - 1, graph, resultMap);
  //     if (checkFlow > maxFlow) {
  //       maxFlow = checkFlow;
  //     }
  //     // Close the valve again to maintain correct state moving to part 3
  //     graph.closeValve(currentValve);
  //   }
  //   // Option 3: Leave the valve and move to a new valve
  //   // Get the tunnels leaving this valve
  //   ArrayList<TunnelEdge> tunnelList = graph.getTunnels().get(currentValve);
  //   // Iterate through the tunnels, checking each one
  //   for (TunnelEdge tunnel : tunnelList) {
  //     String destValve = tunnel.getDest().getValveName();
  //     int checkFlow = recursiveMaxFlow(destValve, remainingTime - 1, graph, resultMap);
  //     if (checkFlow > maxFlow) {
  //       maxFlow = checkFlow;
  //     }
  //   }
  //
  //   int finalFlow = maxFlow + graph.getCurrentFlowRate();
  //   System.out.println("At valve: " + currentValve + ", time is: " + remainingTime);
  //   System.out.println(finalFlow);
  //
  //   // If we have gotten to this point, we need to add it to the result map
  //   if (resultMap == null || !resultMap.containsKey(currentValve)) {
  //     resultMap.put(currentValve, new HashMap<Integer, HashMap<HashMap<String, Boolean>, Integer>>());
  //   }
  //   if (resultMap.get(currentValve) == null || !resultMap.get(currentValve).containsKey(remainingTime)) {
  //     resultMap.get(currentValve).put(remainingTime, new HashMap<HashMap<String, Boolean>, Integer>());
  //   }
  //   resultMap.get(currentValve).get(remainingTime).put(currentValveStatus, finalFlow);
  //
  //   // Finally, return the maximum flow found
  //   return finalFlow;
  // }

  private static int recursiveMaxFlow(
  String currentValve,
  int currentTime,
  int maxTime,
  int currentTotalFlow,
  VolcanoGraph graph,
  HashMap<String, HashMap<Integer, HashMap<String, Integer>>> resultMap) {
    // First, the base case - if time is out, we can't have any flow
    if (currentTime == maxTime - 1) {
      return graph.getCurrentFlowRate();
    }
    // Second, check to see if we already have a maximum assigned for this config
    String currentValveStatus = graph.getValveStatusString();
    if(
    resultMap != null &&
    resultMap.containsKey(currentValve) &&
    resultMap.get(currentValve) != null &&
    resultMap.get(currentValve).containsKey(currentTime) &&
    resultMap.get(currentValve).get(currentTime) != null &&
    resultMap.get(currentValve).get(currentTime).containsKey(currentValveStatus)
    ) {
      // System.out.println("Using dynamic programming!");
      return resultMap.get(currentValve).get(currentTime).get(currentValveStatus);
    }

    int newFlow = currentTotalFlow + graph.getCurrentFlowRate();
    // System.out.println("At valve: " + currentValve + " and time " + currentTime + ", current total flow is: ");
    // System.out.println(newFlow);

    // Otherwise, proceed to recursive calls
    // Option 1: sit at this node (can maybe delete this one?)
    int maxBelow = recursiveMaxFlow(currentValve, currentTime + 1, maxTime, newFlow, graph, resultMap);

    // Option 2: If the valve we are at is closed, open it
    if (!graph.getValveStatus(currentValve)) {
      // Open the valve
      graph.openValve(currentValve);
      // Call the function again, with time increased by 1
      int checkBelow = recursiveMaxFlow(currentValve, currentTime + 1, maxTime, newFlow, graph, resultMap);
      if (checkBelow > maxBelow) {
        maxBelow = checkBelow;
      }
      // Close the valve again to maintain correct state moving to part 3
      graph.closeValve(currentValve);
    }
    // Option 3: Leave the valve and move to a new valve
    // Get the tunnels leaving this valve
    ArrayList<TunnelEdge> tunnelList = graph.getTunnels().get(currentValve);
    // Iterate through the tunnels, checking each one
    for (TunnelEdge tunnel : tunnelList) {
      String destValve = tunnel.getDest().getValveName();
      int checkBelow = recursiveMaxFlow(destValve, currentTime + 1, maxTime, newFlow, graph, resultMap);
      if (checkBelow > maxBelow) {
        maxBelow = checkBelow;
      }
    }

    int finalFlow = maxBelow + graph.getCurrentFlowRate();

    // If we have gotten to this point, we need to add it to the result map
    if (resultMap == null || !resultMap.containsKey(currentValve)) {
      resultMap.put(currentValve, new HashMap<Integer, HashMap<String, Integer>>());
    }
    if (resultMap.get(currentValve) == null || !resultMap.get(currentValve).containsKey(currentTime)) {
      resultMap.get(currentValve).put(currentTime, new HashMap<String, Integer>());
    }
    resultMap.get(currentValve).get(currentTime).put(currentValveStatus, finalFlow);

    // Finally, return the maximum flow found
    return finalFlow;
  }



}
