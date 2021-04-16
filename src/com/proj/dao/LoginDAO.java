package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proj.util.DbConnection;

public class LoginDAO
{

	public static boolean validate(String username, String password)
	{
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = DbConnection.getInstance();
			ps = conn.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

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
