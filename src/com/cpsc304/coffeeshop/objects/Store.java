package com.cpsc304.coffeeshop.objects;

import javafx.beans.property.SimpleStringProperty;

public class Store {
	private final SimpleStringProperty storeId;
    private final SimpleStringProperty houseNo;
    private final SimpleStringProperty street;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty province;

    public Store(String storeId, String houseNo, String street, String postalCode, String city, String province) {
    	this.storeId = new SimpleStringProperty(storeId);
    	this.houseNo = new SimpleStringProperty(houseNo);
    	this.street = new SimpleStringProperty(street);
    	this.postalCode = new SimpleStringProperty(postalCode);
    	this.city = new SimpleStringProperty(city);
    	this.province = new SimpleStringProperty(province);
    }

    public String getStoreId() {
    	return storeId.get();
    }
    
    public void setStoreId(String storeId) {
    	this.storeId.set(storeId);
    }
    
    public String getHouseNo() {
    	return houseNo.get();
    }
    
    public void setHouseNo(String houseNo) {
    	this.houseNo.set(houseNo);
    }
    
    public String getStreet() {
    	return street.get();
    }
    
    public void setStreet(String street) {
    	this.street.set(street);
    }
    
    public String getPostalCode() {
    	return postalCode.get();
    }
    
    public void setPostalCode(String postalCode) {
    	this.postalCode.set(postalCode);
    }
    
    public String getCity() {
    	return city.get();
    }
    
    public void setCity(String city) {
    	this.city.set(city);
    }
    
    public String getProvince() {
    	return province.get();
    }
    
    public void setProvince(String province) {
    	this.province.set(province);
    }
}
