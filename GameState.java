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
  * 03.07 - Added basic rendering functionality.
  * 06.07 - Added looping and basic player updating.
  * 10.07 - Fixed minor details with bounding player elements and aligned it with recent Player.java updates.
  * 13.07 - Reconfigured how main loop worked, from being a standard while loop with frame redrawing, to timers with
  *          action listeners to trigger redraw.
  * 17.07 - Added most functionality to finalize the game, including updating tokens, player interaction with tokens,
  *          itegration with the new LinguistLizard Swing window, difficulty variance, setting up initial board state,
  *          and detecting game over.
  * 18.07 - Added more polish, fixed bugs, and integrated uploading of scores.
  */

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;  // Needed to state explicitly, as it cleared up ambiguity of Timer class.
import javax.swing.text.JTextComponent;

public class GameState
{
  // Enum to represent different difficulty levels
  public static enum Difficulty { EASY, MEDIUM, HARD }
  
  // Static variables associated with the various difficulty levels
  public static final long PLAYER_DEFAULT_MOVE_DELAY_EASY = 400;  // Time in milliseconds between each player update
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_EASY = 5000; // Time in milliseconds between each fruit spawn 
  public static final long SOUND_DEFAULT_SPAWN_DELAY_EASY = 1000; // Time in milliseconds between each sound spawn
  public static final long PLAYER_DEFAULT_MOVE_DELAY_MEDIUM = 250;
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_MEDIUM = 5000;
  public static final long SOUND_DEFAULT_SPAWN_DELAY_MEDIUM = 1000;
  public static final long PLAYER_DEFAULT_MOVE_DELAY_HARD = 150;
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_HARD = 5000;
  public static final long SOUND_DEFAULT_SPAWN_DELAY_HARD = 1000;
  public static final double SCORE_MULT_EASY = 1.0;  // Score multiplier
  public static final double SCORE_MULT_MEDIUM = 2.0;
  public static final double SCORE_MULT_HARD = 4.0;
    
  public static final long TOKEN_LIFESPAN = 20000;  // Duration that tokens will remain before despawning
  
  
  // Minimum number of grid squares away that tokens are allowed to spawn.
  public static final int TOKEN_SPAWN_MIN_DISTANCE = 6;
  
  // Score values for game actions
  public static final int FRUIT_SCORE = 1000;
  public static final int SOUND_RIGHT_SCORE = 5000;
  public static final int SOUND_WRONG_SCORE = -8000;
  
  
  private Player player;
  private ArrayList<Token> tokens;
  Difficulty difficulty;
  JPanel screen;
  private long playerMoveDelay, fruitSpawnDelay, soundSpawnDelay;
  private ArrayList<Timer> timers;
  private JTextComponent messageOutput;
  private Fruit currentFruit;
  private int score;
  private double scoreMult;
  
  ///// CONSTRUCTORS
  /** Default constructor.  Initializes variables and sets default values.
    */
  public GameState()
  {
    player = new Player();
    tokens = new ArrayList<Token>();
    setDifficulty(Difficulty.EASY);
    timers = new ArrayList<Timer>();
    currentFruit = null;
    score = 0;
  }
  
  
  
