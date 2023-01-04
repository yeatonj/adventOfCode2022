public class OpenTile extends MapTile {
  // Instance variables
  private char tileChar;
  private MapTile[] adjTiles; // R, D, L, U
  private int tileX;
  private int tileY;
  private int cubeZone;

  // Constructor
  OpenTile(char drawCharacter, int tileX, int tileY) {
    this.tileChar = drawCharacter;
    this.tileX = tileX;
    this.tileY = tileY;
    this.adjTiles = new MapTile[4];
    this.cubeZone = 0;
  }
  OpenTile(char drawCharacter, int tileX, int tileY, int cubeZone) {
    this(drawCharacter, tileX, tileY);
    this.cubeZone = cubeZone;
  }

  // Getter for the character (for drawing the map)
  public char getChar() {
    return this.tileChar;
  }

  public int getX() {
    return this.tileX;
  }

  public int getY() {
    return this.tileY;
  }

  public int getcubeZone() {
    return this.cubeZone;
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
    if (rightTile instanceof OpenTile) {
      this.adjTiles[0] = rightTile;
    } else {
      this.adjTiles[0] = null;
    }
  }

  public void setDownTile(MapTile downTile) {
    if (downTile instanceof OpenTile) {
      this.adjTiles[1] = downTile;
    } else {
      this.adjTiles[1] = null;
    }
  }

  public void setLeftTile(MapTile leftTile) {
    if (leftTile instanceof OpenTile) {
      this.adjTiles[2] = leftTile;
    } else {
      this.adjTiles[2] = null;
    }
  }

  public void setUpTile(MapTile upTile) {
    if (upTile instanceof OpenTile) {
      this.adjTiles[3] = upTile;
    } else {
      this.adjTiles[3] = null;
    }
  }

  public void setDrawChar(char newChar) {
    this.tileChar = newChar;
  }
}
