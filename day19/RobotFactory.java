// Class for creating a robot factory based on several inputs
// For AOC day 19

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

class RobotFactory implements Comparable<RobotFactory> {
  // Instance variables
  int oreRobotCost; // cost of an ore robot, in ore
  int clayRobotCost; // cost of a clay robot, in ore
  int[] obsidianRobotCost; // cost of an obsidian robot [ore, clay]
  int[] geodeRobotCost; // cost of a geode robot [ore, obsidian]
  Integer[] numRobots; // ore, clay, obsidian, geode
  int[] totalResources; // ore, clay, obsidian, geode
  int currentTime; // time elapsed since factory created
  int maxOreCost; // Max possible cost of an ore consuming robot blueprint
  int maxPossGeodes; // max possible geodes for this factory config

  // Constructor for use in initializing factory
  RobotFactory(int oreCost, int clayCost, int[] obsidianCost, int[] geodeCost) {
    // Initialize cost of robots
    this.oreRobotCost = oreCost;
    this.maxOreCost = oreCost;
    this.clayRobotCost = clayCost;
    if (clayCost > oreCost) {
      this.maxOreCost = clayCost;
    }
    this.obsidianRobotCost = obsidianCost;
    if (obsidianCost[0] > this.maxOreCost) {
      this.maxOreCost = obsidianCost[0];
    }
    this.geodeRobotCost = geodeCost;
    if (geodeRobotCost[0] > this.maxOreCost) {
      this.maxOreCost = geodeRobotCost[0];
    }
    // Initialize the robots
    this.numRobots = new Integer[] {1,0,0,0};
    // Initialize starting resources
    this.totalResources = new int[] {0,0,0,0};
    this.currentTime = 0;
    this.maxPossGeodes = 0;
  }

  // Constructor for creating a new instance of a factory that is a copy of the parent
  RobotFactory(RobotFactory parentFactory) {
    // Initialize cost of robots
    this.oreRobotCost = parentFactory.getOreRobotCost();
    this.clayRobotCost = parentFactory.getClayRobotCost();
    this.obsidianRobotCost = new int[] {parentFactory.getObsRobotOreCost(), parentFactory.getObsRobotClayCost()};
    this.geodeRobotCost = new int[] {parentFactory.getGeodeRobotOreCost(), parentFactory.getGeodeRobotObsCost()};
    // Initialize the robots
    this.numRobots = new Integer[] {parentFactory.getNumOreRobots(),
                                parentFactory.getNumClayRobots(),
                                parentFactory.getNumObsRobots(),
                                parentFactory.getNumGeodeRobots()};
    // Initialize starting resources
    this.totalResources = new int[] {parentFactory.getTotalOre(),
                                    parentFactory.getTotalClay(),
                                    parentFactory.getTotalObs(),
                                    parentFactory.getTotalGeode()};
    this.currentTime = parentFactory.getCurrentTime();
    this.maxOreCost = parentFactory.getMaxOreCost();
    this.maxPossGeodes = 0;
  }

  // Getters for robot costs
  public int getOreRobotCost() {
    return this.oreRobotCost;
  }

  public int getClayRobotCost() {
    return this.clayRobotCost;
  }

  public int getObsRobotOreCost() {
    return this.obsidianRobotCost[0];
  }

  public int getObsRobotClayCost() {
    return this.obsidianRobotCost[1];
  }

  public int getGeodeRobotOreCost() {
    return this.geodeRobotCost[0];
  }

  public int getGeodeRobotObsCost() {
    return this.geodeRobotCost[1];
  }

  // Getters for number of robots
  public int getNumOreRobots() {
    return this.numRobots[0];
  }

  public int getNumClayRobots() {
    return this.numRobots[1];
  }

  public int getNumObsRobots() {
    return this.numRobots[2];
  }

  public int getNumGeodeRobots() {
    return this.numRobots[3];
  }

  // Getters for number of resources
  public int getTotalOre() {
    return this.totalResources[0];
  }

  public int getTotalClay() {
    return this.totalResources[1];
  }

  public int getTotalObs() {
    return this.totalResources[2];
  }

  public int getTotalGeode() {
    return this.totalResources[3];
  }

  // Getter for current time
  public int getCurrentTime() {
    return this.currentTime;
  }

  public int getMaxOreCost() {
    return this.maxOreCost;
  }

  // Gets the difference between the most and least number of each robot
  public int getMinRobots() {
    return Collections.min(Arrays.asList(numRobots));
  }

  public int getMaxPossGeodes() {
    return this.maxPossGeodes;
  }

