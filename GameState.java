/** GameState.java
  * 
  * Class for holding all information about the game state, and looping on that data
  * to run the game itself.
  * 
  * by:  Christopher Dilley
  * modified by: -
  * for:  Java Group Project (2015)
  *        aka. Linguist Lizard
  * 
  * CHANGELOG:
  * 02.07 - Initial write-up (outline) of class.  (Chris)
  */

import java.util.*;

public class GameState
{
  
  public static enum Difficulty { EASY, MEDIUM, HARD }
  
  private Player player;
  private ArrayList<Token> tokens;
  Difficulty difficulty;
  Screen screen;
  
  ///// CONSTRUCTORS
  /** Default constructor.
    */
  public GameState()
  {
    player = new Player();
    tokens = new ArrayList<Token>();
    difficulty = Difficulty.EASY;
  }
  
  
  
  ///// ACCESSOR METHODS
  /** Sets the current game's difficulty level.
    * @param diff - Member of Difficulty enum representing difficulty to set
    */
  public void setDifficulty(Difficulty diff)
  {
    difficulty = diff;
  }
  
  /** Gets the current game's difficulty level
    * @return - Member of Difficulty enum representing current difficulty setting
    */
  public Difficulty getDifficulty()
  {
    return difficulty;
  }
  
  /** Gets the Player object present within the game.
    * @return - The game's player object.
    */
  public Player getPlayer()
  {
    return player;
  }
  
  /** Gets the list of tokens present within the game.
    * @return - The game's list of tokens
    */
  public ArrayList<Token> getTokens()
  {
    return tokens;
  }
  
  
  ///// FUNCTIONAL METHODS
  /** Starts the game with a countdown.
    */
  public void start()
  {
    initializeScreen();
    performCountdown();
    mainLoop();
  }
  
  
  
  /// PRIVATE METHODS
  ///////////////////////////////////
  /** MAIN LOOP.  Performs all repeated actions for processing and rendering the game.
    */
  private void mainLoop()
  {
    while (screen.isOpen())
    {
      screen.repaint();
    }
  }
  
  /** Performs all actions required to initialize the screen.
    */
  private void initializeScreen()
  {
    screen = new Screen(this);
  }
  
  /** Performs 3-2-1-go action before game begins
    */
  private void performCountdown() 
  {
  
  }
  
  /** Spawns new tokens and updates all tokens
    */
  private void updateTokens()
  {
    for (Token tok : tokens)
    {
    }
  }
  
  /** Performs actions when a given token is eaten
    * @param eatenToken - Reference to the token that was eaten
    */
  private void onTokenEaten(Token eatenToken)
  {
    
  }
  
  
}