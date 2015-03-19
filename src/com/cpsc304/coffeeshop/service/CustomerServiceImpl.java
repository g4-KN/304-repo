package com.cpsc304.coffeeshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class CustomerServiceImpl {
	
	private Connection connection;
	
	public CustomerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}
	
	public void testQuery(int customerId) {
		try {
			String query = "SELECT * FROM customer c";
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer c WHERE c.id = ?");
			ps.setInt(1, 123);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
			ps.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
