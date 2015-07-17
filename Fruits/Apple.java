/* class Apple:
 * Creates an Apple object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Apple extends Fruit {
    
    /*
     * Constructor loads all obstruents into the ArrayList Fruit.soundColl
     */
    public Apple()  {
        ArrayList<String> apple = new ArrayList<>();
        apple.add("p");
        apple.add("b");
        apple.add("t");
        apple.add("d");
        apple.add("k");
        apple.add("g");
        apple.add("thUnv");
        apple.add("thV");
        apple.add("s");
        apple.add("z");
        apple.add("shUnv");
        apple.add("shV");
        apple.add("ich");
        apple.add("ach");
        apple.add("f");
        apple.add("v");
        super.setSoundColl(apple);
        super.setSortOfFruit("apple");
        super.setTask("Collect only obstruents!");
    }
    
}
