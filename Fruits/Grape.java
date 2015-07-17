/* class Grape:
 * Creates a Grape object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Grape extends Fruit {

    /*
     * Constructor loads all plosives into the ArrayList Fruit.soundColl
     */
    public Grape()  {
        ArrayList<String> grape = new ArrayList<>();
        grape.add("p");
        grape.add("b");
        grape.add("t");
        grape.add("d");
        grape.add("k");
        grape.add("g");    
        super.setSoundColl(grape);
        super.setSortOfFruit("grape");
        super.setTask("Collect only plosives!");
    }
}
