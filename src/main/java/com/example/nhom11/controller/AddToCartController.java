package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Cart;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.PhoneSelling;

@WebServlet(urlPatterns = "/cart-add")
public class AddToCartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		long phoneId = Long.parseLong(req.getParameter("phoneId"));
		
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		
		Phone phone=pd.getById(phoneId);
		
		PhoneSelling phoneSelling = new PhoneSelling(0, phone, 1);
		
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart==null) {
			cart=new Cart();
		}
		
		cart.addPhone(phoneSelling);		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart");
		
	}
	
	

}
