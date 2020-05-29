package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.utils.RestFBUtil;

@WebServlet(urlPatterns = "/login-facebook")
public class LoginFacebookController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = req.getRequestDispatcher("login.jsp");
			dis.forward(req, resp);
		} else {
			String accessToken = RestFBUtil.getToken(code);
			String u = RestFBUtil.getUserInJson(accessToken);
			
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");			
			resp.getWriter().print(u);
			System.out.println(u);
		}

	}

}
