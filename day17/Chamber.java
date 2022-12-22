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
    while (this.contentsArray.size() < rockHeight + 5) {
      ChamberContents[] emptyRow = new ChamberContents[this.chamberWidth + 2];
      emptyRow[0] = new ChamberWall(this.wallPattern);
      emptyRow[this.chamberWidth + 1] = new ChamberWall(this.wallPattern);
      this.contentsArray.add(emptyRow);
    }
    System.out.println("Done.");
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
