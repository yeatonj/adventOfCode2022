// Program to process signal data in a file
// Written by: Josh Yeaton
// Written on 12/6/2022

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collection;

class TuningTrouble {
  public static void main(String[] args) throws IOException {
    // Load the data file
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day6/tuning_data.txt";
    FileReader tuningFile = null;
    BufferedReader tuningReader = null;

    try {
      tuningFile = new FileReader(filePath);
      tuningReader = new BufferedReader(tuningFile);
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't open data file...");
    }

    // Create a queue to hold the data and populate with the first four elements
    // Also create a hash map that contains the number of each character that
    // we will update each time we add/remove characters
    Queue<Character> markerQueue = new LinkedList<>();
    HashMap<Character,Integer> charMap = new HashMap<>();
    Boolean notFound = false;
    for (int i = 0; i < 4; i++) {
      int nextCharInt = tuningReader.read();
      Character nextChar = (char) nextCharInt;
      // First, add to the HashMap
      Integer currVal = charMap.getOrDefault(nextChar, 0);
      if (currVal > 0) {
        notFound = true; // if we add a duplicate, indicates we haven't found it
      }
      charMap.put(nextChar, currVal + 1); // add to the character map
      markerQueue.add(nextChar); // add to the queue
    }


    int currIndex = 4;
    // Now, loop through vals until we have no duplicates, adding/removing from
    // queue/map as we go
    int nextCharInt = tuningReader.read(); // reads in to prep the loop
    while ((notFound == true) && nextCharInt != -1) {
      // Remove the head of the queue
      Character removedChar = markerQueue.remove();
      Integer currentVal = charMap.get(removedChar);
      if (currentVal == 1) {
        charMap.remove(removedChar);
      } else {
        charMap.put(removedChar, currentVal - 1);
      }

      // Add the next character to the queue and the map
      Character nextChar = (char) nextCharInt;
      markerQueue.add(nextChar);
      Integer currVal = charMap.getOrDefault(nextChar, 0);
      charMap.put(nextChar, currVal + 1); // add to the character map

      // Now, check the map for any values > 1
      Collection<Integer> valueList = charMap.values();
      notFound = false;
      for (Integer i : valueList) {
        if (i > 1) {
          notFound = true;
        }
      }
      // Increment the index counter
      currIndex++;
      // Read the next character in
      nextCharInt = tuningReader.read();
    }

    // Print the location of the index
    System.out.println("Start-of-packet marker is at index: " + currIndex);

    tuningReader.close();

  }
}
