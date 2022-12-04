// File to determine if in a pair of elves in the input file have overlapping
// sections for Advent of Code 2022 Day 4
// Written by: Josh Yeaton
// Written on 12/4/2022

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

 public class CampCleanup {
   public static void main(String[] args) {
     // Part 1: figure out how many of the pairs have one elf's sections
     // fully contained by the other
     // Part 2: figure out how many pairs have any overlap at all

     // Open the file and create the scanner

     File cleanupFile = null;
     Scanner cleanupScanner = null;
     String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day4/elf_assignments";

     // Open the file
     try {
       cleanupFile = new File(filePath);
       cleanupScanner = new Scanner(cleanupFile);
     } catch (FileNotFoundException e) {
       System.out.println("File not found...");
     }

     int numContained = 0;
     int numOverlap = 0;
     while (cleanupScanner.hasNextLine()) {
       String[] elfSections = cleanupScanner.nextLine().split(",");
       String[] elfOneSections = elfSections[0].split("-");
       String[] elfTwoSections = elfSections[1].split("-");
       int[] elfOneIntSections = new int[2];
       int[] elfTwoIntSections = new int[2];
       for (int i = 0; i < 2; i++) {
         elfOneIntSections[i] = Integer.parseInt(elfOneSections[i]);
         elfTwoIntSections[i] = Integer.parseInt(elfTwoSections[i]);
       }
       if (sectionsContained(elfOneIntSections, elfTwoIntSections)) {
         numContained++;
       }
       if (anyOverlap(elfOneIntSections, elfTwoIntSections)) {
         numOverlap++;
       }
     }

     System.out.println("Number of sections contained: " + numContained);
     System.out.println("Number of overlapping sections: " + numOverlap);
     cleanupScanner.close();
   }

   // Function to check if either elf's sections are fully contained in the
   // other's sections
   private static boolean sectionsContained(int[] elfOneSections,
                                            int[] elfTwoSections) {
     if ((elfOneSections[0] >= elfTwoSections[0] &&
          elfOneSections[1] <= elfTwoSections[1]) ||
          (elfOneSections[0] <= elfTwoSections[0] &&
          elfOneSections[1] >= elfTwoSections[1])) {
       return true;
     }
     return false;
   }

   // Method to check if there is any overlap at all between their sections
   private static boolean anyOverlap(int[] elfOneSections,
                                            int[] elfTwoSections) {
     if ((elfOneSections[1] >= elfTwoSections[0] &&
          elfOneSections[1] <= elfTwoSections[1]) ||
          (elfTwoSections[1] >= elfOneSections[0] &&
          elfTwoSections[1] <= elfOneSections[1])) {
       return true;
     }
     return false;
   }
 }
