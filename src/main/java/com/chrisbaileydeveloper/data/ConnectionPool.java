package com.chrisbaileydeveloper.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionPool {

	private DataSource dataSource;

	public ConnectionPool() {
		try {
			dataSource = JdbcConnectionPool.create(
					"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		}
	}

	private static class ConnectionPoolHolder {
		public static final ConnectionPool INSTANCE = new ConnectionPool();
	}

	public static ConnectionPool getInstance() {
		return ConnectionPoolHolder.INSTANCE;
	}
}
