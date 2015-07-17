/**
 * Class definition for scores.
 * 
 * Intended usage:
 * ```
 * Score s = new Score("Lizardio", 42);
 * if(s.send() == 200) {
 *     // successful
 * }
 * else {
 *     // net problems, most likely
 * }
 * ```
 * 
 * @author pavelsof
 */
import java.io.*;
import java.net.*;


public class Score {
	
	/**
	 * The URL of the API.
	 * Ideally this should not be hard-coded, but given the scope of
	 * the project, it is more sensible to avoid configurability.
	 */
	protected String API_URL = "http://lizard.yatcode.com/api/";
	
	/**
	 * Some simple security.
	 * Again, ideally this should not be hard-coded.
	 */
	protected String CODE = "TheAnswerIs42";
	
	/**
	 * The name of the player that scored.
	 * Because a Hall of Fame does not make sense without names.
	 */
	protected String playerName;
	
	/**
	 * The numeric score itself.
	 */
	protected int scoreValue;
	
	
	/**
	 * Constructor. Sets the playerName and scoreValue properties.
	 * 
	 * @param String The name of the player.
	 * @param int The score itself.
	 */
	public Score(String initPlayerName, int initScoreValue) {
		playerName = initPlayerName;
		scoreValue = initScoreValue;
	}
	
	
	/**
	 * Sends the score to the server.
	 * 
	 * For reference how to do HTTP requests without Apache:
	 * http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
	 * 
	 * @return The HTTP response code, or -1 if an error was raised.
	 */
	public int send() {
		
		try {
			URL urlObject = new URL(API_URL);
			HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			// request body
			String body = new String();
			body += "{";
			body += "\"name\":\""+ playerName +"\",";
			body += "\"score\":"+ scoreValue +",";
			body += "\"code\":\""+ CODE +"\"";
			body += "}";
			
			// java magic
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.writeBytes(body);
			writer.flush();
			writer.close();
			
			return conn.getResponseCode();
		}
		
		catch(Exception error) {
			return -1;
		}
		
	}
	
}
