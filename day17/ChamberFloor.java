// Class for modeling the chamber floor
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022


public class ChamberFloor extends ChamberContents {
  // Instance variables
  private char drawChar;

  // Constructor
  ChamberFloor(char drawChar) {
    this.drawChar = drawChar;
  }

  // Rock character getter
  public char getDrawChar() {
    return this.drawChar;
  }
}
