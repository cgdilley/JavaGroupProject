
/* class Token
 * Used as base class for Fruit and Sound
 */

/**
 * @author Patricia
 */

import java.awt.Image;
import javax.swing.ImageIcon;


public class Token {
    
    private String tokenName; //can be used as argument for the loadSoundImage() method
    
    public Image tokenPic;
    public Coord coord;
    public long spawnTime;
    
    /*
     * Method to locate the Sound in the game
     */
    public Coord randomizeLocation()  {
      if (coord==null)
        coord = new Coord();
      coord.set((int) Math.floor(Math.random()*Screen.GAME_COLUMNS), 
                  (int) Math.floor(Math.random()*Screen.GAME_ROWS));     
        
      return coord;
    }
    
    /*
     * Method for loading an image of a sound to render it to the screen
     */
    public void loadTokenImage() {
        ImageIcon image = new ImageIcon(tokenName + ".png");
        tokenPic = image.getImage();
    }
    
    /*
     * Setter method for the coordinate
     * @param coord - the coordinate value to set for this token
     */
    public void setCoord(Coord coord)    {
        this.coord = coord;
    }
    
    /*
     * Getter method for the coordinate
     * @return - the coordinate value of this token
     */
    public Coord getCoord()  {
        return coord;
    }
    
    /** Getter method for the image
      * @return - this token's image
      */
    public Image getImage()
    {
      return tokenPic;
    }
    
    /** Sets the spawn time for this token
      */
    public void markSpawn()
    {
      spawnTime = System.currentTimeMillis();
    }
    
    /** Gets the spawn time of this token
      * @return - The time that this token was spawned
      */
    public long getSpawnTime()
    {
      return spawnTime;
    }
            
}
