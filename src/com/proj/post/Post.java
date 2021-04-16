package com.proj.post;

public class Post {
	
	private int id;
	
	private int author;
	private String title;
	private String content;
	private long time; 
	
	
	public Post(int author, String title, String content, long time) {
		super();
		this.id = -1;
		this.author=author;
		this.title = title;
		this.content = content;
		this.time=time;
	}
	
	public Post(int id, int author, String title, String content, long time) {
		super();
		this.id = id;
		this.author=author;
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

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}
	
	
	
	

}
