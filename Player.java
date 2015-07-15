/*
 * Player class of the Linguist Lizard game.
 * Takes care of the functionality and movements of the lizard object,
 * consisting of head and tail.
 */
 
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.*;
 
public class Player implements KeyListener {
   
    private Coord head; //head of the lizard, moving/controlled
    private ArrayList<Coord> tail; //array of tokens forming the tail of the lizard, follows head
    private int direction; //direction of moving
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3; //values for direction
    private boolean collisionOccured; //if lizard walks into himself
    private boolean gameOver = false; 
   
    /**
     * Construct a new linguistic lizard.
     * Specify head coordinate, tail array and initial direction of moving.
     */
    public Player() {
        head = new Coord(1,1);
        tail = new ArrayList<Coord>();
        direction = RIGHT;
    }
   
    /**
     * Method to move the lizard one step in the current given direction. 
     */
    public void updatePlayer() {  
       
        Coord headPos = new Coord(head.getX(), head.getY());
        switch(direction)
        {
            case UP: if(!collision(head.getAbove(1)))//check if lizard would hit itself when moving there
                       {
                        head.moveUp(1); // move
                        tailFollow(headPos); //make tail follow the head
                        break;
                        } else {
                        gameOver= true; //if collision occurs, game over
                        }
 
            case DOWN: if(!collision(head.getBelow(1)))
                       {
                        head.moveDown(1);
                        tailFollow(headPos);
                        break;
                        } else {
                        gameOver = true;
                        }
            case LEFT: if(!collision(head.getLeft(1)))
                       {
                        head.moveLeft(1);
                        tailFollow(headPos);
                        break;
                        } else {
                        gameOver = true;
                        }
            case RIGHT: if(!collision(head.getRight(1)))
                       {
                        head.moveRight(1);
                        tailFollow(headPos);
                        break;
                        } else {
                        gameOver = true;
                        }
            default: gameOver = true;
        }
    }
   
    /**
     * Method that makes the tail "follow" the head. 
     * @param oldHeadPos position that head had before it was moved
     */
    private void tailFollow(Coord oldHeadPos) {
        if(!tail.isEmpty()) {
           
            tail.add(0, oldHeadPos);//insert new tail part at old head position
            tail.remove(tail.size() - 1);//remove last tail element
            
        }
    }
       
    /**
     * Get head coordinates of lizard
     * @return - head coord of lizard
     */
    public Coord getHead() {
       
        return head;
    }
    
    /**
     * Get a part of the tail
     * @param index - index of tail part we want
     * @return - the part of the tail at the specified index
     */
    public Coord getTailPart(int index) {
        return tail.get(index);
    }
    
    /** Get the entire tail
      * @return - the array containing all tail elements
      */
    public ArrayList<Coord> getTail()
    {
      return tail;
    }
      
   
    /**
     * Add a food token to the end of the tail of the lizard (on the opposite side of the
     * second last item of the tail compared to the last (so that its straight)
     * @param add - the number of parts to add
     */
    public void addToTail(int add) {
       
        for(int i = 0; i < add; i++)
        {
            Coord toAdd = new Coord();
           
            if(tail.isEmpty()) //if lizard consists only of head, add opposite to direction
            {
                if(direction == UP)
                {
                    toAdd.set(head.getBelow(1));
                } else if(direction == DOWN)
                {
                    toAdd.set(head.getAbove(1));
                } else if(direction == LEFT)
                {
                    toAdd.set(head.getRight(1));
                } else if(direction == RIGHT)
                {
                    toAdd.set(head.getLeft(1));
                }
               
            } else {//otherwise, add new coordinate depending on last and second last part in line
                Coord last = tail.get(tail.size()-1);
                Coord secondLast;
                if(tail.size() == 1)
                {
                    secondLast = head;
                } else {
                    secondLast = tail.get(tail.size() - 2);
                }
               
                if(secondLast.isAbove(last)) {
                    toAdd.set(last.getBelow(1));
                } else if(secondLast.isBelow(last)) {
                    toAdd.set(last.getAbove(1));
                } else if(secondLast.isToLeftOf(last)) {
                    toAdd.set(last.getRight(1));
                } else if(secondLast.isToRightOf(last)) {
                    toAdd.set(last.getLeft(1));
                }
 
            } //else
           
            tail.add(toAdd); // add the new coordinate to the tail
           
        }//for loop
    }
   
    /**
     * Remove given number of tokens from the END of the lizard's tail
     * @param substract
     */
    public void substractFromTail(int substract) {
       
            for(int i = 0; i < substract; i++)
            {
                if(!tail.isEmpty()) {
                    tail.remove(tail.get(tail.size() - 1));
                }
            }
    }
   
    /**
     * Check if lizard hits itself
     * @param place - coordinate to be checked for collision
     * @return - true if a collision occures in the place
     */
    public boolean collision(Coord place) {
       
        collisionOccured = false;
       
        for(int i = 0; i < tail.size(); i++)
        {
            if(tail.get(i).equals(place))
            {
                collisionOccured = true;
            }
        }
        return collisionOccured;
    }
   
        //change direction of moving depending on key actions (ARROW KEYS)
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode()==KeyEvent.VK_UP && direction != DOWN)
            {
                direction = UP;
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN && direction != UP)
            {
                direction = DOWN;
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT && direction != RIGHT)
            {
                direction = LEFT;
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT && direction != LEFT)
            {
                direction = RIGHT;
            }
        }
   
       @Override
       public void keyTyped(KeyEvent e) {
       }
       @Override
       public void keyReleased(KeyEvent e) {
        
       }
   
}
