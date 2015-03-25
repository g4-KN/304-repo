package com.cpsc304.coffeeshop.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class CustomerServiceImpl {
	
	private Connection connection;
	
	public CustomerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}
	
	 public List<Map<String, String>> showStoreInfo() throws SQLException {
	        String query = "SELECT * FROM store";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Map<String, String> nm = new HashMap<>();
	                nm.put("StoreId", "" + rs.getInt("StoreId"));
	                nm.put("HouseNo", "" + rs.getInt("HouseNo"));
	                nm.put("Street", rs.getString("Street"));
	                nm.put("PostalCode", "" + rs.getString("PostalCode"));
	               
	                resultData.add(nm);
	                
	              //  System.out.println(nm);
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
	        String query = "SELECT * FROM food";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointReward", "" + rs.getInt("pointReward"));
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("foodName", "" + rs.getString("foodName"));
	               
	                resultData.add(nm);
	                
	               // System.out.println(nm);
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
	        String query = "SELECT * FROM drink";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()){
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointReward", "" + rs.getInt("pointReward"));
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("drinkName", "" + rs.getString("drinkName"));
	                nm.put("size", "" + rs.getString("size"));
	               
	                resultData.add(nm);
	                
	              //  System.out.println(nm);
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
		 // joining tables together
	        String query = "SELECT * FROM drink d, drinkMenu dm WHERE dm.drinkName = d.drinkName AND dm.storeId = ?";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            stmt.setInt(1, storeId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()){
	            	// this result adding process act as a second layer of filtering
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointReward", "" + rs.getInt("pointReward"));
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("drinkName", "" + rs.getString("drinkName"));
	                nm.put("size", "" + rs.getString("size"));
	               
	                resultData.add(nm);
	                
	               // System.out.println(nm);
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
	        String query = "SELECT * FROM food f, foodMenu fm WHERE fm.foodName = f.foodName AND fm.storeId = ?";
	        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
	        PreparedStatement stmt = null;
	        try {
	            stmt = connection.prepareStatement(query);
	            stmt.setInt(1, storeId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()){
	            	// this result adding process act as a second layer of filtering
	                Map<String, String> nm = new HashMap<>();
	                nm.put("pointReward", "" + rs.getInt("pointReward"));
	                nm.put("pointCost", "" + rs.getInt("pointCost"));
	                nm.put("price", "" + rs.getInt("price"));
	                nm.put("foodName", "" + rs.getString("foodName"));
	               
	                resultData.add(nm);
	                
	               // System.out.println(nm);
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
