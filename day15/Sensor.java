// Class to define a sensor object
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/15/2022

import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;

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
  public ArrayList<Point> findPointsInRow(int row) {
    int rowDist = Math.abs(row - (int) this.sensorLoc.getY());
    // Total number of points in the row
    int numPoints = 2 * (beaconDistance - rowDist) + 1;
    // Initialize the arrayList
    ArrayList<Point> pointList = new ArrayList<>();
    for (int i = (-1*numPoints)/2; i <= numPoints/2; i++) {
      // Add the points to the arraylist and return them
      Point tempPoint = new Point(((int) this.sensorLoc.getX()) + i, row);
      pointList.add(tempPoint);
    }
    return pointList;
  }

  // Returns the points in a column that this sensor covers
  public ArrayList<Point> findPointsInCol(int col) {
    int colDist = Math.abs(col - (int) this.sensorLoc.getX());
    // Total number of points in the row
    int numPoints = 2 * (beaconDistance - colDist) + 1;
    // Initialize the arrayList
    ArrayList<Point> pointList = new ArrayList<>();
    for (int i = (-1*numPoints)/2; i <= numPoints/2; i++) {
      // Add the points to the arraylist and return them
      Point tempPoint = new Point(col, ((int) this.sensorLoc.getY()) + i);
      pointList.add(tempPoint);
    }
    return pointList;
  }
}
