// Program to determine the amount of monkey business occurring
// Written by: Josh Yeaton
// Written on 12/11/2022

import java.util.ArrayList;
import java.util.Collections;

class MonkeyMiddleTestData {
  public static void main(String[] args) {
    // Create the monkey memory locations
    Monkey monkey0 = null;
    Monkey monkey1 = null;
    Monkey monkey2 = null;
    Monkey monkey3 = null;


    // Instantiate the monkeys
    // (int monkeyNum, String opOperator, int opNum, int testQuot, int[] startingItems)
    monkey0 = new Monkey(0, "*", 19, 23, new int[] {79, 98});
    monkey1 = new Monkey(1, "+", 6, 19, new int[] {54, 65, 75, 74});
    monkey2 = new Monkey(2, "^", 0, 13, new int[] {79, 60, 97});
    monkey3 = new Monkey(3, "+", 3, 17, new int[] {74});


    // Set the targets of each monkey
    monkey0.setTargets(monkey2, monkey3);
    monkey1.setTargets(monkey2, monkey0);
    monkey2.setTargets(monkey1, monkey3);
    monkey3.setTargets(monkey0, monkey1);

    // Create an arraylist of monkeys
    ArrayList<Monkey> monkeyList = new ArrayList<>();
    monkeyList.add(monkey0);
    monkeyList.add(monkey1);
    monkeyList.add(monkey2);
    monkeyList.add(monkey3);

    // Iterate over the monkeys and perform monkey business
    int numRounds = 1;
    for (int i = 0; i < numRounds; i++) {
      System.out.println("Round: " + i);
      for (Monkey monkey : monkeyList) {
        monkey.printQueue();
      }
      for (Monkey monkey : monkeyList) {
        monkey.startBusiness();
      }
    }

    // Create an arraylist for the number inspected of each monkey
    ArrayList<Integer> inspections = new ArrayList<>();
    for (Monkey monkey : monkeyList) {
      inspections.add(monkey.numInspectedItems());
    }
    System.out.println(inspections);
    Collections.sort(inspections, Collections.reverseOrder());
    System.out.println(inspections);
    int businessLevel = inspections.get(0) * inspections.get(1);
    System.out.println("Monkey Business level is: " + businessLevel);
    // 87216 is incorrect (too low)

  }
}
