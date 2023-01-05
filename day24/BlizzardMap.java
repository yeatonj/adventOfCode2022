// Program for generating a blizzard map at a specific time

import java.util.ArrayList;

public class BlizzardMap {
  // Instance variables
  // Below variable is the current time
  private int mapTime;
  // Each coordinate on the map is an ArrayList of characters
  // To get the list of objects at a specific location:
  // mapArray.get(y).get(x)
  // If it's empty, nothing is there, otherwise there is a blizzard there
  private ArrayList<ArrayList<ArrayList<String>>> mapArray;

  // Makes a new blizzardmap
  BlizzardMap(String blizzardData) {
    this.mapTime = 0;
    this.mapArray = new ArrayList<>();
    String[] splitBlizzard = blizzardData.split("\n");
    for (int y = 0; y < splitBlizzard.length; y++) {
      ArrayList<ArrayList<String>> arrayLine = new ArrayList<>();
      for (int x = 0; x < splitBlizzard[y].length(); x++) {
        ArrayList<String> entry = new ArrayList<>();
        if (splitBlizzard[y].charAt(x) != '.') {
          entry.add(Character.toString(splitBlizzard[y].charAt(x)));
        }
        arrayLine.add(entry);
      }
      this.mapArray.add(arrayLine);
    }
  }

  // Makes a new map one timestep further into the future
  BlizzardMap(BlizzardMap prevMap) {
    this.mapTime = prevMap.getMapTime() + 1;
    this.mapArray = new ArrayList<>();

  }

  // Getters
  public int getMapTime() {
    return this.mapTime;
  }



  // Methods
  // This method prints the map to the console without the character represented
  public void printMap() {
    for (int y = 0; y < mapArray.size(); y++) {
      for (int x = 0; x < mapArray.get(0).size(); x++) {
        if (mapArray.get(y).get(x).size() == 0) {
          System.out.print(".");
        } else if (mapArray.get(y).get(x).size() == 1) {
          System.out.print(mapArray.get(y).get(x).get(0));
        } else {
          System.out.print(mapArray.get(y).get(x).size());
        }
      }
      System.out.print("\n");
    }
  }

  // This method returns valide nodes to move to given a current x and y coord.
  // In this case, the #'s will be returned as pairs in the ArrayList, ie,
  /// .get(0) is the first x coord, .get(1) is the first y coord, .get(2) second x, etc
  public ArrayList<Integer> getValidTiles(int xCoord, int yCoord) {
    return new ArrayList<Integer>();
  }
}
