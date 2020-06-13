package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.model.Cart;
import com.example.nhom11.model.Customer;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		Customer customer=(Customer) session.getAttribute("person");
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null || cart.getPhones().size()==0) {
			req.setAttribute("notification", "There is no Item in your Cart");
			req.getRequestDispatcher("notification.jsp").forward(req, resp);
		}
		else {
			
		}
	}
	
	
	

}
