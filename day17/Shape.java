// Class for modeling a chamber with falling rocks
// Written by: Joshua Yeaton
// Written for: AOC 2022
// Written on 12/22/2022

import java.awt.Point;
import java.util.ArrayList;

abstract class Shape {
  // Instance variables

  abstract ArrayList<Point> leftMovePoints();
  abstract ArrayList<Point> rightMovePoints();
  abstract ArrayList<Point> downMovePoints();
  abstract void moveLeft();
  abstract void moveRight();
  abstract void moveDown();
  abstract ArrayList<Point> getPoints();
  abstract int highestRow();

}

// Class for the horizontal line shape
class ShapeOne extends Shape {
  private ArrayList<Point> points; // Points added from left to right, top to bottom

  ShapeOne(int originX, int originY) {
    this.points = new ArrayList<>();
    this.points.add(new Point(originX, originY));
    this.points.add(new Point(originX + 1, originY));
    this.points.add(new Point(originX + 2, originY));
    this.points.add(new Point(originX + 3, originY));
  }

  // Returns the height of the highest point
  int highestRow() {
    return (int)this.points.get(0).getY();
  }

  // Returns x,y points that need to be empty for a move to the left
  ArrayList<Point> leftMovePoints() {
    ArrayList<Point> leftArray = new ArrayList<>();
    leftArray.add(new Point((int)points.get(0).getX() - 1, (int)points.get(0).getY()));
    return leftArray;
  }

  // Returns x,y points that need to be empty for a move to the right
  ArrayList<Point> rightMovePoints() {
    ArrayList<Point> rightArray = new ArrayList<>();
    rightArray.add(new Point((int)points.get(3).getX() + 1, (int)points.get(3).getY()));
    return rightArray;
  }

  // Returns x,y points that need to be empty for a move down
  ArrayList<Point> downMovePoints() {
    ArrayList<Point> downArray = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      downArray.add(new Point((int)points.get(i).getX(), (int)points.get(i).getY() - 1));
    }
    return downArray;
  }

  // Moves all points to the left
  void moveLeft() {
    for (int i = 0; i < points.size(); i++) {
      points.get(i).move((int)points.get(i).getX() - 1, (int)points.get(i).getY());
    }
  }

  // Moves all points to the right
  void moveRight() {
    for (int i = 0; i < points.size(); i++) {
      points.get(i).move((int)points.get(i).getX() + 1, (int)points.get(i).getY());
    }
  }

  // Moves all points down
  void moveDown() {
    for (int i = 0; i < points.size(); i++) {
      points.get(i).move((int)points.get(i).getX(), (int)points.get(i).getY() - 1);
    }
  }

  // Returns an array with all points
  ArrayList<Point> getPoints() {
    return this.points;
  }
}
