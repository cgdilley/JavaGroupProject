/* class Lemon:
 * Creates a Lemon object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Lemon extends Fruit {

    /*
     * Constructor loads all unrounded vowels into the ArrayList Fruit.soundColl
     */
    public Lemon()  {
        ArrayList<String> lemon = new ArrayList<>();
        lemon.add("i");
        lemon.add("shorti");
        lemon.add("e");
        lemon.add("shorte");
        lemon.add("ethree");
        lemon.add("ae");
        lemon.add("schwa");
        lemon.add("shorta");
        lemon.add("a");
        lemon.add("vocR");
        super.setSoundColl(lemon);
        super.setSortOfFruit("lemon");
        super.setTask("Collect only unrounded vowels!");
        }
}
