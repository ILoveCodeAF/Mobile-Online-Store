package com.example.nhom11.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;

@WebServlet(urlPatterns = "/admin/search-phone")
public class SearchPhoneController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("key", "");
		req.getRequestDispatcher("search-phone.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		List<Phone> phones=pd.search(key);
		
		req.setAttribute("key", key);
		req.setAttribute("phones", phones);
		req.getRequestDispatcher("search-phone.jsp").forward(req, resp);
		
	}

	
	
}
