package com.proj.util;

import java.sql.*;

public class DbConnection {

	/**
	 * JDBC driver's class name
	 */
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * DSN to connect to the database
	 */
	static final String DB_URL = "jdbc:mysql://localhost/projetjee";

	/**
	 * Username to connect to the database
	 */
	static final String USER = "root";

	/**
	 * Password to connect to the database
	 */
	static final String PASS = "";

	/**
	 * The connection to the database
	 */
	private static Connection connection; 

	/**
	 * Get a connection to the database
	 * 
	 * It's a singleton, it let us using only one connection
	 * instead of one connection in each method call.
	 * @return
	 */
	public static Connection getInstance()
	{
		try {
			if (null == connection || connection.isClosed()) {
				try {
					Class.forName(JDBC_DRIVER);
					connection = DriverManager.getConnection(DB_URL,USER,PASS);
				} catch (ClassNotFoundException e) {
					//Class.forName failed
					e.printStackTrace();
				} catch (SQLException e) {
					//DriverManager.getConnection failed
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Close the connection to the database
	 */
	public static void close()
	{
		try{
			if(connection != null) {
				connection.close();
				connection = null;
			}
		} catch(SQLException se){
			se.printStackTrace();
		}	
	}
		
}
