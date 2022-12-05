// File to determine cargo stacking for supply stacks
// Written by: Josh Yeaton
// Written on 12/5/2022

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;

class SupplyStacks {
  public static void main(String[] args) {
    String startCargo = "/Users/yeato/Documents/git_projects/adventOfCode2022/day5/cargo_start.txt";
    String instructions = "/Users/yeato/Documents/git_projects/adventOfCode2022/day5/cargo_instructions.txt";
    ArrayList<ArrayList<Character>> testFile = createCargoList(startCargo);
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
    // Create the arrayList
    ArrayList<ArrayList<Character>> cargoStacks = new ArrayList<>();
    File startingConfig = null;
    Scanner stackScannerNumCrates = null;
    Scanner stackScannerCargo = null;
    try {
      startingConfig = new File(filePath);
      stackScannerNumCrates = new Scanner(startingConfig);
      stackScannerCargo = new Scanner(startingConfig);
    } catch (FileNotFoundException e){
      System.out.println("Starting config not found, returning null array...");
      return null;
    }

    // Go to the last line of the cargo file to find the number of crates
    String currLine = null;
    while (stackScannerNumCrates.hasNextLine()) {
      if (stackScannerNumCrates.hasNextInt()) {
        currLine = stackScannerNumCrates.nextLine();
      } else {
        stackScannerNumCrates.nextLine();
      }
    }
    stackScannerNumCrates.close();
    // Create the appropriate number of stacks
    for (int i = 0; i < currLine.length()/ + 1; i++) {
      cargoStacks.add(new ArrayList<Character>());
    }

    // Now, create the stacks (in reverse order...)
    while (stackScannerCargo.hasNextLine() && !stackScannerCargo.hasNextInt()) {
      currLine = stackScannerCargo.nextLine();
      for (int j = 1; j < currLine.length(); j+=4) {
        if (currLine.charAt(j) != ' ') {
          cargoStacks.get(j/4).add(currLine.charAt(j));
          // System.out.println("Adding: " + currLine.charAt(j));
        }
      }
    }

    // Finally, reverse each of the lists to make the stacks correct
    for (int k = 0; k < cargoStacks.size(); k++) {
      Collections.reverse(cargoStacks.get(k));
      // System.out.println("Cargo bucket " + k);
      // for (int l = 0; l < cargoStacks.get(k).size(); l++) {
      //   System.out.println(cargoStacks.get(k).get(l));
      // }
    }

    return cargoStacks;
  }
}
