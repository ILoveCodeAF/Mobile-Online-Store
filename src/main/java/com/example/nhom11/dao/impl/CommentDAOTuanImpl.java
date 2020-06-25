package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.nhom11.dao.CommentDAOTuan;
import com.example.nhom11.model.Comment;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Phone;
import com.example.nhom11.utils.ConnectionPool;

public class CommentDAOTuanImpl implements CommentDAOTuan {

	@Override
	public Comment add(Comment c) {
		String sql = "INSERT INTO comment(content, customer_id, phone_id, date) "
				+ "VALUES(?,?,?,?)";
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getContent());
			ps.setLong(2, c.getCustomer().getId());
			ps.setLong(3, c.getPhone().getId());
			c.setDate(new Date());
			ps.setDate(4, new java.sql.Date(c.getDate().getTime()));
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) {		//Neu them thanh cong se tra ve ID vua sinh ra cua Comment
				c.setId(rs.getLong(1));
			}			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
		
		return c;
	}

	@Override
	public List<Comment> loadAllComment(Phone phone) {
		List<Comment> comments=new ArrayList<>();
		String sql = "SELECT comment.content as content, comment.date as date, customer.name as name "
				+ "FROM comment, customer "
				+ "WHERE comment.customer_id = customer.id AND comment.phone_id = ? "
				+ "ORDER BY comment.id DESC";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, phone.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				Comment comment = new Comment();
				customer.setName(rs.getString("name"));
				comment.setDate(new Date(rs.getDate("date").getTime()));
				comment.setContent(rs.getString("content"));
				comment.setCustomer(customer);
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			pool.closeConnection(con);
		}
		
		return comments;
	}

}
