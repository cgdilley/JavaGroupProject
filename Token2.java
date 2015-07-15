
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
    private final int TOKEN_SIZE = 10;
    private final int RANDOM_POS = 29;
    private int token_x, token_y; //x and y coordinate of the token
    public Image tokenPic;
    public Coord token;
    
    /*
     * Method to locate the Sound in the game
     */
    public void locateToken()  {
        int i = (int) (Math.random()*RANDOM_POS);
        setTokenX(i*TOKEN_SIZE);
        
        int j = (int) (Math.random()*RANDOM_POS);
        setTokenY(i*TOKEN_SIZE);
        
        token = new Coord(token_x, token_y);
    }
    
    /*
     * Method for loading an image of a sound to render it to the screen
     */
    public void loadTokenImage() {
        ImageIcon image = new ImageIcon(tokenName + ".png");
        tokenPic = image.getImage();
    }
    
    /*
     * Set token_x to x
     * @param x - the x coordinate
     */
    public void setTokenX(int x)    {
        token_x = x;
    }
    
    /*
     * Return token_x, the x coordinate of token
     * @return token_x
     */
    public int getTokenX()  {
        return token_x;
    }
    
    
    /*
     * Set token_y to y
     * @param y - the y coordinate
     */
    public void setTokenY(int y)    {
        token_y = y;
    }
    
    /*
     * Return token_y, the y coordinate of token
     * @return token_y
     */    
    public int getTokenY()  {
        return token_y;
    }
            
}
