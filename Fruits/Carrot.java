/* class Carrot:
 * Creates a Carrot object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Carrot extends Fruit {
    
    
    /*
     * Constructor loads all ejectives into the ArrayList Fruit.soundColl
     */
    public Carrot()  {
        ArrayList<String> carrot = new ArrayList<>();
        carrot.add("pBar");
        carrot.add("tBar");
        carrot.add("kBar");
        carrot.add("sBar");
        super.setSoundColl(carrot);
        super.setSortOfFruit("carrot");
        super.setTask("How about to eat some ejectives?");
        }    
    
}
