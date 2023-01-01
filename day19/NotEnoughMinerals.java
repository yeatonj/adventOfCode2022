// Program for collecting and breaking geodes
// Written for AOC 2022 Day 19
// Written by Josh Yeaton
// Written on 12/30/2022

import java.util.ArrayList;
import java.util.PriorityQueue;

class NotEnoughMinerals {
  public static void main(String[] args) {
    RobotFactory factoryOne = new RobotFactory(4, 2, new int[]{3,14}, new int[]{2,7});
    RobotFactory factoryTwo = new RobotFactory(2, 3, new int[]{3,8}, new int[]{3,12});

    int baselineGeodesFactoryOne = findFactoryMax(factoryOne, 24);
    int baselineGeodesFactoryTwo = findFactoryMax(factoryTwo, 24);
    System.out.println(baselineGeodesFactoryOne + " Geodes from factory one.");
    System.out.println(baselineGeodesFactoryTwo + " Geodes from factory two.");


  }

  // General idea - find the maximum possible value for the factory, then use that
  // to govern which nodes we search to quickly narrow down the search - BFS
  // with a priority queue based on the estimated # for each factory
  public static int findFactoryMax(RobotFactory factory, int timeLimit) {
    int maxGeodes = factory.getUpperGeodeBound(timeLimit);
    int currentGeodes = 0;
    PriorityQueue<RobotFactory> factoryQueue = new PriorityQueue<>();
    factoryQueue.add(factory);
    int j = 0;
    while(!factoryQueue.isEmpty() && currentGeodes < maxGeodes) {
      j++;
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
