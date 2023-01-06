// Program for representing an expedition in a blizzard

import java.util.ArrayList;

public class ExpeditionLocation implements Comparable<ExpeditionLocation> {
  // instance variables
  private int currentTime;
  private BlizzardMap blizzardState;
  private BlizzardMap nextBlizzardState;
  private int xLoc;
  private int yLoc;
  private int shortestPossTime;

  // Constructor
  ExpeditionLocation(int currentTime, ArrayList<BlizzardMap> allBlizzards, int xLoc, int yLoc) {
    this.currentTime = currentTime;
    this.blizzardState = allBlizzards.get(currentTime);
    // Generate the next blizzard state, if one doesn't exist
    if (allBlizzards.size() == (currentTime + 1)) {
      allBlizzards.add(new BlizzardMap(allBlizzards.get(currentTime)));
    }
    this.nextBlizzardState = allBlizzards.get(currentTime + 1);
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    // Get shortest possible time here
    this.shortestPossTime = currentTime + blizzardState.shortestRemainingTime(this.xLoc, this.yLoc);
  }

  // Getters
  public int getShortestPossTime() {
    return this.shortestPossTime;
  }

  public int getCurrentTime() {
    return this.currentTime;
  }

  public int getXLoc() {
    return this.xLoc;
  }

  public int getYLoc() {
    return this.yLoc;
  }

  // Comparator to allow use of priority queue
  public int compareTo(ExpeditionLocation otherLocation) {
    return this.shortestPossTime - otherLocation.getShortestPossTime();
  }

  // Method to create possible next locations based on current location and blizzard state
  public ArrayList<ExpeditionLocation> getNextLocs(ArrayList<BlizzardMap> allBlizzards) {
    ArrayList<Integer> possNextLocs = this.nextBlizzardState.getValidTiles(this.xLoc, this.yLoc);
    ArrayList<ExpeditionLocation> tempList = new ArrayList<>();
    for (int i = 0; i < possNextLocs.size(); i += 2) {
      tempList.add(new ExpeditionLocation(this.currentTime + 1, allBlizzards, possNextLocs.get(i), possNextLocs.get(i+1)));
    }
    return tempList;
  }

  // Hash function

  public int hashCode() {
    int prime1 = 109;
    int prime2 = 821;
    return (this.currentTime * prime1 + this.xLoc)*prime2 + this.yLoc;
  }

  public String toString() {
    String returnString = "Expedition at t = " + this.currentTime + ", (" + this.xLoc + ", " + this.yLoc + ")";
    return returnString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof ExpeditionLocation)) {
      return false;
    }
    ExpeditionLocation otherExp = (ExpeditionLocation)o;
    boolean timeEqual = this.currentTime == otherExp.getCurrentTime();
    boolean xEqual = this.xLoc == otherExp.getXLoc();
    boolean yEqual = this.yLoc == otherExp.getYLoc();

    return (timeEqual && xEqual && yEqual);
  }

}
