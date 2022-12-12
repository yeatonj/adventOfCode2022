// Class to represent a HeightMap made up of HeightMapNodes

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class HeightMap {
  // Instance Variables
  private int numNodes; // Total number of nodes in the map
  private HeightMapNode origin; // Starting point
  private HeightMapNode dest; // Where we are trying to go
  private int mapWidth; // Width of the map
  private int mapHeight; // Height of the map
  private HashMap<Integer, HeightMapNode> nodeNumMap; // makes nodes accessible by node number
  private HashMap<HeightMapNode, HashMap<String, HeightMapEdge>> nodeEdges; // maps nodes to their edges

  // Methods

  // Constructor for the map
  // The letter heights map should map the heights to integer values
  public HeightMap(HashMap<Character, Integer> letterHeights, String dataFile, Character sourceChar, Character destChar) throws FileNotFoundException{
    this.numNodes = 0;
    this.nodeNumMap = new HashMap<>();
    this.nodeEdges = new HashMap<>();

    // Open the data file
    File data = new File(dataFile);
    Scanner mapScanner = new Scanner(data);

    // Initialize row counter and string outside loop so we can get its length
    // after the loop
    int numRows = 0;
    String currRow = "";
    // Initialize a 2D array to temporarily store our nodes in
    ArrayList<ArrayList<HeightMapNode>> nodeArray = new ArrayList<>();
    // Initialize 2D arrays for the source and dest nodes
    int[] sourceNode = new int[] {0,0};
    int[] destNode = new int[] {0,0};

    // Iterate through the file, adding each node to the 2D array of nodes,
    // we will connect them later
    while(mapScanner.hasNextLine()) {
      // Move to the next line and create a list of nodes to add to the array
      currRow = mapScanner.nextLine();
      ArrayList<HeightMapNode> rowNodes = new ArrayList<>();

      for (int i = 0; i < currRow.length(); i++) {
        // Get the current character, find its height, and create a node
        char currChar = currRow.charAt(i);
        HeightMapNode newNode = new HeightMapNode(numRows, i, letterHeights.get(currChar));
        // Add the node to the map
        this.nodeNumMap.put(newNode.getNodeNum(), newNode);

        // Check if it matches source or dest, and update
        if (currChar == sourceChar) {
          this.origin = newNode;
        } else if (currChar == destChar) {
          this.dest = newNode;
        }

        // Add it to the next column in the arraylist of nodes in this row
        rowNodes.add(newNode);
        this.numNodes++;
      }
      // Add the row to the 2D array of nodes
      nodeArray.add(rowNodes);
      numRows++;
    }
    mapScanner.close();
    // Now, we have the appropriate size of the map
    this.mapWidth = currRow.length();
    this.mapHeight = numRows;

    // AT THIS POINT, WE HAVE ALL OF THE NODES CREATED AND IN A 2D ARRAY, BUT
    // NO CONNECTIONS BETWEEN THE NODES. NOW NEED TO MAKE THE EDGES

    // Finally, iterate through the nodes and make the connections
    for (int i = 0; i < this.mapHeight; i++) {
      for (int j = 0; j < this.mapWidth; j++) {
        HeightMapNode currNode = nodeArray.get(i).get(j);
        HashMap<String, HeightMapEdge> currNodeEdges = new HashMap<>();
        int currNodeHeight = currNode.getNodeHeight();
        // Set edges to null, we will connect if there is a node next to them
        HeightMapEdge northEdge = null;
        HeightMapEdge eastEdge = null;
        HeightMapEdge westEdge = null;
        HeightMapEdge southEdge = null;
        // If a North node exists, add an edge
        if (i > 0) {
          HeightMapNode northNode = nodeArray.get(i-1).get(j);
          int heightDiff = currNodeHeight - northNode.getNodeHeight();
          northEdge = new HeightMapEdge(heightDiff, currNode, northNode);
        }
        // If a East node exists, add an edge
        if (j < this.mapWidth - 1) {
          HeightMapNode eastNode = nodeArray.get(i).get(j+1);
          int heightDiff = currNodeHeight - eastNode.getNodeHeight();
          eastEdge = new HeightMapEdge(heightDiff, currNode, eastNode);
        }
        // If a West node exists, add an edge
        if (j > 0) {
          HeightMapNode westNode = nodeArray.get(i).get(j-1);
          int heightDiff = currNodeHeight - westNode.getNodeHeight();
          westEdge = new HeightMapEdge(heightDiff, currNode, westNode);
        }
        // If a South node exists, add an edge
        if (i < this.mapHeight - 1) {
          HeightMapNode southNode = nodeArray.get(i+1).get(j);
          int heightDiff = currNodeHeight - southNode.getNodeHeight();
          southEdge = new HeightMapEdge(heightDiff, currNode, southNode);
        }
        // Add each edge to the node's hashmap, then add that hashmap to the
        // hashmap for the map as a whole
        currNodeEdges.put("North", northEdge);
        currNodeEdges.put("East", eastEdge);
        currNodeEdges.put("West", westEdge);
        currNodeEdges.put("South", southEdge);
        nodeEdges.put(currNode, currNodeEdges);
      }
    }
    // Constructor complete
  }

  // Getter for the origin
  public HeightMapNode getOrigin() {
    return this.origin;
  }

  // Getter for the destination
  public HeightMapNode getDest() {
    return this.dest;
  }

  // Getter for the number of nodes
  public int getNumNodes() {
    return this.numNodes;
  }

  // Getter for the width of the map
  public int getMapWidth() {
    return this.mapWidth;
  }

  // Getter for the height of the map
  public int getMapHeight() {
    return this.mapHeight;
  }

  // Getter for the node number map
  public HashMap<Integer, HeightMapNode> getNodeNumMap() {
    return this.nodeNumMap;
  }

  // Getter for node edges hashMap
  public HashMap<HeightMapNode, HashMap<String, HeightMapEdge>> getNodeEdges() {
    return nodeEdges;
  }
}
