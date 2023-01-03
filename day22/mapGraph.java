// Class to hold the map, made up of different map tiles

import java.util.ArrayList;

class mapGraph {
  // This holds the map itself
  private ArrayList<ArrayList<mapTile>> mapCoords;
  // This holds the characters (players) in the map
  private ArrayList<mapCharacter> mapCharacters;
  // Starting x and y position for characters
  private int startingX;
  private int startingY;

  // Constructor
  mapGraph(String mapData) {
    mapCharacters = new ArrayList<>();
    mapCoords = new ArrayList<>();
    startingY = 1; // indexed to 1
  }
}
