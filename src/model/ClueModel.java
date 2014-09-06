package model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given shot
 */

@ParseClassName("ClueModel")
public class ClueModel extends ParseObject {

	public ClueModel() {
		// A default constructor is required.
	}
	
	public ClueModel(String clue, String points, String type) {
		
	}

	public ParseUser getAuthor() {
		return getParseUser("user");
	}

	public void setAuthor(ParseUser user) {
		put("user", user);
	}
	
	public void setClue(String clue) {
		put("clue", clue);
	}
	
	public String getClue() {
		return this.getString("clue");
	}
	
	public void setPoints(String points) {
		put("points", points);
	}
	
	public String getPoints() {
		return this.getString("points");
	}
	
	public void setType(String type) {
		put("type", type);
	}
	
	public int getType() {
		return Integer.parseInt(this.getString("type"));
	}
}
