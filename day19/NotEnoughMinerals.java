// Program for collecting and breaking geodes
// Written for AOC 2022 Day 19
// Written by Josh Yeaton
// Written on 12/30/2022

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class NotEnoughMinerals {
  public static void main(String[] args) throws FileNotFoundException {

    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day19/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day19/data_test.txt";
    ArrayList<ArrayList<Integer>> fileInts = new ArrayList<>();

    // Scan the data into the file
    File blueprintFile = new File(filePath);
    Scanner blueprintScanner = new Scanner(blueprintFile);
    int counter = 0;
    ArrayList<Integer> blueprintInts = new ArrayList<>();
    while (blueprintScanner.hasNext()) {
      if (blueprintScanner.hasNextInt()) {
        counter++;
        blueprintInts.add(blueprintScanner.nextInt());
        if (counter % 6 == 0 && counter > 0) {
          fileInts.add(blueprintInts);
          blueprintInts = new ArrayList<>();
        }
      } else {
        blueprintScanner.next();
      }
    }

    int timeLimit = 24;
    int totalQualityScore = 0;
    ArrayList<Integer> factoryScores = new ArrayList<>();
    ArrayList<Integer> qualityScores = new ArrayList<>();
    for (int i = 0; i < fileInts.size(); i++) {

      RobotFactory tempFactory = new RobotFactory(
      fileInts.get(i).get(0),
      fileInts.get(i).get(1),
      new int[]{fileInts.get(i).get(2),fileInts.get(i).get(3)},
      new int[]{fileInts.get(i).get(4),fileInts.get(i).get(5)});

      factoryScores.add(findFactoryMax(tempFactory, timeLimit));
      qualityScores.add(factoryScores.get(i) * (i+1));
      totalQualityScore += qualityScores.get(i);
    }
    System.out.println(factoryScores);
    System.out.println(qualityScores);
    System.out.println("Total Part 1 quality score is: " + totalQualityScore);

    // Now, part 2
    timeLimit = 32;
    int totalFactoryScore = 1;
    ArrayList<Integer> pt2FactoryScores = new ArrayList<>();
    ArrayList<Integer> pt2QualityScores = new ArrayList<>();
    for (int i = 0; i < 3; i++) {

      RobotFactory tempFactory = new RobotFactory(
      fileInts.get(i).get(0),
      fileInts.get(i).get(1),
      new int[]{fileInts.get(i).get(2),fileInts.get(i).get(3)},
      new int[]{fileInts.get(i).get(4),fileInts.get(i).get(5)});

      pt2FactoryScores.add(findFactoryMax(tempFactory, timeLimit));
      totalFactoryScore *= pt2FactoryScores.get(i);
    }
    System.out.println(pt2FactoryScores);
    System.out.println("Total Part 2 quality score is: " + totalFactoryScore);


  }

  // General idea - find the maximum possible value for the factory, then use that
  // to govern which nodes we search to quickly narrow down the search - BFS
  // with a priority queue based on the estimated # for each factory
  public static int findFactoryMax(RobotFactory factory, int timeLimit) {
    int maxGeodes = factory.getUpperGeodeBound(timeLimit);
    int currentGeodes = 0;
    PriorityQueue<RobotFactory> factoryQueue = new PriorityQueue<>();
    factoryQueue.add(factory);
    while(!factoryQueue.isEmpty() && currentGeodes < maxGeodes) {
      RobotFactory tempFactory = factoryQueue.poll();
      maxGeodes = tempFactory.getMaxPossGeodes();
      currentGeodes = tempFactory.getTotalGeode();
      // System.out.println("Max geodes: " + maxGeodes);
      // System.out.println(tempFactory);
      // Generate new nodes and add them to the queue
      for (int i = 0; i < 5; i++) {
        RobotFactory newFactory = tempFactory.genNewFactory(i);
        if (newFactory != null) {
          newFactory.getUpperGeodeBound(timeLimit);
          factoryQueue.add(newFactory);
        }
      }
    }
    return currentGeodes;
  }
}
