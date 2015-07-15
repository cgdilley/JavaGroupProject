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

public class Fruit extends Token {
   
    private ArrayList<String> soundColl = new ArrayList(); //The collection of sounds which share a certain feature
    private String sortOfFruit; //can be used as argument for the loadFruitImage() method
    private String task; //display the task to be fulfilled
    
    private final int FRUIT_SIZE = 10;
    private final int RANDOM_POS = 29;
    public Image fruitPic;
    public Coord fruit;
    
    /*
     * Default constructor
     */
    public Fruit()  {
        soundColl = null;
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
     * Method to locate the Fruit in the game
     * @Override Token.locateToken()
     */
    public void locateToken()  {
        int i = (int) (Math.random()*RANDOM_POS);
        setTokenX(i*FRUIT_SIZE);
        
        int j = (int) (Math.random()*RANDOM_POS);
        setTokenY(i*FRUIT_SIZE);
        
        fruit = new Coord(getTokenX(), getTokenY());
    }
    
    /*
     * Method for loading an image of a fruit to render it to the screen
     * @Override Token.loadTokenImage()
     */
    public void loadTokenImage() {
        ImageIcon image = new ImageIcon(getSortOfFruit() + ".png");
        fruitPic = image.getImage();
    }
}
