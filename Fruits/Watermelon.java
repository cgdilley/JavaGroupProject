/* class Warermelon:
 * Creates a Watermelon object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Watermelon extends Fruit {
    
    /*
     * Constructor loads all approximants into the ArrayList Fruit.soundColl
     */
    public Watermelon()  {
        ArrayList<String> watermelon = new ArrayList<>();
        watermelon.add("flippedR");
        watermelon.add("j");
        watermelon.add("l");
        watermelon.add("w");    
        super.setSoundColl(watermelon);
        super.setSortOfFruit("watermelon");
        super.setTask("Collect only approximants!");
    }
}
