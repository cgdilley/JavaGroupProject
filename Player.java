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
    
    // These variables are to facilitate smoother input response.  Without it, multiple direction changes to direction
    //  can be made without the player having moved, with unpredictable results.  However, movement commands can be
    //  "buffered" in pendingDirection to be applied after the next player movement so that the game doesn't completely 
    //  ignore commands.  Without this buffering, input feels very hit or miss.
    private int pendingDirection;
    private boolean allowDirectionChange = true;  
    
   
    /**
     * Construct a new linguistic lizard.
     * Specify head coordinate, tail array and initial direction of moving.
     */
    public Player() {
        head = new Coord(9,1);
        tail = new ArrayList<Coord>();
        direction = DOWN;
        pendingDirection = -1;
    }
    
    /** Returns whether the player is dead or not.
      * @return - Returns true of the player is dead.  False otherwise.
      */
    public boolean isDead()
    {
      return gameOver;
    }
    
    /**
     * Method to move the lizard one step in the current given direction. 
     */
    public void updatePlayer() {  
       
        Coord headPos = new Coord(head.getX(), head.getY());
        allowDirectionChange = true;  // Toggle the player to allow accepting direction change inputs again
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
        if (pendingDirection != -1)  // If there is a pending direction change, apply it
        {
          direction = pendingDirection;
          pendingDirection = -1;
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
     * @param subtract
     */
    public void subtractFromTail(int subtract) {
       
            for(int i = 0; i < subtract; i++)
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
          if (allowDirectionChange)  // If this is the first direction change request since the last player update
          {
            if(e.getKeyCode()==KeyEvent.VK_UP && direction != DOWN)
            {
                direction = UP;
                allowDirectionChange = false;  // Disallow further direciton changes until the player is updated
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN && direction != UP)
            {
                direction = DOWN;
                allowDirectionChange = false;
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT && direction != RIGHT)
            {
                direction = LEFT;
                allowDirectionChange = false;
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT && direction != LEFT)
            {
                direction = RIGHT;
                allowDirectionChange = false;
            }
          } else {  // If a new direction has already been given since the last time the player was updated, queue up
                    //  this movement command to applied after the player is updated.
            if(e.getKeyCode()==KeyEvent.VK_UP && direction != DOWN)
            {
                pendingDirection = UP;
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN && direction != UP)
            {
                pendingDirection = DOWN;
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT && direction != RIGHT)
            {
                pendingDirection = LEFT;
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT && direction != LEFT)
            {
                pendingDirection = RIGHT;
            }
          }
        }
   
       @Override
       public void keyTyped(KeyEvent e) {
       }
       @Override
       public void keyReleased(KeyEvent e) {
        
       }
   
}
