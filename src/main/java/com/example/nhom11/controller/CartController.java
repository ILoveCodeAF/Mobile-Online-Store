package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.model.Cart;
import com.example.nhom11.model.PhoneSelling;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		float price = 0;
		
		Cart cart=(Cart) req.getSession().getAttribute("cart");
		
		for(PhoneSelling phone:cart.getPhones()) {
			price+=phone.getPhone().getPrice()*phone.getQuantity();
		}
		
		req.setAttribute("price", price);		
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
		
	}

}
