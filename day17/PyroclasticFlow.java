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
    char rockPattern = '#';

    Chamber flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern, rockPattern);
    Shape dropShape = new ShapeOne(3, 4);
    flowChamber.drawChamber();
    flowChamber.dropShape(dropShape);
    flowChamber.addShape(dropShape);
    flowChamber.drawChamber();
  }
}
