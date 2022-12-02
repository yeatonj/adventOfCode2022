// Program to find how many calories the elf with the most calories is
// carrying.  Input file is "elf_calories.txt"

// Written by Joshua Yeaton
// Written on 12/1/22

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class CalorieCounting {
  public static void main(String[] arg) {
    // Set filepath and open file
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day1/elf_calories.txt";
    File calorieFile = null;
    Scanner calorieScanner = null;
    try {
      calorieFile = new File(filePath);
      calorieScanner = new Scanner(calorieFile);
    }
    catch (FileNotFoundException e) {
      System.out.println("File seems to be missing...");
    }

    // Setup array to hold the calorie totals for each elf and a temp variable
    // as we iterate through lines
    ArrayList<Integer> calorieList = new ArrayList<>();
    int tempTotal = 0;
    while(calorieScanner.hasNextLine()) {
      String output = calorieScanner.nextLine();
      if (output.equals("")) {
        calorieList.add(tempTotal);
        tempTotal = 0;
      } else {
        tempTotal += Integer.valueOf(output);
      }
    }
    // Close the scanner
    calorieScanner.close();
    Collections.sort(calorieList, Collections.reverseOrder());
    System.out.println("Highest calories is: " + calorieList.get(0));

    int topThree = calorieList.get(0) + calorieList.get(1) + calorieList.get(2);
    System.out.println("Top 3 is: " + topThree);
  }
}
