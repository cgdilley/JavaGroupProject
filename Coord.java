/** class Coord
  * 
  * Utility class for storing a pair of integer values (x and y), and methods to access, manipulate, and compare these
  * values.
  * 
  * by:  Christopher Dilley
  * modified by: --
  * for: Java Group Project, 2015
  * 
  * 
  * 
  * CHANGELOG:
  * 28.06.15 - Initial write-up of class.  (Chris)
  */


import java.lang.Math;

public class Coord
{
  private int x, y;
  
  //// CONSTRUCTOR METHODS
  /** Default constructor.  X and Y default to 0.
    */
  public Coord()
  {
    set(0,0);
  }
  
  /** Constructor.  Sets X and Y to given values.
    * @param x - X value to set
    * @param y - Y value to set
    */
  public Coord(int x, int y)
  {
    set(x, y);
  }
  
  /** Constructor. Sets X and Y values to those contained in the given coord.
    * @param coord - Coord with values to copy.
    */
  public Coord(Coord coord)
  {
    set(coord);
  }
  
  
  //// ACCESSOR METHODS
  /** Sets X and Y to given values.
    * @param x - X value to set
    * @param y - Y value to set
    */
  public void set(int x, int y) { this.x = x; this.y = y; }
  
  /** Sets X and Y values to those contained in the given coord.
    * @param coord - Coord with values to copy
    */
  public void set(Coord coord) { this.x = coord.x; this.y = coord.y; }
  
  /** Sets X to given value.
    * @param x - X value to set
    */
  public void setX(int x) { this.x = x; }
  
  /** Sets Y to given value.
    * @param y - Y value to set
    */
  public void setY(int y) { this.y = y; }
  
  /** Gets X value
    * @return - Value of X
    */
  public int getX() { return this.x; }
  
  /** Gets Y value
    * @return - Value of Y
    */
  public int getY() { return this.y; }
  
  /** Returns a new Coord object that is a copy of this Coord
    * @return - Copied Coord object
    */
  public Coord copy()
  {
    return (new Coord(this));
  }
  
  /** Returns the Coord that lies to the left of this Coord by a given number of coordinate spaces.
    * @param jump - Number of coordinate spaces to jump
    * @return - Coord with X-jump and Y as its values.
    */
  public Coord getLeft(int jump)
  {
    return (new Coord(this.x-jump, this.y));
  }
  
  /** Returns the Coord that lies to the right of this Coord by a given number of coordinate spaces.
    * @param jump - Number of coordinate spaces to jump
    * @return - Coord with X+jump and Y as its values.
    */
  public Coord getRight(int jump)
  {
    return (new Coord(this.x+jump, this.y));
  }
  
  /** Returns the Coord that lies below this Coord by a given number of coordinate spaces.
    * Assumes that Y=0 is at the top of the screen.
    * @param jump - Number of coordinate spaces to jump
    * @return - Coord with X and Y+jump as its values.
    */
  public Coord getBelow(int jump)
  {
    return (new Coord(this.x, this.y+jump));
  }
  
  /** Returns the Coord that lies above this Coord by a given number of coordinate spaces.
    * Assumes that Y=0 is at the top of the screen.
    * @param jump - Number of coordinate spaces to jump
    * @return - Coord with X and Y-jump as its values.
    */
  public Coord getAbove(int jump)
  {
    return (new Coord(this.x, this.y-jump));
  }
  
