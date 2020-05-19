package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.AccountDAOTuan;
import com.example.nhom11.dao.impl.AccountDAO;
import com.example.nhom11.model.Account;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		AccountDAOTuan ad=new AccountDAO();
		Account account=ad.login(username, password);
		
		
		if(account==null || !account.isLogin()) {	//Dang nhap that bai
			req.setAttribute("notification", "Login failed!!!");
			req.setAttribute("username", username);
			RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		}
		else {						//Dang nhap thanh cong
			HttpSession session=req.getSession();
			session.setAttribute("account", account);
			resp.sendRedirect(req.getContextPath());
		}
		
		
	}

}
