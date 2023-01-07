// Program for interpreting a SNAFU
// Written for AOC 2022 Day 25
// Written by Josh Yeaton
// Written on 1/6/2023

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FullOfHotAir {
  public static void main(String[] args) throws FileNotFoundException{
    // General idea:
    // 1: convert each line to an actual number
    // 2: sum each number
    // 3: find the sum
    // 4: convert it back into SNAFU
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day25/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day25/data_test.txt";
    File dataFile = new File(filePath);
    Scanner fileScanner = new Scanner(dataFile);

    ArrayList<String> snafuData = new ArrayList<>();
    while(fileScanner.hasNextLine()) {
      snafuData.add(fileScanner.nextLine());
    }

    long sum = 0;
    for (String entry : snafuData) {
      sum +=snafuToLong(entry);
    }
    String result = longToSnafu(sum);
    System.out.println("SNAFU result is : " + result);

  }

  // Method to convert from snafu to decimal number
  public static long snafuToLong(String snafuIn) {
    long currentPower = 1L;
    long sum = 0L;
    for (int i = snafuIn.length() - 1; i >=0; i--) {
      char currentChar = snafuIn.charAt(i);
      if (currentChar == '2') {
        sum += (2L * currentPower);
      } else if (currentChar == '1') {
        sum += currentPower;
      } else if (currentChar == '-') {
        sum -= currentPower;
      } else if (currentChar == '=') {
        sum -= (2L * currentPower);
      }
      currentPower *= 5L;
    }
    return sum;
  }

  // Method to convert from decimal number to snafu
  public static String longToSnafu(long longIn) {
    String returnString = "";
    // Convert to base 5
    ArrayList<Long> baseFive = longToBaseFive(longIn);
    // Adjust to snafu digits
    for (int i = baseFive.size() - 1; i > 0; i--) {
      if (baseFive.get(i) > 2) {
        baseFive.set(i, baseFive.get(i) - 5);
        baseFive.set(i - 1, baseFive.get(i-1) + 1);
      }
    }
    if (baseFive.get(0) > 2) {
      baseFive.set(0, baseFive.get(0) - 5);
      baseFive.add(0, 1L);
    }
    // System.out.println(baseFive);
    // And convert to the proper format
    for (Long num : baseFive) {
      if (num >=0) {
        returnString += num;
      } else if (num == -1) {
        returnString += "-";
      } else {
        returnString += "=";
      }
    }
    return returnString;

  }

  // Method to convert from decimal to base 5
  public static ArrayList<Long> longToBaseFive(long longIn) {
    ArrayList<Long> baseFive = new ArrayList<>();
    long tempLong = longIn;
    long power = 1;
    while (tempLong >= 5 * power) {
      power*=5;
    }
    while (power > 0) {
      long digit = tempLong / power;
      tempLong %= power;
      baseFive.add(digit);
      power /= 5;
    }
    return baseFive;
  }
}
