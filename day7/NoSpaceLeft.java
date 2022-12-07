// Program to XXX
// Written by: Josh Yeaton
// Written on 12/7/2022

class NoSpaceLeft {
  public static void main(String[] args) {
    // Build the file structure
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day7/dir_contents.txt";
    fileTree noSpace = new fileTree();
    noSpace.buildFileTree(filePath);

    int maxSize = 100000;

    System.out.println("Size of root: " + noSpace.getRootSize());
    System.out.println("Sum of all directories under " + maxSize + ": " + noSpace.getSizeUnder(maxSize));

  }
}
