// Class to represent the graph of valves in the volcano
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

import java.util.HashMap;
import java.util.ArrayList;

public class VolcanoGraph {
  // Instance Variables
  HashMap<String, ValveNode> valveNameMap; // Access valves through their name
  HashMap<String, ArrayList<TunnelEdge>> tunnelMap; // access lists of edges from a node name
  int currentFlowRate; // Will be adjusted up when a valve is opened


  // Constructor
  public VolcanoGraph() {
    this.valveNameMap = new HashMap<>();
    this.tunnelMap = new HashMap<>();
    this.currentFlowRate = 0;
  }


  // Getters
  public HashMap<String, ValveNode> getValves() {
    return this.valveNameMap;
  }

  public HashMap<String, ArrayList<TunnelEdge>> getTunnels() {
    return this.tunnelMap;
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
}
