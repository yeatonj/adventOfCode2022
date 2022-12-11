// Program to determine the amount of monkey business occurring
// Written by: Josh Yeaton
// Written on 12/11/2022

import java.util.ArrayList;
import java.util.Collections;

class MonkeyMiddle {
  public static void main(String[] args) {
    // Create the monkey memory locations
    Monkey monkey0 = null;
    Monkey monkey1 = null;
    Monkey monkey2 = null;
    Monkey monkey3 = null;
    Monkey monkey4 = null;
    Monkey monkey5 = null;
    Monkey monkey6 = null;
    Monkey monkey7 = null;

    // Instantiate the monkeys
    // (int monkeyNum, String opOperator, int opNum, int testQuot, int[] startingItems)
    monkey0 = new Monkey(0, "*", 11, 7, new int[] {63, 57});
    monkey1 = new Monkey(1, "+", 1, 11, new int[] {82, 66, 87, 78, 77, 92, 83});
    monkey2 = new Monkey(2, "*", 7, 13, new int[] {97, 53, 53, 85, 58, 54});
    monkey3 = new Monkey(3, "+", 3, 3, new int[] {50});
    monkey4 = new Monkey(4, "+", 6, 17, new int[] {64, 69, 52, 65, 73});
    monkey5 = new Monkey(5, "+", 5, 2, new int[] {57, 91, 65});
    monkey6 = new Monkey(6, "^", 0, 5, new int[] {67, 91, 84, 78, 60, 69, 99, 83});
    monkey7 = new Monkey(7, "+", 7, 19, new int[] {58, 78, 69, 65});

    // Set the targets of each monkey
    monkey0.setTargets(monkey6, monkey2);
    monkey1.setTargets(monkey5, monkey0);
    monkey2.setTargets(monkey4, monkey3);
    monkey3.setTargets(monkey1, monkey7);
    monkey4.setTargets(monkey3, monkey7);
    monkey5.setTargets(monkey0, monkey6);
    monkey6.setTargets(monkey2, monkey4);
    monkey7.setTargets(monkey5, monkey1);

    // Create an arraylist of monkeys
    ArrayList<Monkey> monkeyList = new ArrayList<>();
    monkeyList.add(monkey0);
    monkeyList.add(monkey1);
    monkeyList.add(monkey2);
    monkeyList.add(monkey3);
    monkeyList.add(monkey4);
    monkeyList.add(monkey5);
    monkeyList.add(monkey6);
    monkeyList.add(monkey7);

    // Iterate over the monkeys and perform monkey business
    int numRounds = 20;
    for (int i = 0; i < numRounds; i++) {
      for (Monkey monkey : monkeyList) {
        monkey.startBusiness();
        System.out.println("Working...");
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
