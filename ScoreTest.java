/**
 * Unit tests for class Score.
 * 
 * Note that no successful http request is made;
 * thus, we do not pollute the scoreboard with dummy data.
 * 
 * @author pavelsof
 */
import junit.framework.TestCase;


public class ScoreTest extends TestCase {
	
	public void testBadName() {
		Score s = new Score("", 42);
		assertEquals(s.send(), 400);
	}
	
	public void testBadScore() {
		Score s = new Score("Lizardio", -1);
		assertEquals(s.send(), 400);
	}
	
}
