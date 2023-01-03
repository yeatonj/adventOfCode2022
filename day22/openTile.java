public class openTile extends mapTile {
  // Instance variables
  public char tileChar;
  public mapTile[] adjTiles; // R, D, L, U

  // Constructor
  openTile(char drawCharacter) {
    this.tileChar = drawCharacter;
    this.adjTiles = new mapTile[4];
  }

  // Getter for the character (for drawing the map)
  public char getChar() {
    return this.tileChar;
  }

  // Getters for adjacent tiles
  public mapTile getRightTile() {
    return this.adjTiles[0];
  }

  public mapTile getDownTile() {
    return this.adjTiles[1];
  }

  public mapTile getLeftTile() {
    return this.adjTiles[2];
  }

  public mapTile getUpTile() {
    return this.adjTiles[3];
  }

  // Setter for adjacent tiles
  public void setRightTile(mapTile rightTile) {
    this.adjTiles[0] = rightTile;
  }

  public void setDownTile(mapTile downTile) {
    this.adjTiles[1] = downTile;
  }

  public void setLeftTile(mapTile leftTile) {
    this.adjTiles[2] = leftTile;
  }

  public void setUpTile(mapTile upTile) {
    this.adjTiles[3] = upTile;
  }
}
