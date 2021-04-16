package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj.user.User;
import com.proj.util.DbConnection;

public class DatabaseUserDao implements UserDao
{

	/* TODO: USE A FACTORY TO CREATE THE USERS */

	/**
	 * SQL query used to fetch users
	 */
	private static String FETCH_USERS_SQL = "SELECT user_id, username, firstname, lastname, email, password FROM users";

	/**
	 * SQL query used to insert user
	 */
	private static String INSERT_USER_SQL = "INSERT INTO users VALUES ?, ?, ?, ?, ?, ?";

	/**
	 * SQL query used to remove user
	 */
	private static String REMOVE_USER_SQL = "DELETE FROM users WHERE user_id = ?";

	/**
	 * SQL query used to find user by ID
	 */
	private static String FIND_USER_ID_SQL = "SELECT user_id, username, firstname, lastname, email, password FROM users WHERE user_id = ?";

	/**
	 * SQL query used to find user by Username and Pwd
	 */
	private static String FIND_USER_USERNAMEPWD_SQL = "SELECT user_id, username, firstname, lastname, email, password FROM users WHERE username = ? AND password = ?";

	@Override
	public void insert(User user)
	{
		try
		{
			Connection conn = DbConnection.getInstance();
			PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPwd());

			ps.executeQuery();
			ps.close();
			
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				System.out.println("Connection cannot be closed => " + ex.getMessage());
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Insert user error => " + ex.getMessage());
		}


	}

	@Override
	public void remove(User user)
	{

		try
		{
			Connection conn = DbConnection.getInstance();
			PreparedStatement ps = conn.prepareStatement(REMOVE_USER_SQL);
			ps.setInt(1, user.getId());

			ps.executeQuery();
			ps.close();
			
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				System.out.println("Connection cannot be closed => " + ex.getMessage());
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Remove user error => " + ex.getMessage());
		}


	}

	@Override
	public List<User> fetchAll()
	{
		List<User> users = new ArrayList<User>();

		try
		{
			Connection conn = DbConnection.getInstance();
			Statement stmt;
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(FETCH_USERS_SQL);
			// Loop over the database result set and create the
			// user objects.
			while (rs.next())
			{
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				// Probably need to be removed for security
				u.setPwd(rs.getString("password"));
				users.add(u);
			}
			// Free resources
			rs.close();
			stmt.close();
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				System.out.println("Connection cannot be closed => " + ex.getMessage());
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Fetch error => " + ex.getMessage());
		}


		return users;
	}

	@Override
	public User findById(int id)
	{

		try
		{
			Connection conn = DbConnection.getInstance();
			PreparedStatement ps = conn.prepareStatement(FIND_USER_ID_SQL);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				// Probably need to be removed for security
				u.setPwd(rs.getString("password"));

				// Free resources
				rs.close();
				ps.close();
				
				try
				{
					conn.close();
				}
				catch (SQLException ex)
				{
					System.out.println("Connection cannot be closed => " + ex.getMessage());
				}

				return u;
			}
		}
		catch (SQLException ex)
		{
			System.out.println("FindById error => " + ex.getMessage());
		}

		return null;

	}

	@Override
	public User findByUsernamePwd(String username, String pwd)
	{

		try
		{
			Connection conn = DbConnection.getInstance();
			PreparedStatement ps = conn.prepareStatement(FIND_USER_USERNAMEPWD_SQL);
			ps.setString(1, username);
			ps.setString(2, pwd);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				// Probably need to be removed for security
				u.setPwd(rs.getString("password"));

				// Free resources
				rs.close();
				ps.close();
				
				try
				{
					conn.close();
				}
				catch (SQLException ex)
				{
					System.out.println("Connection cannot be closed => " + ex.getMessage());
				}

				return u;
			}

		}
		catch (SQLException ex)
		{
			System.out.println("FindByUsernamePwd error => " + ex.getMessage());
		}

		return null;

	}

}
