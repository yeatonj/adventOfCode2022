// Class to model a cave with rock and falling sand
// Written for Advent of Code 2022
// Written by: Joshua Yeaton
// Written on: 12/14/2022

import java.util.ArrayList;

class CaveModel {
  // Instance Variables
  private int caveDepth;
  private int caveWidth;
  private int widthOffset; // data coming in needs to be adjusted to align with cave matrix
  private CaveContent[][] caveContents; // Holds the contents of the cave
  private int sandSource;

  // Constructor
  public CaveModel(int maxDepth, int minWidthCoord, int maxWidthCoord, int sandSource, int floorDepth, char floorChar) {
    // Note, the depth is 0 indexed, so we need an array of size 1 bigger
    this.caveDepth = maxDepth + 1 + floorDepth;
    // Make the width larger by 2 to allow us to flow sand off the sides
    this.caveWidth = maxWidthCoord - minWidthCoord + 3;
    // The width offset is subtracting the minimum value and adding 1 (to account for extra width)
    this.widthOffset = (-1*minWidthCoord) + 1;
    this.sandSource = sandSource + this.widthOffset;

    // Now, create the caveContents array
    caveContents = new CaveContent[this.caveWidth][this.caveDepth];

    // And, create the floor
    for (int i = 0; i < this.caveWidth; i++) {
      caveContents[i][this.caveDepth - 1] = new Rock(floorChar);
    }
  }

  // Getters
  public int getCaveDepth() {
    return this.caveDepth;
  }

  public int getCaveWidth() {
    return this.caveWidth;
  }

  public int getWidthOffset() {
    return this.widthOffset;
  }

  // Other methods

  // Method to add rocks
  // Each should be coordinates (x,y)
  public void addRocks(int[] coord1, int[] coord2, char rockChar) {
    // Adding on const x
    int x1 = coord1[0];
    int x2 = coord2[0];
    int y1 = coord1[1];
    int y2 = coord2[1];
    if (x1 == x2) {
      int diff = y2 - y1;
      if (diff < 0) {
        for (int i = y1; i >= y2; i--) {
          rockHelper(x1, i, rockChar);
        }
      } else {
        for (int i = y1; i <= y2; i++) {
          rockHelper(x1, i, rockChar);
        }
      }
    } else {
      int diff = x2 - x1;
      if (diff < 0) {
        for (int i = x1; i >= x2; i--) {
          rockHelper(i, y1, rockChar);
        }
      } else {
        for (int i = x1; i <= x2; i++) {
          rockHelper(i, y1, rockChar);
        }
      }
    }
  }

  // Helper method to add a rock
  private boolean rockHelper(int xAdd, int yAdd, char rockChar) {
    int xTransform = this.widthOffset + xAdd;
    if (this.caveContents[xTransform][yAdd] == null) {
      this.caveContents[xTransform][yAdd] = new Rock(rockChar);
      return true;
    }
    return false;
  }


  // Method to draw the cave
  public void printCaveMap() {
    for (int i = 0; i < this.caveDepth; i++) {
      for (int j = 0; j < this.caveWidth; j++) {
        if (caveContents[j][i] == null) {
          System.out.print(".");
        } else {
          System.out.print(caveContents[j][i].getChar());
        }
      }
      // New line
      System.out.print("\n");
    }
  }

  // Method to drop sand, returns true if the sand stayed on the map
  public boolean dropSand(char sandChar) {
    // Generate the sand at the source
    int currentX = this.sandSource;
    int currentY = 0;
    if (this.caveContents[currentX][currentY] != null) {
      System.out.println("Source is blocked!");
      return false;
    } else {
      this.caveContents[currentX][currentY] = new Sand(sandChar);
    }
    boolean result = moveSand(currentX, currentY);
    return result;
  }

  private boolean moveSand(int currentX, int currentY) {
    boolean canMove = true;
    do {
      // Check to see if we are too close to the cave edges
      if (currentX == 0 || currentX == this.caveWidth - 1) {
        return false;
      }
      // Now, move the sand
      if (this.caveContents[currentX][currentY + 1] == null) {
        // First possibility - clear below!
        currentY++;
        this.caveContents[currentX][currentY] = this.caveContents[currentX][currentY-1];
        this.caveContents[currentX][currentY-1] = null;
      } else if ((this.caveContents[currentX-1][currentY + 1] == null)) {
        // Second possibility, clear diagonal left
        currentY++;
        currentX--;
        this.caveContents[currentX][currentY] = this.caveContents[currentX+1][currentY-1];
        this.caveContents[currentX+1][currentY-1] = null;
      } else if ((this.caveContents[currentX+1][currentY + 1] == null)) {
        // Third possibility, clear diagonal right
        currentY++;
        currentX++;
        this.caveContents[currentX][currentY] = this.caveContents[currentX-1][currentY-1];
        this.caveContents[currentX-1][currentY-1] = null;
      } else {
        canMove = false;
      }
    } while (canMove);
    return true;
  }




}
