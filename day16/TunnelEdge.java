// Class to represent a tunnel/edge in the volcano
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

public class TunnelEdge {
  // Class variables
  private static int numEdges = 0;

  // Instance Variables
  private int edgeWeight;
  private ValveNode origin;
  private ValveNode dest;

  // Constructor
  public TunnelEdge(int edgeWeight, ValveNode origin, ValveNode dest) {
    numEdges++;
    this.edgeWeight = edgeWeight;
    this.origin = origin;
    this.dest = dest;
  }

  // Getters
  public int getWeight() {
    return this.edgeWeight;
  }

  public ValveNode getOrigin() {
    return this.origin;
  }

  public ValveNode getDest() {
    return this.dest;
  }

  public static int getNumEdges() {
    return TunnelEdge.numEdges;
  }
}
