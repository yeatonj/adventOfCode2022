// Program for collecting and breaking geodes
// Written for AOC 2022 Day 19
// Written by Josh Yeaton
// Written on 12/30/2022

import java.util.ArrayList;

class NotEnoughMineralsTest {
  public static void main(String[] args) {
    RobotFactory factoryOne = new RobotFactory(4, 2, new int[]{3,14}, new int[]{2,7});
    RobotFactory factoryTwo = new RobotFactory(2, 3, new int[]{3,8}, new int[]{3,18});

    int baselineGeodesFactoryOne = factoryOne.getUpperGeodeBound(24);
    // int baselineGeodesFactoryTwo = fullSim(factoryTwo, 24, 10);
    System.out.println(baselineGeodesFactoryOne + " Geodes from factory one.");
    // System.out.println(baselineGeodesFactoryTwo + " Geodes from factory two.");


  }

  // Modified greedy algorithm to create a baseline for geodes
  public static int greedySim(RobotFactory simFactory, int simTime, int diffRobots) {
    RobotFactory copyFactory = new RobotFactory(simFactory);
    for (int i = 0; i < simTime; i++) {
      // General heuristic - if we can make a geode robot, we will do so, otherwise
      // we will make an obsidian robot.  If we can't make one of those, we will
      // make a clay robot, and if we can't do that, we'll make an ore robot.
      // Importantly, we'll only make a third robot of any type if the difference
      // between the most robots and least robots of any type is < 3
      // System.out.println(copyFactory);
      // System.out.println("");
      int minRobots = copyFactory.getMinRobots();
      if (copyFactory.makeGeodeRobot()) {
        copyFactory.timeStep(4);
      } else if (copyFactory.makeObsRobot() && minRobots + diffRobots > copyFactory.getNumObsRobots()) {
        copyFactory.timeStep(3);
      } else if (copyFactory.makeClayRobot() && minRobots + diffRobots > copyFactory.getNumClayRobots()) {
        copyFactory.timeStep(2);
      } else if (copyFactory.makeOreRobot() && minRobots + diffRobots > copyFactory.getNumOreRobots()) {
        copyFactory.timeStep(1);
      } else {
        copyFactory.timeStep(0);
      }
    }
    // System.out.println(copyFactory);
    // System.out.println("");
    return copyFactory.getTotalGeode();
  }

  // Does a full test of a factory, returning the maximum number of geodes for a
  // given factory.  Starting guess is based on heuristic on the greedySim
  public static int fullSim(RobotFactory simFactory, int simTime, int diffRobots) {
    // Start by setting a baseline guess with a simple heuristic - this allows
    // us to quickly eliminate possibilities less than this
    int initialBaseline = 0;
    for (int i = 0; i < diffRobots; i++) {
      int testBaseline = greedySim(simFactory, simTime, i);
      // System.out.println(testBaseline);
      if (testBaseline > initialBaseline) {
        initialBaseline = testBaseline;
      }
    }
    // Now, create an ArrayList of factories to generate each successive step
    ArrayList<ArrayList<RobotFactory>> testFactories = new ArrayList<>();
    ArrayList<RobotFactory> startEntry = new ArrayList<>();
    startEntry.add(simFactory);
    testFactories.add(startEntry);
    int currentMax = initialBaseline;

    // Run 24 timesteps
    for (int i = 0; i < simTime; i++) {
      System.out.println(i);
      ArrayList<RobotFactory> nextLine = new ArrayList<>();
      for (int j = 0; j < testFactories.get(i).size(); j++) {
        RobotFactory currentFactory = testFactories.get(i).get(j);
        // Check if we need to update the current maximum
        int currGeodes = currentFactory.getTotalGeode();
        if (currGeodes > currentMax) {
          currentMax = currGeodes;
        }
        // Conditional to break loop (to implement correctly)
        else if (simTime - i < (currentMax - currGeodes - 3)) {
          // System.out.println("Using");
          continue;
        }

        // Generate the new factories based on this
        for (int k = 0; k < 5; k++) {
          RobotFactory newFactory = currentFactory.genNewFactory(k);
          // If it's null, then it wasn't possible to generate that factory
          if (newFactory != null) {
            nextLine.add(newFactory);
          }
        }
      }
      // System.out.println(currentMax);
      testFactories.add(nextLine);
    }

    return currentMax;
  }
}
