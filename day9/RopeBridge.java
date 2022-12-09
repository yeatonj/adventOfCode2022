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

    KnotPath headPath = new KnotPath();
    headPath.genPathFromFile(filePath);
    ArrayList<KnotLoc> headLocs = headPath.getPath();
    for (KnotLoc location : headLocs) {
      System.out.println(location.getX() + ", " + location.getY());
    }

  }
}
