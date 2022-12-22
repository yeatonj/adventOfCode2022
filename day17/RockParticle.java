// Class for modeling a rock in a chamber
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022


public class RockParticle extends ChamberContents {
  // Instance variables
  private char drawChar;

  // Constructor
  RockParticle(char drawChar) {
    this.drawChar = drawChar;
  }

  // Rock character getter
  public char getDrawChar() {
    return this.drawChar;
  }
}
