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

    // Part 2: for each group of 3 elves, find the shared item between all of them
    // and return its score

    // Find the repeated values in each rucksack
    int prioritySum = 0;
    int elfCount = 0;
    String elf1 = null;
    String elf2 = null;
    String elf3 = null;
    int badgeScore = 0;
    while (ruckScanner.hasNextLine()) {
      String contents = ruckScanner.nextLine();
      prioritySum += getScore(contents);

      if (elfCount == 0) {
        elf1 = contents;
        elfCount++;
      } else if (elfCount == 1) {
        elf2 = contents;
        elfCount++;
      } else {
        elf3 = contents;
        badgeScore += findBadge(elf1, elf2, elf3);
        elfCount = 0;
      }

    }

    System.out.println("Priority sum is: " + prioritySum);
    System.out.println("Badge sum is: " + badgeScore);
    ruckScanner.close();
  }

  // Function to find the priority of the item shared between each half
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

  // Function to find the single item shared in all bags
  private static int findBadge(String bagOne, String bagTwo, String bagThree) {
    // Create a set for each of the first two sacks, and add their items to them
    Set<Character> firstSack = new HashSet<>();
    Set<Character> secondSack = new HashSet<>();
    for (int i = 0; i < bagOne.length(); i++) {
      if (!firstSack.contains(bagOne.charAt(i))) {
        firstSack.add(bagOne.charAt(i));
      }
    }
    for (int j = 0; j < bagTwo.length(); j++) {
      if (!secondSack.contains(bagTwo.charAt(j))) {
        secondSack.add(bagTwo.charAt(j));
      }
    }

    // Iterate through the third sack, and find the shared item, returning its value
    for (int k = 0; k < bagThree.length(); k++) {
      if (firstSack.contains(bagThree.charAt(k)) &&
          secondSack.contains(bagThree.charAt(k))) {
        // System.out.println("Line 1 is: " + bagOne);
        // System.out.println("Line 2 is: " + bagTwo);
        // System.out.println("Line 3 is: " + bagThree);
        // System.out.println("Repeated char is: " + bagThree.charAt(k));
        return findCharValue(bagThree.charAt(k));
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
