package com.cpsc304.coffeeshop.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        testQuery(con);
//		try {
//			PreparedStatement ps = con.prepareStatement("insert into PostalCodeReference values ('v1v1v1', 'van', 'bc')");
//			ps.executeUpdate();
//			ps.close();
//		} catch (Exception e) {
//			System.out.println("Test Failed");
//		}
	}

    public static ObservableList<Map> testQuery(Connection con) {
        String query = "select * from PostalCodeReference";
        ObservableList<Map> resultData = FXCollections.observableArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Map<String, String> nm = new HashMap<>();
                nm.put("PostalCode", rs.getString("PostalCode"));
                nm.put("City", rs.getString("City"));
                nm.put("Province", rs.getString("Province"));
                resultData.add(nm);
            }
        } catch (Exception e) {
            System.out.println("Test Failed");
        }
        return resultData;

    }

}
