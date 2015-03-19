package com.cpsc304.coffeeshop.service;

import java.sql.Connection;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class EmployeeServiceImpl {
	
	private Connection connection;
	
	public EmployeeServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

}
