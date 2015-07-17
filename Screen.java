/** Screen.java
  * 
  * Used for creating the JFrame and rendering all graphics for the game.
  * 
  * by:
  * modified by:  Christopher Dilley
  * for:  Java Group Project (2015)
  * 
  * CHANGELOG:
  * 03.07.15 - Added basic initial functionality (Chris)
  * 04.07.15 - Fixed comment typo, changed player color to be a static constant (Chris)
  * 06.07.15 - Changed paint() to paintComponent(), also calling super.paintComponent to wipe screen.  Also added
  *            code for rendering tail elements, but is not currently active.
  * 10.07.15 - Activated code for rendering tail
  * 13.07.15 - Changed paintComponent() back to paint().  Not sure it actually matters.
  */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Screen extends JPanel
{
  ///// STATIC VARIABLES, ALLOWING ONE TO DESCRIBE (AND EASILY CHANGE) CONSTANTS USED FOR RENDERING
  // The dimensions of the entire screen region.
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  
  // The dimensions of the game board
  public static final int GAME_WIDTH = 500;
  public static final int GAME_HEIGHT = 500;
  // The amount of space to leave between the edges of the screen and the game board
  public static final int GAME_MARGINS = 30;
  
  // The number of rows and columns of the game board and their inferred dimensions
  public static final int GAME_ROWS = 20;
  public static final int GAME_COLUMNS = 20;
  public static final int GAME_COLUMN_WIDTH = GAME_WIDTH / GAME_COLUMNS;
  public static final int GAME_ROW_HEIGHT = GAME_HEIGHT / GAME_ROWS;
  
  // The amount of space surrounding the player object inside its grid location
  public static final int PLAYER_MARGINS = 2;
  public static final Color PLAYER_COLOR_HEAD = new Color(0,255,0);  // Green
  public static final Color PLAYER_COLOR_TAIL = new Color(255,0,0);  // Red
  
  public static final int TOKEN_MARGINS = 1;
  
  
  
  ///// PRIVATE VARIABLES
  private GameState game;
  private JFrame frame;
  
  /** Constructor, requires a GameState object to refer to for rendering objects.
    */  
  public Screen()
  {
    super();
    //init(game);
  }
  
  /** Connects the game state to the screen
    * @param game - game state to connect
    */
  public void connectGame(GameState game)
  {
    this.game = game;
  }
  
  /** Initializes the screen with new JFrame, using default screen options.
    */
  public JFrame init(GameState game)
  {
    return this.init(game, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);
  }
  
  /** Initializes the screen with new JFrame with given width and height.
    * @param width - Width of screen to create
    * @param height - Height of screen to create
    */
  public JFrame init(GameState game, int width, int height)
  {
    this.setPreferredSize( new Dimension(GAME_WIDTH + (GAME_MARGINS*2), GAME_HEIGHT + (GAME_MARGINS*2)) );
    
    frame = new JFrame("Linguist Lizard");
    frame.add(this);
    
    
    frame.addKeyListener(game.getPlayer());
    
    this.game = game;
    
    return frame;
  }
  
  /** Override of JPanel's paint method, handles all rendering.
    * @param g - Graphics object to manipulate for rendering objects.
    */
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    Graphics2D g2d = (Graphics2D) g;
    
    /* EXPLANATIONS FOR ALL CONSTANTS CAN BE FOUND IN THEIR DECLARATIONS */
    
    // Draw all column boundaries, starting from the left side of the game board (0 + the margins for the game board) 
    //  to the right side of the game board (0 + the margins for the game board + the width of the game board)
    //  with an interval of the width of each column.
    for (int x = GAME_MARGINS; x <= GAME_MARGINS+GAME_WIDTH; x += GAME_COLUMN_WIDTH)
    {
      g2d.draw(new Line2D.Float(x, GAME_MARGINS, x, GAME_MARGINS + GAME_HEIGHT) );
    }
    // Draw all row boundaries, starting from the top side of the game board (0 + the margins for the game board) 
    //  to the bottom side of the game board (0 + the margins for the game board + the height of the game board)
    //  with an interval of the height of each row.
    for (int y = GAME_MARGINS; y <= GAME_MARGINS+GAME_HEIGHT; y += GAME_ROW_HEIGHT)
    {
      g2d.draw(new Line2D.Float(GAME_MARGINS, y, GAME_MARGINS + GAME_WIDTH, y) );
    }
    
    // Draw player's head
    // The drawing position is calculated by taking the player's coordinate location (a number between 0 and
    //  the row/column dimensions minus 1) multiplied by the width/height of the columns/rows, and then adding in the
    //  the margins for the game board and the margins for the player.  This gives you the top-left corner of the area
    //  in which to draw the player.  Then the width/height of the player to be drawn is the width/height of the
    //  columns/rows minus the space required for margins on either side.
    g2d.setPaint(PLAYER_COLOR_HEAD);
    g2d.fill(new Ellipse2D.Float(GAME_MARGINS+PLAYER_MARGINS + (GAME_COLUMN_WIDTH * game.getPlayer().getHead().getX()),
                                 GAME_MARGINS+PLAYER_MARGINS + (GAME_ROW_HEIGHT * game.getPlayer().getHead().getY()),
                                 GAME_COLUMN_WIDTH - (PLAYER_MARGINS*2),
                                 GAME_ROW_HEIGHT - (PLAYER_MARGINS*2)));
    // Perform similar math to determine rendering of all tail elements
    g2d.setPaint(PLAYER_COLOR_TAIL);
    for (Coord elem : game.getPlayer().getTail())
    {
      g2d.fill(new Ellipse2D.Float(GAME_MARGINS+PLAYER_MARGINS + (GAME_COLUMN_WIDTH * elem.getX()),
                                 GAME_MARGINS+PLAYER_MARGINS + (GAME_ROW_HEIGHT * elem.getY()),
                                 GAME_COLUMN_WIDTH - (PLAYER_MARGINS*2),
                                 GAME_ROW_HEIGHT - (PLAYER_MARGINS*2)));
    }
    
    
    for (Token tok : game.getTokens())
    {
      g.drawImage(tok.getImage(), 
                  GAME_MARGINS + TOKEN_MARGINS + (GAME_COLUMN_WIDTH * tok.getCoord().getX()),
                  GAME_MARGINS + TOKEN_MARGINS + (GAME_ROW_HEIGHT * tok.getCoord().getY()),
                  null);
      
    }
    
  }
  
  /** Return whether JFrame is active or not.
    * @return - Returns true if JFrame is active, false otherwise.
    */
  public boolean isOpen()
  {
    return frame.isActive();
  }
  
  
}
  
  
