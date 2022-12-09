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
  }

  public void genPathFromFile(String filePath) {
    return;
  }

  public ArrayList<KnotLoc> getPath() {
    return path;
  }
}
