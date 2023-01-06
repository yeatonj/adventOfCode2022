// Program for avoiding blizzards
// Written for AOC 2022 Day 24
// Written by Josh Yeaton
// Written on 1/4/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;


public class BlizzardBasin {
  public static void main(String[] args) throws FileNotFoundException {
    // General approach:
    // 1. Read the storm into memory, and create a t = 0 representation of it
    // 2. Generate the storm state at next timeslot and save it to refer back to
    // it.
    // 3. Check the party's current position and look for adjacent open slots
    // at the next timeslot.
    // 4. Add those positions to a queue, including the current time
    // 5. Poll the queue and repeat
    // 6. Optimize to allow for calculation of shortest path (empty board?) and
    // use that to eliminate paths that won't allow us to reach the end
    // 7. Memoize based on current position and time?

    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data_test.txt";
    File dataFile = new File(filePath);
    Scanner fileScanner = new Scanner(dataFile);

    String fileString = "";
    while(fileScanner.hasNext()) {
      fileString += (fileScanner.next() + "\n");
    }
    fileString = fileString.substring(0, fileString.length() - 1);
    // Initialize the blizzards
    BlizzardMap initialBlizzard = new BlizzardMap(fileString);
    ArrayList<BlizzardMap> blizzardList = new ArrayList<>();
    blizzardList.add(initialBlizzard);
    System.out.println("Starting with map: ");
    initialBlizzard.printMap();

    // Initialize expedition
    ExpeditionLocation startingPosition = new ExpeditionLocation(0, blizzardList, initialBlizzard.getOriginXLoc(), initialBlizzard.getOriginYLoc());

    // Below code is for stack and queue based methods
    // PriorityQueue<ExpeditionLocation> checkQueue = new PriorityQueue<>();
    Stack<ExpeditionLocation> checkStack = new Stack<>();
    // checkQueue.add(startingPosition);
    checkStack.push(startingPosition);

    // int elapsedTime = aStarSearch(startingPosition, checkQueue, blizzardList);
    int elapsedTime = dfsStack(startingPosition, checkStack, blizzardList);

    System.out.println("Minimum elapsed time to reach destination is: " + elapsedTime);
    // blizzardList.get(18).printMap();


  }

  // A* search, however the function to govern remaining path length is
  // dramatically undershooting actual estimated remaining path length
  public static int aStarSearch(ExpeditionLocation startingPosition, PriorityQueue<ExpeditionLocation> checkQueue, ArrayList<BlizzardMap> blizzardList) {
    BlizzardMap initialBlizzard = blizzardList.get(0);
    int destinationX = initialBlizzard.getDestXLoc();
    int destinationY = initialBlizzard.getDestYLoc();
    int currentX = initialBlizzard.getOriginXLoc();
    int currentY = initialBlizzard.getOriginYLoc();
    int elapsedTime = 0;

    // Perform the search
    while(!(currentX == destinationX && currentY == destinationY) && !checkQueue.isEmpty()) {
      // poll the queue
      ExpeditionLocation tempLoc = checkQueue.poll();
      elapsedTime = tempLoc.getCurrentTime();
      currentX = tempLoc.getXLoc();
      currentY = tempLoc.getYLoc();
      // System.out.println("Current Location: (" + currentX + ", " + currentY + ")");
      ArrayList<ExpeditionLocation> newExpeditionLocs = tempLoc.getNextLocs(blizzardList);
      for (ExpeditionLocation exp : newExpeditionLocs) {
        checkQueue.add(exp);
      }
    }
    return elapsedTime;
  }

  // Method for doing a dfs on the expedition
  public static int dfsStack(ExpeditionLocation startingPosition, Stack<ExpeditionLocation> checkStack, ArrayList<BlizzardMap> blizzardList) {
    BlizzardMap initialBlizzard = blizzardList.get(0);
    int destinationX = initialBlizzard.getDestXLoc();
    int destinationY = initialBlizzard.getDestYLoc();
    int currentX = initialBlizzard.getOriginXLoc();
    int currentY = initialBlizzard.getOriginYLoc();
    int minTime = -1;

    // Perform the search
    while(!checkStack.isEmpty()) {
      // CHECK MEMOIZATION HERE

      // poll the queue
      ExpeditionLocation tempLoc = checkStack.pop();
      currentX = tempLoc.getXLoc();
      currentY = tempLoc.getYLoc();
      if (currentX == destinationX && currentY == destinationY) {
        if (minTime == -1) {
          minTime = tempLoc.getCurrentTime();
        } else if (tempLoc.getCurrentTime() < minTime) {
          minTime = tempLoc.getCurrentTime();
        }
        // Memoize the solution HERE
      }
      ArrayList<ExpeditionLocation> newExpeditionLocs = tempLoc.getNextLocs(blizzardList);
      for (ExpeditionLocation exp : newExpeditionLocs) {
        // Include a condition to exit the loop (ie, we can't possibly find a
        // shorter path)
        if (minTime == -1 || exp.getShortestPossTime() <= minTime) {
          checkStack.push(exp);
        }
      }
    }
    return minTime;
  }

  // Recursive DFS with memoization
  public static int recursiveDFS(ExpeditionLocation currentPosition,
  ArrayList<BlizzardMap> blizzardList,
  ArrayList<Integer> currentMinTime,
  int destinationX,
  int destinationY) {
    // Start function
    BlizzardMap initialBlizzard = blizzardList.get(0);
    int currentX = currentPosition.getXLoc();
    int currentY = currentPosition.getYLoc();
    System.out.println(currentX + ", " + currentY);
    // Base case
    if (currentX == destinationX && currentY == destinationY) {
      currentMinTime.add(currentPosition.getCurrentTime());
      return currentPosition.getCurrentTime();
    }

    // Now, ensure we're not checking solutions that can't meet the current min
    int currentGlobalMin = currentMinTime.get(currentMinTime.size() - 1);
    if (currentGlobalMin != -1 && currentPosition.getShortestPossTime() > currentGlobalMin) {
      // Not correct, but breaks the loop
      return (currentPosition.getShortestPossTime());
    }

    // Finally, check each adjacent position
    ArrayList<ExpeditionLocation> newExpeditionLocs = currentPosition.getNextLocs(blizzardList);
    int minTime = -1;
    for (ExpeditionLocation exp : newExpeditionLocs) {
      int newTime = recursiveDFS(exp, blizzardList, currentMinTime, destinationX, destinationY);
      if (minTime == -1 || newTime < minTime) {
        minTime = newTime;
      }
    }
    return minTime;
  }
}
