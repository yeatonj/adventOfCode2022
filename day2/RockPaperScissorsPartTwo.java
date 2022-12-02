// Program to calculate total score for the strategy guide in a game of
// rock paper scissors in which you must win, lose, or tie based on a strategy
// guide

// Written by Joshua Yeaton
// Written on 12/2/22

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

public class RockPaperScissorsPartTwo {
  public static void main(String[] args) {
    // Create the maps
    Map<String,String> throwMap = makeThrowMap();
    Map<String,Integer> scoreMap = makeScoreMap();

    // Create filepath variable and file variable
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day2/rps_strategy_guide.txt";
    File stratFile = null;
    Scanner stratScanner = null;

    // Open the file
    try {
      stratFile = new File(filePath);
      stratScanner = new Scanner(stratFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found...");
    }
    // Read the lines of the file, calculating a score for each and adding to
    // the total
    int myTotalScore = 0;
    while (stratScanner.hasNextLine()) {
      String currentGame = stratScanner.nextLine();
      String myThrow = throwMap.get(currentGame);
      String oppThrow = throwMap.get(currentGame.substring(0,1));
      myTotalScore += scoreGame(oppThrow, myThrow, scoreMap);
    }
    System.out.println("Total score is: " + myTotalScore);

    stratScanner.close();
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

    // X means we need to lose, Y means tie, Z means win
    final String myRock1 = "B X";
    final String myRock2 = "A Y";
    final String myRock3 = "C Z";
    final String myPaper1 = "C X";
    final String myPaper2 = "B Y";
    final String myPaper3 = "A Z";
    final String myScissors1 = "A X";
    final String myScissors2 = "C Y";
    final String myScissors3 = "B Z";

    // Create the hashmap that maps throws to scores
    Map<String,String> throwMap = new HashMap<>();
    throwMap.put(oppRock, "rock");
    throwMap.put(oppPaper, "paper");
    throwMap.put(oppScissors, "scissors");
    throwMap.put(myRock1, "rock");
    throwMap.put(myRock2, "rock");
    throwMap.put(myRock3, "rock");
    throwMap.put(myPaper1, "paper");
    throwMap.put(myPaper2, "paper");
    throwMap.put(myPaper3, "paper");
    throwMap.put(myScissors1, "scissors");
    throwMap.put(myScissors2, "scissors");
    throwMap.put(myScissors3, "scissors");
    return throwMap;
  }
}
