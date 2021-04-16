package com.proj.dao;

import java.util.List;

import com.proj.user.User;


public interface UserDao {
	/**
	 * Insert a user somewhere.
	 * 
	 * @param user User The user to insert.
	 */
	void insert(User user);
	
	/**
	 * Remove a user.
	 * 
	 * @param user User The user to remove.
	 */
	void remove(User user);
	
	/**
	 * Find a user by ID.
	 * 
	 * @param user User The user to find.
	 */
	User findById(int id);
	
	/**
	 * Find a user by Username and Password.
	 * 
	 * @param user User The user to find.
	 */
	User findByUsernamePwd(String username, String password);
	
	/**
	 * Fetch all the users.
	 * 
	 * @return ArrayList<User>
	 */
	List<User> fetchAll();
}
