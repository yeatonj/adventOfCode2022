// Program for checking distress signal data
// Written for Advent of Code 2022 day 13
// Written by Joshua Yeaton, 12/13/22

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class DistressSignal {
  public static void main(String args[]) throws FileNotFoundException {
    // Open and load the data into a string array
    ArrayList<String> distressLines = new ArrayList<>();
    // Mac OS FilePaths
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day13/distress_data.txt";
    // String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day13/distress_data_test.txt";
    // Windows FilePaths
    // String filePath = "C:\\Users\\yeato\\git_projects\\adventOfCode2022\\day13\\distress_data.txt";
    String filePath = "C:\\Users\\yeato\\git_projects\\adventOfCode2022\\day13\\distress_data_test.txt";

    File distressData = new File(filePath);
    Scanner distressScanner = new Scanner(distressData);

    // Load the distress data into the array
    int lineCount = 0;
    while(distressScanner.hasNextLine()) {
      lineCount++;
      if (lineCount % 3 !=0){
        String currentLine = distressScanner.nextLine();
        currentLine = currentLine.replace(" ", "");
        distressLines.add(currentLine);
      } else {
        // Skips blank lines
        distressScanner.nextLine();
      }
    }
    distressScanner.close();

    System.out.println(distressLines);

    // -----------------------------------
    // We now have the data loaded into the array, even lines start the pair
    int correctLineSum = 0;
    for (int i = 0; i < distressLines.size(); i+=2) {
      // Create the starting arrays from each line in the pair
      ArrayList<String> startList1 = splitArray(distressLines.get(i));
      ArrayList<String> startList2 = splitArray(distressLines.get(i+1));
      // Print the starting arrays
      System.out.println(startList1);
      System.out.println(startList2);
      // Check if they are ordered correctly and print that
      boolean checkPair = checkLineOrder(startList1, startList2);
      System.out.println(checkPair);
      // If they are, add the sum to the total
      if (checkPair == true) {
        correctLineSum += ((i/2) + 1);
      }
    }
    System.out.println("Sum of correct indices is: " + correctLineSum);
    // Printed 1414, this is too low - bug was that program wasn't comparing ints correctly
    // Guess 2, 6127, still too low...
  }

  // Method for converting a string into an arrayList of new strings, split
  // based on commas
  private static ArrayList<String> splitArray(String stringIn) {
        String newString = stringIn.substring(1,stringIn.length()-1);
        ArrayList<String> stringArray = new ArrayList<>();

        String addString = "";
        int i = 0;
        int openBrackets = 0;
        int closedBrackets = 0;
        boolean splitNow = false;
        while (i < newString.length()) {
          // Check for special next character
          if (newString.charAt(i) == '[') {
            openBrackets++;
          } else if (newString.charAt(i) == ']') {
            closedBrackets++;
          } else if (newString.charAt(i) == ',' && openBrackets == closedBrackets) {
            splitNow = true;
          }
          if (splitNow) {
            stringArray.add(addString);
            splitNow = false;
            addString = "";
          } else {
            addString += newString.charAt(i);
          }
          i++;
        }
        if (!addString.equals("")) {
          stringArray.add(addString);
        }
        return stringArray;
  }

  private static boolean checkLineOrder(ArrayList<String> line1, ArrayList<String> line2) {
    // Empty case checks
    if (line1.isEmpty() && line2.isEmpty()) {
      return true;
    } else if (line1.isEmpty()) {
      return true;
    } else if (line2.isEmpty()) {
      return false;
    }

    // Find which is smaller, and use that as the index
    int leftSize = line1.size();
    int rightSize = line2.size();
    int minSize = leftSize;
    if (rightSize < minSize) {
      minSize = rightSize;
    }

    // Now, iterate through each element.  If either is not a single element,
    // We convert both to new ArrayList<String>s and pass them through the
    // function again
    boolean check = true;
    for (int i = 0; i < minSize; i++) {
      String leftLine = line1.get(i);
      String rightLine = line2.get(i);
      System.out.println("Comparing left line: " + leftLine);
      System.out.println("to right line: " + rightLine);
      int subLeftSize = leftLine.length();
      int subRightSize = rightLine.length();
      // Test cases
      if (leftLine.charAt(0) != '[' && rightLine.charAt(0) != '[') {
        // Case 1 (base case), both are single elements
        // Simply compare the two
        System.out.println("Case 1");
        int leftInt = Integer.parseInt(leftLine);
        int rightInt = Integer.parseInt(rightLine);
        int comparison = leftInt - rightInt;
        System.out.println(comparison);
        if (comparison > 0) {
          return false;
        } else if (comparison < 0) {
          System.out.println("Here");
          return true;
        }
      } else if (rightLine.charAt(0) == '[' && leftLine.charAt(0) != '[') {
        //Case 2, right is an array, left is a single element
        // Create new arrays for each
        System.out.println("Case 2");
        ArrayList<String> newLeft = new ArrayList<>();
        newLeft.add(leftLine);
        ArrayList<String> newRight = splitArray(rightLine);
        // And then pass through back into this function
        return checkLineOrder(newLeft, newRight);
      } else if (leftLine.charAt(0) == '[' && rightLine.charAt(0) != '[') {
        System.out.println("Case 3");
        //Case 3, left is an array, right is a single element
        // Create new arrays for each
        ArrayList<String> newRight = new ArrayList<>();
        newRight.add(rightLine);
        ArrayList<String> newLeft = splitArray(leftLine);
        // And then pass through back into this function
        return checkLineOrder(newLeft, newRight);
      } else if (leftLine.equals(rightLine)) {
        System.out.println("Case 4");
        // Case 4, strings are identical
        return true;
      } else {
        System.out.println("Case 5");
        // Case 5, both are arrays
        // Create new arrays for each
        ArrayList<String> newLeft = splitArray(leftLine);
        ArrayList<String> newRight = splitArray(rightLine);
        // And then pass through back into this function
        check = checkLineOrder(newLeft, newRight);
      }
      if (check == false) {
        return false;
      }
    }
    // If we have reached this point, we know that comparisons have not caused
    // us to send back a false value - all values of the array were equal.
    // However, if the right side was shorter than the left, this is a failure,
    // so check this last condition
    if (rightSize >= leftSize) {
      return true;
    } else {
      return false;
    }
  }
}
