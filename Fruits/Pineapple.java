/* class Pineapple:
 * Creates a Pineapple object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Pineapple extends Fruit {
    
    /*
     * Constructor loads all unvoiced consonants into the ArrayList Fruit.soundColl
     */
    public Pineapple()  {
        ArrayList<String> pineapple = new ArrayList<>();
        pineapple.add("p");
        pineapple.add("t");
        pineapple.add("thUnv");
        pineapple.add("s");
        pineapple.add("shUnv");
        pineapple.add("ich");
        pineapple.add("ach");
        pineapple.add("f");
        pineapple.add("k");    
        super.setSoundColl(pineapple);
        super.setSortOfFruit("pineapple");
        super.setTask("Gather the unvoiced consonants!");
    }
}
