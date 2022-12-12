// Class for a node of a heightmap

class HeightMapEdge {
  private static int numEdges = 0;
  // Instance Variables
  private int weight;
  private HeightMapNode source;
  private HeightMapNode dest;

  // Methods

  // Constructor, sets source, dest, and edge weight (height diff)
  public HeightMapEdge(int edgeWeight, HeightMapNode sourceNode, HeightMapNode destNode) {
    this.weight = edgeWeight;
    this.source = sourceNode;
    this.dest = destNode;
    this.numEdges++;
  }

  // Getters for all instance variables
  public int getWeight() {
    return this.weight;
  }

  public HeightMapNode getSource() {
    return this.source;
  }

  public HeightMapNode getDest() {
    return this.dest;
  }

  public static int getNumEdges() {
    return HeightMapEdge.numEdges;
  }


}
