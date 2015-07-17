/* class Olive:
 * Creates an Olive object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Olive extends Fruit {
    
    /*
     * Constructor loads all bilabial consonants into the ArrayList Fruit.soundColl
     */
    public Olive()  {
        ArrayList<String> olive = new ArrayList<>();
        olive.add("p");
        olive.add("b");
        olive.add("m");
        super.setSoundColl(olive);
        super.setSortOfFruit("olive");
        super.setTask("Collect only bilabial consonants!");
    }
    
}
