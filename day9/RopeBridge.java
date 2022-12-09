// Program to find the path of two knots
// Written by: Josh Yeaton
// Written on 12/9/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class RopeBridge {
  public static void main(String[] args) {
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day9/rope_movement.txt";

    // Generate the head path
    KnotPath headPath = new KnotPath();
    KnotPath tailPath = new KnotPath();
    headPath.genPathFromFileWithTail(filePath, tailPath);
    // headPath.genPathFromFile(filePath);

    // Start the tail path

    ArrayList<KnotLoc> headLocs = headPath.getPath();
    ArrayList<KnotLoc> tailLocs = tailPath.getPath();
    ArrayList<KnotLoc> uniqueTailLocs = new ArrayList<>();
    for (int i = 0; i < tailLocs.size(); i++) {
      System.out.println("Head: " + headLocs.get(i).getX() + ", " + headLocs.get(i).getY());
      System.out.println("Tail: " + tailLocs.get(i).getX() + ", " + tailLocs.get(i).getY());
      int j = 0;
      // Check whether this location is unique
      while (j < uniqueTailLocs.size() && !uniqueTailLocs.get(j).equals(tailLocs.get(i))) {
        j++;
      }
      // if it is, add it to the list of unique locations
      if (j == uniqueTailLocs.size()) {
        uniqueTailLocs.add(tailLocs.get(i));
      }
    }
    System.out.println("Number of unique tail locations: " + uniqueTailLocs.size());
  }
}
