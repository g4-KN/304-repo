package com.cpsc304.coffeeshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberServiceImpl {
	
	private Connection connection;
	
	public MemberServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

    public  ObservableList<Map> getMemberById(int memberId) {
        String query = "select * from Member where MemberId = ?";
        ObservableList<Map> resultData = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                //| MemberId | PointBalance | Name  | Phone    | HouseNo | Street     | PostalCode |
                //System.out.println(rs.getString("Name"));
                Map<String, String> nm = new HashMap<>();
                nm.put("MemberId", "" + rs.getInt("MemberId"));
                nm.put("PointBalance", "" + rs.getInt("PointBalance"));
                nm.put("Name", rs.getString("Name"));
                nm.put("Phone", "" + rs.getInt("Phone"));
                nm.put("HouseNo", "" + rs.getInt("HouseNo"));
                nm.put("Street", rs.getString("Street"));
                nm.put("PostalCode", rs.getString("PostalCode"));
                resultData.add(nm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

}
