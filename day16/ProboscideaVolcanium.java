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

    System.out.println(result);


  }


  // Method to find the maximum flow rate given a starting valve and time limit
  public static int findMaxFlow(String startingValve, int timeLimit, VolcanoGraph graph) {
    if (!graph.getValves().containsKey(startingValve)) {
      System.out.println("Valve not in Volcano, stopping analysis.");
      return -1;
    }
    return 0;
  }
}
