package com.example.nhom11.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.OrderDAOTuan;
import com.example.nhom11.dao.impl.OrderDAOTuanImpl;
import com.example.nhom11.model.Cart;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Order;
import com.example.nhom11.model.PaymentType;
import com.example.nhom11.model.PhoneSelling;
import com.example.nhom11.model.ReceivingType;
import com.example.nhom11.model.Shipment;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Shipment shipment = null;
		if(ReceivingType.valueOf(req.getParameter("receivingType"))==ReceivingType.SHIPPING) {
			shipment =new Shipment(0, req.getParameter("address").trim(), req.getParameter("phone").trim(), 
					null, 1);
		}		
		
		cart.setCustomer((Customer) session.getAttribute("person"));		
		Order order = new Order(0, new Date(), cart, ReceivingType.valueOf(req.getParameter("receivingType")), 
				PaymentType.valueOf(req.getParameter("paymentType")), shipment);
		
		OrderDAOTuan orderDAO = new OrderDAOTuanImpl();		
		order = orderDAO.order(order);
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		//Order khong thanh cong
		if(order.getId()==0) {
			String content = "<script>"
					+ "alert('Order Fail');"
					+"location.replace('order-history');"
					+ "</script>";
			out.print(content);
		}
		//Order thanh cong
		else {
			cart.setId(0);
			cart.setPhones(new HashSet<PhoneSelling>());
			String content = "<script>"
					+ "alert('Order Success');"
					+"location.replace('order-history');"
					+ "</script>";
			out.print(content);
		}
		
	}

	
	
	
}
