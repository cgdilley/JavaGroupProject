/** Test Class for Coord.java
  * 
  * by: Christopher Dilley
  * for:  Java Group Project 2015
  */

import junit.framework.TestCase;

public class CoordTest extends TestCase
{
  
  public void testConstructors()
  {
    Coord test = new Coord();
    assertTrue(test.equals(0,0));
    assertFalse(test.equals(1,0));
    assertFalse(test.equals(0,1));
    test = new Coord(5,5);
    assertTrue(test.equals(5,5));
    assertFalse(test.equals(0,0));
    Coord test2 = new Coord(10,10);
    test = new Coord(test2);
    assertTrue(test.equals(10,10));
    assertTrue(test.equals(test2));
    assertTrue(test.copy().equals(test));
  }
  
  public void testComparitors()
  {
    Coord test1 = new Coord(5,5);
    Coord test2 = new Coord(2,2);
    assertTrue(test1.isToRightOf(test2));
    assertFalse(test1.isToLeftOf(test2));
    assertTrue(test1.isBelow(test2));
    assertFalse(test1.isAbove(test2));
  }
  
  public void testDistances()
  {
    Coord test1 = new Coord(5,6);
    Coord test2 = new Coord(2,2);
    assertEquals(test1.distanceX(test2), 3);
    assertEquals(test1.distanceY(test2), 4);
    assertEquals(test1.distanceRaw(test2), 7);
    assertEquals(test1.distanceCrow(test2), 5.0);
  }
  
  public void testMovements()
  {
    Coord test = new Coord(5,5);
    assertTrue(test.getAbove(1).equals(5,4));
    assertTrue(test.getBelow(1).equals(5,6));
    assertTrue(test.getLeft(1).equals(4,5));
    assertTrue(test.getRight(2).equals(7,5));
    assertTrue(test.getTowards(new Coord(9,5), 2).equals(7,5));
    
    assertTrue(test.moveUp(1).equals(5,4));
    assertTrue(test.moveLeft(1).equals(4,4));
    assertTrue(test.moveDown(2).equals(4,6));
    assertTrue(test.moveRight(5).equals(9,6));
    
    assertTrue(test.moveRight(20, 12).equals(12,6));
    
    Coord test2 = new Coord(10,4);
    assertTrue(test.moveTowards(test2, 2).equals(11,5));
  }
    
  
}