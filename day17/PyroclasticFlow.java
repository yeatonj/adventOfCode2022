// Program for modeling falling rocks
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022

public class PyroclasticFlow {
  public static void main(String[] args) {
    String jetDirec = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    int chamberWidth = 7;
    char floorPattern = '-';
    char wallPattern = '|';
    char emptyPattern = '.';

    Chamber flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern);
    flowChamber.drawChamber();
    flowChamber.dropShape();
    flowChamber.drawChamber();
  }
}
