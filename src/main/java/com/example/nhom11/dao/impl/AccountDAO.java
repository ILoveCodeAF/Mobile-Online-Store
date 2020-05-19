package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.nhom11.dao.AccountDAOTuan;
import com.example.nhom11.model.Account;
import com.example.nhom11.model.Role;
import com.example.nhom11.utils.DBUtil;

public class AccountDAO implements AccountDAOTuan {

	@Override
	public Account login(String username, String password) {
		Connection con=DBUtil.getConnection();
		Account account=null;
		String sql = "SELECT * FROM accounts "
				+ "WHERE username = ? AND password = ?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				account=new Account();
				account.setId(rs.getInt("id"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setRole(Role.valueOf(rs.getString("role")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return account;
	}

}