  /** Returns a coordinate that results from moving this Coord towards the given coord by a given number of coordinate
    * spaces.
    * Does NOT allow diagonal movement as a single coordinate movement.
    * @param coord - Coord to move towards.
    * @param move - Number of coordinate spaces to move
    * @return - New Coord that is 'jump' spaces closer to the given coord
    */
  public Coord getTowards(Coord coord, int jump)
  {
    Coord returnCoord = new Coord(this);
    
    for (int i=0; i<jump; i++)  // Repeat algorithm for moving 1 space a 'move'-number of times.
    {
      
      if (!returnCoord.equals(coord)) // If the coordinates are not already equivalent (and thus requires movement)
      {
        if (returnCoord.distanceX(coord) > returnCoord.distanceY(coord))  // If the coordinates are further away horizontally than
        {                                                   // vertically, move horizontally.
          if (returnCoord.isToLeftOf(coord))  // If this coord is on the left, move it to the right
            returnCoord.moveRight(1);
          else                         // If this coord is on the right, move it to the left
            returnCoord.moveLeft(1);
        }
        else                                               // If the coordinates are further away vertically than
        {                                                  // horizontally, move vertically.
          if (returnCoord.isAbove(coord))     // If this coord is above, move it down
            returnCoord.moveDown(1);
          else                         // If this coord is below, move it up
            returnCoord.moveUp(1);
        }
      }
    }
    
    return returnCoord;
  }
  
    
  
  
  
  //// COMPARISON METHODS
  /** Tests if two coordinates are equivalent
    * @param coord - Coord to compare with
    * @return - Returns true if given coord has same X and Y values as this coord.  False otherwise.
    */
  public boolean equals(Coord coord)
  {
    return (coord.x == this.x && coord.y == this.y);
  }
  
  /** Tests if coordinate is equivalent to given values
    * @param x - X value to compare
    * @param y - Y value to compare
    * @return - Returns true if given coord has same X and Y values as the given values.  False otherwise.
    */
  public boolean equals(int x, int y)
  {
    return (x == this.x && y == this.y);
  }
  
  /** Tests if two coordinates share the same X value
    * @param coord - Coord to compare with
    * @return - Returns true if given coord has the same X value as this coord.  False otherwise.
    */
  public boolean equalsX(Coord coord)
  {
    return (coord.x == this.x);
  }
  
   /** Tests if two coordinates share the same Y value
    * @param coord - Coord to compare with
    * @return - Returns true if given coord has the same Y value as this coord.  False otherwise.
    */
  public boolean equalsY(Coord coord)
  {
    return (coord.y == this.y);
  }
  
  /** Tests if this coord is to the left of a given coord
    * @param coord - Coord to compare with
    * @return - Returns true if this Coord's X value is less than the given Coord
    */
  public boolean isToLeftOf(Coord coord)
  {
    return (this.x < coord.x);
  }
  
  /** Tests if this coord is to the right of a given coord
    * @param coord - Coord to compare with
    * @return - Returns true if this Coord's X value is greater than the given Coord
    */
  public boolean isToRightOf(Coord coord)
  {
    return (this.x > coord.x);
  }
  
  /** Tests if this coord is above the given coord.
    * Assumes that Y=0 is at the top of the screen.
    * @param coord - Coord to compare with
    * @return - Returns true if this Coord's Y value is less than the given Coord.
    */
  public boolean isAbove(Coord coord)
  {
    return (this.y < coord.y);
  }
  
  /** Tests if this coord is below the given coord.
    * Assumes that Y=0 is at the top of the screen.
    * @param coord - Coord to compare with
    * @return - Returns true if this Coord's Y value is greater than the given Coord.
    */
  public boolean isBelow(Coord coord)
  {
    return (this.y > coord.y);
  }
  
  /** Returns the raw X + Y distance between this Coord and the given Coord
    * @param coord - Coord to compare with
    * @return - The X distance + the Y distance as an integer
    */
  public int distanceRaw(Coord coord)
  {
    return (distanceX(coord) + distanceY(coord));
  }
  
  /** Returns the X distance between this Coord and the given coord
    * @param coord - Coord to compare with
    * @return - Returns the absolute value of the difference of the two X values.
    */
  public int distanceX(Coord coord)
  {
    return Math.abs(coord.x - this.x);
  }
  
  /** Returns the Y distance between this Coord and the given coord
    * @param coord - Coord to compare with
    * @return - Returns the absolute value of the difference of the two Y values.
    */
  public int distanceY(Coord coord)
  {
    return Math.abs(coord.y - this.y);
  }
  
