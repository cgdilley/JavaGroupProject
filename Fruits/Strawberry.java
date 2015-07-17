/* class Strawberry:
 * Creates a Strawberry object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Strawberry extends Fruit {
    
    /*
     * Constructor loads all nasals into the ArrayList Fruit.soundColl
     */
    public Strawberry()  {
        ArrayList<String> strawberry = new ArrayList<>();
        strawberry.add("m");
        strawberry.add("n");
        strawberry.add("ng");    
        super.setSoundColl(strawberry);
        super.setSortOfFruit("strawberry");
        super.setTask("Collect only nasals!");
    }
}
