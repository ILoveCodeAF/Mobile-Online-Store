package com.example.nhom11.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPool {

	private static ConnectionPool instance = null;
	private static BasicDataSource ds = null;
	private static InputStream is = null;
	private static Properties p = null;

	private ConnectionPool() {
		is = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		p = new Properties();
		try {
			p.load(is);
			is.close();
		} catch (IOException e) {

		}
		ds = new BasicDataSource();
		ds.setDriverClassName(p.getProperty("db.driverClassName"));
		ds.setUrl(p.getProperty("db.url"));
		ds.setUsername(p.getProperty("db.username"));
		ds.setPassword(p.getProperty("db.password"));
		ds.setInitialSize(2);
		ds.setMaxActive(5);
	}

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ConnectionPool pool = ConnectionPool.getInstance();

		Connection con = pool.getConnection();
		if (con == null)
			System.out.println("Fail");

		else
			System.out.println("Success");

	}

}
