// Program to navigate from source "S" to destination: "E" in shortest possible
// path

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;

class HillClimbing {
  public static void main(String[] args) throws FileNotFoundException {
    // Set chars for destination and source, as well as filepath
    char sourceChar = 'S';
    char destChar = 'E';
    int sourceValue = 0;
    int destValue = 25;
    String filepath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day12/heightmap_data.txt";
    // String filepath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day12/heightmap_test_data.txt";

    // Create the hashmap for mapping char values to heights
    HashMap<Character, Integer> heightValues = new HashMap<>();
    heightValues.put(sourceChar, sourceValue);
    heightValues.put(destChar, destValue);
    for (int i = 0; i < 26; i++) {
      heightValues.put((char)(i+97), i);
    }
    HeightMap elfMap = new HeightMap(heightValues, filepath, sourceChar, destChar);
    HashMap<Integer, HeightMapNode> elfMapNodes = elfMap.getNodeNumMap();

  }

  // Implementation of dijkstra's to find the shortest path to every point from a source node
  HashMap<HeightMapNode, Integer> findShortestPaths(HeightMap mapIn, HeightMapNode sourceNode, int maxHeightDiff) {
    // This hashmap is the shortest path to each node
    HashMap<HeightMapNode, Integer> visitedNodes = new HashMap<>();
    // This hashmap allows us to access nodes by their node number
    HashMap<Integer, HeightMapNode> mapNodeNums = mapIn.getNodeNumMap();
    // Set the origin node to 0
    int currPathLength = 0;
    visitedNodes.put(sourceNode, currPathLength);

    // Add the possible nodes from the source node to the priority queue


    return visitedNodes;
  }
}
