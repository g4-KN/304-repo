package com.cpsc304.coffeeshop.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpsc304.coffeeshop.database.LocalConnection;
import com.cpsc304.coffeeshop.objects.Transaction;

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

    public List<Transaction> getTransactionBySinWithDate(int SinNo, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(SinNo, "SinNo", start_date, end_date);
    }

    public List<Transaction> getTransactionByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(StoreId, "StoreId", start_date, end_date);
    }

    public List<Transaction> getTransactionByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetTransactionWithDate(MemberId, "MemberId", start_date, end_date);
    }

    public String getTotalMoneyByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "SUM(moneyCost)", start_date, end_date);
    }

    public String getTotalMoneyByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(MemberId, "MemberId", "SUM(moneyCost)",start_date, end_date);
    }

    public String getTotalPointByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "SUM(pointCost)", start_date, end_date);
    }

    public String getTotalPointByMemberWithDate(int MemberId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(MemberId, "MemberId", "SUM(pointCost)",start_date, end_date);
    }

    public String getTotalTransactionByStoreWithDate(int StoreId, Date start_date, Date end_date) throws SQLException {
        return genericGetAggregateWithDate(StoreId, "StoreId", "COUNT(TransactionNo)",start_date, end_date);
    }

    public String getTotalSalaryByStore(int StoreId) throws SQLException {
        String query = "select SUM(Salary) from Employee where StoreId = ?";

        String resultData = "";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, StoreId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultData = "" + rs.getInt("SUM(Salary)");
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

    public String genericGetAggregateWithDate(int id, String column, String target, Date start_date, Date end_date) throws SQLException {
        String query = "select " + target + " from Transaction where " + column + " = ?";

        String resultData = "";

        ResultSet rs = executeQueryWithDate(id, query, start_date, end_date);
        if (rs != null) {
            if (rs.next()) {
                resultData = "" + rs.getInt(target);
            }
            rs.close();
        }
        return resultData;
    }


    public List<Transaction> genericGetTransactionWithDate(int id, String column, Date start_date, Date end_date) throws SQLException {
        String query = "select * from Transaction where " + column + " = ?";

        List<Transaction> resultData = new ArrayList<Transaction>();

        ResultSet rs = executeQueryWithDate(id, query, start_date, end_date);
        if (rs != null) {
            while (rs.next()) {
                //TransactionNo | StoreId | pointsGenerated | Date | MemberId | SinNo | moneyCost | pointCost
                //System.out.println(rs.getString("Name"));
                Transaction t = new Transaction("" + rs.getInt("TransactionNo"), "" + rs.getInt("pointsGenerated"), rs.getDate("Date").toString(), "" + rs.getInt("StoreId"), "" + rs.getInt("MemberId"), "" + rs.getInt("SinNo"), "" + rs.getInt("pointCost"), "" + rs.getBigDecimal("moneyCost"));
                resultData.add(t);
            }
            rs.close();
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
            } else {
            	stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
            }

            ResultSet rs = stmt.executeQuery();
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> getSpecialCustomer(boolean max) throws SQLException {
    	String minOrMax = (max ? "MAX" : "MIN");
    	String query = "SELECT " + minOrMax + "(a.average) minmax FROM (SELECT Memberid, AVG(moneycost) average FROM TRANSACTION GROUP BY memberid) a, member m WHERE m.memberid = a.memberid";

    	Map<String, String> resultData = new HashMap<String, String>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
            	while (rs.next()) {
	                resultData.put("Value", "" + rs.getBigDecimal("minmax"));
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
