package com.proj.user;

import java.util.ArrayList;
import java.util.Collection;
import com.proj.dao.DatabaseUserDao;
import com.proj.dao.RelationDAO;
import com.proj.dao.UserDao;

public class User {
	
	/* Primary key represent the user */
	private int id;
	
	private String username;
	private String pwd;
	private String firstname;
	private String lastname;
	private String email;
	
	public User() {
		
	}
	
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
	
	public Collection<User> commonRelations(int user2ID) {
		UserDao uDao = new DatabaseUserDao();
		User user2 = uDao.findById(user2ID);
		
		Collection<User> user1Connections = new ArrayList<User>(RelationDAO.getRelationsOf(this)); 
		Collection<User> user2Connections = new ArrayList<User>(RelationDAO.getRelationsOf(user2)); 
		
		user1Connections.retainAll(user2Connections);
		return user1Connections;
	}
	
}
