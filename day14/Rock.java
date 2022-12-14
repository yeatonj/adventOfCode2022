// Class to model a rock
// Written for Advent of Code 2022
// Written by: Joshua Yeaton
// Written on: 12/14/2022

class Rock extends CaveContent {
  // Class variables
  private static int numRocks = 0;
  // Instance Variables
  private char rockChar;

  // Methods
  // Constructor
  public void Rock(char rockChar) {
    this.rockChar = rockChar;
    Rock.numRocks++;
  }

  // Getters
  public char getChar() {
    return this.rockChar;
  }

  public static int getNumRocks() {
    return Rock.numRocks;
  }
}
