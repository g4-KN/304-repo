package com.cpsc304.coffeeshop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagerServiceImpl {
	
	private Connection connection;
	
	public ManagerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

    public List<Map<String, String>> getTransactionBySinWithDate(int SinNo, Date start_date, Date end_date){
        String query = "select * from Transaction where SinNo = ? and Date < ? and Date > ? ";
        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, SinNo);
            stmt.setDate(2, end_date);
            stmt.setDate(3, start_date);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                //TransactionNo | StoreId | pointsGenerated | Date | MemberId | SinNo | moneyCost | pointCost
                //System.out.println(rs.getString("Name"));
                Map<String, String> nm = new HashMap<>();
                nm.put("TransactionNo", "" + rs.getInt("TransactionNo"));
                nm.put("StoreId", "" + rs.getInt("StoreId"));
                nm.put("pointsGenerated", "" + rs.getInt("pointsGenerated"));
                nm.put("Date", rs.getDate("Date").toString());
                nm.put("MemberId", "" + rs.getInt("MemberId"));
                nm.put("SinNo", "" + rs.getInt("SinNo"));
                nm.put("moneyCost", "" + rs.getBigDecimal("moneyCost"));
                nm.put("pointCost", "" + rs.getInt("pointCost"));
                resultData.add(nm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public List<Map<String, String>> getTransactionByStoreWithDate(int StoreId, Date start_date, Date end_date){
        String query = "select * from Transaction where StoreId = ? and Date < ? and Date > ? ";
        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, StoreId);
            stmt.setDate(2, end_date);
            stmt.setDate(3, start_date);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                //TransactionNo | StoreId | pointsGenerated | Date | MemberId | SinNo | moneyCost | pointCost
                //System.out.println(rs.getString("Name"));
                Map<String, String> nm = new HashMap<>();
                nm.put("TransactionNo", "" + rs.getInt("TransactionNo"));
                nm.put("StoreId", "" + rs.getInt("StoreId"));
                nm.put("pointsGenerated", "" + rs.getInt("pointsGenerated"));
                nm.put("Date", rs.getDate("Date").toString());
                nm.put("MemberId", "" + rs.getInt("MemberId"));
                nm.put("SinNo", "" + rs.getInt("SinNo"));
                nm.put("moneyCost", "" + rs.getBigDecimal("moneyCost"));
                nm.put("pointCost", "" + rs.getInt("pointCost"));
                resultData.add(nm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

}
