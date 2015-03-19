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
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Could not establish driver");
			System.exit(-1);
		}
		
		String ubcConnectionUrl = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
		
		try {
			connection = DriverManager.getConnection(ubcConnectionUrl, "ora_q8p8", "a31827116");
			System.out.println("Connection established with Oracle database");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Could not establish connection to Oracle database");
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
			PreparedStatement ps = con.prepareStatement("INSERT INTO test values (7,1)");
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (Exception e) {
			System.out.println("GG");
		}
	}
}
