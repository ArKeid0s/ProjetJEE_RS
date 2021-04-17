package com.proj.user;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> commonRelations(int user2ID) {
		UserDao uDao = new DatabaseUserDao();
		User user2 = uDao.findById(user2ID);
		
		List<User> user1Connections = RelationDAO.getRelationsOf(this);
		List<User> user2Connections =RelationDAO.getRelationsOf(user2);
		
		List<User> result = new ArrayList<>();
		
		for(User u : user1Connections) {
			for(User u2 : user2Connections) {
				if(u.getId()==u2.getId()) {
					result.add(u);
					break;
				}
				
			}
		}
		return result;
	}
	
}
