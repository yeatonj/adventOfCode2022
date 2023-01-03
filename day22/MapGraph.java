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

  // Constructor
  MapGraph(String mapData, char wallChar, char openChar, char emptyChar) {
    this.mapCharacters = new ArrayList<>();
    this.mapCoords = new ArrayList<>();
    this.startingY = 1; // indexed to 1
    String[] splitData = mapData.split("\n");

    // For each line in the map
    for (String dataLine : splitData) {
      // Create an array with the corresponding tiles
      ArrayList<MapTile> lineTiles = new ArrayList<>();
      for (int i = 0; i < dataLine.length(); i++) {
        char currChar = dataLine.charAt(i);
        // Add the corresponding tile (null, if empty);
        if (currChar == wallChar) {
          lineTiles.add(new WallTile(currChar));
        } else if (currChar == openChar) {
          lineTiles.add(new OpenTile(currChar));
        } else {
          lineTiles.add(null);
        }
      }
      this.mapCoords.add(lineTiles);
    }
    // Set the map size and starting x coordinate (first actual point on top line)
    this.sizeY = this.mapCoords.size();
    this.sizeX = this.mapCoords.get(0).size();
    for (int i = 0; i < this.sizeX; i++) {
      if (this.mapCoords.get(0).get(i) != null) {
        this.startingX = i + 1;
      }
    }
  }


  // Other methods
  public void printMap() {
    for (int i = 0; i < this.sizeY; i++) {
      for (int j = 0; j < this.sizeX; j++) {
        System.out.print(mapCoords.get(i).get(j).getChar());
      }
      System.out.print("\n");
    }
  }
}
