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
  */

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

public class GameState
{
  
  public static enum Difficulty { EASY, MEDIUM, HARD }
  
  public static final long PLAYER_DEFAULT_MOVE_DELAY_EASY = 400;
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_EASY = 5000;
  public static final long SOUND_DEFAULT_SPAWN_DELAY_EASY = 1000;
  public static final long PLAYER_DEFAULT_MOVE_DELAY_MEDIUM = 250;
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_MEDIUM = 5000;
  public static final long SOUND_DEFAULT_SPAWN_DELAY_MEDIUM = 1000;
  public static final long PLAYER_DEFAULT_MOVE_DELAY_HARD = 150;
  public static final long FRUIT_DEFAULT_SPAWN_DELAY_HARD = 5000;
  public static final long SOUND_DEFAULT_SPAWN_DELAY_HARD = 1000;
  public static final double SCORE_MULT_EASY = 1.0;
  public static final double SCORE_MULT_MEDIUM = 2.0;
  public static final double SCORE_MULT_HARD = 4.0;
    
  public static final long TOKEN_LIFESPAN = 20000;
  
  
  // How many grid squares away that tokens are allowed to spawn.
  public static final int TOKEN_SPAWN_MIN_DISTANCE = 5;
  
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
  /** Default constructor.
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
    if (diff == Difficulty.EASY)
    {
      playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY_EASY;
      fruitSpawnDelay = FRUIT_DEFAULT_SPAWN_DELAY_EASY;
      soundSpawnDelay = SOUND_DEFAULT_SPAWN_DELAY_EASY;
      scoreMult = SCORE_MULT_EASY;
    }
    else if (diff == Difficulty.MEDIUM)
    {
      playerMoveDelay = PLAYER_DEFAULT_MOVE_DELAY_MEDIUM;
      fruitSpawnDelay = FRUIT_DEFAULT_SPAWN_DELAY_MEDIUM;
      soundSpawnDelay = SOUND_DEFAULT_SPAWN_DELAY_MEDIUM;
      scoreMult = SCORE_MULT_HARD; 
    }
    else if (diff == Difficulty.HARD)
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
  /** Starts the game with a countdown.
    */
  public void start()
  {
    generateInitialFruit();
    startTimers();
  }
  
  /** Connects the game state to the JPanel
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
    
    
    
    Timer playerTimer = new Timer((int)playerMoveDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {     
        player.updatePlayer();
        testPlayerBounds(LinguistLizard.GAME_COLUMNS, LinguistLizard.GAME_ROWS);
        if (player.collision(player.getHead()) || player.isDead())
        {
          killTimers();
          messageOutput.setText("GAME OVER.\nScore = "+Integer.toString(score));
          // update the server
          Score s = new Score("", score);
          s.obtainPlayerName((JFrame)SwingUtilities.getWindowAncestor(screen));
          
          if(s.send() == 200) {
            
          }
          else {
            JOptionPane.showMessageDialog((JFrame)SwingUtilities.getWindowAncestor(screen),
                                          "Network Error",
                                          "Could not upload score.",
                                          JOptionPane.ERROR_MESSAGE
                                         );
          }
        }
  
  
  
  
        updateTokens();
        screen.repaint();
      }  
    });
    playerTimer.start();
    timers.add(playerTimer);
    
    Timer fruitSpawnTimer = new Timer((int)fruitSpawnDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        Fruit newFruit = Fruit.randomFruit(difficulty);
        findSpawnLocation(newFruit);
        newFruit.markSpawn();
        tokens.add(newFruit);
        screen.repaint();
      }
    });
    fruitSpawnTimer.start();
    timers.add(fruitSpawnTimer);
    
    Timer soundSpawnTimer = new Timer((int)soundSpawnDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        if (currentFruit != null)
        {
          Sound newSound = Sound.randomSound(difficulty);
          findSpawnLocation(newSound);
          newSound.markSpawn();
          tokens.add(newSound);
          screen.repaint();
        }
      }
    });
    soundSpawnTimer.start();
    timers.add(soundSpawnTimer);

        
  }
  
  /** Creates all the fruit that will exist on the board at the start of the game
    */
  private void generateInitialFruit()
  {
    Fruit temp = new Cherry();
    temp.setCoord(new Coord(1,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Strawberry();
    temp.setCoord(new Coord(4,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Pear();
    temp.setCoord(new Coord(7,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Pineapple();
    temp.setCoord(new Coord(10,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Grape();
    temp.setCoord(new Coord(13,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Tomato();
    temp.setCoord(new Coord(16,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
    
    temp = new Watermelon();
    temp.setCoord(new Coord(19,12));
    temp.loadTokenImage();
    temp.markSpawn();
    tokens.add(temp);
  }
  
  /** Randomize token spawn until spawn location is valid
    * @return - Coord location of where token is spawned
    */
  private Coord findSpawnLocation(Token tok)
  {
    boolean found = false;
    while (!found)
    {
      found = true;
      tok.randomizeLocation();
      if (player.getTail().contains( tok.getCoord() ) 
            || tok.getCoord().distanceRaw(player.getHead()) < TOKEN_SPAWN_MIN_DISTANCE)
        found = false;
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
      
      
      if (System.currentTimeMillis() - tok.spawnTime > TOKEN_LIFESPAN)
        it.remove();
      else
      {        
        if (player.getHead().equals(tok.getCoord()))
        {
          if (currentFruit == null)
          {
            onTokenEaten(tok);
            tokens.clear();
            break;
          }
          onTokenEaten(tok);
          it.remove();
        }
      }
    }
  }
  
  /** Performs actions when a given token is eaten
    * @param eatenToken - Reference to the token that was eaten
    */
  private void onTokenEaten(Token eatenToken)
  {
    if (eatenToken instanceof Fruit)
    {
      Fruit eatenFruit = (Fruit)eatenToken;
      messageOutput.setText(eatenFruit.getTask());
      currentFruit = eatenFruit;
      score += (FRUIT_SCORE * scoreMult);
    }
    else if (eatenToken instanceof Sound)
    {
      Sound eatenSound = (Sound)eatenToken;
      if (currentFruit.getSoundColl().contains( eatenSound.getSound() ))
      {
        player.addToTail(1);
        score += (SOUND_RIGHT_SCORE * scoreMult);
      } else {
        player.subtractFromTail(2);
        score += (SOUND_WRONG_SCORE * scoreMult);
      }
    }
      
  }
  
  
}
