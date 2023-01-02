// Program for doing monkeymath
// Written for AOC 2022 Day 21
// Written by Josh Yeaton
// Written on 1/1/2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Scanner;

class MonkeyMath {
  public static void main(String[] args) throws FileNotFoundException {
    /*
    General idea:
    1. Read each line, doing the following:
      a. If a sequence corresponds to a #, save it in a hashmap <String, Integer>
      b. if a sequence corresponds to an equation, save it in a queue with an arraylist
      [solutionSeq, string1, op, string2]
      c. Iterate through the queue, checking if both string 1 and string 2 are
      in the hashmap.  If they are, solve the equation and add to the hashmap.
      If not, simply add it to the back of the queue
    */

    // Set up variables
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day21/data_test.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day21/data.txt";
    HashMap<String, Long> solutionsMap = new HashMap<>();
    ArrayDeque<ArrayList<String>> eqQueue = new ArrayDeque<>(); // queue
    String rootEntryOne = "";
    String rootEntryTwo = "";

    File inputFile = new File(filePath);
    Scanner inputScanner = new Scanner(inputFile);
    // Parse input as in comments above
    while (inputScanner.hasNextLine()) {
      String line = inputScanner.nextLine();
      String[] splitLine = line.split(": ");
      try {
        long num = Long.parseLong(splitLine[1]);
        solutionsMap.put(splitLine[0], num);
      } catch (NumberFormatException nfe) {
        String[] equation = splitLine[1].split(" ");
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(splitLine[0]);
        for (int i = 0; i < 3; i++) {
          tempList.add(equation[i]);
        }
        if (tempList.get(0).equals("root")) {
          rootEntryOne = tempList.get(1);
          rootEntryTwo = tempList.get(3);
        }
        eqQueue.add(tempList);
      }
    }
    HashMap<String,Long> origSolutionsMap = (HashMap<String, Long>)solutionsMap.clone();
    ArrayDeque<ArrayList<String>> origEqQueue = copyQueue(eqQueue);


    // Now, move through the queue, solving equations that have both values
    // until the queue is empty
    while (!eqQueue.isEmpty()) {
      ArrayList<String> tempList = eqQueue.poll();
      if(solutionsMap.containsKey(tempList.get(1)) && solutionsMap.containsKey(tempList.get(3))) {
        solutionsMap.put(tempList.get(0), solveEquation(solutionsMap.get(tempList.get(1)), solutionsMap.get(tempList.get(3)), tempList.get(2)));
      } else {
        eqQueue.add(tempList);
      }
    }
    // Part 1 solution
    System.out.println("For part 1, root monkey is shouting: " + solutionsMap.get("root"));

    // Part 2
    long startVal = 0L;
    // long startVal = 0;
    long finVal = 10000000000000L;
    // long finVal = 1000;
    long pt2Solution = findHumanVal(origEqQueue, origSolutionsMap, startVal, finVal, rootEntryOne, rootEntryTwo);
    System.out.println("For part 2, human needs to shout: " + pt2Solution);
    // Guess of 3378273370684 is too high
    // Guess of 3378273370680 was correct. Algorithm gives multiple results??
    // Fixed by using double rather than long
  }

  public static long solveEquation(long num1, long num2, String op) {
    if (op.equals("+")) {
      return num1 + num2;
    } else if (op.equals("-")) {
      return num1 - num2;
    } else if (op.equals("*")) {
      return num1 * num2;
    } else {
      return num1 / num2;
    }
  }

  public static ArrayDeque<ArrayList<String>> copyQueue(ArrayDeque<ArrayList<String>> origQueue) {
    ArrayDeque<ArrayList<String>> tempQueue = new ArrayDeque<>();
    for (ArrayList<String> origArrays : origQueue) {
      ArrayList<String> tempList = new ArrayList<>();
      for (String str : origArrays) {
        tempList.add(str);
      }
      tempQueue.add(tempList);
    }
    return tempQueue;
  }

  public static long findHumanVal(ArrayDeque<ArrayList<String>> queueIn, HashMap<String,Long> solutionsMap, long minVal, long maxVal, String rootEqualityOne, String rootEqualityTwo) {
    while (maxVal != minVal) {
      long i = (maxVal + minVal) / 2;
      ArrayDeque<ArrayList<String>> tempQueue = copyQueue(queueIn);
      HashMap<String,Double> tempMap = new HashMap<>();
      for (HashMap.Entry<String, Long> entry : solutionsMap.entrySet()) {
        tempMap.put(entry.getKey(), (double)entry.getValue());
      }
      tempMap.put("humn", (double)i);
      while (!tempQueue.isEmpty()) {
        ArrayList<String> tempList = tempQueue.poll();
        // Check to see if we have #'s for the equal state
        if (tempList.get(0).equals("root") && tempMap.containsKey(tempList.get(1)) && tempMap.containsKey(tempList.get(3))) {
          if (tempMap.get(tempList.get(1)).equals(tempMap.get(tempList.get(3)))) {
            return i;
          }
        }
        else if(tempMap.containsKey(tempList.get(1)) && tempMap.containsKey(tempList.get(3))) {
          tempMap.put(tempList.get(0), solveEquation(tempMap.get(tempList.get(1)), tempMap.get(tempList.get(3)), tempList.get(2)));
        } else {
          tempQueue.add(tempList);
        }
      }
      // Uncomment the second line for example data - equality is the other
      // direction
      double diff = tempMap.get(rootEqualityOne) - tempMap.get(rootEqualityTwo);
      // double diff = -tempMap.get(rootEqualityOne) + tempMap.get(rootEqualityTwo);
      // System.out.println("Current diff is: " + diff);
      if (diff > 0) {
        minVal = i;
      } else {
        maxVal = i;
      }
    }
    return -1;
  }

  public static double solveEquation(double num1, double num2, String op) {
    if (op.equals("+")) {
      return num1 + num2;
    } else if (op.equals("-")) {
      return num1 - num2;
    } else if (op.equals("*")) {
      return num1 * num2;
    } else {
      return num1 / num2;
    }
  }
}
