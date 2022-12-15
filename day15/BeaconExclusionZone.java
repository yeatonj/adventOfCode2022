// Program for examining beacon/signal data
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/15/2022

import java.awt.Point;
import java.util.HashSet;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.math.BigInteger;

class BeaconExclusionZone {
  public static void main(String[] args) throws FileNotFoundException {
    // Load in the data
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day15/beacon_data_test.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day15/beacon_data.txt";

    File beaconFile = new File(filePath);
    Scanner beaconScanner = new Scanner(beaconFile);

    // Create an ArrayList to hold sensors as we make them
    ArrayList<Sensor> sensorList = new ArrayList<>();
    // And a hashset to hold all existing beacons
    HashSet<Point> beaconLocs = new HashSet<>();

    // Read numbers from each line and create objects for each sensor and beacon
    while (beaconScanner.hasNextLine()) {
      Pattern numPattern = Pattern.compile("-?\\d+");
      String newLine = beaconScanner.nextLine();
      Matcher numMatch = numPattern.matcher(newLine);
      int[] numOutput = new int[4];
      int i = 0;
      while(numMatch.find()) {
        numOutput[i] = Integer.parseInt(numMatch.group());
        i++;
      }
      // Now, add the beacon sensor pair to our file and the beacon set
      Point beacon = new Point(numOutput[2], numOutput[3]);
      Sensor newSensor = new Sensor(numOutput[0], numOutput[1], beacon);
      sensorList.add(newSensor);
      if (!beaconLocs.contains(beacon)) {
        beaconLocs.add(beacon);
      }
    }

    // Now, part 1 - check for locations that can't contain a beacon
    int testRow = 10; // Test data
    // int testRow = 2000000; // Actual data
    HashSet<Point> noBeacon = new HashSet<>();
    for (Sensor sen:sensorList) {
      HashSet<Point> noBeaconPoints = sen.findPointsInRow(testRow);
      if (noBeaconPoints == null) {
        continue;
      }
      // System.out.println("Possible points beacon can't be:");
      for (Point p: noBeaconPoints) {
        // System.out.println(p);
        if (!noBeacon.contains(p) && !beaconLocs.contains(p)) {
          noBeacon.add(p);
        }
      }
    }
    // System.out.println(noBeacon);
    System.out.println("Locations that can't have a beacon: " + noBeacon.size());
    // Guessed 4724228 - correct!

    // Now for part 2 - sort the sensors by their x locations (increasing), then
    // iterate through each row, grabbing the coordinates that each overlap on.
    // Check to see if there is a point at which they don't overlap, and if so,
    // that's the row we want.

    int beaconRow = 0;
    int beaconCol = 0;
    int maxRowCol = 4000000;
    // int maxRowCol = 20; // Test case
    int minRowCol = 0;

    // First, sort the sensors
    SensorColComparator sensorColCompare = new SensorColComparator();
    Collections.sort(sensorList, sensorColCompare);

    // Then, loop through each of the rows
    boolean found = false;
    for (int i = minRowCol; i < maxRowCol; i++) {
      // List of the sensor coverage on this line - build it out
      ArrayList<int[]> sensorCoverageLine = new ArrayList<>();
      for (Sensor s : sensorList) {
        int[] sensorCoverage = s.findPointsInRow(i, minRowCol, maxRowCol);
        if (sensorCoverage != null) {
          sensorCoverageLine.add(sensorCoverage);
        }
      }
      // Set the current row variable
      beaconRow = i;
      // Check the first position and set the range for the first sensor
      beaconCol = 0;
      // System.out.println("Testing Sensor " + 0 + " on line " + i);
      // System.out.println("Sensor coverage " + 0 + " to " + beaconCol);

      // Iterate through the rest of the sensors
      for (int j = 0; j < sensorCoverageLine.size(); j++) {
        int sensorStart = sensorCoverageLine.get(j)[0];
        int sensorEnd = sensorCoverageLine.get(j)[1];
        // System.out.println("Testing Sensor " + j + " on line " + i);
        // System.out.println("Sensor coverage " + sensorStart + " to " + sensorEnd);
        // Check to see if the sensor has overlapping coverage
        if (sensorStart <= beaconCol && sensorEnd >= beaconCol) {
          beaconCol = sensorEnd;
        }
        // System.out.println("Total coverage " + 0 + " to " + beaconCol);
      }
      // System.out.println("Last overlapping index:" + beaconCol);
      if (beaconCol != maxRowCol) {
        beaconCol++;
        break;
      }
      // System.out.println("");
    }

    BigInteger bigRow = BigInteger.valueOf(beaconRow);
    BigInteger bigCol = BigInteger.valueOf(beaconCol);
    BigInteger freq = BigInteger.valueOf(4000000);

    BigInteger output1 = bigCol.multiply(freq);
    BigInteger output2 = output1.add(bigRow);



    System.out.println("Beacon is in row " + beaconRow);
    System.out.println("Beacon is in column " + beaconCol);
    System.out.println("Tuning frequency is: " + output2);
    // Guess of 4237168587415 was too low
    // Correct guess 13622251246513
  }
}
