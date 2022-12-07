// Class for representing a file tree with dirNode nodes
// Written for Advent of Code 2022
// Written by: Josh Yeaton
// Written on 12/7/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class fileTree {
  // Instance variables
  private dirNode root;
  private int size; // number of nodes, not number of files

  // Constructor (with no inputs)
  public fileTree() {
    root = new dirNode(null); // parent of root is null
    size = 0;
  }

  // Method (with dirStructure as string that indicates the path of a file
  // containing the commands for mapping a full directory) to create a full
  // file tree from a text file after instantiating the tree
  /* Commands in file:
  $ cd xxx -> move to directory xxx (current node's child)
  $ cd .. -> move up to current node's parent
  $ cd / -> sets current node to root of tree
  $ ls -> list all nodes and files
    file listed as (size filename), lead with int
    directories listed as (dir dirname), lead with string
  */
  public void buildFileTree(String dirStructure) {
    // Sets the node we're currently working on
    dirNode currentNode = null;
    // First, load the file and create a scanner
    File dirFile = null;
    Scanner dirScanner = null;
    try {
      dirFile = new File(dirStructure);
      dirScanner = new Scanner(dirFile);
    } catch (FileNotFoundException e){
      System.out.println("File not found...");
    }

    // Loop through the file, creating directories and files and moving through
    // them as necessary
    while(dirScanner.hasNext()) {
      if (dirScanner.hasNext("\\$")) { // \\ is to escape regex...
        // Case running a command
        dirScanner.next(); // Skip "$"
        String command = dirScanner.next();
        if (command.equals("cd")) {
          String newDir = dirScanner.next();
          if (newDir.equals("/")) {
            currentNode = root;
          } else if (newDir.equals("..")) {
            currentNode = currentNode.getParent();
          } else {
            currentNode = currentNode.getChild(newDir);
          }
        } else {
          // Case "ls", just skip to the next line
          continue;
        }

      } else if (dirScanner.hasNextInt()) {
        // Case adding a file
        int fileSize = dirScanner.nextInt();
        String fileName = dirScanner.next();
        currentNode.addFile(fileName, fileSize);
        // update the file sizes up the tree
        updateParentSize(currentNode, fileSize);

      } else {
        // Case adding a directory (dir dirname)
        // Add a directory dirname to the current node
        dirScanner.next(); // skip "dir"
        String dirName = dirScanner.next();
        currentNode.addChildDir(dirName);
        size++;
      }
    }
  }

  // Recursive function to travel up the tree, adding/subtracting the size of a
  // change to each parent node
  private void updateParentSize(dirNode currentNode, int updateSize) {
    dirNode updateNode = currentNode;
    // Base case (root of tree)
    if (updateNode.getParent() == null) {
      return;
    }
    // otherwise, move to the parent, update the parent, and then call the
    // function again on the parent
    updateNode = updateNode.getParent();
    updateNode.updateDirSize(updateSize);
    this.updateParentSize(updateNode, updateSize);
    return;
  }

  public int getRootSize() {
    return root.getDirSize();
  }

  // Returns the sum of the sizes of all directories in the file structure
  // with each directory under the size specified in the function input
  public int getSizeUnder(int maxSize) {
    return recursiveSizeUnder(maxSize, root);
  }

  // function checks all children's sizes and adds any under the maxSize to the
  // total, then returns that total
  private int recursiveSizeUnder(int maxSize, dirNode currentNode) {
    int returnVal = 0;
    // Base case
    if (currentNode.getChildren() == null) {
      return 0;
    } else {
      HashMap<String,dirNode> children = currentNode.getChildren();
      for (String key : children.keySet()) {
        int childSize = children.get(key).getDirSize();
        if (childSize <= maxSize) {
          returnVal += childSize;
        }
        returnVal += recursiveSizeUnder(maxSize, children.get(key));
      }
    }
    return returnVal;
  }

}
