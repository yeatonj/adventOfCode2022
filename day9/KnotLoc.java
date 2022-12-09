// Class to represent a 2D coordinate location of a knot

public class KnotLoc {
  // Instance variables
  private int xLoc;
  private int yLoc;

  // Constructor
  public KnotLoc(int xCoord, int yCoord) {
    this.xLoc = xCoord;
    this.yLoc = yCoord;
  }

  // Equality method
  public boolean equals(KnotLoc knot2) {
    return ((this.xLoc == knot2.getX()) && (this.yLoc == knot2.getY()));
  }

  // Subtraction method
  // Subtracts knot loc passed in from current instance's location
  public KnotLoc minus(KnotLoc knot2) {
    return new KnotLoc(this.xLoc - knot2.getX(), this.yLoc - knot2.getY());
  }

  // Getters
  public int getX() {
    return this.xLoc;
  }

  public int getY() {
    return this.yLoc;
  }


}
