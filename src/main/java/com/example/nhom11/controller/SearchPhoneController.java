package com.example.nhom11.controller;

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

@WebServlet(urlPatterns = "/search-phone")
public class SearchPhoneController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int EACH_PAGE = 10;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = Integer.parseInt(req.getParameter("page"));
		
		String key = "";
		if(req.getParameter("key")!=null) {
			key = req.getParameter("key").trim();
		}
		
		
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		List<Phone> phones=pd.searchWithPagination(key, page, EACH_PAGE);
		
		req.setAttribute("key", key);
		req.setAttribute("page", page);
		req.setAttribute("phones", phones);
		req.setAttribute("totalPage", pd.getTotalPage(key, EACH_PAGE));
		
		req.getRequestDispatcher("search-phone.jsp").forward(req, resp);
		
	}
	
	

}
