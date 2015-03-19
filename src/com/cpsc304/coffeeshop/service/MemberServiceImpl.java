package com.cpsc304.coffeeshop.service;

import java.sql.Connection;

import com.cpsc304.coffeeshop.database.LocalConnection;

public class MemberServiceImpl {
	
	private Connection connection;
	
	public MemberServiceImpl() {
		LocalConnection lc = LocalConnection.getLocalConnection();
		this.connection = lc.getConnection();
	}

}
