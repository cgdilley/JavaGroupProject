/* class Plum:
 * Creates a Radish object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Radish extends Fruit {
    
    /*
     * Constructor loads all high vowels into the ArrayList Fruit.soundColl
     */
    public Radish()  {
        ArrayList<String> radish = new ArrayList<>();
        radish.add("i");
        radish.add("shorti");
        radish.add("y");
        radish.add("shorty");
        radish.add("u");
        radish.add("shortu");
        super.setSoundColl(radish);
        super.setSortOfFruit("radish");
        super.setTask("Collect only high vowels!");
        }    
    
}
