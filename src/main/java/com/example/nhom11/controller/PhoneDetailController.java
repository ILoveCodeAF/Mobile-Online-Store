package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;

@WebServlet(urlPatterns = "/phone-detail")
public class PhoneDetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		Phone phone=pd.getById(Long.parseLong(req.getParameter("id")));
		
		req.setAttribute("phone", phone);
		req.getRequestDispatcher("phone-detail.jsp").forward(req, resp);
		
	}
	
	

}
