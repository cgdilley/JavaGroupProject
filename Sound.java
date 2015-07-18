/* class Sound:
 * Creates Sound objects that are rendered as IPA symbols to the screen
 * Right sound has to be eaten by snake to grow, a wrong sound will make it shrink
 */

/**
 * @author Patricia
 */

import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.*;

public class Sound extends Token {
   
    private String soundName; //can be used as argument for the loadSoundImage() method
     
    
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
     * Method for loading an image of a sound to render it to the screen, and resizing it as needed.
     * @Override Token.loadTokenImage()
     */
    public void loadTokenImage() {
        ImageIcon image = new ImageIcon("Icons/Sound/" + getSound() + ".png");
        tokenPic = image.getImage().getScaledInstance(Screen.GAME_COLUMN_WIDTH-(Screen.TOKEN_MARGINS*2), 
                                                      Screen.GAME_ROW_HEIGHT-(Screen.TOKEN_MARGINS*2),
                                                      Image.SCALE_SMOOTH);
    }
        
    
    /** Static method for generating a random sound
      * @param difficulty - Limits the selection based on current game difficulty
      * @return - The generated sound
      */
    public static Sound randomSound(GameState.Difficulty difficulty)
    {
      Sound sound = new Sound();   
      int rVal = -1;
      // Generate a random number representing a sound.  Higher difficulties include higher number of sounds.
      if (difficulty == GameState.Difficulty.EASY)
        rVal = (int) Math.floor(Math.random()*23);
      else if (difficulty == GameState.Difficulty.MEDIUM)
        rVal = (int) Math.floor(Math.random()*(23+17));
      else if (difficulty == GameState.Difficulty.HARD)
        rVal = (int) Math.floor(Math.random()*(23+17+14));
      
      // Apply the randomly selected sound
      if (rVal==0) sound.p();
      else if (rVal==1) sound.b();
      else if (rVal==2) sound.t();
      else if (rVal==3) sound.d();
      else if (rVal==4) sound.thUnv();
      else if (rVal==5) sound.thV();
      else if (rVal==6) sound.s();
      else if (rVal==7) sound.z();
      else if (rVal==8) sound.shUnv();
      else if (rVal==9) sound.shV();
      else if (rVal==10) sound.ich();
      else if (rVal==11) sound.ach();
      else if (rVal==12) sound.flippedR();
      else if (rVal==13) sound.l();
      else if (rVal==14) sound.w();
      else if (rVal==15) sound.j();
      else if (rVal==16) sound.f();
      else if (rVal==17) sound.v();
      else if (rVal==18) sound.k();
      else if (rVal==19) sound.g();
      else if (rVal==20) sound.ng();
      else if (rVal==21) sound.m();
      else if (rVal==22) sound.n();
      else if (rVal==23) sound.i();
      else if (rVal==24) sound.shorti();
      else if (rVal==25) sound.y();
      else if (rVal==26) sound.shorty();
      else if (rVal==27) sound.e();
      else if (rVal==28) sound.shorte();
      else if (rVal==29) sound.ethree();
      else if (rVal==30) sound.ae();
      else if (rVal==31) sound.schwa();
      else if (rVal==32) sound.vocR();
      else if (rVal==33) sound.u();
      else if (rVal==34) sound.shortu();
      else if (rVal==35) sound.o();
      else if (rVal==36) sound.shorto();
      else if (rVal==37) sound.shorta();
      else if (rVal==38) sound.a();
      else if (rVal==39) sound.ao();
      else if (rVal==40) sound.bil();
      else if (rVal==41) sound.den();
      else if (rVal==42) sound.post();
      else if (rVal==43) sound.pal();
      else if (rVal==44) sound.lat();
      else if (rVal==45) sound.bilimp();
      else if (rVal==46) sound.denimp();
      else if (rVal==47) sound.palimp();
      else if (rVal==48) sound.velimp();
      else if (rVal==49) sound.uvimp();
      else if (rVal==50) sound.pBar();
      else if (rVal==51) sound.tBar();
      else if (rVal==52) sound.kBar();
      else if (rVal==53) sound.sBar();
      
      // Load the token's image
      sound.loadTokenImage();
     
      return sound;
    }
    
        
    /*
     * Create distinct sounds
     */
    
    /*
     * Consonants: p-b, t-d, thUnv-thV, s-z, shUnv-shV, ich-ach, flippedR, w, f-v, k-g, ng, m, n
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
     * A String representation of the sound /Î¸/
     */
    public void thUnv() {
        setSound("thUnv");
    }
    
    /*
     * A String representation of the sound /Ã°/
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
     * A String representation of the sound /Êƒ/
     */
    public void shUnv() {
        setSound("shUnv");
    }
    
    /*
     * A String representation of the sound /Ê’/
     */
    public void shV() {
        setSound("shV");
    }
    
    /*
     * A String representation of the sound /Ã§/
     */
    public void ich() {
        setSound("ich");
    }
    
    /*
     * A String representation of the sound /x/
     */
    public void ach() {
        setSound("ach");
    }
    
    /*
     * A String representation of the sound /É¹/
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
     * A String representation of the sound /Å‹/
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
     * Vowels: i-shorti, y-shorty, e, shorte, ethree, ae, schwa, vocR, u-shortu, o-shortu, shorta, a, ao
     */
    
    /*
     * A String representation of the sound /i/
     */
    public void i() {
        setSound("i");
    }
    
    /*
     * A String representation of the sound /Éª/
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
     * A String representation of the sound /Ê/
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
     * A String representation of the sound /É›/
     */
    public void shorte() {
        setSound("shorte");
    }
    
    /*
     * A String representation of the sound /Éœ/
     */
    public void ethree() {
        setSound("ethree");
    }
    
    /*
     * A String representation of the sound /Ã¦/
     */
    public void ae() {
        setSound("ae");
    }
    
    /*
     * A String representation of the sound /É˜/
     */
    public void schwa() {
        setSound("schwa");
    }
    
    /*
     * A String representation of the sound /É?/
     */
    public void vocR() {
        setSound("vocR");
    }
    
    /*
     * A String representation of the sound /u/
     */
    public void u() {
        setSound("u");
    }
    
    /*
     * A String representation of the sound /ÊŠ/
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
     * A String representation of the sound /É�/
     */
    public void shorto() {
        setSound("shorto");
    }
    
    /*
     * A String representation of the sound /É…/
     */
    public void shorta() {
        setSound("shorta");
    }
    
    /*
     * A String representation of the sound /É‘/
     */
    public void a() {
        setSound("a");
    }
    
    /*
     * A String representation of the sound /É’/
     */
    public void ao() {
        setSound("ao");
    }
    
        
    /*
     * Clicks: bil, den, post, pal, lat
     */
    
    /*
     * A String representation of the sound /Ê˜/
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
     * A String representation of the sound /Ç‚/
     */
    public void pal() {
        setSound("pal");
    }
            
    /*
     * A String representation of the sound /â•‘/
     */
    public void lat() {
        setSound("lat");
    }
    
    
    /*
     * Voiced implosives: bilimp, denimp, palimp, velimp, uvimp
     */
    
    /*
     * A String representation of the sound /É“/
     */
    public void bilimp() {
        setSound("bilimp");
    }
    
    /*
     * A String representation of the sound /É—â€‰/
     */
    public void denimp() {
        setSound("denimp");
    }
    
    /*
     * A String representation of the sound /Ê„/
     */
    public void palimp() {
        setSound("palimp");
    }
    
    /*
     * A String representation of the sound /É /
     */
    public void velimp() {
        setSound("velimp");
    }
            
    /*
     * A String representation of the sound /Ê›/
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
