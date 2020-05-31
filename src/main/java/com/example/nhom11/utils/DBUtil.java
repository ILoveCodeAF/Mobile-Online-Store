package com.example.nhom11.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static InputStream is=null;
	private static Properties p=null;
	
	private DBUtil(){
		is = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		p=new Properties();
		try {
			p.load(is);
			is.close();
		} catch (IOException e) {
			
		}
	}
	
	public static Connection getConnection() {
		if(p==null || is==null) {
			new DBUtil();
		}		
		try {
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			String url = p.getProperty("db.url");
			String driverClassName = p.getProperty("db.driverClassName");
			Class.forName(driverClassName);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
