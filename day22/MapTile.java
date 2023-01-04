// Class to represent tiles on a map

abstract class MapTile {
  abstract public char getChar();

  abstract public int getX();

  abstract public int getY();

  abstract public int getcubeZone();

  abstract public void setUpTile(MapTile upTile);

  abstract public void setDownTile(MapTile downTile);

  abstract public void setLeftTile(MapTile leftTile);

  abstract public void setRightTile(MapTile rightTile);

  abstract public void setDrawChar(char newChar);
}
