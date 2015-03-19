package com.cpsc304.coffeeshop.service;

import java.sql.Connection;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class AdminServiceImpl {
	
	private Connection connection;
	
	public AdminServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

}
