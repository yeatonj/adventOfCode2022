// Class to model a piece of sand
// Written for Advent of Code 2022
// Written by: Joshua Yeaton
// Written on: 12/14/2022

class Sand extends CaveContent {
  // Class variables
  private static int numSand = 0;
  // Instance Variables
  private char sandChar;

  // Methods
  // Constructor
  public void Sand(char sandChar) {
    this.sandChar = sandChar;
    Sand.numSand++;
  }

  // Getters
  public char getChar() {
    return this.sandChar;
  }

  public static int getNumSand() {
    return Sand.numSand;
  }
}
