public class WallTile extends MapTile {
  // Instance variables
  public char tileChar;
  public int tileX;
  public int tileY;

  WallTile(char drawCharacter, int tileX, int tileY) {
    this.tileChar = drawCharacter;
    this.tileX = tileX;
    this.tileY = tileY;
  }

  public char getChar() {
    return this.tileChar;
  }

  public int getX() {
    return this.tileX;
  }

  public int getY() {
    return this.tileY;
  }
}
