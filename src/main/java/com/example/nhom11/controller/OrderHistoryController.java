package com.example.nhom11.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.dao.OrderDAOTuan;
import com.example.nhom11.dao.impl.OrderDAOTuanImpl;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Order;

@WebServlet(urlPatterns = "/order-history")
public class OrderHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDAOTuan od = new OrderDAOTuanImpl();
		List<Order> orders = od.viewHistory((Customer) req.getSession().getAttribute("person"));
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("order-history.jsp").forward(req, resp);		
	}
	
	

}
