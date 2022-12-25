// Program for checking boiling boulders falling from the sky
// Written for AOC 2022 Day 18
// Written by Josh Yeaton
// Written on 12/23/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ArrayDeque;

class BoilingBoulders {
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day18/data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day18/sample_data.txt";

    File fileIn = new File(filePath);
    Scanner fileScanner = new Scanner(fileIn);

    ArrayList<ArrayList<Integer>> boulderList = new ArrayList<>();

    while (fileScanner.hasNextLine()) {
      ArrayList<Integer> boulder = new ArrayList<>();
      String boulderLine = fileScanner.nextLine();

      String[] numbers = boulderLine.split(",");
      int num1 = Integer.parseInt(numbers[0]);
      int num2 = Integer.parseInt(numbers[1]);
      int num3 = Integer.parseInt(numbers[2]);
      boulder.add(num1);
      boulder.add(num2);
      boulder.add(num3);
      boulderList.add(boulder);
    }
    fileScanner.close();

    // Counter for number of faces exposed
    int numFaces = 0;
    HashSet<String> cubeSet = new HashSet<>();
    for (ArrayList<Integer> cube : boulderList) {
      ArrayList<String> adjCubes = getAdjCubes(cube);
      String indCube = cube.get(0) + "," + cube.get(1) + "," + cube.get(2);
      cubeSet.add(indCube);
      numFaces += 6;
      for (String adjCube : adjCubes) {
        if (cubeSet.contains(adjCube)) {
          numFaces-=2;
        }
      }
    }
    System.out.println("Number of cubes in the boulder: " + cubeSet.size());
    System.out.println("Number of exposed faces is: " + numFaces);

    // Now, part 2
    // Find the min/max values for x, y, and z
    int minX = boulderList.get(0).get(0);
    int maxX = boulderList.get(0).get(0);
    int minY = boulderList.get(0).get(1);
    int maxY = boulderList.get(0).get(1);
    int minZ = boulderList.get(0).get(2);
    int maxZ = boulderList.get(0).get(2);
    for (ArrayList<Integer> bould : boulderList) {
      if (bould.get(0) < minX) {
        minX = bould.get(0);
      }
      if (bould.get(0) > maxX) {
        maxX = bould.get(0);
      }
      if (bould.get(1) < minY) {
        minY = bould.get(1);
      }
      if (bould.get(1) > maxY) {
        maxY = bould.get(1);
      }
      if (bould.get(2) < minZ) {
        minZ = bould.get(2);
      }
      if (bould.get(2) > maxZ) {
        maxZ = bould.get(2);
      }
    }

    // Now, iterate through all possible cubes and check if they are not in
    // the set, and if they aren't, whether the 6 cubes around them are in the
    // set
    int part2Faces = numFaces;
    HashSet<String> fullSet = new HashSet<>();
    for (int i = minX ; i < maxX + 1; i++) {
      for (int j = minY; j < maxY + 1; j++) {
        for (int k = minZ; k < maxZ + 1; k++) {
          String tempString = i + "," + j + "," + k;
          fullSet.add(tempString);
        }
      }
    }

    HashSet<String> emptyLocs = generateEmptySet(cubeSet, fullSet);
    // System.out.println(emptyLocs);
    ArrayList<HashSet<String>> voids =  findVoids(emptyLocs);
    // System.out.println(voids);
    int internalArea = 0;
    for (HashSet<String> space : voids) {
      System.out.println(space.size());
      if (space.size() == 1) {
        internalArea += 6;
      } else if (space.size() == 2) {
        internalArea += 10;
      } else if (space.size() < 3000){
        HashSet<String> tempCubes = new HashSet<>();
        for (String cube : space) {
          ArrayList<Integer> intCube =  convertCubeString(cube);
          ArrayList<String> adjCubes = getAdjCubes(intCube);
          tempCubes.add(cube);
          internalArea += 6;
          for (String adjCube : adjCubes) {
            if (tempCubes.contains(adjCube)) {
              internalArea-=2;
            }
          }
        }
      }
    }

    System.out.println("Number of internal faces is: " + internalArea);
    System.out.println("Number of outer exposed faces is: " + (part2Faces - internalArea));
    // Guesssed 3128, answer was too high

  }

  // Function that generates a list of adjacent cubes to a specific cube
  // location
  public static ArrayList<String> getAdjCubes(ArrayList<Integer> cube) {
    int num1 = cube.get(0);
    int num2 = cube.get(1);
    int num3 = cube.get(2);

    ArrayList<String> adjCubes = new ArrayList<>();
    String cube1 = (num1 + 1) + "," + num2 + "," + num3;
    String cube2 = (num1 - 1) + "," + num2 + "," + num3;
    String cube3 = num1 + "," + (num2 + 1) + "," + num3;
    String cube4 = num1 + "," + (num2 - 1) + "," + num3;
    String cube5 = num1 + "," + num2 + "," + (num3 + 1);
    String cube6 = num1 + "," + num2 + "," + (num3 - 1);
    adjCubes.add(cube1);
    adjCubes.add(cube2);
    adjCubes.add(cube3);
    adjCubes.add(cube4);
    adjCubes.add(cube5);
    adjCubes.add(cube6);
    return adjCubes;
  }

  // Function to convert a string to integers
  public static ArrayList<Integer> convertCubeString(String cube) {
    String[] numbers = cube.split(",");
    ArrayList<Integer> tempList = new ArrayList<>();
    int num1 = Integer.parseInt(numbers[0]);
    int num2 = Integer.parseInt(numbers[1]);
    int num3 = Integer.parseInt(numbers[2]);
    tempList.add(num1);
    tempList.add(num2);
    tempList.add(num3);
    return tempList;
  }

  // Function that checks for adjacent cubes to see if they are present
  public static boolean checkAdjCubes(HashSet<String> fullCubeSet, ArrayList<String> checkCubes) {
    for (String cube : checkCubes) {
      if (!fullCubeSet.contains(cube)) {
        return false;
      }
    }
    return true;
  }

  // Function that generates the locations not in a boulder set as part of a full
  // set of points
  public static HashSet<String> generateEmptySet(HashSet<String> boulders, HashSet<String> fullSet) {
    HashSet<String> emptyLocs = new HashSet<>();
    for (String loc : fullSet) {
      if (!boulders.contains(loc)) {
        emptyLocs.add(loc);
      }
    }
    return emptyLocs;
  }

  // Function that returns an array of sets of adjacent voids (ie, non-boulders)
  // in the data list
  public static ArrayList<HashSet<String>> findVoids(HashSet<String> emptyLocs) {
    HashSet<String> clonedSet = new HashSet<String>();
    clonedSet = (HashSet)emptyLocs.clone();
    ArrayList<HashSet<String>> tempVoids = new ArrayList<>();
    for (String cube : emptyLocs) {
      String tempCube = cube;
      ArrayDeque<String> connectedCubes = new ArrayDeque<>();
      HashSet<String> tempSet = new HashSet<>();
      connectedCubes.add(tempCube);
      while(!connectedCubes.isEmpty()) {
        tempCube = connectedCubes.poll();
        if (clonedSet.contains(tempCube)) {
          tempSet.add(tempCube);
          clonedSet.remove(tempCube);
          ArrayList<Integer> currCube = convertCubeString(tempCube);
          // Check for adjacent cubes
          ArrayList<String> adjCubes = getAdjCubes(currCube);
          for (String adjCube : adjCubes) {
            connectedCubes.add(adjCube);
          }
        }
      }
      if (!tempSet.isEmpty()) {
        tempVoids.add(tempSet);
      }
    }
    return tempVoids;
  }
}
