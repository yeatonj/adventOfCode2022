public class WallTile extends MapTile {
  // Instance variables
  public char tileChar;

  WallTile(char drawCharacter) {
    this.tileChar = drawCharacter;
  }

  public char getChar() {
    return this.tileChar;
  }
}