  /** Returns the "as the crow flies" distance between this Coord and the given coord
    * @param coord - Coord to compare with
    * @return - Returns the straight line distance between the two points (Pythagorean Theorem) as a DOUBLE value
    */
  public double distanceCrow(Coord coord)
  {
    return (Math.sqrt( Math.pow( (double)distanceX(coord), 2) + Math.pow( (double)distanceY(coord), 2) ));
  }
  
  
  
  
  //// MANIPULATOR METHODS
  /** Moves the Coord to the left by the given amount
    * @param move - Number of coordinate spaces to move
    * @return - This Coord after the movement has been performed.
    */
  public Coord moveLeft(int move)
  {
    this.x -= move;
    return this;
  }
  
  /** Moves the Coord to the left by the given amount, bounded by a given amount. If new value were to exceed the
    * bound, takes on the value of the bound instead.
    * @param move - Number of coordinate spaces to move
    * @param bound - Value that X cannot be allowed to be fall below
    * @return - This Coord after the movement has been performed.  
    */
  public Coord moveLeft(int move, int bound)
  {
    if (this.x - move < bound)
      this.x = bound;
    else
      this.x -= move;
    
    return this;
  }
  
  /** Moves the Coord to the right by the given amount
    * @param move - Number of coordinate spaces to move
    * @return - This Coord after the movement has been performed.
    */
  public Coord moveRight(int move)
  {
    this.x += move;
    return this;
  }
  
  /** Moves the Coord to the right by the given amount, bounded by a given amount. If new value were to exceed the
    * bound, takes on the value of the bound instead.
    * @param move - Number of coordinate spaces to move
    * @param bound - Value that X cannot be allowed to be go above.
    * @return - This Coord after the movement has been performed.  
    */
  public Coord moveRight(int move, int bound)
  {
    if (this.x + move > bound)
      this.x = bound;
    else
      this.x += move;
    
    return this;
  }
  
  
  
  /** Moves the Coord up by the given amount.
    * Assumes that Y=0 is at the top of the screen.
    * @param move - Number of coordinate spaces to move
    * @return - This Coord after the movement has been performed.
    */
  public Coord moveUp(int move)
  {
    this.y -= move;
    return this;
  }
  
  /** Moves the Coord up by the given amount, bounded by a given amount. If new value were to exceed the
    * bound, takes on the value of the bound instead.
    * Assumes that Y=0 is at the top of the screen.
    * @param move - Number of coordinate spaces to move
    * @param bound - Value that Y cannot be allowed to be less than
    * @return - This Coord after the movement has been performed.  
    */
  public Coord moveUp(int move, int bound)
  {
    if (this.y - move < bound)
      this.y = bound;
    else
      this.y -= move;
    
    return this;
  }
  
  /** Moves the Coord down by the given amount.
    * Assumes that Y=0 is at the top of the screen.
    * @param move - Number of coordinate spaces to move
    * @return - This Coord after the movement has been performed.
    */
  public Coord moveDown(int move)
  {
    this.y += move;
    return this;
  }
  
  /** Moves the Coord down by the given amount, bounded by a given amount. If new value were to exceed the
    * bound, takes on the value of the bound instead.
    * Assumes that Y=0 is at the top of the screen.
    * @param move - Number of coordinate spaces to move
    * @param bound - Value that Y cannot be allowed to be greater than.
    * @return - This Coord after the movement has been performed.  
    */
  public Coord moveDown(int move, int bound)
  {
    if (this.y + move > bound)
      this.y = bound;
    else
      this.y += move;
    
    return this;
  }
  
  /** Moves the coord in the direction of the given coordinate by a given amount.
    * Does NOT allow diagonal movement as a single coordinate movement.
    * @param coord - Coord to move towards.
    * @param move - Number of coordinate spaces to move
    * @return - This Coood after the movement has been performed.
    */
  public Coord moveTowards(Coord coord, int move)
  {
    this.set(this.getTowards(coord, move));
    return this;    
  }
  
  
}
  
  
  
  
  
  
  
  