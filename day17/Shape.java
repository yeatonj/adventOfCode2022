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
  abstract int getOriginColumn();

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

  // Returns the current x location of the origin
  int getOriginColumn() {
    return (int)this.points.get(0).getX();
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


// Class for the cross shape
class ShapeTwo extends Shape {
  private ArrayList<Point> points; // Points added from left to right, top to bottom

  ShapeTwo(int originX, int originY) {
    this.points = new ArrayList<>();
    this.points.add(new Point(originX + 1, originY + 2));
    this.points.add(new Point(originX, originY + 1));
    this.points.add(new Point(originX + 1, originY + 1));
    this.points.add(new Point(originX + 2, originY + 1));
    this.points.add(new Point(originX + 1, originY));
  }

  // Returns the height of the highest point
  int highestRow() {
    return (int)this.points.get(0).getY();
  }

  // Returns the current x location of the origin
  int getOriginColumn() {
    return (int)this.points.get(0).getX() - 1;
  }

  // Returns x,y points that need to be empty for a move to the left
  ArrayList<Point> leftMovePoints() {
    ArrayList<Point> leftArray = new ArrayList<>();
    leftArray.add(new Point((int)points.get(0).getX() - 1, (int)points.get(0).getY()));
    leftArray.add(new Point((int)points.get(1).getX() - 1, (int)points.get(1).getY()));
    leftArray.add(new Point((int)points.get(4).getX() - 1, (int)points.get(4).getY()));
    return leftArray;
  }

  // Returns x,y points that need to be empty for a move to the right
  ArrayList<Point> rightMovePoints() {
    ArrayList<Point> rightArray = new ArrayList<>();
    rightArray.add(new Point((int)points.get(0).getX() + 1, (int)points.get(0).getY()));
    rightArray.add(new Point((int)points.get(3).getX() + 1, (int)points.get(3).getY()));
    rightArray.add(new Point((int)points.get(4).getX() + 1, (int)points.get(4).getY()));
    return rightArray;
  }

  // Returns x,y points that need to be empty for a move down
  ArrayList<Point> downMovePoints() {
    ArrayList<Point> downArray = new ArrayList<>();
    downArray.add(new Point((int)points.get(1).getX(), (int)points.get(1).getY() - 1));
    downArray.add(new Point((int)points.get(3).getX(), (int)points.get(3).getY() - 1));
    downArray.add(new Point((int)points.get(4).getX(), (int)points.get(4).getY() - 1));
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

// Class for the L shape
class ShapeThree extends Shape {
  private ArrayList<Point> points; // Points added from left to right, top to bottom

  ShapeThree(int originX, int originY) {
    this.points = new ArrayList<>();
    this.points.add(new Point(originX + 2, originY + 2));
    this.points.add(new Point(originX + 2, originY + 1));
    this.points.add(new Point(originX, originY));
    this.points.add(new Point(originX + 1, originY));
    this.points.add(new Point(originX + 2, originY));
  }

  // Returns the height of the highest point
  int highestRow() {
    return (int)this.points.get(0).getY();
  }

  // Returns the current x location of the origin
  int getOriginColumn() {
    return (int)this.points.get(0).getX() - 2;
  }

  // Returns x,y points that need to be empty for a move to the left
  ArrayList<Point> leftMovePoints() {
    ArrayList<Point> leftArray = new ArrayList<>();
    leftArray.add(new Point((int)points.get(0).getX() - 1, (int)points.get(0).getY()));
    leftArray.add(new Point((int)points.get(1).getX() - 1, (int)points.get(1).getY()));
    leftArray.add(new Point((int)points.get(2).getX() - 1, (int)points.get(2).getY()));
    return leftArray;
  }

  // Returns x,y points that need to be empty for a move to the right
  ArrayList<Point> rightMovePoints() {
    ArrayList<Point> rightArray = new ArrayList<>();
    rightArray.add(new Point((int)points.get(0).getX() + 1, (int)points.get(0).getY()));
    rightArray.add(new Point((int)points.get(1).getX() + 1, (int)points.get(1).getY()));
    rightArray.add(new Point((int)points.get(4).getX() + 1, (int)points.get(4).getY()));
    return rightArray;
  }

  // Returns x,y points that need to be empty for a move down
  ArrayList<Point> downMovePoints() {
    ArrayList<Point> downArray = new ArrayList<>();
    downArray.add(new Point((int)points.get(2).getX(), (int)points.get(2).getY() - 1));
    downArray.add(new Point((int)points.get(3).getX(), (int)points.get(3).getY() - 1));
    downArray.add(new Point((int)points.get(4).getX(), (int)points.get(4).getY() - 1));
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

// Class for the L shape
class ShapeFour extends Shape {
  private ArrayList<Point> points; // Points added from left to right, top to bottom

  ShapeFour(int originX, int originY) {
    this.points = new ArrayList<>();
    this.points.add(new Point(originX, originY + 3));
    this.points.add(new Point(originX, originY + 2));
    this.points.add(new Point(originX, originY + 1));
    this.points.add(new Point(originX, originY));
  }

  // Returns the height of the highest point
  int highestRow() {
    return (int)this.points.get(0).getY();
  }

  // Returns the current x location of the origin
  int getOriginColumn() {
    return (int)this.points.get(0).getX();
  }

  // Returns x,y points that need to be empty for a move to the left
  ArrayList<Point> leftMovePoints() {
    ArrayList<Point> leftArray = new ArrayList<>();
    leftArray.add(new Point((int)points.get(0).getX() - 1, (int)points.get(0).getY()));
    leftArray.add(new Point((int)points.get(1).getX() - 1, (int)points.get(1).getY()));
    leftArray.add(new Point((int)points.get(2).getX() - 1, (int)points.get(2).getY()));
    leftArray.add(new Point((int)points.get(3).getX() - 1, (int)points.get(3).getY()));
    return leftArray;
  }

  // Returns x,y points that need to be empty for a move to the right
  ArrayList<Point> rightMovePoints() {
    ArrayList<Point> rightArray = new ArrayList<>();
    rightArray.add(new Point((int)points.get(0).getX() + 1, (int)points.get(0).getY()));
    rightArray.add(new Point((int)points.get(1).getX() + 1, (int)points.get(1).getY()));
    rightArray.add(new Point((int)points.get(2).getX() + 1, (int)points.get(2).getY()));
    rightArray.add(new Point((int)points.get(3).getX() + 1, (int)points.get(3).getY()));
    return rightArray;
  }

  // Returns x,y points that need to be empty for a move down
  ArrayList<Point> downMovePoints() {
    ArrayList<Point> downArray = new ArrayList<>();
    downArray.add(new Point((int)points.get(3).getX(), (int)points.get(3).getY() - 1));
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

// Class for the L shape
class ShapeFive extends Shape {
  private ArrayList<Point> points; // Points added from left to right, top to bottom

  ShapeFive(int originX, int originY) {
    this.points = new ArrayList<>();
    this.points.add(new Point(originX, originY + 1));
    this.points.add(new Point(originX + 1, originY + 1));
    this.points.add(new Point(originX, originY));
    this.points.add(new Point(originX + 1, originY));
  }

  // Returns the height of the highest point
  int highestRow() {
    return (int)this.points.get(0).getY();
  }

  // Returns the current x location of the origin
  int getOriginColumn() {
    return (int)this.points.get(0).getX();
  }

  // Returns x,y points that need to be empty for a move to the left
  ArrayList<Point> leftMovePoints() {
    ArrayList<Point> leftArray = new ArrayList<>();
    leftArray.add(new Point((int)points.get(0).getX() - 1, (int)points.get(0).getY()));
    leftArray.add(new Point((int)points.get(2).getX() - 1, (int)points.get(2).getY()));
    return leftArray;
  }

  // Returns x,y points that need to be empty for a move to the right
  ArrayList<Point> rightMovePoints() {
    ArrayList<Point> rightArray = new ArrayList<>();
    rightArray.add(new Point((int)points.get(1).getX() + 1, (int)points.get(1).getY()));
    rightArray.add(new Point((int)points.get(3).getX() + 1, (int)points.get(3).getY()));
    return rightArray;
  }

  // Returns x,y points that need to be empty for a move down
  ArrayList<Point> downMovePoints() {
    ArrayList<Point> downArray = new ArrayList<>();
    downArray.add(new Point((int)points.get(2).getX(), (int)points.get(2).getY() - 1));
    downArray.add(new Point((int)points.get(3).getX(), (int)points.get(3).getY() - 1));
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
