/* class Kiwi:
 * Creates a Kiwi object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Eggplant extends Fruit {
    
    /*
     * Constructor loads all rounded vowels into the ArrayList Fruit.soundColl
     */
    public Eggplant()  {
        ArrayList<String> eggplant = new ArrayList<>();
        eggplant.add("y");
        eggplant.add("shorty");
        eggplant.add("u");
        eggplant.add("shortu");
        eggplant.add("o");
        eggplant.add("shorto");
        eggplant.add("ao");
        super.setSoundColl(eggplant);
        super.setSortOfFruit("eggplant");
        super.setTask("Collect only rounded vowels!");
        }
}
