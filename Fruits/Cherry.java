/* class Cherry:
 * Creates a Cherry object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Cherry extends Fruit {

    /*
     * Constructor loads all (post)alveolar consonants into the ArrayList Fruit.soundColl
     */
    public Cherry()  {
        ArrayList<String> cherry = new ArrayList<>();
        cherry.add("t");
        cherry.add("d");
        cherry.add("s");
        cherry.add("z");
        cherry.add("shUnv");
        cherry.add("shV");
        cherry.add("flippedR");
        cherry.add("l");
        cherry.add("w");   
        cherry.add("n");   
        super.setSoundColl(cherry);
        super.setSortOfFruit("cherry");
        super.setTask("Collect only (post-)alveolar consonants!");
        }
}
