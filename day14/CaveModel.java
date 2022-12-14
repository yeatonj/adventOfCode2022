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

  // Constructor
  public CaveModel(int maxDepth, int minWidthCoord, int maxWidthCoord) {
    // Note, the depth is 0 indexed, so we need an array of size 1 bigger
    this.caveDepth = maxDepth + 1;
    // Make the width larger by 2 to allow us to flow sand off the sides
    this.caveWidth = maxWidthCoord - minWidthCoord + 2;
    // The width offset is subtracting the minimum value and adding 1 (to account for extra width)
    this.widthOffset = (-1*minWidthCoord) + 1;

    // Now, create the caveContents array
    caveContents = new CaveContent[this.caveDepth][this.caveWidth];
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
        for (int i = y2; i >= y1; i--) {
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
        for (int i = x2; i >= x1; i--) {
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
        // System.out.println(i);
        // System.out.println(j);
        if (caveContents[i][j] == null) {
          System.out.print(".");
        } else {
          System.out.print(caveContents[i][j].getChar());
        }
      }
      // New line
      System.out.print("\n");
    }
  }




}
