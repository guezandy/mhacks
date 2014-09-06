package model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseFile;
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
	
	public void setTitle(String points) {
		put("title", points);
	}
	
	public String getTitle() {
		return this.getString("title");
	}
	
	public void setType(String type) {
		put("type", type);
	}
	
	public String getType() {
		return this.getString("type");
	}
	
	public void setDif(String dif) {
		put("dif", dif);
	}
	
	public String getDif() {
		return this.getString("dif");
	}
	
	
	public ParseFile getImage() {
		return this.getParseFile("img");
	}
	
	public void setImage(ParseFile image) {
		put("img", image);
	}
}
