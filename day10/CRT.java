// Program to analyze data from a CPU to print to a screen
// Written by: Josh Yeaton
// Written on 12/10/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CRT {
  public static void main(String[] args) throws FileNotFoundException {
    // First, open the file
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day10/cpu_cycles.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day10/cpu_cycles_test.txt";

    File data = new File(filePath);
    Scanner cycleScanner = new Scanner(data);

    // Create an array to hold the cycle data
    ArrayList<Integer> rawSignalStrength = new ArrayList<>();
    ArrayList<Integer> signalStrength = new ArrayList<>();

    int currentRegVal = 1;
    int currCycle = 0;
    rawSignalStrength.add(currentRegVal);
    while(cycleScanner.hasNext()) {
      if (cycleScanner.hasNext("noop")) {
        // Add the signal strength based on thre previous value (this is the
        // value of the register during the next op, no matter what)
        currCycle++;
        signalStrength.add(currCycle * currentRegVal);
        // Cycle the scanner to the next line
        cycleScanner.next();
        // Add the register value and signal strength
        rawSignalStrength.add(currentRegVal);

      } else {
        // Add the signal strength based on thre previous value (this is the
        // value of the register during the next op, no matter what)
        currCycle++;
        signalStrength.add(currCycle * currentRegVal);
        // Cycle the scanner to the int (this counts as a cycle)
        cycleScanner.next();
        rawSignalStrength.add(currentRegVal);

        currCycle++;
        signalStrength.add(currCycle * currentRegVal);
        // Cycle the scanner to the actual change
        currentRegVal = currentRegVal + cycleScanner.nextInt();
        rawSignalStrength.add(currentRegVal);
      }
    }

    cycleScanner.close();

    // Part 1 (guess of 14600 was incorrect)
    int signalTotal = 0;
    for (int i = 19; i <= 219; i+= 40 ) {
      signalTotal += signalStrength.get(i);
    }

    System.out.println("Signal total for part 1 is: " + signalTotal);

    int lineLength = 40;
    int spriteSize = 3;
    for (int i = 0; i < rawSignalStrength.size() / lineLength; i++) {
      System.out.println(drawLine(rawSignalStrength, i, lineLength, spriteSize));
    }

  }

  // Draws a line based on a signal list - both are 0 indexed.
  private static String drawLine(ArrayList<Integer> signalList, int lineNum, int lineLength, int spriteSize) {
    int startIndex = lineNum * lineLength;
    String returnLine = "";
    for (int i = startIndex; i < startIndex + lineLength; i++) {
      returnLine += drawPixel(i % 40,signalList.get(i),spriteSize);
    }
    return returnLine;
  }

  // Draws  # if a pixel is lit, . if not
  private static char drawPixel(int currIndex, int spriteLoc, int spriteSize) {
    if ((currIndex >= spriteLoc - (spriteSize / 2)) && (currIndex <= spriteLoc + (spriteSize / 2))) {
      return '#';
    } else {
      return '.';
    }
  }
}
