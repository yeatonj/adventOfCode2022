// Class to hold the map, made up of different map tiles

import java.util.ArrayList;

class MapGraph {
  // This holds the map itself
  private ArrayList<ArrayList<MapTile>> mapCoords;
  // This holds the characters (players) in the map
  private ArrayList<MapCharacter> mapCharacters;
  // Starting x and y position for characters
  private int startingX;
  private int startingY;
  private int sizeX;
  private int sizeY;
  private char emptyChar;

  // Constructor
  MapGraph(String mapData, char wallChar, char openChar, char emptyChar) {
    this.mapCharacters = new ArrayList<>();
    this.mapCoords = new ArrayList<>();
    this.startingY = 1; // indexed to 1
    this.emptyChar = emptyChar;
    String[] splitData = mapData.split("\n");
    // Find the longest line
    this.sizeX = 0;
    for (String dataLine : splitData) {
      if (dataLine.length() > this.sizeX) {
        this.sizeX = dataLine.length();
      }
    }

    // For each line in the map
    for (String dataLine : splitData) {
      // Create an array with the corresponding tiles
      ArrayList<MapTile> lineTiles = new ArrayList<>();
      for (int i = 0; i < sizeX; i++) {
        try {
          char currChar = dataLine.charAt(i);
          // Add the corresponding tile (null, if empty);
          if (currChar == wallChar) {
            lineTiles.add(new WallTile(currChar));
          } else if (currChar == openChar) {
            lineTiles.add(new OpenTile(currChar));
          } else {
            lineTiles.add(null);
          }
        } catch (Exception e) {
          lineTiles.add(null);
        }
      }
      this.mapCoords.add(lineTiles);
    }
    // Set the map size and starting x coordinate (first actual point on top line)
    this.sizeY = this.mapCoords.size();
    for (int i = 0; i < this.sizeX; i++) {
      if (this.mapCoords.get(0).get(i) != null) {
        this.startingX = i + 1;
      }
    }

    // And, finally, make all the connections between nodes
    for (int i = 0; i < this.sizeY; i++) {
      for (int j = 0; j < this.sizeX; j++) {
        // Check if current node is null or a wall, and if so, continue
        if (mapCoords.get(i).get(j) == null || mapCoords.get(i).get(j) instanceof WallTile) {
          continue;
        }
        OpenTile tempTile = (OpenTile) mapCoords.get(i).get(j);
        // Otherwise, make connections. First, find each neighboring node
        int upNodeCoord = i;
        int downNodeCoord = i;
        int leftNodeCoord = j;
        int rightNodeCoord = j;
        MapTile upTile = null;
        MapTile downTile = null;
        MapTile rightTile = null;
        MapTile leftTile = null;
        // Up node
        do {
          upNodeCoord -= 1;
          if (upNodeCoord < 0) {
            upNodeCoord += this.sizeY;
          }
          upTile = mapCoords.get(upNodeCoord).get(j);
        } while (upTile == null);
        // Down node
        do {
          downNodeCoord = (downNodeCoord + 1) % this.sizeY;
          downTile = mapCoords.get(downNodeCoord).get(j);
        } while (downTile == null);
        // Left node
        do {
          leftNodeCoord -= 1;
          if (leftNodeCoord < 0) {
            leftNodeCoord += this.sizeX;
          }
          leftTile = mapCoords.get(i).get(leftNodeCoord);
        } while (leftTile == null);
        // Right node
        do {
          rightNodeCoord = (rightNodeCoord + 1) % this.sizeX;
          rightTile = mapCoords.get(i).get(rightNodeCoord);
        } while (rightTile == null);

        // If the connecting nodes aren't walls, connect them
        if (upTile instanceof OpenTile) {
          tempTile.setUpTile(upTile);
        }
        if (downTile instanceof OpenTile) {
          tempTile.setDownTile(downTile);
        }
        if (leftTile instanceof OpenTile) {
          tempTile.setLeftTile(leftTile);
        }
        if (rightTile instanceof OpenTile) {
          tempTile.setRightTile(rightTile);
        }
      }
    }
  }


  // Other methods
  public void printMap() {
    for (int i = 0; i < this.sizeY; i++) {
      for (int j = 0; j < this.sizeX; j++) {
        if (mapCoords.get(i).get(j) != null) {
          System.out.print(mapCoords.get(i).get(j).getChar());
        } else {
          System.out.print(this.emptyChar);
        }
      }
      System.out.print("\n");
    }
  }
}
