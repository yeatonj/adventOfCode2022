// Class to represent a valve node in the volcano graph
// Written for AOC 2022
// Written by: Josh Yeaton
// Written on 12/16/2022

public class ValveNode {
  // Class variables
  private static int numValves = 0;

  // Instance Variables
  private String valveName;
  private int valveNum;
  private int flowRate;
  private boolean isOpen;

  // Constructor
  public ValveNode(String valveName, int flowRate) {
    // Increase the number of nodes and assign the valve number
    ValveNode.numValves++;
    this.valveNum = ValveNode.numValves;

    // Assign the other variables
    this.valveName = valveName;
    this.flowRate = flowRate;
    this.isOpen = false;
  }

  // Getters
  public String getValveName() {
    return this.valveName;
  }

  public int getFlowRate() {
    return this.flowRate;
  }

  public int getValveNum() {
    return this.valveNum;
  }

  public boolean getValveStatus() {
    return this.isOpen;
  }

  public static int getNumValves() {
    return ValveNode.numValves;
  }

  // Method to open the valve
  public boolean openValve() {
    this.isOpen = true;
    return this.isOpen;
  }
}
