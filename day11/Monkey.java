// Class to represent a monkey in a game of monkey in the middle

import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Function;

class Monkey {
  // Instance variables
  private Queue<Integer> itemQueue; // Queue for the items the monkey needs to inspect
  private int itemsInspected; // Variable to hold the number of items the monkey has checked
  private Function<Integer,Integer> itemOp; // Operation performed when looking at an item
  private Function<Integer,Integer> testOp; // Operation performed when deciding where to throw
  private Monkey trueTarget; // Which monkey to throw to if test is true
  private Monkey falseTarget; // Which monkey to throw to if test is false
  private int monkeyNumber; // Number of the monkey

  // Constructor
  // Square is ^, if square, opNum == null
  public Monkey(int monkeyNum, String opOperator, int opNum, int testQuot, int[] startingItems) {
    // Initialize standard variables
    this.itemQueue = new LinkedList<>();
    for (int item : startingItems) {
      this.itemQueue.add(item);
    }
    this.itemsInspected = 0;
    this.trueTarget = trueTarget;
    this.falseTarget = falseTarget;
    this.monkeyNumber = monkeyNum;

    // Initialize functions
    this.testOp = item -> item % testQuot;
    if (opOperator.equals("+")) {
      this.itemOp = item -> item + opNum;
    } else if (opOperator.equals("-")) {
      this.itemOp = item -> item - opNum;
    } else if (opOperator.equals("*")) {
      this.itemOp = item -> item * opNum;
    } else if (opOperator.equals("/")) {
      this.itemOp = item -> item / opNum;
    } else if (opOperator.equals("^")) {
      this.itemOp = item -> item * item;
    } else {
      System.out.println("Error creating monkey... Incorrect operator.");
    }
    System.out.println("Set targets for Monkey " + this.monkeyNumber + " prior to using.");
  }

  // Methods to add

  // Setter for targets
  public void setTargets(Monkey trueTarget, Monkey falseTarget) {
    this.trueTarget = trueTarget;
    this.falseTarget = falseTarget;
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
  public void recieveItem(int itemNumber) {
    this.itemQueue.add(itemNumber);
  }

  // 3: Method to start inspecting and throwing items
  public void startBusiness() {
    while (!this.itemQueue.isEmpty()) {
      // Determine the front item in the queue
      int currentItem = this.itemQueue.poll();

      // Examine the item
      int updatedItem = this.itemOp.apply(currentItem);
      // Use this for part 1
      updatedItem /= 3;
      System.out.println(updatedItem);
      this.itemsInspected++;

      // Decide where to throw the item, and throw it
      if (this.testOp.apply(updatedItem) == 0) {
        trueTarget.recieveItem(updatedItem);
      } else {
        falseTarget.recieveItem(updatedItem);
      }
    }
  }

}
