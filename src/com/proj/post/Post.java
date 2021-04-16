package com.proj.post;

import java.sql.Timestamp;
import java.util.Date;

public class Post {

	private int id;

	private int authorID;
	private String title;
	private String content;
	private long time; 


	public Post(int author, String title, String content, long time) {
		super();
		this.id = -1;
		this.authorID=author;
		this.title = title;
		this.content = content;
		this.time=time;
	}

	public Post(int id, int author, String title, String content, long time) {
		super();
		this.id = id;
		this.authorID=author;
		this.title = title;
		this.content = content;
		this.time=time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getDate() {
		Timestamp ts=new Timestamp(time);
		Date date=ts;  
		return date.toString();
	}
	
//	public User getAuthor() {
//		return authorID;
//	}


}
