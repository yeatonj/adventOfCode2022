// Class that keeps track of the distance to a node by traveling a particular
// edge. These are comparable to each other

import java.util.Comparator;

class DistToNode implements Comparator<DistToNode> {
  // Instance Variables
  private int distToDest;
  private HeightMapEdge travelEdge;

  // Constructor
  public DistToNode(int distToDest, HeightMapEdge travelEdge) {
    this.distToDest = distToDest;
    this.travelEdge = travelEdge;
  }

  // Methods

  // Getter for destination
  public HeightMapNode getDest() {
    return this.travelEdge.getDest();
  }

  // Comparator
  public int compare(DistToNode a, DistToNode b) {
    return a.distToDest - b.distToDest;
  }
}
