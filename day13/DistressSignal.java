// Program for checking distress signal data
// Written for Advent of Code 2022 day 13
// Written by Joshua Yeaton, 12/13/22

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class DistressSignal {
  public static void main(String args[]) throws FileNotFoundException {
    // Open and load the data into a string array
    ArrayList<String> distressLines = new ArrayList<>();
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day13/distress_data.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day13/distress_data_test.txt";
    File distressData = new File(filePath);
    Scanner distressScanner = new Scanner(distressData);

    // Load the distress data into the array
    int lineCount = 0;
    while(distressScanner.hasNextLine()) {
      lineCount++;
      if (lineCount % 3 !=0){
        distressLines.add(distressScanner.nextLine());
      } else {
        // Skips blank lines
        distressScanner.nextLine();
      }
    }
    distressScanner.close();

    System.out.println(distressLines);

    // -----------------------------------
    // We now have the data loaded into the array, even lines start the pair
  }
}
