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
    String filePath = "C:\\Users\\yeato\\git_projects\\adventOfCode2022\\day13\\distress_data.txt";
    // String filePath = "C:\\Users\\yeato\\git_projects\\adventOfCode2022\\day13\\distress_data_test.txt";

    File distressData = new File(filePath);
    Scanner distressScanner = new Scanner(distressData);

    // Load the distress data into the array
    int lineCount = 0;
    while(distressScanner.hasNextLine()) {
      lineCount++;
      if (lineCount % 3 !=0){
        String currentLine = distressScanner.nextLine();
        distressLines.add(currentLine);
      } else {
        // Skips blank lines
        distressScanner.nextLine();
      }
    }
    distressScanner.close();

    // -----------------------------------
    // We now have the data loaded into the array, even lines start the pair
    int correctLineSum = 0;
    for (int i = 0; i < distressLines.size(); i+=2) {
      // Create the starting arrays from each line in the pair
      ArrayList<String> startList1 = splitArray(distressLines.get(i));
      ArrayList<String> startList2 = splitArray(distressLines.get(i+1));
      boolean checkPair = compare(startList1, startList2);
      // If they are, add the sum to the total
      if (checkPair == true) {
        correctLineSum += ((i/2) + 1);
      }
    }
    System.out.println("Sum of correct indices is: " + correctLineSum);
    // Correct number is 6420
  }

  // Method for converting a string into an arrayList of new strings, split
  // based on commas
  private static ArrayList<String> splitArray(String stringIn) {
    if (!stringIn.contains(",") && stringIn.charAt(0) != '[') {
      ArrayList<String> stringArray = new ArrayList<>();
      stringArray.add(stringIn);
      return stringArray;
    }
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

  // Custom compare function to compare the two inputs
  // Returns true if order is correct, false if order incorrect, and null if the
  // two are equal
  private static Boolean compare(ArrayList<String> line1, ArrayList<String> line2) {
    // Check for empty lists... -> not 100% sure on this part
    if (line1.isEmpty() && line2.isEmpty()) {
      return null;
    } else if (line1.isEmpty()) {
      return true;
    } else if (line2.isEmpty()) {
      return false;
    }
    boolean line1Int = intCheck(line1);
    boolean line2Int = intCheck(line2);
    // Case 1: both elements are integers
    if (line1Int && line2Int) {
      // Convert both to integers and compare
      int int1 = Integer.parseInt(line1.get(0));
      int int2 = Integer.parseInt(line2.get(0));
      if (int1 < int2) {
        return true;
      } else if (int1 > int2) {
        return false;
      } else {
        return null;
      }
    }
    // Case 2: Left element is a list, right is an integer
    else if (line2Int) {
      // Convert the int into an array before passing it in
      String oldLine2 = line2.get(0);
      String newLine2 = "[";
      newLine2 += (oldLine2 + "]");
      ArrayList<String> newArray2 = new ArrayList<String>();
      newArray2.add(newLine2);
      return compare(line1, newArray2);
    }
    // Case 3: Left element is an integer, right element is a list
    else if (line1Int) {
      // Convert the int into an array before passing it in
      String oldLine1 = line1.get(0);
      String newLine1 = "[";
      newLine1 += (oldLine1 + "]");
      ArrayList<String> newArray1 = new ArrayList<String>();
      newArray1.add(newLine1);
      return compare(newArray1, line2);
    }
    // Case 4: Both elements are lists
    else {
      // Find which is smaller, and use that as the index
      int leftSize = line1.size();
      int rightSize = line2.size();
      int minSize = leftSize;
      if (rightSize < minSize) {
        minSize = rightSize;
      }
      // Iterate through the list
      for (int i = 0; i < minSize; i++) {
        // Get the contents of each array
        ArrayList<String> line1Array = splitArray(line1.get(i));
        ArrayList<String> line2Array = splitArray(line2.get(i));
        // Then compare
        Boolean result = compare(line1Array, line2Array);
        // If we have a result, return it
        if (result != null) {
          return result;
        }
      }
      if (leftSize < rightSize) {
        return true;
      } else if (leftSize > rightSize) {
        return false;
      } else {
        return null;
      }
    }
  }

  // Function to check if a line is actually an integer
  private static boolean intCheck(ArrayList<String> lineIn) {
    int lineSize = lineIn.size();
    boolean singleEntry = lineSize == 1;
    boolean multEntries = lineIn.get(0).contains(",");
    boolean isArray = lineIn.get(0).charAt(0) == '[';
    return singleEntry && !multEntries && !isArray;
  }

}
