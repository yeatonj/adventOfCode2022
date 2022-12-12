// Class for a node of a heightmap

class HeightMapNode {
  // Class variables
  private static int totalNodes = 0;
  // Instance Variables
  private int nodeHeight;
  private int xCoord;
  private int yCoord;
  private int nodeNum;

  // Methods

  // Constructor, does not initialize destinations as they may not exist yet
  public HeightMapNode(int xCoord, int yCoord, int nodeHeight) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.nodeHeight = nodeHeight;
    HeightMapNode.totalNodes++;
    this.nodeNum = totalNodes;
  }

  // Getter for the x and y coordinates, returns as a vector
  public int[] getCoord() {
    return new int[] {this.xCoord, this.yCoord};
  }

  // Getter for the node number
  public int getNodeNum() {
    return this.nodeNum;
  }

  // Getter for this node's height
  public int getNodeHeight() {
    return this.nodeHeight;
  }
}
