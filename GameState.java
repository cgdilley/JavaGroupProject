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
  
  /** Default constructor.
    */
  public GameState()
  {
    player = null;
    tokens = new ArrayList<Token>();
    difficulty = Difficulty.EASY;
  }
  
  /** Sets the current game's difficulty level.
    * @param diff - Member of Difficulty enum representing difficulty to set
    */
  public void setDifficulty(Difficulty diff)
  {
    difficulty = diff;
  }
  
  /** Starts the game with a countdown.
    */
  public void start()
  {
    performCountdown();
    mainLoop();
  }
  
  
  
  /// PRIVATE METHODS
  ///////////////////////////////////
  /** MAIN LOOP.  Performs all repeated actions for processing and rendering the game.
    */
  private void mainLoop()
  {
    
  }
  
  /** Performs 3-2-1-go action before game begins
    */
  private void performCountdown() {}
  
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