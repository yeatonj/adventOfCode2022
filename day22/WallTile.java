public class WallTile extends MapTile {
  // Instance variables
  private char tileChar;
  private int tileX;
  private int tileY;
  private int cubeZone;

  WallTile(char drawCharacter, int tileX, int tileY) {
    this.tileChar = drawCharacter;
    this.tileX = tileX;
    this.tileY = tileY;
    this.cubeZone = 0;
  }

  WallTile(char drawCharacter, int tileX, int tileY, int cubeZone) {
    this(drawCharacter, tileX, tileY);
    this.cubeZone = cubeZone;
  }

  public char getChar() {
    return this.tileChar;
  }

  public int getX() {
    return this.tileX;
  }

  public int getcubeZone() {
    return this.cubeZone;
  }

  public int getY() {
    return this.tileY;
  }

  public void setUpTile(MapTile upTile) {
  };

  public void setDownTile(MapTile downTile) {
  };

  public void setLeftTile(MapTile leftTile) {
  };

  public void setRightTile(MapTile rightTile) {
  };

  public void setDrawChar(char newChar) {
    this.tileChar = newChar;
  }
}
