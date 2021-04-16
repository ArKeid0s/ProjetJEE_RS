package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.proj.user.User;
import com.proj.util.DbConnection;

public class LoginDAO
{
	
	/*TODO: REMOVE NOT USED ANYMORE*/
	
	
	/*
	 * Setup User DAO
	 * */
	UserDao userDao = new DatabaseUserDao();
	List<User> users = userDao.fetchAll();

	public static boolean validate(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPwd());

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				/* If result found -> valid inputs */
				return true;
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Login error => " + ex.getMessage());
			return false;
		}
		finally
		{
			DbConnection.close();
		}
		return false;
	}

}
