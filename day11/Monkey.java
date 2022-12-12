// Class to represent a monkey in a game of monkey in the middle

import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Function;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

class Monkey {
  // Class Variables
  private static ArrayList<Integer> quotients = new ArrayList<>();
  // Instance variables
  private int[] startingItems; // Temporary queue for the items
  private Queue<HashMap<Integer, Integer>> itemQueue; // Queue for the items the monkey needs to inspect
  private int itemsInspected; // Variable to hold the number of items the monkey has checked
  private Monkey trueTarget; // Which monkey to throw to if test is true
  private Monkey falseTarget; // Which monkey to throw to if test is false
  private int monkeyNumber; // Number of the monkey
  private String opOperator; // Operator (+, *, or ^)
  private int opNum; // Number to perform the operator on
  private int testQuot; // What to check against

  // Constructor
  // Square is ^, if square, opNum == null
  public Monkey(int monkeyNum, String opOperator, int opNum, int testQuot, int[] startingItems) {
    // Initialize standard variables
    this.itemQueue = new LinkedList<>();
    this.startingItems = startingItems;
    this.itemsInspected = 0;
    this.monkeyNumber = monkeyNum;
    this.opOperator = opOperator;
    this.opNum = opNum;
    this.testQuot = testQuot;
    Monkey.quotients.add(this.testQuot);
  }

  // Setter for targets and checking which of the quotients are factors of the
  // initial values
  public void setTargets(Monkey trueTarget, Monkey falseTarget) {
    this.trueTarget = trueTarget;
    this.falseTarget = falseTarget;
    // For each intiial item
    for (int item : this.startingItems) {
      HashMap<Integer,Integer> remainderMap = new HashMap<>();
      for (int quotient : Monkey.quotients) {
        remainderMap.put(quotient, item % quotient);
      }
      itemQueue.add(remainderMap);
    }

    System.out.println("Targets for Monkey " + this.monkeyNumber + " successfully set.");
  }

  // Getter for current items of each
  public void printQueue() {
    System.out.println(this.itemQueue);
  }

  // 1: Getter for items inspected
  public int numInspectedItems() {
    return this.itemsInspected;
  }

  // 2: Method for recieving a thrown item
  public void recieveItem(HashMap<Integer,Integer> itemNumber) {
    this.itemQueue.add(itemNumber);
  }

  // 3: Method to start inspecting and throwing items
  public void startBusiness() {
    while (!this.itemQueue.isEmpty()) {
      // Determine the front item in the queue
      HashMap<Integer,Integer> currentItem = this.itemQueue.poll();
      HashMap<Integer,Integer> newItem = null;

      // Examine the item
      if (this.opOperator == "+") {
        // System.out.println("Adding");
        newItem = new HashMap<>();
        for (int quotient : Monkey.quotients) {
          int currRem = currentItem.get(quotient);
          newItem.put(quotient, (currRem + this.opNum) % quotient);
        }
      } else if (this.opOperator == "*") {
        // System.out.println("Multiplying");
        newItem = new HashMap<>();
        for (int quotient : Monkey.quotients) {
          int currRem = currentItem.get(quotient);
          newItem.put(quotient, (currRem * this.opNum) % quotient);
        }
      } else {
        // System.out.println("Squaring");
        newItem = new HashMap<>();
        for (int quotient : Monkey.quotients) {
          int currRem = currentItem.get(quotient);
          newItem.put(quotient, (currRem * currRem) % quotient);
        }
      }
      // System.out.println(newItem);
      this.itemsInspected++;

      // Decide where to throw the item, and throw it
      if (newItem.get(this.testQuot) == 0) {
        trueTarget.recieveItem(newItem);
      } else {
        falseTarget.recieveItem(newItem);
      }
    }
  }

}
