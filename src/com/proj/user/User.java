package com.proj.user;

import java.util.List;

import javax.annotation.PostConstruct;

import com.proj.dao.PostDAO;
import com.proj.post.Post;

public class User {

	/* Primary key represent the user */
	private int id;
	
	private String username;
	private String pwd;
	private String firstname;
	private String lastname;
	private String email;
	
	private List <Post> relationsPosts;
	
	/* ID Getter Setter */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/* USERNAME Getter Setter */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* PASSWORD Getter Setter */
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/* FIRSTNAME Getter Setter */
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/* LASTNAME Getter Setter */
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/* EMAIL Getter Setter */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<Post> getRelationsPosts() {
		return relationsPosts;
	}
	public void setRelationsPosts(List<Post> relationsPosts) {
		this.relationsPosts = relationsPosts;
	}
	
	@PostConstruct
	public void initRelationsPosts(){
		final List<Post> posts = PostDAO.getRelationsPostsOf(this);
		this.setRelationsPosts(posts);
	}
}
