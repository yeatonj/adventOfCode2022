// Program for modeling falling rocks
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022

import java.util.HashMap;
import java.util.HashSet;

public class PyroclasticFlow {
  public static void main(String[] args) {
    // String jetDirec = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    String jetDirec = ">>>><>>><<<<>>><<<<>><<<<>>>><<<<>>>><>>><<>><>>><<<<>><<<>>><><<<<>>>><<>>>><<<<>>>><>><<>>>><<>><<<>>>><>>><<>><<>><>>><<<<>>><<>>><<>><<<>>><><<<>>>><<<>><<<<>>>><<>>>><>>><>><<<>>>><<>>><><<<<>><<<<>>><<<<>>><<<<>>><>>><<<>>><>>>><>>>><<<<>><<>>>><<>>><<<>>><>>><><<<<>>>><><>>>><<><<<><<<<><><<>><<<<>>><<>>><<<<>>>><<><<<<>><>><<><<>>><<<>><<<><<<<>><<><<<<>>>><>><<<>>><><<><>>><<<<><>><<>>><<>>>><<><<>><<>><<<<>>>><<<>>>><><<<<>>><<>>><><<<><<<><<<><<>>><<<>>><<<<>>>><<<<>>><>>><>>>><<<>>><<<<><>>><<><>><<>><<<<>>>><<<<>>><<>>>><<>><<<<>>><<<<>>>><><<<<>><<<>><<<><<<><<<<>>><<<<>>>><><<<>>>><><<<<>>><<<<>>>><<<>><>>><<<>><><<<>>>><<<><<<>><<<<>><<<<>>>><<<>><<<>><<<<><<<<>><<>><<<>>><<<<>>>><<<><><>><<<<>>>><<<<><<<<><<>>>><<<>><>>>><>>><<<<>>><<>>>><<<><<<>>><<>>>><<>><<<<>>>><<<>><<<><<>>>><<>>>><<<<><>>>><<<>>>><<>><<<<>><<<<>><<<<>>><>>><<<>>>><<>>><<<>><<<<>>>><>><>>><>>>><<<><<><<<>><<<>>><<<<>>><<<<>>>><<<><<<>>><><>>><<<<>>><<<>>><<<>>>><><<<<>><<>><<<<>>><>>><<<>>><>><>>><><<<<>><<>><<<><>>><<><<<>>><<><<>><<<<><>>>><>>>><<<<><>><<>><<<<><><<<>>><<>><<>><<<>><<<<><<>>><>>><><<<>>><<<<>>><<><<<<>>><<<>>>><<>><<<>><<<<>>>><<>><<>>><><>>><>><<<>><><<>>><>><<>><<><<><<<>>>><<<>>><<<<>><<<<>>>><>>>><>>><<<<>><<<>>>><<<>>><<>><>>><>>>><<<<>>>><<<>>>><<<<>><<>>>><>><<<>>><<<><<>>>><<<><<<><<>>><><<<><<<>>>><<>>>><<<<>>>><<<<><<<><<<<>>>><<<<>>><<<>>>><<<><>>><>><<<<>>>><<<><<<<>><<<<>>>><><<<<>><>>><>>>><<<<>><><><<<>><<>>><<<><<<<>><>>><<>>>><<<<>>><<<>><<<<>><<<<>>>><>>><<>>><<<<>><<<<><<>><<<>>>><<<<>>><<<>><>><<>>>><<<>>>><>><>><<<>>>><>>>><<<<>>>><>><><><<<>>>><<>>>><<<><<<><>>><<<><<<<><><<<>>>><<<<>>>><<<<><<><<>>><<>>><<><<<><<<>><><>>>><<<><<>><<>><<<<>>><><<<>>>><>>>><<<>>><>>>><<<>>><<>>>><<<<><><<<>>>><<<<>><><<>><<<<>>><<<<><<<><>>><>>><>>>><>>><<<<>>><<<<><<><<<<>>><<<<>>>><><<<<><<<>><<<>><<>>>><<<><<<>>><<<>>>><<<<>>><>>><<>>>><>><<<><<<>><<<<><<<<>>><<<><><<<>><<<<>><>><>>><<<>>><><<<<><<<>><<<<>>>><<<>>>><>><<>>><<<>>>><<<<>>>><<<<><<>>>><<<>><<<<>><<<>>><<>>>><><<<<>>>><><<>>>><<><<<<>>><>><<<><<>>><>><<>><<<<><<<<>><<><<<><<<<>>>><<<>>>><<<<>><<>>><>>>><><<>><>>><<><>>><<<<>>>><<<<>>><<<><<<<>>><<<><>>>><<<<>>>><<<<>><<>><<<>>><>>>><<>>><>><<>><><><<<<>>>><<><<<>>>><<<>>><>><<>>><<<<>>><>>>><<>><<<<>>><<<<>><><<>>>><><<<>><<<>>><<<<>>><<<>><>>>><>>><<<><<<<>>>><<<<>><<>>>><>>>><<>><>>>><<>>><<><<>><<<<>><<<<>>><>>>><<<<>>><<<><>>>><<<>>>><>>><>><<<<>>><<<<>>><<>>><>>><>>>><>>><<<><<<><<<>>><<<>>>><<<<><<<<>><<<>>>><<>>>><>>><<<><>>>><<>><<<>>>><>>>><<<><<<<><><<>><<>><>>><<>>><>>><<>>>><<<<>>>><<<<>>><<<>>>><<<>><<>>>><<>>><<<>>><<<<><<<<>>><<>>>><<>>><<<<>>><<<<>>><<<<>>>><<<>>><<<><<>><<>><<><>>><<<>>><<>>>><>>>><>><><<>><<>>><<>>><<<>>><<<<>><<>>><<<>>><<<>><<<<>>><<<<>>><<>>><<<>>>><<<><><<>>><>><<<>>><<<>><<<><<<>><>><<<<>>><<<<>>><>>><<<<><<>>>><<><<<><<<>><<>>><<<<>><><<<>>>><<<><<><>>><><<><<<>>>><<>>><<<<>><<<<>><<<>>>><<<<><>>>><<<<>><<<>>>><<>>>><<<<>>>><>><<<<><>><<<<>>>><<<<>><<<>>><<>>>><>><<<<>><>>>><<<>>>><<<<><<<<>>>><<>>><<>>><<<<>>><<<><><<>>><<<>>>><<<<><>>><<<>>>><<>>>><<<>><<<>>>><>>><<<<>><><<<<>><<<<>><<>><<<>>><<><<<><<>>><<><<>><><<><<>>><<<<>><<<>><<<<><<<>><<<<>>>><<<><>>><<>><<<<>>><<<>>>><<<<>>><<>>>><<<>>>><<<<>><<<>>>><<<>><<<>>><<>>>><>><><<>>><<<<>>><<<<>>><>>><<>>>><<<<>><<<<>>><<<><<<><<><<<>>><>>>><<<><<>>>><<<>>><>><<>><<<<>><><><<>>>><><>>>><<<<>>><<>>>><>>>><<>>><<<><<<>><<>>><><>><<<><<<><>><>>>><<<<>><<<>>>><<>>><>><<>><<<>><<<>>>><>><<<>>><<>>><<<>>><<<<><>><<>>>><<><<><><<>>><><<<<>><<>><<<>>>><<<>>>><<<<>>><<>>>><<<<><>>><>>><<>><<<<>>><<<<>><>>>><<<<>>><<<>><<<<>><<<<>>><<<<>>><<<>><>><>><<>>><<<<>><>><>>><<>>><<>><<>>><<<<>>>><<<>><<<>>><><<>>><<<<>>><>><><<<><<<<>>>><<<<>>>><><<<>>>><<<<>>><>><<>><>>>><<>>><<>><<<><<<>>><<>>>><>>><<>>>><<<>>>><<>><<>>><>>>><<>>><<>><<<<>><<<<><>>>><<<<>><<>><<<<>>>><<>>><<<<>>><><<>><<><<<>><<<<>><>>>><<<>><<<>>>><<><<<<>>>><<<>><<<><><<<>>>><<<>>><<>>><<<>><<<<>>>><<<>>>><<<><>>><<><<<<>>><<<>>>><<>><>>>><<<<><<>><<>>><>>>><<>>>><><>>>><<<><<<>>>><>>>><<>>>><<>>>><><<>><<><<>>><<<<><<<<>>><<>>><<<>>><>>><<>>>><>><<<>><>>><<>><<<>>>><>>><<<<>>>><<>>>><<<>><<<>><<<<>>><<>>><<>><<>><>>><>>>><<>><>>>><<<<>><>>><<<>><<<><<>>>><>>><<>>><<><<<<>>>><<<>>><<<<>>><>><<<<>>><<<<>><>><<<<><<>><<<<>>>><><<<<>>><<<>>><>>>><<>>>><<<<>>><<>>><<>>><>><<<><><>>>><<>><><<>>><>><>>><<>>>><<<<>>><<<>><<<>>><<<<>>><>>>><>>><>>><<>>>><<<<><<<<>>><<>>>><<<<>>><>>><>><<<<>>><<>><<<<><<<<>><<<<>><<<<>><<>>><<><<<>>>><<<<><<<<>>>><<<<>><<<<><<<<>>>><<><<<<>>><<<<>>>><>>>><<>><<<>>>><<<><<<<>><<<>><<<>><><>>><><<><<><<<><<<>>><>>>><<>>>><><>>>><<<>>>><<<>>>><<><<><<>><<<<><><>><>>><<>><<<<>><<>>><>>>><<>>>><>>>><<<<>>>><<<<>>><>>><<>><<<>>><<>><<<>><<<>>>><<><<><<<<>>>><<<>>>><<><<<<>>>><<<><<<>>>><<<><<>>><><<>>><<<<>><<>><<<>>><<<<>>><<<><>>><<<<>>><<<<>><>>>><<<<><<<<>>><>>>><<<<>><<><<<><<>><<<<>><<>>><<<<>>><<<>>><<<>>>><>>><>>>><<>>>><<>><<>>><<><<<>><<>><>>><>><<<<><>>>><<>><<<>><<><><<<>>><<<<><<>>>><<<>>>><<<>><<<>>><<>>><<<<>>><>>>><<<>>><<>><<<>><<<<><<<>><<<<>>><>>>><><<<>>>><<<<>>>><<>><>>>><<<<><<<<>>>><<<>>>><><>>><<>>><<<<>>>><<>>><<>>>><<>>>><<>>><<>><<<<>>>><<<<>>><<<>><><>><>>><<<>><<<>><<>>><<<<>>>><<<>>><><<<>>><<<<>>>><<>>><<<<>>>><<>>>><<>>>><<><>>><<<<>>><<<<>>>><<<<>>><<><>><>>>><>>>><<<<>>><<<<>><<<<><<<>>><<<>>><>>><>>>><<<>>><<<<>>>><<<<><<><<<<>><>>><<<>><<>><<>>>><<<<>><<>><<<>><<<>>><<<<><<><<<>>><<<><<<<>><<><<<<>><<<>>>><<<>>><<>><<>>>><<<><<>><>>>><<>>><<<<><<<<><<<<><<<<>>><<<<><<>>><>>>><<<<>>><<<>>>><>>>><<>><>><<>>><>><>>>><<<<>>><<>>><<<<>>>><>><<<<>>><>>>><<<>><<>>>><<<<>><<><>>>><<<>>>><<>><<>>>><<<<><<<<>>><<><<<><<><<>>><<<<>>><>>>><<<>>>><<><<<<><>>><<<<>><<>><<>>><<>>><<<>><<<<><>>><>>>><>>>><<<<><<<<>>>><>>><<<<>>><<<<>>><>><<>>><>><>><<<<>><>>>><<<>>><><<<<>>>><<<<><<<><<><<<<>>><<<<><<>>><<<<>>>><<<<>>>><<<><<>><>>>><<<>><<>>><<<<>>><<<<>>>><<>>><<<>>>><><<><<<<>><<<<>><<<>>><<>>>><><>>><<<<><<>>>><>>><<><>>><>>><<>><<<<><<<<><<><<<<>><<<>>>><<>>>><><<><<>>>><>><<<>>><<<>><>>>><<<<>>>><<>><<<><>>><<<><>><<<><><>><<><<><<>><<<<><<><>>><<<<><<<>>>><>><<<>>><<<>>><<<>>>><<<>>><<><>>>><<<<>>>><<<>><<<<>>>><>><<<><<<><<<><<><<><<<>>><<>>><>><>>>><>>>><<<<>>>><<>><<<<>>><<<>>>><<><<<<>>>><><<<>><<>><>><<<>><>>>><<><>>><<><<>>><<<>><>><<>>><<<<>>>><<<<>>><<<<><<<>>><<><<<>><>><<<<>>>><<><<><<<>>><<<<>>>><<><<<><<>><<<<><<<<>>><>><<<><<><<<<>>><<>>><<<<>><<<><>><<<><<<>>>><<<<><<<><<<><>><><<<><<<<><<>><<<<>>>><<<>>><<>>>><<>>><<><>>><<><<<><>>>><<><<><>>><>><<<<>>>><>>><<<><>>>><<>>>><>><<<<>><<<>>>><<>><<<>>>><<<>><<<<>>><<<>>><<<<>>>><><>><<<<>><<>>><<<<>><<<>><<>>>><>><>>><<<>>>><<><>>><<<><>>>><<<>><>>>><<>>>><<<<><<>>>><>><<<>><<>>><<<<>>><<<<>>>><>>><<<>><<>>><<<>><<<><<<<><<<><>><<<<>>>><<<<>>>><>>>><>>>><<>><<><<<<><<<><<>>><>>>><<<>><<<><<<>>>><<<>>><<<>>><<<>>><>>><<<>>><>>><<>><>><<<<>>><<<><<>>><<<>><<<<>><<>><<<><<<>>>><>><<>>><<>><>>>><<<<><<>>>><<<>>><>><<<>>><<<<>>>><<<<>><<>><<<<>>><<<<>>><<<<>>><<>>><<>>><><>>><<<<>><>><<>>><<<>><<<><<<>>>><<><<<>><<<<>><<<<>>><>><><<<<><<<<>>><>>><<>><<>>>><<<<>>><>><<>>><>>><>><<<>>>><<<><><<<<>><<<>>>><<>><<>>>><<>>><>>>><<><>><<<<>><<>>><>>><<><<<><<<<>>><><<><<<><<<<>>>><<<>>>><<<<><<<>><<<<><>>><<>>>><<<>>><>>>><<><>>><<<>><<>>><<<<>>>><>>>><<<<><<<<><>>>><<<<>>><>>><<>>><<<>><<><<<>>><>>><<>>><<<<>><<>>>><<<<><<<><<<<>><<<<>>><<<><><<>>><>>><>>><<<<><>><<<<>>><<>>><>>>><<>>>><<>>>><<<<>>>><<<<>>><<>><<<<>>>><><<><><<<<>>><<<<>>><<<<><>><<>>>><>><<<>>><<><<>><<<<>>>><>><<>><<<><<<><<<<>>>><<><<>>>><<<<>>><<<<>>><>>>><<>><>><<><>><<>>><<<>>>><<>>>><<>>><<<<>>><<>>><><<<<><<<><<>><<<<>>><>>><<<>>>><<>>>><<<<>><>><<<>><>>><<<>>><<<>>>><>>><>><<<<>>>><><<<>>>><>><<<<>><<<<>>><><<<<>>><<>>><<>>>><<<<>><<>>><<><>><>>><<<<>><<<>><<<><<>>>><<><<<<><>><<>>><<<>><>>>><><<>>><<<<>>>><<><<>>><<<<><<>>>><<<<><<<><<>><><<<<>>><<>>>><><>>><<<>><<<<><<<>><<>>><<<>><<><<<>>>><>>><<><<>>><>>>><<<><<<<><<<>>><<<<>>><<<>><<<<>>>><<<><<<<>>>><><<<>><<>><<<<>>>><>><>>>><<<<>>><>>>><<<<><<<<>>>><<><>>>><<<><><>>>><<<>>>><<>>><<<><<>><<<><<<<><<<><<>><<>>><<<><<<<>><<<<><<<><<<><>>><<<<><<<<><><>><>><<<>><<<><<<>>>><<<>><<<>><<><><<<<>><>>>><<<>>>><>>><<>>>><<<>><<>><<<<><<<><>><<<>>><<<>>><<<<>><<<>>>><<>>>><>><<<<>><><<<>>><>><>>><<<<>>><<<<>>><<<><<<<>><<>>><<<><<<>><<<<>>><<<<><<>>>><<<>>><<<><<<<>>>><<>>><<>><<><>><<<<>>>><<<<>>>><<<<>>><<<<>><<>><<>><<<>><>><<<><<<<>>>><><<><<<><>>>><<<<>>><<>>>><<<>>>><<<<>>>><<<<>>>><<<>><<<<>><<<><<<>>>><<<<>>>><>><<<<>>>><<<>>><<>>>><<>>><<>>>><<>>><<<<>>>><>><<<<>>><<>>><>>><<<<>><<<>>>><<<<>>><<<>>><<<<>>>><<<<><<<<>>><<>>><>>><<<>>>><<>>>><<<>>>><<>>>><<>>><<<>>><<<<><>><<<<>>>><<<<>>><>>>><><>><>><>>><<<<>><>><<>>>><<<<>>><>>><<<>>><<>><<<<>><<>>>><<<>><>><<>>>><<<>>>><<>>>><><>>>><<<<><<<>>><<<<>>><<<>>>><<<<>>>><<<<><<>>><<>>><<<<>>><<<><<><>>><<<>><<>>><<<>>><<<<>><<<<>>><>>><<<<><><<><<<<><>>>><>><<<<>>>><<<<><<>>>><<<<>><<<<>>><<<<>><<<>>>><<<>>><<>><<<<>>><<<<>>>><<>>><<<<><<<>><<<<>><>>>><<<>>>><<<<>>><<<>><><>>><<>><<<>>><<<<>>>><<>>>><<>><<<<>>>><<<>>>><<<><<<<><<<<><<<<>>>><<<>>><<<<>>><<<>>>><<<<>>><<<<>>>><<<<>><<><<<>>><>>>><>>>><<<<><<<>>>><<>><<><<<>>>><<<>>><<<<>>>><<><>>><><<<<>>><<<<>>>><<<>>>><<>>><<<>><<<<>><<<>>>><><<><<<>>>><<<>><>><<<<>>><<<>>><<<<>>><<<<>>>><><<<><>>><>><<<>>><<<<><<<>>><<>>>><><<>>>><<<>>><<<><>>><<<<><<<<>>><<><>><<<<><><<<><<<<>>><<<>>>><>>><<>><<<>><<<>>>><<<>>>><<>>><<><<<><><><<<>>><<>>><<>>><<<<><<>>>><<<><>>><><<><<>>><<<>>><<<<>>><<<<>><<<<><>>>><<>>><<>><>>><<>><>><<><<<<>><>><<<<>>><<>>><<<<>><<<<>><<><<<><<<<>>>><<<<>><<>>><<<>><<<><<<>><>>>><<<>>>><><<><<<<>><<<<>>><<>>>><<<><>>>><<<>>><<<>><<<>>>><<<<>><<><<>>>><<<>><<<>>><<><<<>>><<><><<<<>>>><<><><<<>>>><<>><<>>><>><<<<>><<>>>><<<>><>><>>><<<<>><<<<>><<<>><<<>>>><<<>><<<<>>><<<>>><<<<>>><><<<>>>><<<><>><>>>><>>>><<<<><<>><<<><<<><>>><<>>><<>>>><>>>><>>><<>><<>>><>>>><<<>><<>><<<<>>><<<>>>><<<><<<><<<>>>><<<<>>><<<<>>>><<<>><<>>>><<>>>><><<<>>><>><<<>>>><<<<>><><<<><>><>>><>>>><<<<>>><<<>>><<<<><<<>><<>>>><<>>><<<<>>>><><<<><<<<>>>><<<<>><<<>>>><<>>><<><>><><<<>>>><<<><<<><>>><<<<>>>><<<<>>>>";
    int chamberWidth = 7;
    char floorPattern = '-';
    char wallPattern = '|';
    char emptyPattern = '.';
    char rockPattern = '#';

    Chamber flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern, rockPattern);

