// Program to model falling sand into a reservoir
// Written for Advent of Code 2022
// Written by: Joshua Yeaton
// Written on: 12/14/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class RegolithReservoir {
  public static void main(String[] args) throws FileNotFoundException {
    // Read in the data, keeping track of the minimum and maximum x and y values
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day14/rock_data.txt";
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day14/rock_data_test.txt";
    File data = new File(filePath);
    Scanner dataScanner = new Scanner(data);

    // Create an array to store the x and y values, as well as max/min values
    ArrayList<ArrayList<int[]>> rockLineCoords = new ArrayList<>();
    Integer xMax, xMin, yMax, yMin;
    xMax = xMin = yMax = yMin = null;

    while(dataScanner.hasNextLine()) {
      String currLine = dataScanner.nextLine();
      // Split the line into coordinates and create a new arraylist to hold them
      String[] stringCoord = currLine.split(" -> ");
      ArrayList<int[]> lineCoords = new ArrayList<>();
      // Iterate through each coordinate, converting to an int[] and adding to line
      for (String coords : stringCoord) {
        // Reassign the coordinates to integers
        String[] rawCoords = coords.split(",");
        int[] intCoords = new int[2];
        int xCoord = Integer.parseInt(rawCoords[0]);
        int yCoord = Integer.parseInt(rawCoords[1]);
        intCoords[0] = xCoord;
        intCoords[1] = yCoord;
        // Now, add the coordinates to the arraylist
        lineCoords.add(intCoords);

        // Finally, update the mins/maxes, if necessary
        if (xMax == null || xCoord > xMax) {
          xMax = xCoord;
        }
        if (xMin == null || xCoord < xMin) {
          xMin = xCoord;
        }
        if (yMax == null || yCoord > yMax) {
          yMax = yCoord;
        }
        if (yMin == null || yCoord < yMin) {
          yMin = yCoord;
        }
      }
      // And then add each line to the full arraylist
      rockLineCoords.add(lineCoords);
    }
    dataScanner.close();
    System.out.println(yMin);
    System.out.println(yMax);
    System.out.println(xMin);
    System.out.println(xMax);

    // Create cave model here!
    CaveModel elfCave = new CaveModel(yMax, xMin, xMax);
    elfCave.printCaveMap();


  }
}
