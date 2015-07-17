/* class Grain:
 * Creates an Grain object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Grain extends Fruit {
    
    /*
     * Constructor loads all (labio-)dental consonants into the ArrayList Fruit.soundColl
     */
    public Grain()  {
        ArrayList<String> grain = new ArrayList<>();
        grain.add("thUnv");
        grain.add("thV");
        grain.add("f");
        grain.add("v");
        super.setSoundColl(grain);
        super.setSortOfFruit("grain");
        super.setTask("Collect only (labio-)dental consonants!");
    }
    
}
