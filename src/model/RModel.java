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

@ParseClassName("RModel")
public class RModel extends ParseObject {

	public RModel() {
		// A default constructor is required.
	}


	public ParseUser getAuthor() {
		return getParseUser("user");
	}

	public void setAuthor(ParseUser user) {
		put("user", user);
	}
	
	public void setQuestion(String q) {
		put("ques", q);
	}
	
	public String getQuestion() {
		return this.getString("ques");
	}
	
	public void setResponse(String q) {
		put("res", q);
	}
	
	public String getResponse() {
		return this.getString("res");
	}
	
	public void setLocked(Boolean l) {
		put("locked", l);
	}
	
	public Boolean getLocked() {
		return this.getBoolean("locked");
	}
	
	
	public void setTitle(String s) {
		put("title", s);
	}
	
	public String getTitle() {
		return this.getString("title");
	}
	
	public ParseFile getImage() {
		return this.getParseFile("img");
	}
	
	public void setImage(ParseFile image) {
		put("img", image);
	}
}
