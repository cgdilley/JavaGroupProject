/* class Pumpkin:
 * Creates a Pumpkin object to be rendered to the screen as a fruit image
 */

/**
 * @author Patricia
 */
import java.util.ArrayList;

public class Pumpkin extends Fruit {
    
    /*
     * Constructor loads all voiced implosives into the ArrayList Fruit.soundColl
     */
    public Pumpkin()  {
        ArrayList<String> pumpkin = new ArrayList<>();
        pumpkin.add("bilimp");
        pumpkin.add("denimp");
        pumpkin.add("palimp");
        pumpkin.add("velimp");
        pumpkin.add("uvimp");
        super.setSoundColl(pumpkin);
        super.setSortOfFruit("pumpkin");
        super.setTask("Collect only voiced implosives!");
        }    
    
}
