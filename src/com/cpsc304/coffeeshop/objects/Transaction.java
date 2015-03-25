package com.cpsc304.coffeeshop.objects;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {
	private final SimpleStringProperty transactionNo;
	private final SimpleStringProperty pointsGenerated;
	private final SimpleStringProperty date;
	private final SimpleStringProperty storeId;
	private final SimpleStringProperty memberId;
	private final SimpleStringProperty sinNo;
	private final SimpleStringProperty pointCost;
	private final SimpleStringProperty moneyCost;
	
	public Transaction (String transactionNo, String pointsGenerated, String date, String storeId, String memberId, String sinNo, String pointCost, String moneyCost) {
		this.transactionNo = new SimpleStringProperty(transactionNo);
		this.pointsGenerated = new SimpleStringProperty(pointsGenerated);
		this.date = new SimpleStringProperty(date);
		this.storeId = new SimpleStringProperty(storeId);
		this.memberId = new SimpleStringProperty(memberId);
		this.sinNo = new SimpleStringProperty(sinNo);
		this.pointCost = new SimpleStringProperty(pointCost);
		this.moneyCost = new SimpleStringProperty(moneyCost);
	}
	
	public String getTransactionNo() {
		return transactionNo.get();
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo.set(transactionNo);
	}
	
	public String getPointsGenerated() {
		return pointsGenerated.get();
	}

	public void setPointsGenerated(String pointsGenerated) {
		this.pointsGenerated.set(pointsGenerated);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}
	
	public String getStoreId() {
		return storeId.get();
	}

	public void setStoreId(String storeId) {
		this.storeId.set(storeId);
	}
	
	public String getMemberId() {
		return transactionNo.get();
	}

	public void setMemberId(String memberId) {
		this.memberId.set(memberId);
	}
	
	public String getSinNo() {
		return sinNo.get();
	}

	public void setSinNo(String sinNo) {
		this.sinNo.set(sinNo);
	}
	
	public String getPointCost() {
		return pointCost.get();
	}

	public void setPointCost(String pointCost) {
		this.pointCost.set(pointCost);
	}
	
	public String getMoneyCost() {
		return moneyCost.get();
	}

	public void setMoneyCost(String moneyCost) {
		this.moneyCost.set(moneyCost);
	}
}
