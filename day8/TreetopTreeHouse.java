// Program to XXX
// Written by: Josh Yeaton
// Written on 12/8/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class TreetopTreeHouse {
  public static void main(String[] args) throws FileNotFoundException {
    final int MAXHEIGHT = 9;

    // Open the file
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day8/tree_map.txt";
    File treeFile = new File(filePath);
    Scanner treeScanner = new Scanner(treeFile);

    ArrayList<ArrayList<Integer>> forestHeights = new ArrayList<>();
    ArrayList<ArrayList<Integer>> visibleTrees = new ArrayList<>();

    // Build the array of tree heights from the file
    String treeRow = null;
    while(treeScanner.hasNextLine()) {
      treeRow = treeScanner.nextLine();
      ArrayList<Integer> rowHeights = new ArrayList<>();
      ArrayList<Integer> visibleHeights = new ArrayList<>();
      for (int i = 0; i < treeRow.length(); i ++) {
        rowHeights.add(Character.getNumericValue(treeRow.charAt(i)));
        visibleHeights.add(-1);
      }
      forestHeights.add(rowHeights);
      visibleTrees.add(visibleHeights);
    }
    treeScanner.close();
    checkVisible(forestHeights, visibleTrees);

    // Count the visible trees
    int visCount = 0;
    for (int i = 0; i < visibleTrees.size(); i++) {
      for (int j = 0; j < visibleTrees.get(0).size(); j++) {
        if (visibleTrees.get(i).get(j) >= 0) {
          visCount++;
        }
      }
    }
    System.out.println("Number of visible trees is: " + visCount);
    System.out.println(visibleTrees.get(5));

  }

  // Function to update the visible array by checking each of the directions
  // visTrees should be an array of 0's entering this function, of same dims
  // as treeHeights
  public static void checkVisible(ArrayList<ArrayList<Integer>> treeHeights,
                                  ArrayList<ArrayList<Integer>> visTrees) {
    checkLeft(treeHeights, visTrees);
    checkRight(treeHeights, visTrees);
    checkUp(treeHeights, visTrees);
    checkDown(treeHeights, visTrees);
  }

  private static void checkLeft(ArrayList<ArrayList<Integer>> treeHeights,
                                  ArrayList<ArrayList<Integer>> visTrees) {
    for (int i = 0; i < treeHeights.size(); i++) {
      int currHeight = -1;
      for (int j = 0; j < treeHeights.get(0).size(); j++) {
        // Break out of the loop if we hit a max height tree
        if (currHeight > 8) {
          break;
        } else if (treeHeights.get(i).get(j) > currHeight) {
          // Set the new current height
          currHeight = treeHeights.get(i).get(j);
          // and set that tree to visible
          visTrees.get(i).set(j,treeHeights.get(i).get(j));
        }
      }
    }
  }

  private static void checkRight(ArrayList<ArrayList<Integer>> treeHeights,
                                ArrayList<ArrayList<Integer>> visTrees) {
    for (int i = 0; i < treeHeights.size(); i++) {
      int currHeight = -1;
      for (int j = treeHeights.get(0).size() - 1; j >= 0; j--) {
        // Break out of the loop if we hit a max height tree
        if (currHeight > 8) {
          break;
        } else if (treeHeights.get(i).get(j) > currHeight) {
          // Set the new current height
          currHeight = treeHeights.get(i).get(j);
          // and set that tree to visible
          visTrees.get(i).set(j,treeHeights.get(i).get(j));
        }
      }
    }
  }

  private static void checkUp(ArrayList<ArrayList<Integer>> treeHeights,
                              ArrayList<ArrayList<Integer>> visTrees) {
    for (int j = 0; j < treeHeights.get(0).size(); j++) {
      int currHeight = -1;
      for (int i = treeHeights.size() - 1; i >= 0; i--) {
        // Break out of the loop if we hit a max height tree
        if (currHeight > 8) {
          break;
        } else if (treeHeights.get(i).get(j) > currHeight) {
          // Set the new current height
          currHeight = treeHeights.get(i).get(j);
          // and set that tree to visible
          visTrees.get(i).set(j,treeHeights.get(i).get(j));
        }
      }
    }
  }

  private static void checkDown(ArrayList<ArrayList<Integer>> treeHeights,
                                ArrayList<ArrayList<Integer>> visTrees) {
    for (int j = 0; j < treeHeights.get(0).size(); j++) {
      int currHeight = -1;
      for (int i = 0; i < treeHeights.size(); i++) {
        // Break out of the loop if we hit a max height tree
        if (currHeight > 8) {
          break;
        } else if (treeHeights.get(i).get(j) > currHeight) {
          // Set the new current height
          currHeight = treeHeights.get(i).get(j);
          // and set that tree to visible
          visTrees.get(i).set(j,treeHeights.get(i).get(j));
        }
      }
    }
  }

}
