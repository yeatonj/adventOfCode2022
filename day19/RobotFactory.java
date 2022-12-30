// Class for creating a robot factory based on several inputs
// For AOC day 19

class RobotFactory {
  // Instance variables
  int oreRobotCost; // cost of an ore robot, in ore
  int clayRobotCost; // cost of a clay robot, in ore
  int[] obsidianRobotCost; // cost of an obsidian robot [ore, clay]
  int[] geodeRobotCost; // cost of a geode robot [ore, obsidian]
  int[] numRobots; // ore, clay, obsidian, geode
  int[] totalResources; // ore, clay, obsidian, geode
  int currentTime; // time elapsed since factory created

  // Constructor for use in initializing factory
  RobotFactory(int oreCost, int clayCost, int[] obsidianCost, int[] geodeCost) {
    // Initialize cost of robots
    this.oreRobotCost = oreCost;
    this.clayRobotCost = clayCost;
    this.obsidianRobotCost = obsidianCost;
    this.geodeRobotCost = geodeCost;
    // Initialize the robots
    this.numRobots = new int[] {1,0,0,0};
    // Initialize starting resources
    this.totalResources = new int[] {0,0,0,0};
    this.currentTime = 0;
  }

  // Constructor for creating a new instance of a factory that is a copy of the parent
  RobotFactory(RobotFactory parentFactory) {
    // Initialize cost of robots
    this.oreRobotCost = parentFactory.getOreRobotCost();
    this.clayRobotCost = parentFactory.getClayRobotCost();
    this.obsidianRobotCost = new int[] {parentFactory.getObsRobotClayCost(), parentFactory.getObsRobotClayCost()};
    this.geodeRobotCost = new int[] {parentFactory.getGeodeRobotOreCost(), parentFactory.getGeodeRobotObsCost()};
    // Initialize the robots
    this.numRobots = new int[] {parentFactory.getNumOreRobots(),
                                parentFactory.getNumClayRobots(),
                                parentFactory.getNumObsRobots(),
                                parentFactory.getNumGeodeRobots()};
    // Initialize starting resources
    this.totalResources = new int[] {parentFactory.getTotalOre(),
                                    parentFactory.getTotalClay(),
                                    parentFactory.getTotalObs(),
                                    parentFactory.getTotalGeode()};
    this.currentTime = parentFactory.getCurrentTime();
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
      this.totalResources[2] -= this.geodeRobotCost[2];
    }
    this.currentTime++;
    return this.totalResources[3];
  }

  // Functions to decide if a certain robot type can be made
  public boolean makeOreRobot() {
    return (this.totalResources[0] >= this.oreRobotCost);
  }

  public boolean makeClayRobot() {
    return (this.totalResources[0] >= this.clayRobotCost);
  }

  public boolean makeObsRobot() {
    return (this.totalResources[0] >= this.obsidianRobotCost[0] &&
            this.totalResources[1] >= this.obsidianRobotCost[1]);
  }

  public boolean makeGeodeRobot() {
    return (this.totalResources[0] >= this.geodeRobotCost[0] &&
            this.totalResources[2] >= this.geodeRobotCost[1]);
  }

}
