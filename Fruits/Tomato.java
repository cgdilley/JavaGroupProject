/* class Tomato:
 * Creates a Tomato object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Tomato extends Fruit {
    
    /*
     * Constructor loads all fricatives into the ArrayList Fruit.soundColl
     */
    public Tomato()  {
        ArrayList<String> tomato = new ArrayList<>();
        tomato.add("thUnv");
        tomato.add("thV");
        tomato.add("s");
        tomato.add("z");
        tomato.add("shUnv");
        tomato.add("shV");
        tomato.add("ich");
        tomato.add("ach");
        tomato.add("f");
        tomato.add("v");
        super.setSoundColl(tomato);
        super.setSortOfFruit("tomato");
        super.setTask("Collect only fricatives!");
    }
        
}