  ///// ACCESSOR METHODS
  /** Sets the current game's difficulty level.
    * @param diff - Member of Difficulty enum representing difficulty to set
    */
  public void setDifficulty(Difficulty diff)
  {
    difficulty = diff;
    if (diff == Difficulty.EASY)  // If difficulty is easy, set variables to their easy values.
    {
      playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY_EASY;
      fruitSpawnDelay = FRUIT_DEFAULT_SPAWN_DELAY_EASY;
      soundSpawnDelay = SOUND_DEFAULT_SPAWN_DELAY_EASY;
      scoreMult = SCORE_MULT_EASY;
    }
    else if (diff == Difficulty.MEDIUM)  // If difficulty is medium, set variables to their medium values.
    {
      playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY_MEDIUM;
      fruitSpawnDelay = FRUIT_DEFAULT_SPAWN_DELAY_MEDIUM;
      soundSpawnDelay = SOUND_DEFAULT_SPAWN_DELAY_MEDIUM;
      scoreMult = SCORE_MULT_HARD; 
    }
    else if (diff == Difficulty.HARD)  // If difficulty is hard, set variables to their hard values.
    {
      playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY_HARD;
      fruitSpawnDelay = FRUIT_DEFAULT_SPAWN_DELAY_HARD;
      soundSpawnDelay = SOUND_DEFAULT_SPAWN_DELAY_HARD;
      scoreMult = SCORE_MULT_HARD;
    }    
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
  /** Sets the initial board state and starts the game.
    */
  public void start()
  {
    generateInitialFruit();
    startTimers();
  }
  
  /** Connects the game state to the JPanel
    * @param screen - JPanel to connect
    */
  public void connectScreen(JPanel screen)
  {
    this.screen = screen;
  }
  
  /** Connects the game state to the text field to output messages to
    * @param field - Text field to connect
    */
  public void connectMessageOutput(JTextComponent field)
  {
    messageOutput = field;
  }
  
  /** Stops all timers and clears the timer list.
    */
  public void killTimers()
  {
    for (Timer t : timers)
    {
      t.stop();
    }
    timers.clear();
  }
  
  /** Stops all timers and clears the timer list, then regenerates the timer list.
    * Mostly used for after the difficulty changes.
    */
  public void restartTimers()
  {
    killTimers();
    startTimers();
  }
    
  
  
  
  /// PRIVATE METHODS
  ///////////////////////////////////
  /** Starts all the timers that perform the functions of the game.
    */
  private void startTimers()
  {
    
    // Creates repeating timer with action listener to handle updating the player.  Every time the player is updated,
    //  the screen is redrawn.
    Timer playerTimer = new Timer((int)playerMoveDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {     
        player.updatePlayer();  // Move the player in its current direction
        testPlayerBounds(LinguistLizard.GAME_COLUMNS, LinguistLizard.GAME_ROWS);  // Keep player on the board
        if (player.collision(player.getHead()) || player.isDead())  // Test for if the player has died
        {
          // If the player has died, stop the game from moving forward, outputs score, and queries the player for their
          //  name, and uploads the name and score to website.
          killTimers();  
          messageOutput.setText("GAME OVER.\nScore = "+Integer.toString(score));
          // update the server
          Score s = new Score("", score);
          s.obtainPlayerName((JFrame)SwingUtilities.getWindowAncestor(screen));
          
          if(s.send() == 200) {
            //
          }
          else {  // Score upload failed.  Display error.
            JOptionPane.showMessageDialog((JFrame)SwingUtilities.getWindowAncestor(screen),
                                          "Network Error",
                                          "Could not upload score.",
                                          JOptionPane.ERROR_MESSAGE
                                         );
          }
        }
        updateTokens();  // Perform all actions necessary for updating tokens and testing their player interactions
        screen.repaint();  // Redraw the screen
      }  
    });
    playerTimer.start();  // Start timer and add the timer to the timer list
    timers.add(playerTimer);
    
    // Creates repeating timer with action listener to handle spawning fruit tokens.  Every time a fruit is added,
    //  the screen is redrawn.
    Timer fruitSpawnTimer = new Timer((int)fruitSpawnDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        // Create a random fruit, place it on the board randomly, and add it to the token list, then redraw screen.
        Fruit newFruit = Fruit.randomFruit(difficulty);  
        findSpawnLocation(newFruit);
        newFruit.markSpawn();
        tokens.add(newFruit);
        screen.repaint();
      }
    });
    fruitSpawnTimer.start();  // Start timer and add the timer to the timer list
    timers.add(fruitSpawnTimer);
    
    // Creates repeating timer with action listener to handle spawning sound tokens.  Every time a sound is added,
    //  the screen is redrawn.
    Timer soundSpawnTimer = new Timer((int)soundSpawnDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        if (currentFruit != null)
        {
          // Create a random sound, place it on the board randomly, and add it to the token list, then redraw screen.
          Sound newSound = Sound.randomSound(difficulty);
          findSpawnLocation(newSound);
          newSound.markSpawn();
          tokens.add(newSound);
          screen.repaint();
        }
      }
    });
    soundSpawnTimer.start();  // Start timer and add the timer to the timer list
    timers.add(soundSpawnTimer);

        
  }
  
  /** Creates all the fruit that will exist on the board at the start of the game
    */
  private void generateInitialFruit()
  {
    // Each block creates a different fruit, places at a predetermined place on the board and adds it to the token list
    
    // Create cherry
    Fruit temp = new Cherry();
    temp.setCoord(new Coord(1,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create strawberry
    temp = new Strawberry();
    temp.setCoord(new Coord(4,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create pear
    temp = new Pear();
    temp.setCoord(new Coord(7,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create pineapple
    temp = new Pineapple();
    temp.setCoord(new Coord(10,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create grape
    temp = new Grape();
    temp.setCoord(new Coord(13,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create tomato
    temp = new Tomato();
    temp.setCoord(new Coord(16,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    // Create watermelon
    temp = new Watermelon();
    temp.setCoord(new Coord(19,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
  }
  
  /** Randomize token spawn until spawn location is valid
    * @param tok - Token whose spawn needs to be tested
    * @return - Coord location of where token is spawned
    */
  private Coord findSpawnLocation(Token tok)
  {
    /** I have two methods of doing this, and this is a kind of problem I run into a lot.  The method I chose to
      * utilize generates a random spawn position and then tests if it is valid.  If not, it tries again from
      * the start.  This means best case, it generates a valid location on its first attempt.  In worst case, it gets
      * stuck forever.  I think its average case is pretty good in the context of this game.
      * The alternative is that I go through and test each coordinate square on the entire board.  If a square is
      * valid, I add it to a list.  After testing all squares, I randomly choose a coordinate from this list.  This
      * will always run in constant time, but will run much slower than the other method's best case, and probably
      * slower than its average case.  However, it can't get stuck.  I'm curious as to which is better practice, or
      * if it is entirely dependant on context.  -- Christopher Dilley
      */
    
    boolean found = false;
    
    while (!found)  // While a valid spawn location has not yet been found
    {
      found = true;
      tok.randomizeLocation();  // Try randomizing a location
      // If the coordinate exists as part of the player's tail or is within a certain distance from the player's head,
      //  then the location is invalid.
      if (player.getTail().contains( tok.getCoord() )  
            || tok.getCoord().distanceRaw(player.getHead()) < TOKEN_SPAWN_MIN_DISTANCE) 
        found = false;
      // If the coordinate is already taken by an existing token, then the location is invalid.
      for (Token oldToken : tokens)
      {
        if (oldToken.getCoord().equals(tok.getCoord()))
          found = false;
      }
    }
    return tok.getCoord();
  }
  
  /** Tests to see if the player is out of bounds, and loops them back around
    */
  private void testPlayerBounds(int maxCol, int maxRow)
  {
    // If the player (P) is beyond bound limit (B), then player should instead be placed at (P-B)
    // If the player (P) is less than 0, then player should instead be placed at (B+P)
    if (player.getHead().getX() >= maxCol)
       player.getHead().setX(player.getHead().getX() - maxCol);
    else if (player.getHead().getX() < 0)
      player.getHead().setX(maxCol + player.getHead().getX());
    if (player.getHead().getY() >= maxRow)
      player.getHead().setY(player.getHead().getY() - maxRow);
    if (player.getHead().getY() < 0)
      player.getHead().setY(maxRow + player.getHead().getY());
    
    // Iterate through all tail elements and apply the same logic to them as the player
    for (Coord elem : player.getTail())
    {
      if (elem.getX() >= maxCol)
        elem.setX(elem.getX() - maxCol);
      else if (elem.getX() < 0)
        elem.setX(maxCol + elem.getX());
      else if (elem.getY() >= maxRow)
        elem.setY(elem.getY() - maxRow);
      else if (elem.getY() < 0)
        elem.setY(maxRow + elem.getY());      
    }
  }
  
  /** Spawns new tokens and updates all tokens
    */
  private void updateTokens()
  {
    for (Iterator<Token> it = tokens.iterator(); it.hasNext(); )
    {
      Token tok = it.next();
      if (currentFruit == null) // If a fruit has not been chosen, don't despawn tokens
        tok.markSpawn();  
      
      
      if (System.currentTimeMillis() - tok.spawnTime > TOKEN_LIFESPAN)  // If the token has lived its lifespan, kill it
        it.remove();
      else
      {        
        if (player.getHead().equals(tok.getCoord()))  // If the player eats this token
        {
          if (currentFruit == null)  // If we're in the initial fruit picking mode, destroy all other tokens
          {
            onTokenEaten(tok);
            tokens.clear();
            break;
          }
          onTokenEaten(tok);  // Apply the effect of eating this token to the gamestate
          it.remove();  // Then kill the token
        }
      }
    }
  }
  
  /** Performs actions when a given token is eaten
    * @param eatenToken - Reference to the token that was eaten
    */
  private void onTokenEaten(Token eatenToken)
  {
    if (eatenToken instanceof Fruit)  // If the token is a fruit
    {
      Fruit eatenFruit = (Fruit)eatenToken;
      messageOutput.setText(eatenFruit.getTask());  // Set the text to indicate the new fruit's task
      currentFruit = eatenFruit;  // Change the game to accept the new fruit's valid sound tokens
      score += (FRUIT_SCORE * scoreMult);  // Increase the player's score
    }
    else if (eatenToken instanceof Sound)  // If the token is a sound
    {
      Sound eatenSound = (Sound)eatenToken;
      if (currentFruit.getSoundColl().contains( eatenSound.getSound() ))  // If the sound was a correct sound
      {
        player.addToTail(1);  // Grow the player's tail and grant points
        score += (SOUND_RIGHT_SCORE * scoreMult);
      } else {
        player.subtractFromTail(2);  // Shrink the player's tail and take away points (add a negative number)
        score += (SOUND_WRONG_SCORE * scoreMult);
      }
    }
      
  }
  
  
}
