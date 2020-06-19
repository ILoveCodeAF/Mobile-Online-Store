package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.nhom11.dao.CustomerDAOTuan;
import com.example.nhom11.model.Customer;
import com.example.nhom11.utils.ConnectionPool;

public class CustomerDAOTuanImpl implements CustomerDAOTuan {

	@Override
	public Customer add(Customer customer) {
		PreparedStatement ps;
		ResultSet rs;

		String sqlAccount = "INSERT INTO Account(username, password, googleId, fbId, role) " + "VALUES(?,?,?,?,?)";
		String sqlCustomer = "INSERT INTO Customer(name, address, email, dob, phone, account_id) "
				+ "VALUES(?,?,?,?,?,?)";

//		Connection con = DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e2) {
		}
		try {

			ps = con.prepareStatement(sqlAccount, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getAccount().getUsername());
			ps.setString(2, customer.getAccount().getPassword());
			ps.setString(3, customer.getAccount().getGoogleId());
			ps.setString(4, customer.getAccount().getFbId());
			ps.setString(5, customer.getAccount().getRole().toString());

			// Them Account
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				customer.getAccount().setId(rs.getLong(1));
			}
			ps = con.prepareStatement(sqlCustomer, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getEmail());
			try {
				ps.setDate(4, new Date(customer.getDob().getTime()));
			} catch (NullPointerException e) {
				ps.setDate(4, null);
			}
			ps.setString(5, customer.getPhone());
			ps.setLong(6, customer.getAccount().getId());

			// Them Customer
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				customer.setId(rs.getLong(1));
			}

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}

		}
		finally {
			pool.closeConnection(con);
		}

		return customer;

	}

	@Override
	public long checkIfGoogleAccountExist(String googleId) {

		long result = 0;

		String sql = "SELECT customer.id FROM customer, account " 
				+ "WHERE customer.account_id=account.id "
				+ "AND account.googleId is not null AND account.googleId=? ";

		PreparedStatement ps;
		ResultSet rs;
//		Connection con = DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, googleId);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {

		}
		finally {
			pool.closeConnection(con);
		}
		return result;
	}

	@Override
	public long checkIfFbAccountExist(String fbId) {
		long result = 0;

		String sql = "SELECT customer.id FROM customer, account " 
				+ "WHERE customer.account_id=account.id "
				+ "AND account.fbId is not null AND account.fbId=? ";

		PreparedStatement ps;
		ResultSet rs;
//		Connection con = DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, fbId);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
		}
		finally {
			pool.closeConnection(con);
		}

		return result;
	}

}
