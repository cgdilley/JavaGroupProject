/* class Salad:
 * Creates a Salad object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Salad extends Fruit {
    
    /*
     * Constructor loads all clicks into the ArrayList Fruit.soundColl
     */
    public Salad()  {
        ArrayList<String> salad = new ArrayList<>();
        salad.add("bil");
        salad.add("den");
        salad.add("post");
        salad.add("pal");
        salad.add("lat");
        super.setSoundColl(salad);
        super.setSortOfFruit("salad");
        super.setTask("Time to make some salad from the clicks.");
        }    
    
}
