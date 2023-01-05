// Program for avoiding blizzards
// Written for AOC 2022 Day 24
// Written by Josh Yeaton
// Written on 1/4/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BlizzardBasin {
  public static void main(String[] args) throws FileNotFoundException {
    // General approach:
    // 1. Read the storm into memory, and create a t = 0 representation of it
    // 2. Generate the storm state at next timeslot and save it to refer back to
    // it.
    // 3. Check the party's current position and look for adjacent open slots
    // at the next timeslot.
    // 4. Add those positions to a queue, including the current time
    // 5. Poll the queue and repeat
    // 6. Optimize to allow for calculation of shortest path (empty board?) and
    // use that to eliminate paths that won't allow us to reach the end
    // 7. Memoize based on current position and time?

    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day24/data_test.txt";
    File dataFile = new File(filePath);
    Scanner fileScanner = new Scanner(dataFile);

    String fileString = "";
    while(fileScanner.hasNext()) {
      fileString += (fileScanner.next() + "\n");
    }
    fileString = fileString.substring(0, fileString.length() - 1);
    BlizzardMap partOneBlizzard = new BlizzardMap(fileString);

    partOneBlizzard.printMap();

    fileScanner.close();


  }
}
