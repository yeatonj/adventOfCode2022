// Program for diffusing elves
// Written for AOC 2022 Day 23
// Written by Josh Yeaton
// Written on 1/4/2023

import java.util.HashMap;
import java.util.HashSet;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UnstableDiffusion {
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day23/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day23/data_test.txt";

    File dataFile = new File(filePath);
    Scanner dataScanner = new Scanner(dataFile);

    int elfNumber = 1;
    int line = 0;
    HashMap<String, Point> elfToPoint = new HashMap<>();
    HashMap<Point, String> pointToElf = new HashMap<>();
    while(dataScanner.hasNextLine()) {
      String input = dataScanner.nextLine();
      for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) == '#') {
          String elfString = "elf" + elfNumber;
          Point elfPoint = new Point(i, line);
          elfToPoint.put(elfString, elfPoint);
          pointToElf.put(elfPoint, elfString);
          elfNumber++;
        }
      }
      line++;
    }

    // At this point, we are finished reading in data and can proceed with

    System.out.println(elfToPoint);
    System.out.println(pointToElf);
    System.out.println("Initial Map: ");
    drawMap(elfToPoint, pointToElf);

    // Part 1 - uncomment these two lines to complete
    int emptyTiles = simulateMoves(elfToPoint, pointToElf, 10);
    System.out.println("Number of empty tiles is: " + emptyTiles);

    // Part 2 - uncomment these two lines to complete
    int firstRoundNoMoves = findFinalRest(elfToPoint, pointToElf);
    System.out.println("Numer of rounds to final resting is: " + firstRoundNoMoves);
  }

  // Method to simulate moves
  public static int simulateMoves(HashMap<String, Point> elfToPoint, HashMap<Point, String> pointToElf, int rounds) {
    String checkRotation = "NSWE";
    int currentFirstCheck = 0;
    for (int i = 0; i < rounds; i++) {
      // Create a map of proposed and duplicate moves
      HashMap<Point, String> proposedMoves = new HashMap<>();
      HashSet<Point> duplicateMoves = new HashSet<>();
      HashSet<String> duplicateElves = new HashSet<>();
      // Now, iterate through each elf and determine its move
      for (HashMap.Entry<String,Point> mapElement : elfToPoint.entrySet()) {
        // first, check to see if all points around this elf are clear
        if (checkAdjPoints(mapElement.getValue(), pointToElf)) {
          // if they are, keep the elf here
          proposedMoves.put(mapElement.getValue(), mapElement.getKey());
          continue;
        }
        boolean moveFound = false;
        int counter = 0;
        while(!moveFound && counter < 4) {
          char checkDirection = checkRotation.charAt((currentFirstCheck + counter) % 4);
          if (checkDirection == 'N') {
            moveFound = checkNorth(mapElement.getValue(), pointToElf);
          } else if (checkDirection == 'S') {
            moveFound = checkSouth(mapElement.getValue(), pointToElf);
          } else if (checkDirection == 'W') {
            moveFound = checkWest(mapElement.getValue(), pointToElf);
          } else {
            moveFound = checkEast(mapElement.getValue(), pointToElf);
          }
          // If a move is found, propose the move and check to see if it's acceptable
          if (moveFound) {
            Point proposedMove = proposeMove(mapElement.getValue(), checkDirection);
            // Option 1 - this move has already been proposed by multiple elves - stay put!
            if (duplicateMoves.contains(proposedMove)) {
              duplicateElves.add(mapElement.getKey());
            }
            // Option 2 - this move has already been proposed by one elf - both stay put!
            else if (proposedMoves.containsKey(proposedMove)) {
              // Move the current elf into duplicates
              duplicateMoves.add(proposedMove);
              duplicateElves.add(mapElement.getKey());
              // Move the other duplicate elf into duplicates
              duplicateElves.add(proposedMoves.get(proposedMove));
              // Remove the other duplicate elf from the proposed move
              proposedMoves.remove(proposedMove);
            }
            // Option 3 - this move is brand new - record this proposed move
            else {
              proposedMoves.put(proposedMove, mapElement.getKey());
            }
          }
          counter++;
        }
        // If a move is not found, leave the elf in the same place
        if (!moveFound) {
          proposedMoves.put(mapElement.getValue(), mapElement.getKey());
        }
      }
      // Now, update the proposedMove HashMap with the elves that aren't moving
      for (String elf : duplicateElves) {
        proposedMoves.put(elfToPoint.get(elf), elf);
      }
      // The proposed moves list is now unique and complete, so create its inverse
      HashMap<String,Point> proposedElves = new HashMap<>();
      for (HashMap.Entry<Point,String> mapElement : proposedMoves.entrySet()) {
        proposedElves.put(mapElement.getValue(), mapElement.getKey());
      }
      // Make the proposed maps into the original maps, then iterate
      elfToPoint = proposedElves;
      pointToElf = proposedMoves;
      System.out.println("Map after step " + (i + 1));
      drawMap(elfToPoint, pointToElf);
      // Iterate the checker
      currentFirstCheck++;
    }
    // Calculate the coverage area
    return calculateArea(elfToPoint);
  }


  // Method to simulate moves
  public static int findFinalRest(HashMap<String, Point> elfToPoint, HashMap<Point, String> pointToElf) {
    String checkRotation = "NSWE";
    int currentFirstCheck = 0;
    boolean noMove = false;
    while (!noMove){
      noMove = true;
      // Create a map of proposed and duplicate moves
      HashMap<Point, String> proposedMoves = new HashMap<>();
      HashSet<Point> duplicateMoves = new HashSet<>();
      HashSet<String> duplicateElves = new HashSet<>();
      // Now, iterate through each elf and determine its move
      for (HashMap.Entry<String,Point> mapElement : elfToPoint.entrySet()) {
        // first, check to see if all points around this elf are clear
        if (checkAdjPoints(mapElement.getValue(), pointToElf)) {
          // if they are, keep the elf here
          proposedMoves.put(mapElement.getValue(), mapElement.getKey());
          continue;
        }
        noMove = false;
        boolean moveFound = false;
        int counter = 0;
        while(!moveFound && counter < 4) {
          char checkDirection = checkRotation.charAt((currentFirstCheck + counter) % 4);
          if (checkDirection == 'N') {
            moveFound = checkNorth(mapElement.getValue(), pointToElf);
          } else if (checkDirection == 'S') {
            moveFound = checkSouth(mapElement.getValue(), pointToElf);
          } else if (checkDirection == 'W') {
            moveFound = checkWest(mapElement.getValue(), pointToElf);
          } else {
            moveFound = checkEast(mapElement.getValue(), pointToElf);
          }
          // If a move is found, propose the move and check to see if it's acceptable
          if (moveFound) {
            Point proposedMove = proposeMove(mapElement.getValue(), checkDirection);
            // Option 1 - this move has already been proposed by multiple elves - stay put!
            if (duplicateMoves.contains(proposedMove)) {
              duplicateElves.add(mapElement.getKey());
            }
            // Option 2 - this move has already been proposed by one elf - both stay put!
            else if (proposedMoves.containsKey(proposedMove)) {
              // Move the current elf into duplicates
              duplicateMoves.add(proposedMove);
              duplicateElves.add(mapElement.getKey());
              // Move the other duplicate elf into duplicates
              duplicateElves.add(proposedMoves.get(proposedMove));
              // Remove the other duplicate elf from the proposed move
              proposedMoves.remove(proposedMove);
            }
            // Option 3 - this move is brand new - record this proposed move
            else {
              proposedMoves.put(proposedMove, mapElement.getKey());
            }
          }
          counter++;
        }
        // If a move is not found, leave the elf in the same place
        if (!moveFound) {
          proposedMoves.put(mapElement.getValue(), mapElement.getKey());
        }
      }
      // Now, update the proposedMove HashMap with the elves that aren't moving
      for (String elf : duplicateElves) {
        proposedMoves.put(elfToPoint.get(elf), elf);
      }
      // The proposed moves list is now unique and complete, so create its inverse
      HashMap<String,Point> proposedElves = new HashMap<>();
      for (HashMap.Entry<Point,String> mapElement : proposedMoves.entrySet()) {
        proposedElves.put(mapElement.getValue(), mapElement.getKey());
      }
      // Make the proposed maps into the original maps, then iterate
      elfToPoint = proposedElves;
      pointToElf = proposedMoves;
      // System.out.println("Map after step " + (currentFirstCheck + 1));
      // drawMap(elfToPoint, pointToElf);
      // Iterate the checker
      currentFirstCheck++;
    }
    // Calculate the coverage area
    return currentFirstCheck;
  }

 // Method to propose a new point for an elf
  public static Point proposeMove(Point currPoint, char direction) {
    if (direction == 'N') {
      return new Point((int)currPoint.getX(), (int)currPoint.getY() - 1);
    } else if (direction == 'S') {
      return new Point((int)currPoint.getX(), (int)currPoint.getY() + 1);
    } else if (direction == 'W') {
      return new Point((int)currPoint.getX() - 1, (int)currPoint.getY());
    } else {
      return new Point((int)currPoint.getX() + 1, (int)currPoint.getY());
    }
  }

  // Method to check all tiles around an elf are clear (false means they are not)
  public static boolean checkAdjPoints(Point currPoint, HashMap<Point, String> currLocs) {
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        Point checkPoint = new Point((int)currPoint.getX() + i, (int)currPoint.getY() - j);
        if (currLocs.containsKey(checkPoint)) {
          return false;
        }
      }
    }
    return true;
  }

  // Method to check to see if a move to the north is valid
  public static boolean checkNorth(Point currPoint, HashMap<Point, String> currLocs) {
    Point northWest = new Point((int)currPoint.getX() -  1, (int)currPoint.getY() - 1);
    Point north = new Point((int)currPoint.getX(), (int)currPoint.getY() - 1);
    Point northEast = new Point((int)currPoint.getX() +  1, (int)currPoint.getY() - 1);

    // If any of those is already in the map, fail the move
    if (currLocs.containsKey(northWest) ||
    currLocs.containsKey(north) ||
    currLocs.containsKey(northEast)) {
      return false;
    }
    return true;
  }

  // Method to check to see if a move to the south is valid
  public static boolean checkSouth(Point currPoint, HashMap<Point, String> currLocs) {
    Point southWest = new Point((int)currPoint.getX() -  1, (int)currPoint.getY() + 1);
    Point south = new Point((int)currPoint.getX(), (int)currPoint.getY() + 1);
    Point southEast = new Point((int)currPoint.getX() +  1, (int)currPoint.getY() + 1);

    // If any of those is already in the map, fail the move
    if (currLocs.containsKey(southWest) ||
    currLocs.containsKey(south) ||
    currLocs.containsKey(southEast)) {
      return false;
    }
    return true;
  }

  // Method to check to see if a move to the west is valid
  public static boolean checkWest(Point currPoint, HashMap<Point, String> currLocs) {
    Point northWest = new Point((int)currPoint.getX() -  1, (int)currPoint.getY() - 1);
    Point west = new Point((int)currPoint.getX() - 1, (int)currPoint.getY());
    Point southWest = new Point((int)currPoint.getX() -  1, (int)currPoint.getY() + 1);

    // If any of those is already in the map, fail the move
    if (currLocs.containsKey(northWest) ||
    currLocs.containsKey(west) ||
    currLocs.containsKey(southWest)) {
      return false;
    }
    return true;
  }

  // Method to check to see if a move to the east is valid
  public static boolean checkEast(Point currPoint, HashMap<Point, String> currLocs) {
    Point northEast = new Point((int)currPoint.getX() + 1, (int)currPoint.getY() - 1);
    Point east = new Point((int)currPoint.getX() + 1, (int)currPoint.getY());
    Point southEast = new Point((int)currPoint.getX() +  1, (int)currPoint.getY() + 1);

    // If any of those is already in the map, fail the move
    if (currLocs.containsKey(northEast) ||
    currLocs.containsKey(east) ||
    currLocs.containsKey(southEast)) {
      return false;
    }
    return true;
  }

  // Function to calculate the area (spare tiles) in a region covered by elves
  public static int calculateArea(HashMap<String, Point> elfToPoint) {
    Point startPoint = elfToPoint.get("elf1");
    int minX = (int) startPoint.getX();
    int maxX = (int) startPoint.getX();
    int minY = (int) startPoint.getY();
    int maxY = (int) startPoint.getY();
    // Find the max values in the hashmaps
    for (HashMap.Entry<String,Point> mapElement : elfToPoint.entrySet()) {
      Point currPoint = mapElement.getValue();
      int currX = (int) currPoint.getX();
      int currY = (int) currPoint.getY();
      if (currX > maxX) {
        maxX = currX;
      } else if (currX < minX) {
        minX = currX;
      }
      if (currY > maxY) {
        maxY = currY;
      } else if (currY < minY) {
        minY = currY;
      }
    }
    // Add 1 to make it inclusive
    return ((maxX - minX + 1) * (maxY - minY + 1) - elfToPoint.size());
  }

  // Function to draw the map
  public static void drawMap(HashMap<String, Point> elfToPoint, HashMap<Point, String> pointToElf) {
    Point startPoint = elfToPoint.get("elf1");
    int minX = (int) startPoint.getX();
    int maxX = (int) startPoint.getX();
    int minY = (int) startPoint.getY();
    int maxY = (int) startPoint.getY();
    // Find the max values in the hashmaps
    for (HashMap.Entry<String,Point> mapElement : elfToPoint.entrySet()) {
      Point currPoint = mapElement.getValue();
      int currX = (int) currPoint.getX();
      int currY = (int) currPoint.getY();
      if (currX > maxX) {
        maxX = currX;
      } else if (currX < minX) {
        minX = currX;
      }
      if (currY > maxY) {
        maxY = currY;
      } else if (currY < minY) {
        minY = currY;
      }
    }
    for (int i = minY - 1; i <= maxY + 1; i++) {
      for (int j = minX - 1; j <= maxX + 1; j++) {
        if (pointToElf.containsKey(new Point(j,i))) {
          System.out.print("#");
        } else {
          System.out.print(".");
        }
      }
      System.out.print("\n");
    }
  }
}
