package com.cpsc304.coffeeshop.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;
import com.cpsc304.coffeeshop.objects.Store;

public class CustomerServiceImpl {
	
	private Connection connection;
	
	public CustomerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}
	
	 public List<Store> showStoreInfo() throws SQLException {
	        String query = "SELECT * FROM store s, postalcodereference p WHERE s.postalcode = p.postalcode";
	        List<Store> resultData = new ArrayList<Store>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Store s = new Store("" + rs.getInt("StoreId"), "" +rs.getInt("HouseNo"), rs.getString("Street"), rs.getString("PostalCode"), rs.getString("City"), rs.getString("Province"));
	                resultData.add(s);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	        return resultData;
	  }
	 
	 public List<Map<String, String>> showFoodInfo() throws SQLException {
	        String query = "SELECT f.foodName, f.price, f.pointCost FROM food f";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("foodName", "" + rs.getString("foodName"));
	               
	                resultData.add(nm);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	        return resultData;
	 }
	 
	 public List<Map<String, String>> showDrinkInfo() throws SQLException {
	        String query = "SELECT d.drinkName, d.size, d.price, d.pointCost FROM drink d";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("drinkName", "" + rs.getString("drinkName"));
	                nm.put("size", "" + rs.getString("size"));
	               
	                resultData.add(nm);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	        return resultData;
	 }
	 
	 public List<Map<String, String>> showDrinkMenuByStoreId(int storeId) throws SQLException {
	        String query = "SELECT d.drinkName, d.size, d.price, d.pointCost FROM drink d, drinkMenu dm WHERE dm.drinkName = d.drinkName AND dm.storeId = ?";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            stmt.setInt(1, storeId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()){
	            	// this result adding process act as a second layer of filtering
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("drinkName", "" + rs.getString("drinkName"));
	                nm.put("size", "" + rs.getString("size"));
	               
	                resultData.add(nm);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	        return resultData;
	 }
	 public List<Map<String, String>> showFoodMenuByStoreId(int storeId) throws SQLException {
		 // joining tables together
	        String query = "SELECT f.foodName, f.price, f.pointCost FROM food f, foodMenu fm WHERE fm.foodName = f.foodName AND fm.storeId = ?";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            stmt.setInt(1, storeId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()){
	            	// this result adding process act as a second layer of filtering
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("foodName", "" + rs.getString("foodName"));
	               
	                resultData.add(nm);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	        return resultData;
	 }
	  
	  
}
