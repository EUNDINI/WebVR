package model;

import java.util.Date;

public class GuestBook { 
	private int gbID;//pk
	private String content;
	private Date date;
	private int userID;//fk
	private int exhbId;//fk
	
	public GuestBook(int gbID, int userID, int exhbId, String content, Date date) {
		super();
		this.gbID = gbID;
		this.content = content;
		this.date = date;
		this.userID = userID;
		this.exhbId = exhbId;
	}
	
	public int getGbID() {
		return gbID;
	}
	public void setGbID(int gbID) {
		this.gbID = gbID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getExhbId() {
		return exhbId;
	}
	public void setExhbId(int exhbId) {
		this.exhbId = exhbId;
	}
	

}
