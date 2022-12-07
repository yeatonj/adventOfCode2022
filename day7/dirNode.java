// Class for the node in a file directory - this node represents a folder
// that can contain a list of files and sizes
// Written for Advent of Code 2022
// Written by: Josh Yeaton
// Written on 12/7/2022

import java.util.HashSet;
import java.util.HashMap;

public class dirNode {
  // Instance variables:
  private dirNode parentNode; // parent of this node
  private int totalContents; // total contents of this node and all its children
  private HashMap<String, dirNode> childrenNodes; // set of children of this node
  private HashMap<String, Integer> fileList; // list of files and sizes

  // Constructor
  public dirNode(dirNode parent) {
    this.parentNode = parent;
    this.totalContents = 0;
    this.childrenNodes = null;
    this.fileList = null;
  }

  // Method to add a file to a node
  public void addFile(String fileName, int fileSize) {
    this.totalContents += fileSize;
    if (this.fileList == null) {
      this.fileList = new HashMap<>();
    }
    this.fileList.put(fileName, fileSize);
  }

  // Method to add a child directory to the node
  public void addChildDir(String childName) {
    if (this.childrenNodes == null) {
      this.childrenNodes = new HashMap<>();
    }
    this.childrenNodes.put(childName, new dirNode(this));
  }

  // Getter for files and values
  public HashMap<String, Integer> getFiles() {
    return this.fileList;
  }

  // Getter method for children nodes
  public HashMap<String, dirNode> getChildren() {
    return this.childrenNodes;
  }

  // Getter method for specific child node
  public dirNode getChild(String childName) {
    return this.childrenNodes.getOrDefault(childName,null);
  }

  // Getter method for total directory size
  public int getDirSize() {
    return this.totalContents;
  }

  // Method for updating total directory size
  public void updateDirSize(int updateSize) {
    this.totalContents += updateSize;
  }

  // Getter method for parent node
  public dirNode getParent() {
    return this.parentNode;
  }
}
