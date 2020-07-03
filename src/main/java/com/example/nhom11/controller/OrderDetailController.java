package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.dao.OrderDAOTuan;
import com.example.nhom11.dao.impl.OrderDAOTuanImpl;
import com.example.nhom11.model.Order;
import com.example.nhom11.model.PhoneSelling;

@WebServlet(urlPatterns = "/order-detail")
public class OrderDetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long orderId = Long.parseLong(req.getParameter("id"));
		OrderDAOTuan od = new OrderDAOTuanImpl();
		Order order = od.viewDetail(orderId);		
		float price = 0;
		for(PhoneSelling ps: order.getCart().getPhones()) {
			price += ps.getQuantity()*ps.getPhone().getPrice();
		}
		if(order.getShipment()!=null) {
			price +=order.getShipment().getPrice();
		}
		order.setPrice(price);
		req.setAttribute("order", order);
		req.getRequestDispatcher("order-detail.jsp").forward(req, resp);
	}
	
	

}
