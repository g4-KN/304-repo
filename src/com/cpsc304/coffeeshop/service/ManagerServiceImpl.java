package com.cpsc304.coffeeshop.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class ManagerServiceImpl {
	
	private Connection connection;
	
	public ManagerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}
	
	public void deleteTransaction(int transactionNo) throws SQLException {
		String query = "DELETE FROM transaction WHERE transactionno = ?";
		PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, transactionNo);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
	}
	
	public void updateTransactionPointReward(int transactionNo, int reward) throws SQLException {
		String query = "UPDATE transaction t SET t.pointsgenerated = ? WHERE t.transactionNo = ?";
		PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, reward);
            stmt.setInt(2, transactionNo);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
	}

    public List<Map<String, String>> getTransactionBySinWithDate(int SinNo, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(SinNo, "SinNo", start_date, end_date);
    }

    public List<Map<String, String>> getTransactionByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(StoreId, "StoreId", start_date, end_date);
    }

    public List<Map<String, String>> getTransactionByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(MemberId, "MemberId", start_date, end_date);
    }

    public List<Map<String, String>> getTotalMoneyByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "SUM(moneyCost)", start_date, end_date);
    }

    public List<Map<String, String>> getTotalMoneyByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(MemberId, "MemberId", "SUM(moneyCost)",start_date, end_date);
    }

    public List<Map<String, String>> getTotalPointByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "SUM(pointCost)", start_date, end_date);
    }

    public List<Map<String, String>> getTotalPointByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(MemberId, "MemberId", "COUNT(TransactionNo)",start_date, end_date);
    }

    public List<Map<String, String>> getTotalTransactionByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "SUM(pointCost)",start_date, end_date);
    }

    public List<Map<String, String>> getTotalSalaryByStore(int StoreId) throws SQLException {
        String query = "select SUM(Salary) from Employee where StoreId = ?";

        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, StoreId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Map<String, String> nm = new HashMap<>();
                nm.put("SUM(Salary)", "" + rs.getInt("SUM(Salary)"));
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

    public List<Map<String, String>> genericGetAggregateWithDate(int id, String column, String target, Date start_date, Date end_date) throws SQLException {
        String query = "select " + target + " from Transaction where " + column + " = ?";

        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();

        ResultSet rs = executeQueryWithDate(id, query, start_date, end_date);
        if (rs != null) {
            if (rs.next()) {
                Map<String, String> nm = new HashMap<>();
                nm.put(target, "" + rs.getInt(target));
                resultData.add(nm);
            }
        }
        return resultData;
    }


    public List<Map<String, String>> genericGetTransactionWithDate(int id, String column, Date start_date, Date end_date) throws SQLException {
        String query = "select * from Transaction where " + column + " = ?";

        List<Map<String, String>> resultData = new ArrayList<Map<String, String>>();

        ResultSet rs = executeQueryWithDate(id, query, start_date, end_date);
        if (rs != null) {
            while (rs.next()) {
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
        }
        return resultData;
    }

    public ResultSet executeQueryWithDate(int id, String query, Date start_date, Date end_date) throws SQLException {
        String startDateCondition = "Date > ?";
        String endDateCondition = "Date < ?";

        PreparedStatement stmt = null;

        try {
            if ((start_date != null)&&(end_date != null)) {
                query = query + " and " + startDateCondition + " and " + endDateCondition;
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.setDate(2, start_date);
                stmt.setDate(3, end_date);
            } else if ((start_date != null)&&(end_date == null)) {
                query = query + " and " + startDateCondition;
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.setDate(2, start_date);
            } else if ((start_date == null)&&(end_date != null)) {
                query = query + " and " + endDateCondition;
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.setDate(2, end_date);
            }

            ResultSet rs = stmt.executeQuery();

            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return null;
    }

    public Map<String, String> getSpecialCustomer(boolean max) throws SQLException {
    	String minOrMax = (max ? "MAX" : "MIN");
    	String query = "SELECT m.memberid, " + minOrMax + "(a.average) minmax, m.name FROM (SELECT Memberid, AVG(moneycost) average FROM TRANSACTION GROUP BY memberid) a, member m WHERE m.memberid = a.memberid";

    	Map<String, String> resultData = new HashMap<String, String>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
            	while (rs.next()) {
	                resultData.put("MemberId", rs.getString("MemberId"));
	                resultData.put("Value", "" + rs.getInt("minmax"));
	                resultData.put("Name", rs.getString("name"));
            	}
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return resultData;
    }
}
