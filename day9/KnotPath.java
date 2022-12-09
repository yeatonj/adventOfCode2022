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
  int tailLength = 0;
  ArrayList<KnotPath> tailPaths = null;

  // Constructor
  public KnotPath(int tailLength) {
    this.path = new ArrayList<>();
    // Add the origin to the path (all knots start at origin)
    this.path.add(new KnotLoc(0,0));
    this.tailLength = tailLength;
    // Initialize the tail paths
    if (this.tailLength > 0) {
      tailPaths = new ArrayList<>();
    }
    for (int i = 0; i < tailLength; i++) {
      tailPaths.add(new KnotPath(0));
    }
  }

  // Function to move the full tail from its current last location on the path
  // to a new location given the current location of the head
  public void genPathFromFileFullTail(String filePath) {
    File knotPathFile = null;
    Scanner headPathScanner = null;
    try {
      knotPathFile = new File(filePath);
      headPathScanner = new Scanner(knotPathFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found...");
    }

    // Set starting locations for the path to generate from
    int currentX = this.path.get(path.size()-1).getX();
    int currentY = this.path.get(path.size()-1).getY();

    while(headPathScanner.hasNext()) {
      // Each line of the file is (direction, magnitude)
      String direction = headPathScanner.next();
      int magnitude = Integer.parseInt(headPathScanner.next());
      while (magnitude > 0) {
        if (direction.equals("U")) {
          currentY += 1;
        } else if (direction.equals("D")) {
          currentY -= 1;
        } else if (direction.equals("R")) {
          currentX += 1;
        } else if (direction.equals("L")) {
          currentX -= 1;
        } else {
          System.out.println("Bad direction, skipping line...");
        }
        // Add this new location to the head path
        this.path.add(new KnotLoc(currentX,currentY));
        // If there are tail elements, move the tail
        if (this.tailPaths != null) {
          this.moveFollower(this, this.tailPaths.get(0));
          // For each tail element, move the tail based on this motion
          for (int i = 1; i < this.tailLength; i++) {
            this.moveFollower(this.tailPaths.get(i-1), this.tailPaths.get(i));
          }
        }
        magnitude--;
      }
    }
    headPathScanner.close();
  }

  public void moveKnot(int moveX, int moveY) {
    // Get current locations of the last point
    int currentX = this.path.get(this.path.size()-1).getX();
    int currentY = this.path.get(this.path.size()-1).getY();

    // add a new location representing the move
    this.path.add(new KnotLoc(currentX + moveX, currentY + moveY));
  }

  // Getter for the path for the head of the rope
  public ArrayList<KnotLoc> getPath() {
    return this.path;
  }

  // Getter for the path for the tail of the rope
  public ArrayList<KnotPath> getTailPaths() {
    return this.tailPaths;
  }

  // Getter for the current location of the head of the rope
  public KnotLoc getCurrentLoc() {
    return this.path.get(this.path.size() - 1);
  }

  private void moveFollower(KnotPath leader, KnotPath tail) {
    // Get the current location of the tail
    KnotLoc currTailLoc = tail.getCurrentLoc();
    KnotLoc currHeadLoc = leader.getCurrentLoc();

    // Find the differences in location
    KnotLoc locDiff = currHeadLoc.minus(currTailLoc);
    int xDiff = locDiff.getX();
    int yDiff = locDiff.getY();

    if ((xDiff <= 1 && xDiff >= -1) && (yDiff <= 1 && yDiff >= -1)) {
      // Don't move the knot
      tail.moveKnot(0,0);
    }
    else if (xDiff == 0) {
      if (yDiff == 2) {
        // move the knot up
        tail.moveKnot(0,1);
      }
      else {
        // move the knot down
        tail.moveKnot(0,-1);
      }
    }
    else if (yDiff == 0){
      if (xDiff == 2) {
        // move the knot right
        tail.moveKnot(1,0);
      }
      else {
        // move the knot left
        tail.moveKnot(-1,0);
      }
    }
    else { // diagonal move
      if (xDiff > 0 && yDiff > 0) {
        // diagonal up right
        tail.moveKnot(1,1);
      }
      else if (xDiff > 0 && yDiff < 0) {
        // diagonal down right
        tail.moveKnot(1,-1);
      }
      else if (xDiff < 0 && yDiff > 0) {
        // diagonal up left
        tail.moveKnot(-1,1);
      }
      else {
        // diagonal down left
        tail.moveKnot(-1,-1);
      }
    }
  }
}
