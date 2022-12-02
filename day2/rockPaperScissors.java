// Program to calculate total score for the strategy guide in a game of
// rock paper scissors

// Written by Joshua Yeaton
// Written on 12/2/22

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

public class rockPaperScissors {
  public static void main(String[] args) {
    Map<String,String> throwMap = makeThrowMap();
    Map<String,Integer> scoreMap = makeScoreMap();




  }

  // Function to return your score for a game given your opponent's throw,
  // your throw, and a map that tells you what each throw is worth
  private static int scoreGame(String oppThrow,
                              String myThrow,
                              Map<String,Integer> scoreMap) {

    // Define score constants
    final int winScore = 6;
    final int loseScore = 0;
    final int tieScore = 3;

    int oppScore = scoreMap.get(oppThrow);
    int myScore = scoreMap.get(myThrow);

    // Check the win condition
    Boolean win = (oppThrow.equals("rock") && myThrow.equals("paper")) ||
                  (oppThrow.equals("paper") && myThrow.equals("scissors")) ||
                  (oppThrow.equals("scissors") && myThrow.equals("rock"));

    // Return the score
    if (myScore == oppScore) {
      return myScore + tieScore;
    } else if (win) {
      return myScore + winScore;
    } else {
      return myScore + loseScore;
    }
  }


  // Function that returns a map that gives a point value to each throw
  private static Map<String,Integer> makeScoreMap() {
    // Define score constants
    final int rockScore = 1;
    final int paperScore = 2;
    final int scissorsScore = 3;

    // Create the hashmap that maps throws to scores
    Map<String,Integer> scoreMap = new HashMap<>();
    scoreMap.put("rock", rockScore);
    scoreMap.put("paper", paperScore);
    scoreMap.put("scissors", scissorsScore);
    return scoreMap;
  }

  // Fuction that returns a map that maps letters to throws
  private static Map<String,String> makeThrowMap() {
    final String oppRock = "A";
    final String oppPaper = "B";
    final String oppScissors = "C";

    final String myRock = "X";
    final String myPaper = "Y";
    final String myScissors = "Z";

    // Create the hashmap that maps throws to scores
    Map<String,String> throwMap = new HashMap<>();
    throwMap.put(oppRock, "rock");
    throwMap.put(oppPaper, "paper");
    throwMap.put(oppScissors, "scissors");
    throwMap.put(myRock, "rock");
    throwMap.put(myPaper, "paper");
    throwMap.put(myScissors, "scissors");
    return throwMap;
  }
}
