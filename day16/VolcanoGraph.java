// Class to represent the graph of valves in the volcano
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class VolcanoGraph {
  // Instance Variables
  private HashMap<String, ValveNode> valveNameMap; // Access valves through their name
  private HashMap<String, ArrayList<TunnelEdge>> tunnelMap; // access lists of edges from a node name
  private int currentFlowRate; // Will be adjusted up when a valve is opened
  private HashMap<String, Boolean> valvesOpen; // Each valve is mapped to whether it is open (true) or closed (false)
  private ArrayList<String> valveList; // array of valves, sorted alphabetically
  private int numValves; // The number of values
  private int numValvesOpen;
  private int maxFlowRate;
  private ArrayList<ArrayList<Integer>> adjMatrix; // by alphabetical order


  // Constructor
  public VolcanoGraph() {
    this.valveNameMap = new HashMap<>();
    this.tunnelMap = new HashMap<>();
    this.valvesOpen = new HashMap<>();
    this.valveList = new ArrayList<>();
    this.adjMatrix = null;
    this.currentFlowRate = 0;
    this.numValvesOpen = 0;
    this.numValves = 0;
    this.maxFlowRate = 0;
  }


  // Getters
  public HashMap<String, ValveNode> getValves() {
    return this.valveNameMap;
  }

  public int getNumValves() {
    return this.numValves;
  }

  public HashMap<String, ArrayList<TunnelEdge>> getTunnels() {
    return this.tunnelMap;
  }

  public HashMap<String, Boolean> getValvesOpen() {
    return this.valvesOpen;
  }

  public int getCurrentFlowRate() {
    return this.currentFlowRate;
  }

  public boolean getValveStatus(String valveName) {
    return this.valvesOpen.get(valveName);
  }

  public String getValveStatusString() {
    String returnString = "";
    for (String valve : this.valveList) {
      returnString += (valve + valvesOpen.get(valve));
    }
    return returnString;
  }

  public boolean allValvesOpen() {
    return (this.currentFlowRate == this.maxFlowRate);
  }

  public int getMaxFlowRate() {
    return this.maxFlowRate;
  }

  public ArrayList<String> getValveList() {
    ArrayList<String> valveListCopy = new ArrayList<>();
    for (String valve : this.valveList) {
      valveListCopy.add(valve);
    }
    return valveListCopy;
  }

  // Returns a copy of the adjacency list
  public ArrayList<ArrayList<Integer>> getAdjMatrix() {
    ArrayList<ArrayList<Integer>> copyAdjMatrix = new ArrayList<>();
    for (int i = 0; i < this.numValves; i++) {
      ArrayList<Integer> subLine = new ArrayList<>();
      for (int j = 0; j < this.numValves; j++) {
        subLine.add(this.adjMatrix.get(i).get(j));
      }
      copyAdjMatrix.add(subLine);
    }
    return copyAdjMatrix;
  }

  //Non-Getter Methods

  // Method to generate the adjacency matrix
  public void genAdjMatrix() {
    this.adjMatrix = new ArrayList<>();
    for (int i = 0; i < this.valveList.size(); i++) {
      String sourceNode = this.valveList.get(i);
      // Generate the row for this source node in the adj matrix
      this.adjMatrix.add(new ArrayList<Integer>());
      // Find the tunnels leaving this node
      ArrayList<TunnelEdge> sourceTunnels = tunnelMap.get(sourceNode);
      // Generate a set of destinations from this node
      HashMap<String, Integer> destMap = new HashMap<>();
      for (TunnelEdge tunnel : sourceTunnels) {
        destMap.put(tunnel.getDest().getValveName(), tunnel.getWeight());
      }
      // And, iterate through the possible destinations, updating if it's in the actual dests
      for (int j = 0; j < this.valveList.size(); j++) {
        String destNode = this.valveList.get(j);
        this.adjMatrix.get(i).add(destMap.getOrDefault(destNode, null));
      }
    }
  }

  // Method to open a valve
  public void openValve(String valveName) {
    ValveNode currentValve = valveNameMap.get(valveName);
    boolean status = currentValve.openValve();
    valvesOpen.put(valveName, status);
    this.currentFlowRate += currentValve.getFlowRate();
    this.numValvesOpen++;
  }

  // Method to close a valve
  public void closeValve(String valveName) {
    ValveNode currentValve = valveNameMap.get(valveName);
    boolean status = currentValve.closeValve();
    valvesOpen.put(valveName, status);
    this.currentFlowRate -= currentValve.getFlowRate();
    this.numValvesOpen--;
  }


  // Method to add a valve based on a name and flow rate
  public void addValve(String valveName, int flowRate) {
    // Ensure the valve isn't already in the map
    if (this.valveNameMap != null && this.valveNameMap.containsKey(valveName)) {
      System.out.println("Cannot add duplicate node to map...");
      return;
    }
    // Add a new valve to the map
    ValveNode newValve = new ValveNode(valveName, flowRate);
    this.valveNameMap.put(valveName, newValve);
    this.maxFlowRate += flowRate;

    // And add the node to the list of possible origins for tunnels
    this.tunnelMap.put(valveName, new ArrayList<TunnelEdge>());

    // And add the valve to the valve map (based on its name) as closed (false)
    this.valvesOpen.put(valveName, false);
    this.valveList.add(valveName);
    Collections.sort(this.valveList);

    this.numValves++;
  }

  // Method to add a tunnel based on an origin, destination, and length
  public void addTunnel(String origin, String destination, int length) {
    // Create a new edge/tunnel
    ValveNode nodeOrig = this.valveNameMap.get(origin);
    ValveNode nodeDest = this.valveNameMap.get(destination);
    TunnelEdge newTunnel = new TunnelEdge(length, nodeOrig, nodeDest);

    // Add the edge to the arraylist in the tunnelMap
    this.tunnelMap.get(origin).add(newTunnel);
  }

  // Method to print the current state of the graph
  public String toString() {
    String returnString = "";
    returnString += ("Current flow rate: " + this.currentFlowRate + "\n");
    returnString += "Current Nodes in graph:\n";
    for (HashMap.Entry<String, ValveNode> mapEl : valveNameMap.entrySet()) {
      String openString = "Current Status: Closed";
      if (mapEl.getValue().getValveStatus()) {
        openString = "Current Status: Open";
      }
      returnString += (mapEl.getKey() + " " + mapEl.getValue().getFlowRate() + ", " + openString + "\n");
    }
    returnString += "Current Tunnels in graph:\n";
    for (HashMap.Entry<String, ArrayList<TunnelEdge>> mapEl : tunnelMap.entrySet()) {
      returnString += ("From Valve " + mapEl.getKey() + ":\n");
      for (TunnelEdge tunnel : mapEl.getValue()) {
        returnString += ("To Valve " + tunnel.getDest().getValveName() + ", length " + tunnel.getWeight() + "||\t");
      }
      returnString += "\n";
    }
    return returnString;
  }
}
