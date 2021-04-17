package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proj.user.User;
import com.proj.util.DbConnection;

public class RelationDAO {

	
	public static boolean insert(int mainUserID, int targetUserID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO relations (main_user_id, target_user_id) VALUES (?,?)";
		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, mainUserID);
			ps.setInt(2, targetUserID);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[INSERT RELATION] "+e.getMessage());
			return false;
		} finally {
			if(ps != null){
				try{
					ps.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static boolean remove(int mainUserID, int targetUserID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM relations WHERE main_user_id=? AND target_user_id=?";
		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, mainUserID);
			ps.setInt(2, targetUserID);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[REMOVE RELATION] "+e.getMessage());
			return false;
		} finally {
			if(ps != null){
				try{
					ps.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static List<User> getRandomNewRelationsFor(User user, int amount)
	{
		Connection conn = null;
		PreparedStatement ps = null;

		List<User> users = new ArrayList<>();

		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement("SELECT * FROM users WHERE user_id NOT IN (SELECT target_user_id FROM relations WHERE main_user_id=?) AND user_id!=? LIMIT ?");
			ps.setInt(1, user.getId());
			ps.setInt(2, user.getId());
			ps.setInt(3, amount);

			ResultSet rs = ps.executeQuery();

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
		}
		catch (SQLException ex)
		{
			System.out.println("[getRandomNewRelationsFor] " + ex.getMessage());
			return users;
		}
		finally
		{
			DbConnection.close();
		}
		return users;
	}

	
	
	public static List<User> getRelationsOf(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;

		List<User> users = new ArrayList<>();

		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement("SELECT * FROM users INNER JOIN relations ON target_user_id=user_id WHERE main_user_id=?");
			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();

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
		}
		catch (SQLException ex)
		{
			System.out.println("[getRelationsOf] " + ex.getMessage());
			return users;
		}
		finally
		{
			DbConnection.close();
		}
		return users;
	}
		
	
}
