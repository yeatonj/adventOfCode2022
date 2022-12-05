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
    ArrayList<ArrayList<Character>> cargoArray = createCargoList(startCargo);

    printContents(cargoArray);
    moveCrates(3,1,2,cargoArray);
    printContents(cargoArray);

  }

  // Method to pop a certain number of elements from a stack of cargo,
  // returning those elements (and modifying the original arrayList)
  private static ArrayList<Character> popEnd(ArrayList<Character> listIn,
                                            int elsToPop) {
    // Add the popped elements to a new list
    ArrayList<Character> tempList = new ArrayList<>();
    for (int i = listIn.size() - elsToPop; i < listIn.size(); i++) {
      tempList.add(listIn.get(i));
    }
    // Then, remove the elements from the original arrayList
    listIn.subList(listIn.size() - elsToPop, listIn.size()).clear();

    return tempList;
  }

  // Method to move a number of crates from one stack to another in a given
  // stack of cargo containers, note that stack 1 is indexed at index 0, etc
  private static void moveCrates(int numCrates,
                                int sourceStack,
                                int destStack,
                                ArrayList<ArrayList<Character>> stackList) {
    ArrayList<Character> tempList = popEnd(stackList.get(sourceStack - 1), numCrates);
    for (int i = 0; i < numCrates; i++) {
      stackList.get(destStack - 1).add(tempList.get(i));
    }
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
    for (int i = 0; i < currLine.length()/4 + 1; i++) {
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
    }

    return cargoStacks;
  }

  // Function to print the cargo manifest
  private static void printContents(ArrayList<ArrayList<Character>> cargoList) {
    for (int i = 1; i < cargoList.size() + 1; i++) {
      System.out.println("Contents of Stack " + i + ", top to bottom:");
      for (int j = cargoList.get(i-1).size() - 1; j >= 0; j --) {
        System.out.println(cargoList.get(i-1).get(j));
      }
    }
  }
}
