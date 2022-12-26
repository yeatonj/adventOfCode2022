// Program for checking boiling boulders falling from the sky
// Written for AOC 2022 Day 20
// Written by Josh Yeaton
// Written on 12/25/2022

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.ArrayList;

public class GPS {
  public static void main(String[] args) throws FileNotFoundException {
    // String fileName = "/Users/yeato/Documents/git_projects/adventOfCode2022/day20/test_data.txt";
    String fileName = "/Users/yeato/Documents/git_projects/adventOfCode2022/day20/data.txt";

    File dataFile = new File(fileName);
    Scanner fileScanner = new Scanner(dataFile);
    // List to hold the mixed data values
    ArrayList<Integer> mixedList = new ArrayList<>();
    // List to hold values that won't be changed
    ArrayList<Integer> originalList = new ArrayList<>();
    // List to hold the indices of the moved values
    ArrayList<Integer> indexList = new ArrayList<>();
    int i = 0;
    while (fileScanner.hasNextInt()) {
      int nextInt = fileScanner.nextInt();
      originalList.add(nextInt);
      indexList.add(i);
      i++;
    }
    fileScanner.close();

    // System.out.println(indexList);

    // Iterate through indices, grab the number then find the index in the indexList
    // to find its current location.  Then move the index to wherever it needs to go
    for (i = 0; i < originalList.size(); i++) {
      int moveAmount = originalList.get(i);
      int currentIndex = indexList.indexOf(i);
      int newIndex = currentIndex + moveAmount;
      // System.out.println(moveAmount);
      // System.out.println(currentIndex);
      // System.out.println(newIndex);
      indexList.remove(currentIndex);
      if (newIndex < 0) {
        while (newIndex < 0) {
          newIndex = newIndex + indexList.size();
        }
      }
      if (newIndex >= indexList.size()) {
        newIndex = newIndex % indexList.size();
      }
      if (newIndex == 0) {
        newIndex = indexList.size() - 1;
      }
      if (newIndex == indexList.size() - 1) {
        newIndex = 0;
      }

      indexList.add(newIndex, i);
      // System.out.println(indexList);
    }

    for (i = 0; i < indexList.size(); i++) {
      mixedList.add(originalList.get(indexList.get(i)));
    }
    // System.out.println(mixedList);
    int zeroIndex = mixedList.indexOf(0);
    int firstCoord = mixedList.get((zeroIndex + 1000) % mixedList.size());
    // System.out.println(firstCoord);
    int secondCoord = mixedList.get((zeroIndex + 2000) % mixedList.size());
    // System.out.println(secondCoord);
    int thirdCoord = mixedList.get((zeroIndex + 3000) % mixedList.size());
    // System.out.println(thirdCoord);
    int coordSum = firstCoord + secondCoord + thirdCoord;
    System.out.println("Sum of coordinates is: " + coordSum);

    // Guess of -7196 was incorrect
  }
}
