// Program to create and move through a file structure
// Written by: Josh Yeaton
// Written on 12/7/2022

class NoSpaceLeft {
  public static void main(String[] args) {
    // Build the file structure
    String filePath = "/Users/yeato/Documents/git_projects/adventOfCode2022/day7/dir_contents.txt";
    fileTree noSpace = new fileTree();
    noSpace.buildFileTree(filePath);

    // Part 1
    int maxSize = 100000;

    System.out.println("Size of root: " + noSpace.getRootSize());
    System.out.println("Sum of all directories under " + maxSize + ": " + noSpace.getSizeUnder(maxSize));

    // Part 2
    // Find the smallest directory that will free up enough space to allow us
    // to get enough free space for the update

    int updateSize = 30000000;
    int totalDiskSpace = 70000000;
    int currentFreeSpace = totalDiskSpace - noSpace.getRootSize();
    int neededSpace = updateSize - currentFreeSpace;

    System.out.println("Current free space is: " + currentFreeSpace);
    System.out.println("Needed space is : "+ neededSpace);
    int clearedSpace = noSpace.clearSpace(neededSpace);
    System.out.println("Cleared space is : "+ clearedSpace);

  }
}
