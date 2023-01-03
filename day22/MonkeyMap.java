// Program for doing monkeymath
// Written for AOC 2022 Day 22
// Written by Josh Yeaton
// Written on 1/2/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    System.out.println(splitOutput[0]);

  }
}
