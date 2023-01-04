// Class to represent a character on a map

public class CubeMapCharacter {
  // Instance variables
  private int direction; // 0->R, 1->D, 2->L, 3->U
  private CubeMapGraph currentMap;


  // Constructor
  CubeMapCharacter(int direction) {
    if (direction > 3 || direction < 0) {
      System.out.println("Direction invalid, direction set to R");
      this.direction = 0;
    } else {
      this.direction = direction;
    }

    // Set the current map to null
    this.currentMap = null;
  }

  // Getters
  // Getter for map
  public CubeMapGraph getCurrentMap() {
    return this.currentMap;
  }

  // Getter for current direction (0->R, 1->D, 2->L, 3->U)
  public int getDirection() {
    return this.direction;
  }

  public char getDirectionChar() {
    if (this.direction == 0) {
      return '>';
    } else if (this.direction == 1) {
      return 'v';
    } else if (this.direction == 2) {
      return '<';
    } else {
      return '^';
    }
  }

  // Setters
  // Setter for map
  public void setMap(CubeMapGraph newMap) {
    this.currentMap = newMap;
  }

  // Functions to turn the character
  public void turnLeft() {
    this.direction = (this.direction - 1);
    if (this.direction < 0) {
      this.direction += 4;
    }
  }

  public void turnRight() {
    this.direction = (this.direction + 1) % 4;
  }

  // Function to move character in the current direction one space
  // Returns true if the move was completed successfully or false if the
  // character ran into an obstacle
  public boolean moveCharacter() {
    // Poll the map to determine if the appropriate square is open. If it is,
    // complete the move by updating the character position in the map. If not,
    // prevent the move in the map, then return false
    if (this.currentMap == null) {
      System.out.println("Can't move character, not on a map");
      return false;
    }
    char charDirec = 'r';
    if (this.direction == 1) {
      charDirec = 'd';
    } else if (this.direction == 2) {
      charDirec = 'l';
    } else if (this.direction == 3) {
      charDirec = 'u';
    }
    return this.currentMap.moveCharacter(this, charDirec);

    // Could also generate a "path" as the character leaves the space?
  }

  // Returns an [x,y] array with the location of the character
  public int[] charLocation() {
    return this.currentMap.characterLocation(this);
  }


}
