// Class to define a sensor object
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/15/2022

import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;

class Sensor {
  // Instance variables
  private Point sensorLoc;
  private Point closestBeacon;
  private int beaconDistance; // manhattan distance

  // Constructor
  public Sensor(int sensorX, int sensorY, Point beacon) {
    // Assign the position variables
    this.sensorLoc = new Point(sensorX, sensorY);
    this.closestBeacon = beacon;

    // Calculate the manhattan distance from origin to beacon location
    int beaconX = (int) beacon.getX();
    int beaconY = (int) beacon.getY();
    int xDist = beaconX - sensorX;
    int yDist = beaconY - sensorY;
    this.beaconDistance = Math.abs(xDist) + Math.abs(yDist);
  }

  // Getters
  public Point getSensorLoc() {
    return this.sensorLoc;
  }

  public Point getClosestBeacon() {
    return this.closestBeacon;
  }

  public int getCoverageDist() {
    return this.beaconDistance;
  }

  // Returns the points in a row that this sensor covers
  public HashSet<Point> findPointsInRow(int row) {
    int rowDist = Math.abs(row - (int) this.sensorLoc.getY()); // This is correctly calculated
    // Total number of points in the row
    // Check this closely for errors!
    int numPoints = 0;
    if (this.beaconDistance - rowDist >= 0) {
      numPoints = 2 * (this.beaconDistance - rowDist) + 1;
    }
    if (numPoints == 0) {
      return null;
    }
    // Initialize the arrayList
    HashSet<Point> pointList = new HashSet<>();
    for (int i = (-1*numPoints)/2; i <= numPoints/2; i++) {
      // Add the points to the arraylist and return them
      Point tempPoint = new Point(((int) this.sensorLoc.getX()) + i, row);
      pointList.add(tempPoint);
    }
    return pointList;
  }

  // Returns the points in a row that this sensor covers
  public HashSet<Point> findPointsInRow(int row, int minX, int maxX) {
    int rowDist = Math.abs(row - (int) this.sensorLoc.getY()); // This is correctly calculated
    // Total number of points in the row
    // Check this closely for errors!
    int numPoints = 0;
    if (this.beaconDistance - rowDist >= 0) {
      numPoints = 2 * (this.beaconDistance - rowDist) + 1;
    }
    if (numPoints == 0) {
      return null;
    }
    // Initialize the arrayList
    HashSet<Point> pointList = new HashSet<>();
    for (int i = (-1*numPoints)/2; i <= numPoints/2; i++) {
      // Add the points to the arraylist and return them
      int xLoc = ((int) this.sensorLoc.getX()) + i;
      if (xLoc <= maxX && xLoc >= minX) {
        Point tempPoint = new Point(xLoc, row);
        pointList.add(tempPoint);
      }
    }
    return pointList;
  }
}
