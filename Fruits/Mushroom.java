/* class Apricot:
 * Creates an Mushroom object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Mushroom extends Fruit {
    
    /*
     * Constructor loads all back vowels into the ArrayList Fruit.soundColl
     */
    public Mushroom()  {
        ArrayList<String> mushroom = new ArrayList<>();
        mushroom.add("u");
        mushroom.add("shortu");
        mushroom.add("o");
        mushroom.add("shorto");
        mushroom.add("a");
        mushroom.add("ao");
        super.setSoundColl(mushroom);
        super.setSortOfFruit("mushroom");
        super.setTask("Back vowels are waiting for you!");
        }
}
