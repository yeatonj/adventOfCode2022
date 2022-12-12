// Program to navigate from source "S" to destination: "E" in shortest possible
// path

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.lang.Math;

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

    HashMap<HeightMapNode, Integer> distanceMap = new HashMap<>();

    // Calculate distance map with max allowable gap 1
    distanceMap = findShortestPaths(elfMap, elfMap.getOrigin(), 1);
    System.out.println("Shortest path from origin to destination is: " + distanceMap.get(elfMap.getDest()));


  }



  // Implementation of dijkstra's to find the shortest path to every point from a source node
  public static HashMap<HeightMapNode, Integer> findShortestPaths(HeightMap mapIn, HeightMapNode sourceNode, int maxHeightDiff) {
    // This hashmap is the shortest path to each node
    HashMap<HeightMapNode, Integer> visitedNodes = new HashMap<>();
    // This priority queue tracks the current distance to any node in the queue
    PriorityQueue<DistToNode> distQueue = new PriorityQueue<>();
    // Set the origin node to 0
    visitedNodes.put(sourceNode, 0);

    // Get the map of nodes and edges
    HashMap<HeightMapNode, HashMap<String, HeightMapEdge>> edgeMap = mapIn.getNodeEdges();
    HashMap<String, HeightMapEdge> currentNodeEdges = edgeMap.get(sourceNode);

    // Add any edges that are less than max height diff to the priority queue
    int edgeWeight;
    HeightMapEdge northEdge = currentNodeEdges.get("North");
    if (northEdge != null) {
      edgeWeight = northEdge.getWeight();
      if (edgeWeight <= maxHeightDiff) {
        distQueue.add(new DistToNode(1, northEdge));
      }
    }
    HeightMapEdge eastEdge = currentNodeEdges.get("East");
    if (eastEdge != null) {
      edgeWeight = eastEdge.getWeight();
      if (edgeWeight <= maxHeightDiff) {
        distQueue.add(new DistToNode(1, eastEdge));
      }
    }
    HeightMapEdge westEdge = currentNodeEdges.get("West");
    if (westEdge != null) {
      edgeWeight = westEdge.getWeight();
      if (edgeWeight <= maxHeightDiff) {
        distQueue.add(new DistToNode(1, westEdge));
      }
    }
    HeightMapEdge southEdge = currentNodeEdges.get("South");
    if (southEdge != null) {
      edgeWeight = southEdge.getWeight();
      if (edgeWeight <= maxHeightDiff) {
        distQueue.add(new DistToNode(1, southEdge));
      }
    }

    // While there are still items in the queue and we haven't completed the set
    while(!distQueue.isEmpty() && visitedNodes.size() < mapIn.getNumNodes()) {
      DistToNode currDist = distQueue.poll();
      HeightMapNode currDest = currDist.getDest();
      // If it's already in the map, skip it (go straight to next loop iteration)
      if (!visitedNodes.containsKey(currDest)) {
        // Otherwise, this is the shortest path to that node - add it!
        int distToNode = currDist.getDist();
        // System.out.println("Adding a node with distance " + distToNode);
        visitedNodes.put(currDest, distToNode);

        // Then, add all of its paths, if they meet conditions
        currentNodeEdges = edgeMap.get(currDest);
        northEdge = currentNodeEdges.get("North");
        if (northEdge != null) {
          edgeWeight = northEdge.getWeight();
          if (edgeWeight <= maxHeightDiff) {
            distQueue.add(new DistToNode(distToNode + 1, northEdge));
          }
        }
        eastEdge = currentNodeEdges.get("East");
        if (eastEdge != null) {
          edgeWeight = eastEdge.getWeight();
          if (edgeWeight <= maxHeightDiff) {
            distQueue.add(new DistToNode(distToNode + 1, eastEdge));
          }
        }
        westEdge = currentNodeEdges.get("West");
        if (westEdge != null) {
          edgeWeight = westEdge.getWeight();
          if (edgeWeight <= maxHeightDiff) {
            distQueue.add(new DistToNode(distToNode + 1, westEdge));
          }
        }
        southEdge = currentNodeEdges.get("South");
        if (southEdge != null) {
          edgeWeight = southEdge.getWeight();
          if (edgeWeight <= maxHeightDiff) {
            distQueue.add(new DistToNode(distToNode + 1, southEdge));
          }
        }
      }
    }

    return visitedNodes;
  }
}
