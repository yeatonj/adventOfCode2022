public class OpenTile extends MapTile {
  // Instance variables
  public char tileChar;
  public MapTile[] adjTiles; // R, D, L, U

  // Constructor
  OpenTile(char drawCharacter) {
    this.tileChar = drawCharacter;
    this.adjTiles = new MapTile[4];
  }

  // Getter for the character (for drawing the map)
  public char getChar() {
    return this.tileChar;
  }

  // Getters for adjacent tiles
  public MapTile getRightTile() {
    return this.adjTiles[0];
  }

  public MapTile getDownTile() {
    return this.adjTiles[1];
  }

  public MapTile getLeftTile() {
    return this.adjTiles[2];
  }

  public MapTile getUpTile() {
    return this.adjTiles[3];
  }

  // Setter for adjacent tiles
  public void setRightTile(MapTile rightTile) {
    this.adjTiles[0] = rightTile;
  }

  public void setDownTile(MapTile downTile) {
    this.adjTiles[1] = downTile;
  }

  public void setLeftTile(MapTile leftTile) {
    this.adjTiles[2] = leftTile;
  }

  public void setUpTile(MapTile upTile) {
    this.adjTiles[3] = upTile;
  }
}
