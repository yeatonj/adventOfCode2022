// Program for modeling falling rocks
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022

import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PyroclasticFlow {
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day17/flow_data.txt";
    // String jetDirec = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    File fileIn = new File(filePath);
    Scanner fileScanner = new Scanner(fileIn);
    String jetDirec = fileScanner.nextLine();
    fileScanner.close();

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
    int shapesPerCycle = 0;
    int cycleStart = 0;
    int firstCycleEndHeight = 0;

    for (int i = 0; i < 10000; i++) {
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
        shapesPerCycle = i - loopShapes.get(currJetIndex).get(shapes).get(droppedCols);
        cycleStart = loopShapes.get(currJetIndex).get(shapes).get(droppedCols);
        firstCycleEndHeight = flowChamber.getRockHeight();
        break;
      } else {
        loopShapes.get(currJetIndex).get(shapes).put(droppedCols, i);
      }

      if (i >=5) {
        droppedCols = droppedCols.substring(1);
      }
    }
    System.out.println("Number of shapes per cycle: " + shapesPerCycle);
    System.out.println("First cycle starts at shape: " + cycleStart);
    System.out.println("First cycle ends at height: " + firstCycleEndHeight);
    // Now, find the height at the start of the cycle (cycleStart)
    flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern, rockPattern);
    for (int i = 0; i < cycleStart; i++) {
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
    int firstCycleStartHeight = flowChamber.getRockHeight();
    System.out.println("First cycle starts at height: " + firstCycleStartHeight);
    int cycleHeight = firstCycleEndHeight - firstCycleStartHeight;
    System.out.println("Height of cycle: " + cycleHeight);
    // Now, calculate the number of cycles that fit into the number of dropped shapes
    BigInteger partTwoShapesDropped = new BigInteger("1000000000000");
    System.out.println("Number of shapes to drop: " + partTwoShapesDropped);
    BigInteger bigCycleHeight = BigInteger.valueOf(shapesPerCycle);
    System.out.println("Number of shapes per cycle as big int: " + bigCycleHeight);
    BigInteger numCycles = partTwoShapesDropped.divide(bigCycleHeight).subtract(BigInteger.valueOf(1));
    System.out.println("Number of complete cycles: " + numCycles);
    BigInteger remShapes = partTwoShapesDropped.subtract(numCycles.multiply(bigCycleHeight).add(BigInteger.valueOf(cycleStart)));
    System.out.println("Shapes left over at end of final cycle: " + remShapes);
    int remShapesInt = remShapes.intValue() + cycleStart;

    // Now, find the additional height to add
    flowChamber = new Chamber(jetDirec, chamberWidth, floorPattern, wallPattern, emptyPattern, rockPattern);
    for (int i = 0; i < remShapesInt; i++) {
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
    int fullLastCycleHeight = flowChamber.getRockHeight();
    int lastCycleHeight = fullLastCycleHeight - firstCycleStartHeight;
    System.out.println("Height of last cycle: " + lastCycleHeight);
    BigInteger finalHeight = BigInteger.valueOf(lastCycleHeight).add(BigInteger.valueOf(firstCycleStartHeight)).add(numCycles.multiply(BigInteger.valueOf(cycleHeight)));



    System.out.println("Part 2 rock height is: " + finalHeight);
  }


}
