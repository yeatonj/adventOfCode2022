// Class that keeps track of the distance to a node by traveling a particular
// edge. These are comparable to each other

class DistToNode implements Comparable<DistToNode> {
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

  public int getDist() {
    return this.distToDest;
  }

  // Comparator
  public int compareTo(DistToNode b) {
    // System.out.println("Comparing this: " + this.distToDest + " to: " + b.getDist());
    // System.out.println("Returning: " + (this.distToDest - b.getDist()));
    return this.distToDest - b.getDist();
  }
}
