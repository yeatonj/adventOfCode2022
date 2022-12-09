// Program to find the path of two knots
// Written by: Josh Yeaton
// Written on 12/9/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class RopeBridge {
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day9/rope_movement.txt";
    File knotPathFile = new File(filePath);
    Scanner headPathScanner = new Scanner(knotPathFile);

    // Create variables to track the total potential size of the coordinate
    // system
    HashMap<String, Integer> directionTotals = new HashMap<>();
    directionTotals.put("L",0);
    directionTotals.put("R",0);
    directionTotals.put("U",0);
    directionTotals.put("D",0);
    directionTotals.put("X",0);
    directionTotals.put("Y",0);

    // Create variables to track the path of the knot
    ArrayList<String> headDirection = new ArrayList<>();
    ArrayList<Integer> headMagnitude = new ArrayList<>();

    while(headPathScanner.hasNext()) {
      // Each line of the file is (direction, magnitude)
      String direction = headPathScanner.next();
      int magnitude = Integer.parseInt(headPathScanner.next());
      headDirection.add(direction);
      headMagnitude.add(magnitude);
      directionTotals.put(direction, directionTotals.get(direction) + magnitude);
    }
    headPathScanner.close();

    System.out.println(headDirection);
    System.out.println(headMagnitude);
    System.out.println(directionTotals);

  }
}
