/* class Plum:
 * Creates a Broccoli object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Broccoli extends Fruit {
    
    /*
     * Constructor loads all close-mid and open-mid vowels into the ArrayList Fruit.soundColl
     */
    public Broccoli()  {
        ArrayList<String> broccoli = new ArrayList<>();
        broccoli.add("e");
        broccoli.add("shorte");
        broccoli.add("ethree");
        broccoli.add("schwa");
        broccoli.add("vocR");
        broccoli.add("o");
        broccoli.add("shorto");
        super.setSoundColl(broccoli);
        super.setSortOfFruit("broccoli");
        super.setTask("Collect only close-mid and open-mid vowels!");
        }    
    
}
