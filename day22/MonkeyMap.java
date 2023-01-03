// Program for doing monkeymath
// Written for AOC 2022 Day 22
// Written by Josh Yeaton
// Written on 1/2/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class MonkeyMap {
  public static void main(String[] args) throws FileNotFoundException {
    // Read the input data and create two strings - one of the map, one of the
    // character instructions

    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day22/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day22/data_test.txt";

    File dataFile = new File(filePath);
    Scanner dataScanner = new Scanner(dataFile);
    // Get the output from the data file, reading into one long string
    String output = "";

    while (dataScanner.hasNextLine()) {
      output += dataScanner.nextLine();
      output += "\n";
    }
    // Split against the two line breaks and remove whitespace from directions
    String[] splitOutput = output.split("\n\n");
    splitOutput[1] = splitOutput[1].substring(0, splitOutput[1].length() - 1);

    // Now, generate the map
    MapGraph partOneMap = new MapGraph(splitOutput[0], '#', '.', ' ');
    partOneMap.printMap();
    // Add the character to the map
    MapCharacter human = new MapCharacter(0);
    partOneMap.addCharacter(human);

    // And give the character directions
    giveDirections(splitOutput[1], human);

    // Then calculate the code
    int finalCol = human.charLocation()[0];
    int finalRow = human.charLocation()[1];
    int finalDir = human.getDirection();
    int code = 1000 * finalRow + 4 * finalCol + finalDir;
    System.out.println("Code is: " + code);

    // Now for part 2:
    CubeMapGraph partTwoMap = new CubeMapGraph(splitOutput[0], '#', '.', ' ');
    partTwoMap.printMap();
    // Add the character to the map
    CubeMapCharacter humanTwo = new CubeMapCharacter(0);
    partTwoMap.addCharacter(humanTwo);

    // And give the character directions
    giveCubeDirections(splitOutput[1], humanTwo);

    // Then calculate the code
    finalCol = humanTwo.charLocation()[0];
    finalRow = humanTwo.charLocation()[1];
    finalDir = humanTwo.getDirection();
    code = 1000 * finalRow + 4 * finalCol + finalDir;
    System.out.println("Code is: " + code);
  }

  public static void giveDirections(String directions, MapCharacter characterIn) {
    // First, parse the input
    ArrayList<String> instructions = new ArrayList<>();
    int currInstChar = 0;
    for (int i = 0; i < directions.length(); i++) {
      char currChar = directions.charAt(i);
      if (currChar == 'L' || currChar == 'R') {
        instructions.add(directions.substring(currInstChar, i));
        instructions.add(Character.toString(currChar));
        currInstChar = i + 1;
      }
    }
    instructions.add(directions.substring(currInstChar));
    for (String instruction : instructions) {
      if (instruction.equals("L")) {
        System.out.println("Turning Left");
        characterIn.turnLeft();
      } else if (instruction.equals("R")) {
        System.out.println("Turning Right");
        characterIn.turnRight();
      } else {
        int moveAmount = Integer.parseInt(instruction);
        while (moveAmount > 0) {
          int[] currLoc = characterIn.charLocation();
          System.out.println("Moving character from (" + currLoc[0] +", " + currLoc[1] + ")");
          characterIn.moveCharacter();
          moveAmount--;
        }
      }
    }
  }


  // Function for giving directions on a cube
  public static void giveCubeDirections(String directions, CubeMapCharacter characterIn) {
    // First, parse the input
    ArrayList<String> instructions = new ArrayList<>();
    int currInstChar = 0;
    for (int i = 0; i < directions.length(); i++) {
      char currChar = directions.charAt(i);
      if (currChar == 'L' || currChar == 'R') {
        instructions.add(directions.substring(currInstChar, i));
        instructions.add(Character.toString(currChar));
        currInstChar = i + 1;
      }
    }
    instructions.add(directions.substring(currInstChar));
    for (String instruction : instructions) {
      if (instruction.equals("L")) {
        // System.out.println("Turning Left");
        characterIn.turnLeft();
      } else if (instruction.equals("R")) {
        // System.out.println("Turning Right");
        characterIn.turnRight();
      } else {
        int moveAmount = Integer.parseInt(instruction);
        while (moveAmount > 0) {
          int[] currLoc = characterIn.charLocation();
          // System.out.println("Moving character from (" + currLoc[0] +", " + currLoc[1] + ")");
          characterIn.moveCharacter();
          moveAmount--;
        }
      }
    }
  }
}