    Shape dropShape = null;
    String droppedCols = "";
    for (int i = 0; i < 2022; i++) {
      if (i % 5 == 0) {
        dropShape = new ShapeOne(flowChamber.getDropX(), flowChamber.getDropY());
      } else if (i % 5 == 1) {
        dropShape = new ShapeTwo(flowChamber.getDropX(), flowChamber.getDropY());;
      } else if (i % 5 == 2) {
        dropShape = new ShapeThree(flowChamber.getDropX(), flowChamber.getDropY());;
      } else if (i % 5 == 3) {
        dropShape = new ShapeFour(flowChamber.getDropX(), flowChamber.getDropY());;
      } else {
        dropShape = new ShapeFive(flowChamber.getDropX(), flowChamber.getDropY());;
      }

      flowChamber.dropShape(dropShape);
    }
    flowChamber.drawChamber();
    System.out.println("Part 1 rock height is: " + flowChamber.getRockHeight());


    // Part 2 ---------------------------------------------
    HashMap<Integer, HashMap<String, HashMap<String, Integer>>> loopShapes = new HashMap<>();
    flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern, rockPattern);
    dropShape = null;

    for (int i = 0; i < 1870; i++) {
      if (i % 5 == 0) {
        dropShape = new ShapeOne(flowChamber.getDropX(), flowChamber.getDropY());
      } else if (i % 5 == 1) {
        dropShape = new ShapeTwo(flowChamber.getDropX(), flowChamber.getDropY());
      } else if (i % 5 == 2) {
        dropShape = new ShapeThree(flowChamber.getDropX(), flowChamber.getDropY());
      } else if (i % 5 == 3) {
        dropShape = new ShapeFour(flowChamber.getDropX(), flowChamber.getDropY());
      } else {
        dropShape = new ShapeFive(flowChamber.getDropX(), flowChamber.getDropY());
      }

      int droppedCol = flowChamber.dropShape(dropShape);
      droppedCols += droppedCol;
      if (i == 0) {
        droppedCols = "";
      }
      // System.out.println(droppedCols);

      String shapes = "";
      if (i % 5 == 0 &&  i > 4) {
        shapes = "23451";
      } else if (i % 5 == 1 && i > 4) {
        shapes = "34512";
      } else if (i % 5 == 2 && i > 4) {
        shapes = "45123";
      } else if (i % 5 == 3 && i > 4) {
        shapes = "51234";
      } else if (i > 4){
        shapes = "12345";
      }
      // Add to the hashmap
      int currJetIndex = flowChamber.getJetIndex();
      if (!loopShapes.containsKey(currJetIndex)) {
        loopShapes.put(currJetIndex, new HashMap<>());
      }
      if (!loopShapes.get(currJetIndex).containsKey(shapes)) {
        loopShapes.get(currJetIndex).put(shapes, new HashMap<>());
      }
      if (loopShapes.get(currJetIndex).get(shapes).containsKey(droppedCols)) {
        System.out.println("Match found at shape " + i);
        System.out.println("Matched to condition at shape " + loopShapes.get(currJetIndex).get(shapes).get(droppedCols));
        // break;
      } else {
        loopShapes.get(currJetIndex).get(shapes).put(droppedCols, i);
      }

      if (i >=5) {
        droppedCols = droppedCols.substring(1);
      }
    }
    // flowChamber.drawChamber();
    System.out.println("Part 2 rock height is: " + flowChamber.getRockHeight());
    // System.out.println(loopShapes);

    // Found loops and found that every 1730 shapes dropped, it begins a cycle,
    // starting at shape 224. Based on this, we can calculate the number of full
    // cycles in 1000000000000, then multiply by the height of the loop.
    // Then, we just add the remainder back in.
  }


}
