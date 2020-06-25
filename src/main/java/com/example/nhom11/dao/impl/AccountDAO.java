package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.example.nhom11.dao.AccountDAOTuan;
import com.example.nhom11.model.Account;
import com.example.nhom11.model.Admin;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Person;
import com.example.nhom11.model.Role;
import com.example.nhom11.utils.ConnectionPool;

public class AccountDAO implements AccountDAOTuan {

	@Override
	public Person login(String username, String password) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
//		Connection con = DBUtil.getConnection();
		Account account = null;
		Person person = null;
		
		String sql = "SELECT * FROM account " + "WHERE username = ? AND password = ?";
		String sqlPerson = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setId(rs.getLong("id"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setRole(Role.valueOf(rs.getString("role")));
			}

			if (account != null) { // Ton tai tai khoan voi Username va Password tren
				switch (account.getRole()) {
				case CUSTOMER:
					person = new Customer();
					sqlPerson = "SELECT * FROM customer WHERE account_id = ?";
					break;
				case ADMIN:
					person = new Admin();
					sqlPerson = "SELECT * FROM admin WHERE account_id = ?";
					break;
				default:
					break;
				}
				
				ps=con.prepareStatement(sqlPerson);
				ps.setLong(1, account.getId());
				rs=ps.executeQuery();
				if(rs.next()) {
					person.setAccount(account);
					person.setAddress(rs.getString("address"));
					person.setEmail(rs.getString("email"));
					person.setId(rs.getLong("id"));
					person.setName(rs.getString("name"));
					person.setPhone(rs.getString("phone"));
					person.setDob(new Date(rs.getDate("dob").getTime()));					
				}
				else {
					person=null;
				}

			}

		} catch (Exception e) {
		}
		finally {
			pool.closeConnection(con);
		}
		return person;
	}

}
