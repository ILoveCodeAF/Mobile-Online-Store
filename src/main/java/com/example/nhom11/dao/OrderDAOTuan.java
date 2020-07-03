package com.example.nhom11.dao;

import java.util.List;

import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Order;

public interface OrderDAOTuan {

	public Order order(Order order);
	
	public List<Order> viewHistory(Customer customer);
	
	public Order viewDetail(long id);
	
}
