package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		
	
}
