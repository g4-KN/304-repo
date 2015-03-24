package com.cpsc304.coffeeshop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalConnection {
	
	private static LocalConnection localConnection;
	private Connection connection;
	
	public LocalConnection() {
		localConnection = this;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.out.println("Add MySQL to your IDE/Server.");
			System.exit(-1);
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc304", "root", "");
			System.out.println("Connection established with Local MySQL database");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Could not establish connection to Local MySQL database");
			System.exit(-1);
		}
	}
	
	public static LocalConnection getLocalConnection() {
		if (localConnection == null) {
			localConnection = new LocalConnection();
		}
		return localConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static void main(String[] args) {
		LocalConnection lc = getLocalConnection();
		Connection con = lc.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO test values (2,1)");
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("Test Failed");
		}
	}
}
