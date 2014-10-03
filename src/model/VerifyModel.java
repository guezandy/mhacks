package model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given shot
 */

@ParseClassName("VerifyModel")
public class VerifyModel extends ParseObject {

	public VerifyModel() {
		// A default constructor is required.
	}
	
	public VerifyModel(String clue, String points, String type) {
		
	}

	public ParseUser getAuthor() {
		return getParseUser("user");
	}

	public void setAuthor(ParseUser user) {
		put("user", user);
	}
	
	public void setClueID(String clue) {
		put("clue", clue);
	}
	
	public String getClueId() {
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
	//string
	public void setString(String solution) {
		put("sstring", solution);
	}
	
	public String getSString() {
		return this.getString("sstring");
	}
	//for gps
	public ParseGeoPoint getLocation() {
	    return getParseGeoPoint("location");
	 }

	 public void setLocation(ParseGeoPoint value) {
	    put("location", value);
	 }

	 public void setVerified(Boolean s) {
		 put("verified", s);
	 }
	 
	 public Boolean getVerified() {
		 return this.getBoolean("verified");
	 }
	
	//for images
	public ParseFile getImage() {
		return this.getParseFile("img");
	}
	
	public void setImage(ParseFile image) {
		put("img", image);
	}
	
	  public static ParseQuery<VerifyModel> getQuery() {
		    return ParseQuery.getQuery(VerifyModel.class);
		  }
}
