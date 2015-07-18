
/* class Banana:
 * Creates a Banana object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Banana extends Fruit {
    
    /*
     * Constructor loads all sonorants into the ArrayList Fruit.soundColl
     */
    public Banana()  {
        ArrayList<String> banana = new ArrayList<>();
        banana.add("l");
        banana.add("m");
        banana.add("n");
        banana.add("ng");
        banana.add("flippedR");
        banana.add("w");
        banana.add("j");   
        super.setSoundColl(banana);
        super.setSortOfFruit("banana");
        super.setTask("And now gather sonorants.");
    }
}
