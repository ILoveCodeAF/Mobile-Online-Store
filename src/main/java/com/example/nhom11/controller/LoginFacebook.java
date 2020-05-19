package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.model.User;
import com.example.nhom11.utils.RestFBUtil;

@WebServlet(urlPatterns = "/login-facebook")
public class LoginFacebook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = req.getRequestDispatcher("login.jsp");
			dis.forward(req, resp);
		} else {
			String accessToken = RestFBUtil.getToken(code);
			User u = RestFBUtil.getUser(accessToken);
			
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");			
			resp.getWriter().print(u.toString());
			System.out.println(u.toString());
		}

	}

}
