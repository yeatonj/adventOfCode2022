// File to check (and possibly sort) rucksacks for Advent of Code 2022 Day 3
// Written by: Josh Yeaton
// Written on 12/3/2022

import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class RucksackReorganization {
  public static void main(String[] args) {
    // Part 1: load the data file and check for duplicate letters in the first
    // and second half of each line. When a duplicate is found, add its value
    // to the total (a-z: 1-26, A-Z: 27-52)

    // Load the data file
    File ruckFile = null;
    Scanner ruckScanner = null;
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day3/rucksack_contents.txt";
    try {
      ruckFile = new File(filePath);
      ruckScanner = new Scanner(ruckFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found...");
    }

    // Find the repeated value in each rucksack
    int prioritySum = 0;
    while (ruckScanner.hasNextLine()) {
      String contents = ruckScanner.nextLine();
      prioritySum += getScore(contents);
    }

    System.out.println("Priority sum is: " + prioritySum);
    ruckScanner.close();
  }

  private static int getScore(String lineIn) {
    // Create a set for each half of the rucksack
    Set<Character> firstHalfChars = new HashSet<>();
    Set<Character> secondHalfChars = new HashSet<>();
    // iterate through the contents of each
    for (int i = 0; i < (lineIn.length() / 2); i++) {
      char char1 = lineIn.charAt(i);
      char char2 = lineIn.charAt(i + lineIn.length() / 2);
      // Add contents to rucksack
      if (!firstHalfChars.contains(char1)) {
        firstHalfChars.add(char1);
      }
      if (!secondHalfChars.contains(char2)) {
        secondHalfChars.add(char2);
      }

      // If the just added content are in the other container, find the value
      // and return it
      if (firstHalfChars.contains(char2)) {
        // System.out.println("Line is: " + lineIn);
        // System.out.println("Repeated char is: " + char2);
        return findCharValue(char2);
      } else if (secondHalfChars.contains(char1)) {
        // System.out.println("Line is: " + lineIn);
        // System.out.println("Repeated char is: " + char1);
        return findCharValue(char1);
      }
    }
    return 0;
  }

  // Function to find the value of a character as described at the top of the
  // program
  private static int findCharValue(char charIn) {
    if (Character.isUpperCase(charIn)) {
      return charIn - 'A' + 27;
    } else {
      return charIn - 'a' + 1;
    }
  }
}
