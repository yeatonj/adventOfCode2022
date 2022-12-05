// File to determine cargo stacking for supply stacks
// Written by: Josh Yeaton
// Written on 12/5/2022

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class SupplyStacks {
  public static void main(String[] args) {
    System.out.println("Test");
  }

  // Method to pop a certain number of elements from a stack of cargo,
  // returning those elements
  private static ArrayList<Character> popEnd(ArrayList<Character> listIn,
                                            int elsToPop) {
    // To be implemented
    return null;
  }

  // Method to move a number of crates from one stack to another in a given
  // stack of cargo containers
  private static void moveCrates(int numCrates,
                                int sourceStack,
                                int destStack,
                                ArrayList<ArrayList<Character>> stackList) {
    // To be implemented

  }

  // Method to create the cargo stacks from an input text file, these stacks
  // can then be worked on by the other methods
  private static ArrayList<ArrayList<Character>> createCargoList(String filePath) {
    // To be implemented
    return null;
  }
}
