// Class for modeling the chamber wall
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022


public class ChamberWall extends ChamberContents {
  // Instance variables
  private char drawChar;

  // Constructor
  ChamberWall(char drawChar) {
    this.drawChar = drawChar;
  }

  // Rock character getter
  public char getDrawChar() {
    return this.drawChar;
  }
}
