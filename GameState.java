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
  
  public static final long PLAYER_DEFAULT_MOVE_DELAY = 200;
  
  private Player player;
  private ArrayList<Token> tokens;
  Difficulty difficulty;
  Screen screen;
  private long playerMoveDelay;
  
  ///// CONSTRUCTORS
  /** Default constructor.
    */
  public GameState()
  {
    player = new Player();
    tokens = new ArrayList<Token>();
    difficulty = Difficulty.EASY;
    playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY;
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
    long playerMoveTime = 0;
    
    //// TEMPORARY STUFF FOR TESTING
    long playerGrowTime = 0;
    int playerGrowDelay = 500;
    
    while (true)
    {
      long currTime = System.currentTimeMillis();
      
      if (currTime - playerMoveTime > playerMoveDelay)
      {
        player.updatePlayer();
        playerMoveTime = currTime;
        
        if (player.getHead().getX() >= Screen.GAME_COLUMNS)
          player.getHead().setX(0);
        else if (player.getHead().getX() < 0)
          player.getHead().setX(Screen.GAME_COLUMNS-1);
        if (player.getHead().getY() >= Screen.GAME_ROWS)
          player.getHead().setY(0);
        if (player.getHead().getY() < 0)
          player.getHead().setY(Screen.GAME_ROWS-1);          
      }
      /*
      if (currTime - playerGrowTime > playerGrowDelay)
      {
        player.addToTail(1);
        playerGrowTime = currTime;
      }*/
      
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