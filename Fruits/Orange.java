/* class Orange:
 * Creates an Orange object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Orange extends Fruit {
    
    /*
     * Constructor loads all front vowels into the ArrayList Fruit.soundColl
     */
    public Orange()  {
        ArrayList<String> orange = new ArrayList<>();
        orange.add("i");
        orange.add("shorti");
        orange.add("y");
        orange.add("shorty");
        orange.add("e");
        orange.add("shorte");
        orange.add("ae");
        super.setSoundColl(orange);
        super.setSortOfFruit("orange");
        super.setTask("Have a breakfast with front vowels!");
        }
}
