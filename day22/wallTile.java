public class wallTile extends mapTile {
  // Instance variables
  public char tileChar;

  wallTile(char drawCharacter) {
    this.tileChar = drawCharacter;
  }

  public char getChar() {
    return this.tileChar;
  }
}
