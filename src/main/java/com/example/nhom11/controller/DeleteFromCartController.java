package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.model.Cart;
import com.example.nhom11.model.PhoneSelling;

@WebServlet(urlPatterns = "/cart-delete")
public class DeleteFromCartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long phoneId = Long.parseLong(req.getParameter("phoneId"));
		
		HttpSession session = req.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
		}
		
		for(PhoneSelling phone:cart.getPhones()) {
			if(phone.getPhone().getId()==phoneId) {
				cart.getPhones().remove(phone);
				break;
			}
		}
		
		float price=0;
		for(PhoneSelling phone:cart.getPhones()) {
			price+=phone.getPhone().getPrice()*phone.getQuantity();
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(price);
		
		
	}

	
	
	
}
