package com.cpsc304.coffeeshop.service;

import java.sql.Connection;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class ManagerServiceImpl {
	
	private Connection connection;
	
	public ManagerServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

}
