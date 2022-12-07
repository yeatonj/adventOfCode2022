// Class for the node in a file directory - this node represents a folder
// that can contain a list of files and sizes
// Written for Advent of Code 2022
// Written by: Josh Yeaton
// Written on 12/7/2022

import java.util.HashSet;

public class dirNode {
  // Instance variables:
  private dirNode parentNode; // parent of this node
  private int totalContents; // total contents of this node and all its children
  private HashSet<dirNode> childrenNodes; // set of children of this node

}
