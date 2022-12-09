// Class to represent a path through 2D space of a knot
// Path is represented as an ArrayList of KnotLoc objects

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class KnotPath {
  // Instance variables
  ArrayList<KnotLoc> path = null;

  // Constructor
  public KnotPath() {
    path = new ArrayList<>();
    // Add the origin to the path (all knots start at origin)
    path.add(new KnotLoc(0,0));
  }

  public void genPathFromFile(String filePath) {
    File knotPathFile = null;
    Scanner headPathScanner = null;
    try {
      knotPathFile = new File(filePath);
      headPathScanner = new Scanner(knotPathFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found...");
    }

    // Set starting locations for the path to generate from
    int currentX = path.get(path.size()-1).getX();
    int currentY = path.get(path.size()-1).getY();

    while(headPathScanner.hasNext()) {
      // Each line of the file is (direction, magnitude)
      String direction = headPathScanner.next();
      int magnitude = Integer.parseInt(headPathScanner.next());
      if (direction.equals("U")) {
        currentY += magnitude;
      } else if (direction.equals("D")) {
        currentY -= magnitude;
      } else if (direction.equals("R")) {
        currentX += magnitude;
      } else if (direction.equals("L")) {
        currentX -= magnitude;
      } else {
        System.out.println("Bad direction, skipping line...");
      }
      path.add(new KnotLoc(currentX,currentY));
    }
    headPathScanner.close();

  }

  public ArrayList<KnotLoc> getPath() {
    return path;
  }
}
