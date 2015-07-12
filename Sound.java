/* class Sound:
 * Creates Sound objects that are rendered to the screen as IPA symbols
 * Right sound has to be eaten by snake to grow, a wrong sound will make it shrink
 */

/**
 * @author Patricia
 */

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sound extends Token {
   
    private String soundName; //can be used as argument for the loadSoundImage() method
    private final int SOUND_SIZE = 10;
    private final int RANDOM_POS = 29;
    private int sound_x;
    private int sound_y;
    public Image soundPic;
    
    
    /*
     * Construct an empty Sound object
     */
    public Sound()  {
        soundName = "no sound name";
    }
    
    /*
     * Construct a Sound object of name aName
     * @param aName - the name of the Sound
     */
    public Sound(String aName)  {
        soundName = aName;
    }
     
    /*
     * Set a Sound to name aName
     * @param aName - the name of the Sound
     */
    public void setSound(String aName)  {
        soundName = aName;
    }
    
    /*
     * Return a Sound object
     * @return the Sound object of name soundName
     */
    public String getSound()    {
        return soundName;
    }
    
        
    /*
     * Method to locate the Sound in the game
     */
    public void locateSound()  {
        int i = (int) (Math.random()*RANDOM_POS);
        sound_x = (i*SOUND_SIZE);
        
        int j = (int) (Math.random()*RANDOM_POS);
        sound_y = (i*SOUND_SIZE);
        
        Coord sound = new Coord(sound_x, sound_y);
    }
    
    /*
     * Method for loading an image of a sound to render it to the screen
     */
    public void loadSoundImage() {
        ImageIcon image = new ImageIcon(getSound() + ".png");
        soundPic = image.getImage();
    }
        
        
    /*
     * Create distinct sounds
     */
    
    /*
     * English consonants: p-b, t-d, thUnv-thV, s-z, shUnv-shV, flippedR, w, f-v, k-g, ng, m, n
     */
    
    
    /*
     * A String representation of the sound /p/
     */
    public void p() {
        setSound("p");
    }
    
    /*
     * A String representation of the sound /b/
     */
    public void b() {
        setSound("b");
    }
    
    /*
     * A String representation of the sound /t/
     */
    public void t() {
        setSound("t");
    }
    
    /*
     * A String representation of the sound /d/
     */
    public void d() {
        setSound("d");
    }
    
    /*
     * A String representation of the sound /θ/
     */
    public void thUnv() {
        setSound("thUnv");
    }
    
    /*
     * A String representation of the sound /ð/
     */
    public void thV() {
        setSound("thV");
    }
    
    /*
     * A String representation of the sound /s/
     */
    public void s() {
        setSound("s");
    }
    
    /*
     * A String representation of the sound /z/
     */
    public void z() {
        setSound("z");
    }
    
    /*
     * A String representation of the sound /ʃ/
     */
    public void shUnv() {
        setSound("shUnv");
    }
    
    /*
     * A String representation of the sound /ʒ/
     */
    public void shV() {
        setSound("shV");
    }
    
    /*
     * A String representation of the sound /ɹ/
     */
    public void flippedR() {
        setSound("flippedR");
    }
    
    /*
     * A String representation of the sound /l/
     */
    public void l() {
        setSound("l");
    }
    
    /*
     * A String representation of the sound /w/
     */
    public void w() {
        setSound("w");
    }
    
    /*
     * A String representation of the sound /j/
     */
    public void j() {
        setSound("j");
    }
    
    /*
     * A String representation of the sound /f/
     */
    public void f() {
        setSound("f");
    }
    
    /*
     * A String representation of the sound /v/
     */
    public void v() {
        setSound("v");
    }
    
    /*
     * A String representation of the sound /k/
     */
    public void k() {
        setSound("k");
    }
    
    /*
     * A String representation of the sound /g/
     */
    public void g() {
        setSound("g");
    }
    
    /*
     * A String representation of the sound /ŋ/
     */
    public void ng() {
        setSound("ng");
    }
    
    /*
     * A String representation of the sound /m/
     */
    public void m() {
        setSound("m");
    }
    
    /*
     * A String representation of the sound /n/
     */
    public void n() {
        setSound("n");
    }
    
    
    /*
     * Vowels: i-shorti, y-shorty, e, shorte, Ǝ, ae, schwa, u-shortu, o-shortu, shorta, a, ao
     */
    
    /*
     * A String representation of the sound /i/
     */
    public void i() {
        setSound("i");
    }
    
    /*
     * A String representation of the sound /ɪ/
     */
    public void shorti() {
        setSound("shorti");
    }
    
    /*
     * A String representation of the sound /y/
     */
    public void y() {
        setSound("y");
    }
    
    /*
     * A String representation of the sound /ʏ/
     */
    public void shorty() {
        setSound("shorty");
    }
    
    /*
     * A String representation of the sound /e/
     */
    public void e() {
        setSound("e");
    }
    
    /*
     * A String representation of the sound /ɛ/
     */
    public void shorte() {
        setSound("shorte");
    }
    
    /*
     * A String representation of the sound /ɜ/
     */
    public void Ǝ() {
        setSound("Ǝ");
    }
    
    /*
     * A String representation of the sound /æ/
     */
    public void ae() {
        setSound("ae");
    }
    
    /*
     * A String representation of the sound /ɘ/
     */
    public void schwa() {
        setSound("schwa");
    }
    
    /*
     * A String representation of the sound /u/
     */
    public void u() {
        setSound("u");
    }
    
    /*
     * A String representation of the sound /ʊ/
     */
    public void shortu() {
        setSound("shortu");
    }
    
    /*
     * A String representation of the sound /o/
     */
    public void o() {
        setSound("o");
    }
    
    /*
     * A String representation of the sound /ɔ/
     */
    public void shorto() {
        setSound("shorto");
    }
    
    /*
     * A String representation of the sound /Ʌ/
     */
    public void shorta() {
        setSound("shorta");
    }
    
    /*
     * A String representation of the sound /ɑ/
     */
    public void a() {
        setSound("a");
    }
    
    /*
     * A String representation of the sound /ɒ/
     */
    public void ao() {
        setSound("ao");
    }
    
        
    /*
     * Clicks: bil, den, post, pal, lat
     */
    
    /*
     * A String representation of the sound /ʘ/
     */
    public void bil() {
        setSound("bil");
    }
    
    /*
     * A String representation of the sound /|/
     */
    public void den() {
        setSound("den");
    }
    
    /*
     * A String representation of the sound /!/
     */
    public void post() {
        setSound("post");
    }
    
    /*
     * A String representation of the sound /ǂ/
     */
    public void pal() {
        setSound("pal");
    }
            
    /*
     * A String representation of the sound /║/
     */
    public void lat() {
        setSound("lat");
    }
    
    
    /*
     * Voiced implosives: bilimp, denimp, palimp, velimp, uvimp
     */
    
    /*
     * A String representation of the sound /ɓ/
     */
    public void bilimp() {
        setSound("bilimp");
    }
    
    /*
     * A String representation of the sound /ɗ /
     */
    public void denimp() {
        setSound("denimp");
    }
    
    /*
     * A String representation of the sound /ʄ/
     */
    public void palimp() {
        setSound("palimp");
    }
    
    /*
     * A String representation of the sound /ɠ/
     */
    public void velimp() {
        setSound("velimp");
    }
            
    /*
     * A String representation of the sound /ʛ/
     */
    public void uvimp() {
        setSound("uvimp");
    }
    
    
    /*
     * Ejectives: pBar, tBar, kBar, sBar
     */
    
    /*
     * A String representation of the sound /p'/
     */
    public void pBar() {
        setSound("pBar");
    }
    
    /*
     * A String representation of the sound /t'/
     */
    public void tBar() {
        setSound("tBar");
    }
    
    /*
     * A String representation of the sound /k'/
     */
    public void kBar() {
        setSound("kBar");
    }
    
    /*
     * A String representation of the sound /s'/
     */
    public void sBar() {
        setSound("sBar");
    }
    
}
