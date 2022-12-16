// Class to represent the graph of valves in the volcano
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

import java.util.HashMap;
import java.util.ArrayList;

public class VolcanoGraph {
  // Instance Variables
  private HashMap<String, ValveNode> valveNameMap; // Access valves through their name
  private HashMap<String, ArrayList<TunnelEdge>> tunnelMap; // access lists of edges from a node name
  private int currentFlowRate; // Will be adjusted up when a valve is opened
  private HashMap<String, Boolean> valvesOpen; // Each valve is mapped to whether it is open (true) or closed (false)


  // Constructor
  public VolcanoGraph() {
    this.valveNameMap = new HashMap<>();
    this.tunnelMap = new HashMap<>();
    this.valvesOpen = new HashMap<>();
    this.currentFlowRate = 0;
  }


  // Getters
  public HashMap<String, ValveNode> getValves() {
    return this.valveNameMap;
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

  // Method to open a valve
  public void openValve(String valveName) {
    ValveNode currentValve = valveNameMap.get(valveName);
    boolean status = currentValve.openValve();
    valvesOpen.put(valveName, status);
    this.currentFlowRate += currentValve.getFlowRate();
  }

  // Method to close a valve
  public void closeValve(String valveName) {
    ValveNode currentValve = valveNameMap.get(valveName);
    boolean status = currentValve.closeValve();
    valvesOpen.put(valveName, status);
    this.currentFlowRate -= currentValve.getFlowRate();
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

    // And add the node to the list of possible origins for tunnels
    this.tunnelMap.put(valveName, new ArrayList<TunnelEdge>());

    // And add the valve to the valve map (based on its name) as closed (false)
    this.valvesOpen.put(valveName, false);
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
