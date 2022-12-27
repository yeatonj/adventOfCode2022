// Program for checking boiling boulders falling from the sky
// Written for AOC 2022 Day 20
// Written by Josh Yeaton
// Written on 12/25/2022

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.ArrayList;
import java.math.BigInteger;

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
    System.out.println("Sum of coordinates for Part 1 is: " + coordSum);

    // Guess of -7196 was incorrect

    // Guess of 9945 was correct

    // Now for part 2.  Remake as long
    ArrayList<Long> pt2IndexList = new ArrayList<>();
    ArrayList<Long> pt2OriginalList = new ArrayList<>();
    ArrayList<Long> pt2MixedList = new ArrayList<>();
    long key = 811589153;
    for (i = 0; i < originalList.size(); i++) {
      pt2IndexList.add((long)i);
      pt2OriginalList.add(key*(long)originalList.get(i));
    }
    // System.out.println(pt2IndexList);
    // System.out.println(pt2OriginalList);

    int numMixes = 10;
    for (int j = 0; j < numMixes; j++) {
      for (i = 0; i < pt2IndexList.size(); i++) {
        long moveAmount = pt2OriginalList.get(i);
        long currentIndex = pt2IndexList.indexOf((long)i);
        long newIndex = currentIndex + moveAmount;
        // System.out.println(moveAmount);
        // System.out.println(currentIndex);
        // System.out.println(newIndex);
        pt2IndexList.remove((int)currentIndex);
        long newSize = (long)pt2IndexList.size();
        newIndex = ((newIndex % newSize) + newSize) % newSize;
        if (newIndex == 0 && moveAmount != 0) {
          newIndex = newSize;
        }

        pt2IndexList.add((int)newIndex, (long)i);
        // System.out.println(pt2IndexList);
      }
    }

    for (i = 0; i < pt2IndexList.size(); i++) {
      pt2MixedList.add(pt2OriginalList.get(pt2IndexList.get(i).intValue()));
    }
    // System.out.println(pt2MixedList);
    int zeroIndexPt2 = pt2MixedList.indexOf((long)0);
    // System.out.println(zeroIndexPt2);
    long firstCoordPt2 = pt2MixedList.get((zeroIndexPt2 + 1000) % pt2MixedList.size());
    // System.out.println(firstCoordPt2);
    long secondCoordPt2 = pt2MixedList.get((zeroIndexPt2 + 2000) % pt2MixedList.size());
    // System.out.println(secondCoordPt2);
    long thirdCoordPt2 = pt2MixedList.get((zeroIndexPt2 + 3000) % pt2MixedList.size());
    // System.out.println(thirdCoordPt2);
    long coordSumPt2 = firstCoordPt2 + secondCoordPt2 + thirdCoordPt2;
    System.out.println("Sum of coordinates for Part 2 is: " + coordSumPt2);



  }
}
