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
    ArrayList<ArrayList<ArrayList<String>>> prevMapArray = prevMap.getMapArray();
    // Create the new empty map
    for (int y = 0; y < prevMapArray.size(); y++) {
      ArrayList<ArrayList<String>> newRow = new ArrayList<>();
      for (int x = 0; x < prevMapArray.get(y).size(); x++) {
        newRow.add(new ArrayList<String>());
      }
      this.mapArray.add(newRow);
    }
    // Now, populate the new array by iterating back through it
    for (int y = 0; y < this.mapArray.size(); y++) {
      ArrayList<ArrayList<String>> newRow = new ArrayList<>();
      for (int x = 0; x < this.mapArray.get(y).size(); x++) {
        // Case - we are at a border (# or entry/exit)
        if (x == 0 ||
        y == 0 ||
        x == this.mapArray.get(y).size() - 1 ||
        y == this.mapArray.size() - 1) {
          if (prevMapArray.get(y).get(x).size() == 1) {
            this.mapArray.get(y).get(x).add(prevMapArray.get(y).get(x).get(0));
          }
        }
        // Case, we are in the map itself
        else {
          // Find coordinat
          int leftCoord = x - 1;
          if (leftCoord == 0) {
            leftCoord += this.mapArray.get(y).size() - 2;
          }
          int rightCoord = x + 1;
          if (rightCoord == this.mapArray.get(y).size() - 1) {
            rightCoord = 1;
          }
          int downCoord = y + 1;
          if (downCoord == this.mapArray.size() - 1) {
            downCoord = 1;
          }
          int upCoord = y - 1;
          if (upCoord == 0) {
            upCoord = this.mapArray.size() - 2;
          }
          // Check left for right facing wind
          for (int i =  0; i < prevMapArray.get(y).get(leftCoord).size(); i++) {
            if (prevMapArray.get(y).get(leftCoord).get(i).equals(">")) {
              this.mapArray.get(y).get(x).add(">");
            }
          }
          // Check right for left facing wind
          for (int i =  0; i < prevMapArray.get(y).get(rightCoord).size(); i++) {
            if (prevMapArray.get(y).get(rightCoord).get(i).equals("<")) {
              this.mapArray.get(y).get(x).add("<");
            }
          }
          // Check up for down facing wind
          for (int i =  0; i < prevMapArray.get(upCoord).get(x).size(); i++) {
            if (prevMapArray.get(upCoord).get(x).get(i).equals("v")) {
              this.mapArray.get(y).get(x).add("v");
            }
          }
          // Check down for up facing wind
          for (int i =  0; i < prevMapArray.get(downCoord).get(x).size(); i++) {
            if (prevMapArray.get(downCoord).get(x).get(i).equals("^")) {
              this.mapArray.get(y).get(x).add("^");
            }
          }
        }
      }
    }

  }

  // Getters
  public int getMapTime() {
    return this.mapTime;
  }

  public ArrayList<ArrayList<ArrayList<String>>> getMapArray() {
    return this.mapArray;
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
