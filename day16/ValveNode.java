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

  // Constructor
  public ValveNode(String valveName, int flowRate) {
    // Increase the number of nodes and assign the valve number
    ValveNode.numValves++;
    this.valveNum = ValveNode.numValves;

    // Assign the other variables
    this.valveName = valveName;
    this.flowRate = flowRate;
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

  public static int getNumValves() {
    return ValveNode.numValves;
  }
}
