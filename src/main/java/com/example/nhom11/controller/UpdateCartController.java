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

@WebServlet(urlPatterns = "/cart-update")
public class UpdateCartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long phoneId = Long.parseLong(req.getParameter("phoneId"));
//		String tag = req.getParameter("tag");
		int tag = Integer.parseInt(req.getParameter("tag"));
		
		float price=0;
		int quantity = 0;
		
		String json = "{\"price\":%f, \"quantity\":%d}";
		
		
		HttpSession session = req.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
		}
		for(PhoneSelling phone:cart.getPhones()) {
			if(phone.getPhone().getId()==phoneId) {
			if(tag==2) 
					phone.setQuantity(phone.getQuantity()+1);				
				else if(tag==1 && phone.getQuantity()>1) 
					phone.setQuantity(phone.getQuantity()-1);
				
				quantity = phone.getQuantity();
				
				break;
			}
		}
		
		
		
		for(PhoneSelling phone:cart.getPhones()) {
			price += phone.getPhone().getPrice()*phone.getQuantity();
		}
		
		String result = String.format(json, price, quantity);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);		
	}
	
	

}