  // Function for creating a new instance of a factory from this one with a single timestep
  // making a decision from previous timestep.
  // 0: don't make any robots
  // 1: make an ore robot
  // 2: make a clay robot
  // 3: make an obsidian robot
  // 4: make a geode robot
  // If it cannot perform the assigned step, it simply returns "null"
  public RobotFactory genNewFactory(int robotToMake) {
    // Return null if it's not possible to make this
    if (robotToMake == 1 && !this.makeOreRobot() ||
        robotToMake == 2 && !this.makeClayRobot() ||
        robotToMake == 3 && !this.makeObsRobot() ||
        robotToMake == 4 && !this.makeGeodeRobot()) {
      return null;
    }

    // Create the new factory here
    RobotFactory tempFactory = new RobotFactory(this);
    tempFactory.timeStep(robotToMake);
    return tempFactory;
  }

  // Function for doing a timestep with the current factory instance, returning the number of geodes
  // 0: don't make any robots
  // 1: make an ore robot
  // 2: make a clay robot
  // 3: make an obsidian robot
  // 4: make a geode robot
  // If it cannot perform the assigned step, it returns -1 and prints an error message
  public int timeStep(int robotToMake) {
    // Check to make sure we can actually do this step...
    if (robotToMake == 1 && !this.makeOreRobot() ||
        robotToMake == 2 && !this.makeClayRobot() ||
        robotToMake == 3 && !this.makeObsRobot() ||
        robotToMake == 4 && !this.makeGeodeRobot()) {
      System.out.println("Improper time step, not enough resources...");
      return -1;
    }
    // Increment our resources based on the current number of robots
    for (int i = 0; i < this.numRobots.length; i++) {
      this.totalResources[i] += this.numRobots[i];
    }
    // Then, create the new robot, if applicable
    if (robotToMake == 1) {
      this.numRobots[0] += 1;
      this.totalResources[0] -= this.oreRobotCost;
    } else if (robotToMake == 2) {
      this.numRobots[1] += 1;
      this.totalResources[0] -= this.clayRobotCost;
    } else if (robotToMake == 3) {
      this.numRobots[2] += 1;
      this.totalResources[0] -= this.obsidianRobotCost[0];
      this.totalResources[1] -= this.obsidianRobotCost[1];
    } else if (robotToMake == 4) {
      this.numRobots[3] += 1;
      this.totalResources[0] -= this.geodeRobotCost[0];
      this.totalResources[2] -= this.geodeRobotCost[1];
    }
    this.currentTime++;
    return this.totalResources[3];
  }

  // Functions to decide if a certain robot type can or should be made
  public boolean makeOreRobot() {
    // Can we make it?
    boolean canMake = (this.totalResources[0] >= this.oreRobotCost);
    // Should we make it?
    boolean shouldMake = numRobots[0] < this.maxOreCost;
    return (canMake && shouldMake);
  }

  public boolean makeClayRobot() {
    boolean canMake = (this.totalResources[0] >= this.clayRobotCost);
    boolean shouldMake = numRobots[1] < this.obsidianRobotCost[1];
    return (canMake && shouldMake);
  }

  public boolean makeObsRobot() {
    boolean canMake = (this.totalResources[0] >= this.obsidianRobotCost[0] &&
            this.totalResources[1] >= this.obsidianRobotCost[1]);
    boolean shouldMake = numRobots[2] < this.geodeRobotCost[1];
    return (canMake && shouldMake);
  }

  public boolean makeGeodeRobot() {
    return (this.totalResources[0] >= this.geodeRobotCost[0] &&
            this.totalResources[2] >= this.geodeRobotCost[1]);
  }

  public String toString() {
    String returnString = "Factory at time t = " + this.currentTime + ", ";
    returnString += "with " + this.totalResources[3] + " geodes. ";
    returnString += "(" + this.totalResources[0] + " Ore, " + this.totalResources[1] + " Clay, " + this.totalResources[2] + " Obsidian).\n";
    returnString += "Robots: " + this.numRobots[0] + " Ore Robots, " + this.numRobots[1] + " Clay Robots, " + this.numRobots[2] + " Obsidian Robots, " + this.numRobots[3] + " Geode Robots.\n";
    returnString += "Each ore robot costs " + this.oreRobotCost + " ore. Each clay robot costs " + this.clayRobotCost + " ore. Each obsidian robot costs " + this.obsidianRobotCost[0] + " ore and " + this.obsidianRobotCost[1] + " clay. Each geode robot costs " + this.geodeRobotCost[0] + " ore and " + this.geodeRobotCost[1] + " obsidian.";
    return returnString;
  }

