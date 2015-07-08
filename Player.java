/*
 * Player class of the Linguist Lizard game.
 * Takes care of the functionality and movements of the lizard object.
 */
 
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.*;
 
public class Player {
   
    private Coord head; //head of the lizard, moving/controlled
    private ArrayList<Coord> tail; //array of tokens forming the tail of the lizard
    private int direction; //direction of moving
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3; //values for direction
    private boolean collisionOccured; //if lizard walks into himself
    private boolean gameOver = false;
   
    /**
     * Construct a new linguistic lizard.
     */
    public Player() {
        head = new Coord(1,1);
        tail = new ArrayList<Coord>();
        direction = RIGHT;
    }
   
    /**
     * controll
     */
    public void updatePlayer() {  
       
        //move head in given direction
        //tail follows head
        Coord headPos = head;
        switch(direction)
        {
            case UP: if(!collision(head.getAbove(1)))
                       {
                        head.moveUp(1);
                        tailFollow(headPos);
                        break;
                        } else {
                        gameOver= true;
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
            default: //something must be wrong, probably game over.
        }
    }
   
    /**
     * Make tail follow the head / every part move
     * @param oldHeadPos position that head had before it was moved
     */
    private void tailFollow(Coord oldHeadPos) {
        //each coord in the tail array moves one step,
        //the new place is the place that the part in front (at its index -1) had before the moving
        if(!tail.isEmpty()) {
           
            ArrayList<Coord> copy = new ArrayList<>(); //copy tail to save positions
            for(int i = 0; i < tail.size(); i++)
            {
                copy.add(i, tail.get(i));
            }
 
            tail.set(0, oldHeadPos);//previous position of head becomes first tail part
 
            if(tail.size() > 1) {
                for(int i = 1; i < tail.size(); i++) //set all further elements in tail to what the part in front of them
                {
                    tail.set(i, copy.get(i-1));  //previously was (and still is in "copy")
                }
            }
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
     * Add a food token to the end of the tail of the lizard (on the opposite side of the
     * second last item of the tail compared to the last (so that its straight)
     * @param add - the
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
     * @param place
     * @return
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
   
    //changing direction of moving depending on key actions (ARROW KEYS)
    private class Keys extends KeyAdapter implements KeyListener {
        public void directionGiven(KeyEvent e)
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
   
    }
   
}
