/*
 * Main class Linguist Lizard.
 * Creates a screen of the game with the main menu and JMenu items 
 * to restart and exit the game,
 * as well as to choose the difficulty level.
 *
 * @author anastasia
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.geom.*;


public class LinguistLizard extends JPanel {
  
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
  
   private Menu menu;
   private JMenuItem menuItem;
   private GameState game;
   private JFrame frame;
   private JTextArea gameMessage;
   Screen screen;
   
  /**
 *Creation of the main Game window
 * with the main menu and JMenu items 
 * which allow to restart and exit the game and choose 
 * the level of difficulty
 */ 
   LinguistLizard() 
   {
   }
   
    public void window (){ // creation of the game frame
     game = new GameState(); 
     
     screen = new Screen(); 
     frame = screen.init(game);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
     frame.setResizable(false);
     frame.setVisible(true);
     frame.setLocationRelativeTo(null);
     frame.getContentPane().setBackground(Color.lightGray);
     
     JMenuBar menubar = new JMenuBar(); //creates the main menubar
     frame.setJMenuBar(menubar);
     JMenu mainmenu = new JMenu ("Game");//creates the main menu
     mainmenu.setMnemonic(KeyEvent.VK_F);
     menubar.add( mainmenu);
     
     JMenuItem restart = new JMenuItem("Restart"); //creates the first menuitem 
     mainmenu.add (restart); //adds it to the main menu
     //restart.setMnemonic(KeyEvent.VK_P);
     restart.addActionListener(new ActionListener ()
           {public void actionPerformed(ActionEvent e){restart();}}); //restarts the game if the user clicks on the first menuitem in the main menu
    
     JMenuItem exit = new JMenuItem("Exit");//creates the second menuitem 
     mainmenu.add(exit);//adds it to the main menu
     exit.addActionListener(new ActionListener()  //exit the game if the user clicks on the second menuitem in the main menu
                             {public void actionPerformed(ActionEvent e) {
             System.exit(0);
     }}); 
   
     JMenu difficulty = new JMenu ("Difficulty");//creates the second menu with the range of difficulty levels
     menubar.add(difficulty);
     JMenuItem easy = new JMenuItem ("Easy");//adds the first difficulty level to the second menu
     easy.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         game.setDifficulty(GameState.Difficulty.EASY);
       }
     });
     difficulty.add(easy);
     JMenuItem medium = new JMenuItem ("Medium");//adds the second difficulty level to the second menu
     medium.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         game.setDifficulty(GameState.Difficulty.MEDIUM);
       }
     });
     difficulty.add(medium);
     JMenuItem hard = new JMenuItem ("Hard");//adds the first difficulty level to the second menu
     hard.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         game.setDifficulty(GameState.Difficulty.HARD);
       }
     });
     difficulty.add(hard);
    
   
     gameMessage = new JTextArea("Choose a fruit"); // create JTextField with the first task
     gameMessage.setPreferredSize( new Dimension( 200, 50 ) );
     gameMessage.setLineWrap(true);


     gameMessage.setEditable(false); // Indicates that this textfield is not editable
     gameMessage.setSize(300, 300);
   
     frame.setJMenuBar(menubar);//sets the menubar to hte frame
     //frame.getContentPane().setLayout(new FlowLayout());    
     frame.add(gameMessage, BorderLayout.EAST);// add textfield to frame
     frame.setVisible(true);
     
     game.connectScreen(screen);
     game.connectMessageOutput(gameMessage);
     game.start();

    }
  /**
  * Method which helps to restart the game
  * after the "Reastart" menu item was clicked
  */
    public void restart() {
      frame.removeKeyListener(game.getPlayer());
      
      game.killTimers();
      
      game = new GameState();
      frame.addKeyListener(game.getPlayer());
      game.connectScreen(screen);
      screen.connectGame(game);
      
      game.start();
    }   
    
    
 /**
  * Creates a new game frame, menubar with menu items
  */
   public static void main(String[] args) {
     new LinguistLizard().window();
//LinguistLizard ll = new LinguistLizard ();
//ll.window();

   }
   }
