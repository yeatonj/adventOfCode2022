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

    // KnotPath headPath = new KnotPath(1);
    KnotPath headPath = new KnotPath(9);
    headPath.genPathFromFileFullTail(filePath);

    ArrayList<KnotLoc> headLocs = headPath.getPath();
    ArrayList<KnotPath> tailPaths = headPath.getTailPaths();
    ArrayList<ArrayList<KnotLoc>> tailLocs = new ArrayList<>();
    ArrayList<KnotLoc> uniqueTailLocs = new ArrayList<>();
    for (int i = 0; i < tailPaths.size(); i++) {
      tailLocs.add(tailPaths.get(i).getPath());
    }
    for (int i = 0; i < headLocs.size(); i++) {
      System.out.println("Head: " + headLocs.get(i).getX() + ", " + headLocs.get(i).getY());
      for (int j = 0; j < tailLocs.size(); j++) {
        System.out.println("Tail " + (j+1) + ": " + tailLocs.get(j).get(i).getX() + ", " + tailLocs.get(j).get(i).getY());
      }
      int j = 0;
      // Check whether this location is unique
      ArrayList<KnotLoc> lastTail = tailLocs.get(tailLocs.size() - 1);
      while (j < uniqueTailLocs.size() && !uniqueTailLocs.get(j).equals(lastTail.get(i))) {
        j++;
      }
      // if it is, add it to the list of unique locations
      if (j == uniqueTailLocs.size()) {
        uniqueTailLocs.add(lastTail.get(i));
      }
    }
    System.out.println("Number of unique tail locations: " + uniqueTailLocs.size());
  }
}
