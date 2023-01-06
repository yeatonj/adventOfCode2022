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
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;


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

    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data_test.txt";
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

    // Initialize expedition for first trip
    ExpeditionLocation startingPosition = new ExpeditionLocation(0, blizzardList, initialBlizzard.getOriginXLoc(), initialBlizzard.getOriginYLoc());

    // Below code is for stack and queue based methods
    ArrayDeque<ExpeditionLocation> checkQueue = new ArrayDeque<>();
    checkQueue.add(startingPosition);

    int elapsedTime = bfs(startingPosition, checkQueue, blizzardList, initialBlizzard.getDestXLoc(), initialBlizzard.getDestYLoc());

    System.out.println("Minimum elapsed time to reach destination for first time is: " + elapsedTime + " minutes.");

    // Take a trip back
    ExpeditionLocation startingPosition2 = new ExpeditionLocation(elapsedTime, blizzardList, blizzardList.get(blizzardList.size() - 1).getDestXLoc(), blizzardList.get(blizzardList.size() - 1).getDestYLoc());
    ArrayDeque<ExpeditionLocation> checkQueue2 = new ArrayDeque<>();
    checkQueue2.add(startingPosition2);
    int elapsedTime2 = bfs(startingPosition2, checkQueue2, blizzardList, blizzardList.get(blizzardList.size() - 1).getOriginXLoc(), blizzardList.get(blizzardList.size() - 1).getOriginYLoc());
    System.out.println("Total time to go back to start is: " + elapsedTime2 + " minutes.");

    // Then head for the destination again
    ExpeditionLocation startingPosition3 = new ExpeditionLocation(elapsedTime2, blizzardList, blizzardList.get(blizzardList.size() - 1).getOriginXLoc(), blizzardList.get(blizzardList.size() - 1).getOriginYLoc());
    ArrayDeque<ExpeditionLocation> checkQueue3 = new ArrayDeque<>();
    checkQueue3.add(startingPosition3);
    int elapsedTime3 = bfs(startingPosition3, checkQueue3, blizzardList, blizzardList.get(blizzardList.size() - 1).getDestXLoc(), blizzardList.get(blizzardList.size() - 1).getDestYLoc());
    System.out.println("Total time to go back to destination again is: " + elapsedTime3 + " minutes.");

  }

  // BFS function
  public static int bfs(ExpeditionLocation startingPosition, ArrayDeque<ExpeditionLocation> checkQueue, ArrayList<BlizzardMap> blizzardList, int destinationX, int destinationY) {
    BlizzardMap initialBlizzard = blizzardList.get(0);
    int currentX = initialBlizzard.getOriginXLoc();
    int currentY = initialBlizzard.getOriginYLoc();
    int minTime = -1;
    HashSet<ExpeditionLocation> visitedLocs = new HashSet<>();
    visitedLocs.add(startingPosition);

    // Perform the search
    while(!checkQueue.isEmpty()) {
      // poll the queue
      ExpeditionLocation tempLoc = checkQueue.poll();
      visitedLocs.add(tempLoc);
      int elapsedTime = tempLoc.getCurrentTime();
      currentX = tempLoc.getXLoc();
      currentY = tempLoc.getYLoc();
      if (currentX == destinationX && currentY == destinationY) {
        return elapsedTime;
      }
      // System.out.println("Current Location: (" + currentX + ", " + currentY + ")");
      ArrayList<ExpeditionLocation> newExpeditionLocs = tempLoc.getNextLocs(blizzardList);
      for (ExpeditionLocation exp : newExpeditionLocs) {
        if (!visitedLocs.contains(exp)) {
          visitedLocs.add(exp);
          checkQueue.add(exp);
        }
      }
    }
    System.out.println(visitedLocs);
    return minTime;
  }
}