  // Method to get a loose estimate of the possible number of geodes given a
  // particular factory state
  public int getUpperGeodeBound(int maxTime) {
    int remainingTime = maxTime - this.currentTime;
    if (remainingTime == -1) {
      return this.totalResources[3];
    }
    // First, find the maximum number of ore robots we can have at any remaining step
    int currentOreRobots = this.numRobots[0];
    int currentOre = this.totalResources[0];
    ArrayList<Integer> maxOreRobots = new ArrayList<>();
    for (int i = 0; i < remainingTime; i++) {
      // Find how much ore we generated
      int generatedOre = currentOreRobots;
      // If we can build an ore Robot, build it
      if (currentOre >= this.oreRobotCost) {
        currentOreRobots++;
        currentOre -= this.oreRobotCost;
      }
      maxOreRobots.add(currentOreRobots);
      currentOre += generatedOre;
    }
    // Now, we have the maximum number of ore robots at each step - find the max
    // amount of ore we can have at each step
    ArrayList<Integer> maxOre = new ArrayList<>();
    maxOre.add(this.totalResources[0] + this.numRobots[0]);
    for (int i = 1; i < remainingTime; i++) {
      maxOre.add(maxOre.get(i-1) + maxOreRobots.get(i-1));
    }
    // Second, find the maximum number of clay robots we can have at any stage,
    // and use that to determine the maximum amount of clay we can have at
    // any stage
    int currentClayRobots = this.numRobots[1];
    int currentOreSpent = 0;
    ArrayList<Integer> maxClay = new ArrayList<>();
    maxClay.add(this.totalResources[1] + currentClayRobots);
    if (maxOre.get(0) >= this.clayRobotCost) {
      currentOreSpent += this.clayRobotCost;
      currentClayRobots++;
    }
    for (int i = 1; i < remainingTime; i++) {
      maxClay.add(maxClay.get(i-1) + currentClayRobots);
      if (maxOre.get(i) >= (this.clayRobotCost + currentOreSpent)) {
        currentOreSpent += this.clayRobotCost;
        currentClayRobots++;
      }
      // System.out.println("Clay: " + maxClay.get(i));
      // System.out.println("Ore: " + maxOre.get(i));
    }

    // Third, find the maximum number of obsidian robots we can have at any stage,
    // and use that to determine the maximum amount of obsidian we can have at
    // any stage
    int currentObsRobots = this.numRobots[2];
    currentOreSpent = 0;
    int currentClaySpent = 0;
    ArrayList<Integer> maxObs = new ArrayList<>();
    maxObs.add(this.totalResources[2] + currentObsRobots);
    if (maxOre.get(0) >= this.obsidianRobotCost[0] &&
        maxClay.get(0) >= this.obsidianRobotCost[1]) {
      currentOreSpent += this.obsidianRobotCost[0];
      currentClaySpent += this.obsidianRobotCost[1];
      currentObsRobots++;
    }
    for (int i = 1; i < remainingTime; i++) {
      maxObs.add(maxObs.get(i-1) + currentObsRobots);
      if (maxOre.get(i) >= (this.obsidianRobotCost[0] + currentOreSpent) &&
          maxClay.get(i) >= (this.obsidianRobotCost[1] + currentClaySpent)) {
        currentOreSpent += this.obsidianRobotCost[0];
        currentClaySpent += this.obsidianRobotCost[1];
        currentObsRobots++;
      }
      // System.out.println("Max Obsidian: " + maxObs.get(i));
    }

    // And, finally, do the same for geode robots
    int currentGeodeRobots = this.numRobots[3];
    currentOreSpent = 0;
    int currentObsSpent = 0;
    ArrayList<Integer> maxGeode = new ArrayList<>();
    maxGeode.add(this.totalResources[3] + currentGeodeRobots);
    if (maxOre.get(0) >= this.geodeRobotCost[0] &&
        maxObs.get(0) >= this.geodeRobotCost[1]) {
      currentOreSpent += this.geodeRobotCost[0];
      currentObsSpent += this.geodeRobotCost[1];
      currentGeodeRobots++;
    }
    for (int i = 1; i < remainingTime; i++) {
      maxGeode.add(maxGeode.get(i-1) + currentGeodeRobots);
      if (maxOre.get(i) >= (this.geodeRobotCost[0] + currentOreSpent) &&
          maxObs.get(i) >= (this.geodeRobotCost[1] + currentObsSpent)) {
        currentOreSpent += this.geodeRobotCost[0];
        currentObsSpent += this.geodeRobotCost[1];
        currentGeodeRobots++;
      }
      // System.out.println(i + ": Max Geode: " + maxGeode.get(i));
    }
    this.maxPossGeodes = maxGeode.get(maxGeode.size()-1);
    if (remainingTime == 0) {
      this.maxPossGeodes = this.totalResources[3];
    }
    return this.maxPossGeodes;
  }

  public int compareTo(RobotFactory b) {
    // System.out.println("Comparing this: " + this.distToDest + " to: " + b.getDist());
    // System.out.println("Returning: " + (this.distToDest - b.getDist()));
    return b.getMaxPossGeodes() -this.maxPossGeodes;
  }

}
