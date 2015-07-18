/* class Fruit
 *
 * Credits for the Fruit icons go to: http://www.flaticon.com/free-icon/
 */

/**
 * @author Patricia
 */

import java.util.ArrayList;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.*;

public class Fruit extends Token {
   
    private ArrayList<String> soundColl = new ArrayList<String>(); //The collection of sounds which share a certain feature
    private String sortOfFruit; //can be used as argument for the loadFruitImage() method
    private String task; //display the task to be fulfilled
     
    /*
     * Default constructor
     */
    public Fruit()  {
        soundColl = new ArrayList<String>();
        sortOfFruit = "no sort of fruit";
        task = "no task";
    }
    
    /*
     * Constructor that takes an ArrayList as an argument
     * @param aList - a list of sounds to be saved in soundColl
     */
    public Fruit(ArrayList<String> aList)   {
        soundColl.clear();
        
        for(String item: aList)
            soundColl.add(item);
    }
    
    /*
     * Method to set the content of soundColl to the elements of ArrayList aList
     * @param aList - the ArrayList which contains the elements to be put into soundColl
     */
    public void setSoundColl(ArrayList<String> aList)   {
        soundColl.clear();
        
        for(String item: aList)
            soundColl.add(item);
    }
    
     /*
     * Method to get the content of soundColl
     * @return soundColl - the instance variable ArrayList<String> soundColl
     */ 
    public ArrayList<String> getSoundColl() {
        return soundColl;
    }
    
    /*
     * Set the sort of fruit to aSort
     * @param aSort - the sort of Fruit
     */
    public void setSortOfFruit(String aSort)  {
        sortOfFruit = aSort;
    }
    
    /*
     * Return the sort of fruit
     * @return the sortOfFruit
     */
    public String getSortOfFruit()    {
        return sortOfFruit;
    }
    
    
    /*
     * Set the task to aTask
     * @param aTask - the task to be fulfilled
     */
    public void setTask(String aTask)  {
        task = aTask;
    }
    
    /*
     * Return the task
     * @return the task
     */
    public String getTask()    {
        return task;
    }
    
    
     /*
     * Method to check whether a Sound object belongs to a certain sound collection
     * @return true if the sound is in the collection, false otherwise
     */
    public boolean containsSound(Sound aSound)  {
        if(aSound == null)
            return false;
        
        String aName = aSound.getSound();
        
        return soundColl.contains(aName);
    }
       
     /*
     * Method for loading an image of a fruit to render it to the screen, and resize it as needed.
     * @Override Token.loadTokenImage()
     */
    public void loadTokenImage() {
      
        ImageIcon image = new ImageIcon("Icons/Fruit/" + getSortOfFruit() + ".png");
        tokenPic = image.getImage().getScaledInstance(Screen.GAME_COLUMN_WIDTH-(Screen.TOKEN_MARGINS*2), 
                                                      Screen.GAME_ROW_HEIGHT-(Screen.TOKEN_MARGINS*2),
                                                      Image.SCALE_SMOOTH);
    }
    
    
    /** Static method for generating a random fruit
      * @param difficulty - Limits the selection based on current game difficulty
      * @return - The generated fruit
      */
    public static Fruit randomFruit(GameState.Difficulty difficulty)
    {
      ArrayList<Fruit> allFruits = new ArrayList<Fruit>();
      
      // Assemble list of possible fruits to choose from.
      
      // Fruits for EASY-MEDIUM-HARD
      allFruits.add(new Pear());
      allFruits.add(new Pineapple());
      allFruits.add(new Grape());
      allFruits.add(new Tomato());
      allFruits.add(new Watermelon());
      allFruits.add(new Strawberry());
      allFruits.add(new Cherry());
      
      // Fruits for MEDIUM-HARD
      if (difficulty != GameState.Difficulty.EASY)
      {
        allFruits.add(new Apple());
        allFruits.add(new Banana());
        allFruits.add(new Orange());
        allFruits.add(new Eggplant());
        allFruits.add(new Lemon());
        allFruits.add(new Mushroom());
        allFruits.add(new Radish());
        allFruits.add(new Broccoli());
      }
      
      // Fruits for HARD
      if (difficulty == GameState.Difficulty.HARD)
      {
        allFruits.add(new Salad());
        allFruits.add(new Pumpkin());
        allFruits.add(new Carrot());
      }
      
      // Randomly select a fruit from this list and return it
      int rVal = (int) Math.floor(Math.random()*allFruits.size());
      System.out.println(Integer.toString(rVal));
      allFruits.get(rVal).loadTokenImage();
      return allFruits.get(rVal);
    }
      
      
    
}
