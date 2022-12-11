// Class to represent a monkey in a game of monkey in the middle

import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Function;
import java.lang.Math;
import java.util.ArrayList;

class Monkey {
  // Instance variables
  private Queue<ArrayList<Integer>> itemQueue; // Queue for the items the monkey needs to inspect
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
    for (int item : startingItems) {
      this.itemQueue.add(findPrimeFactors(item));
    }
    this.itemsInspected = 0;
    this.monkeyNumber = monkeyNum;
    this.opOperator = opOperator;
    this.opNum = opNum;
    this.testQuot = testQuot;
  }

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
  public void recieveItem(ArrayList<Integer> itemNumber) {
    this.itemQueue.add(itemNumber);
  }

  // 3: Method to start inspecting and throwing items
  public void startBusiness() {
    while (!this.itemQueue.isEmpty()) {
      // Determine the front item in the queue
      ArrayList<Integer> currentItem = this.itemQueue.poll();
      ArrayList<Integer> newItem = null;

      // Examine the item
      if (this.opOperator == "+") {
        int currLowest = this.findScoreFromFactors(currentItem);
        System.out.println("Adding " + this.opNum + " to " + currLowest + " (" + currentItem + ")");
        currLowest += this.opNum;
        newItem = this.findPrimeFactors(currLowest);
      } else if (this.opOperator == "*") {
        System.out.println("Multiplying " + this.opNum + " by " + currentItem);
        // Add it as a factor, if it isn't already
        if (!currentItem.contains(this.opNum)) {
          currentItem.add(this.opNum);
          newItem = currentItem;
        }
      } else {
        System.out.println("Squaring " + currentItem);
        // For squaring, the factors don't change
        newItem = currentItem;
      }
      // Use this for part 1
      // updatedItem /= 3;
      // Use this for part 2
      System.out.println(newItem);
      this.itemsInspected++;

      // Cycle through new items and remove numbers larger than product of the 4
      ArrayList<Integer> finalItem = new ArrayList<>();
      for (Integer factor : newItem) {
        if (factor < 5000) {
          finalItem.add(factor);
        }
      }

      // Decide where to throw the item, and throw it
      if (newItem.contains(testQuot)) {
        trueTarget.recieveItem(finalItem);
      } else {
        falseTarget.recieveItem(finalItem);
      }
    }
  }

  // Finds the prime factors of a number
  private ArrayList<Integer> findPrimeFactors(int currentNum) {
    // System.out.println("Current number is: " + currentNum);
    double endNum = currentNum;
    int lastfound = 0;
    boolean found = false;
    ArrayList<Integer> factors = new ArrayList<>();
    while (currentNum % 2 == 0) {
      currentNum /= 2;
      lastfound = 2;
      found = true;
    }
    if (found) {
      factors.add(2);
    }
    int i;
    for (i=3; i <= endNum; i += 2) {
      found = false;
      while (currentNum % i == 0) {
        currentNum /= i;
        found = true;
      }
      if (found) {
        factors.add(i);
      }
    }
    // System.out.println("Factors are: ");
    // System.out.println(factors);
    return factors;
  }

  // Recalculates a new score based on the smallest possible factors
  private int findScoreFromFactors(ArrayList<Integer> factors) {
    int subtotal = 1;
    for (Integer factor : factors) {
      subtotal *= factor;
    }
    return subtotal;
  }

  private int findLowestScore(int currentScore) {
    int newNum = findScoreFromFactors(findPrimeFactors(currentScore));
    System.out.println("New number is: " + newNum);
    return newNum;
  }

}
