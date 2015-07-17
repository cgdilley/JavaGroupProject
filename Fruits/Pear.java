/* class Pear:
 * Creates a Pear object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Pear extends Fruit {
    
    /*
     * Constructor loads all voiced consonants into the ArrayList Fruit.soundColl
     */
    public Pear()  {
        ArrayList<String> pear = new ArrayList<>();
        pear.add("b");
        pear.add("d");
        pear.add("thV");
        pear.add("z");
        pear.add("shV");
        pear.add("flippedR");
        pear.add("w");
        pear.add("j");
        pear.add("l");
        pear.add("v");
        pear.add("g");
        pear.add("ng");
        pear.add("m");
        pear.add("n");    
        super.setSoundColl(pear);
        super.setSortOfFruit("pear");
        super.setTask("Collect only voiced consonants!");
    }
}
