/* class Cabbage:
 * Creates an Cabbage object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Cabbage extends Fruit {
    
    /*
     * Constructor loads all velar consonants into the ArrayList Fruit.soundColl
     */
    public Cabbage()  {
        ArrayList<String> cabbage = new ArrayList<>();
        cabbage.add("ach");
        cabbage.add("k");
        cabbage.add("g");
        cabbage.add("ng");
        super.setSoundColl(cabbage);
        super.setSortOfFruit("cabbage");
        super.setTask("Bite these naughty velars!");
    }
    
}
