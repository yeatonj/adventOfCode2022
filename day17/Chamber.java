// Class for modeling a chamber with falling rocks
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022

import java.util.ArrayList;
import java.awt.Point;

class Chamber {
  // Instance variables
  private int chamberWidth;
  private int rockHeight;
  private ArrayList<ChamberContents[]> contentsArray; // row, column
  private String jetDirections;
  private int jetNumber;
  private int dropX;
  private int dropY;
  private char wallPattern;
  private char emptyPattern;
  private char rockPattern;
  private int currentJetIndex;

  // Constructor
  Chamber(String jetDirections, int chamberWidth, char floorPattern, char wallPattern, char emptyPattern, char rockPattern) {
    this.jetNumber = 0;
    this.rockHeight = 0;
    this.jetDirections = jetDirections;
    this.chamberWidth = chamberWidth;
    this.dropX = 2; // Dropping at (2,3) to start
    this.dropY = 3;
    this.wallPattern = wallPattern;
    this.emptyPattern = emptyPattern;
    this.rockPattern = rockPattern;
    this.currentJetIndex = 0;

    // Create the floor and add to the contents
    ChamberContents[] floor = new ChamberContents[this.chamberWidth + 2];
    for (int i = 0; i < floor.length; i ++) {
      floor[i] = new ChamberFloor(floorPattern);
    }
    this.contentsArray = new ArrayList<>();
    this.contentsArray.add(floor);
  }

  // Draw chamber
  public void drawChamber() {
    for (int i = contentsArray.size() - 1; i >=0; i--) {
      for (ChamberContents content : contentsArray.get(i)) {
        if (content != null) {
          System.out.print(content.getDrawChar());
        } else {
          System.out.print(emptyPattern);
        }
      }
      System.out.println("");
    }
  }

  // Drop shape
  public void dropShape(Shape shape) {
    // Ensure the chamber is big enough to track current location of the rocks
    while (this.contentsArray.size() < rockHeight + 5) {
      ChamberContents[] emptyRow = new ChamberContents[this.chamberWidth + 2];
      emptyRow[0] = new ChamberWall(this.wallPattern);
      emptyRow[this.chamberWidth + 1] = new ChamberWall(this.wallPattern);
      this.contentsArray.add(emptyRow);
    }

    // Loop through the drop:
    boolean impacted = false;
    while (!impacted) {
      if (this.jetDirections.charAt(this.currentJetIndex % this.jetDirections.length()) == '<') {
        // Check to the left
        ArrayList<Point> leftPoints = shape.leftMovePoints();
        // if it doesn't impact, move it to the left
        if (!checkImpact(leftPoints)) {
          shape.moveLeft();
        }
      } else {
        // Check to the right
        ArrayList<Point> rightPoints = shape.rightMovePoints();
        if (!checkImpact(rightPoints)) {
          shape.moveRight();
        }
      }
      // Increment the jet index for the next shape
      this.currentJetIndex++;

      // Now, check down
      ArrayList<Point> downPoints = shape.downMovePoints();
      if (!checkImpact(downPoints)) {
        shape.moveDown();
      } else {
        impacted = true;
      }
    }
    // Finally, draw the shape once it has hit the ground
    addShape(shape);
    // Update the height of the rock
    if (shape.highestRow() > this.rockHeight) {
      this.rockHeight = shape.highestRow();
    }
  }

  public boolean checkImpact(ArrayList<Point> pointsToCheck) {
    for (Point point : pointsToCheck) {
      int xLoc = (int)point.getX();
      int yLoc = (int)point.getY();
      if (yLoc <= contentsArray.size() && this.contentsArray.get(yLoc)[xLoc] != null) {
        return true;
      }
    }
    return false;
  }

  public void addShape(Shape shape) {
    ArrayList<Point> pointsToAdd = shape.getPoints();
    for (Point point : pointsToAdd) {
      int xCoord = (int)point.getX();
      int yCoord = (int)point.getY();
      contentsArray.get(yCoord)[xCoord] = new RockParticle(this.rockPattern);
    }
  }
}
