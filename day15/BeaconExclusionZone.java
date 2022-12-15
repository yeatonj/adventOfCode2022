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

    // Now for part 2 - find the location that we can't have a beacon
    // Generate a set of the nobeacon locs in a row
    // For each possible point in that row, check if it's not in the set
    // If its not, then we've found our point. If it is, then move on to the next
    // row

    int beaconRow = 0;
    int beaconCol = 0;
    int maxRowCol = 4000000;
    int minRowCol = 0;
    for (int i = minRowCol; i < maxRowCol; i++) {
      HashSet<Point> noBeaconLine = new HashSet<>();
      for (Sensor sen:sensorList) {
        HashSet<Point> noBeaconSensor = sen.findPointsInRow(i, minRowCol, maxRowCol);
        if (noBeaconSensor == null) {
          continue;
        }
        for (Point p : noBeaconSensor) {
          if (!noBeaconLine.contains(p)) {
            noBeaconLine.add(p);
          }
        }
      }
      System.out.println("Running...");
      // At this point, we have a set of all the places a beacon cannot be in the line
      // Now, we simply check the size of the set to see if it is full. If it isn't,
      // we know the point is there and we iterate through to find which one
      if (noBeaconLine.size() != maxRowCol + 1) {
        beaconRow = i;
        // Now, find the column
        for (int j = minRowCol; j < maxRowCol; j++) {
          Point p = new Point(j,i);
          if (!noBeaconLine.contains(p)) {
            beaconCol = j;
          }
        }
      }
    }

    System.out.println("Beacon is in row " + beaconRow);
    System.out.println("Beacon is in column " + beaconCol);
    System.out.println("Tuning frequency is: " + (beaconCol*4000000 + beaconRow));
  }
}
