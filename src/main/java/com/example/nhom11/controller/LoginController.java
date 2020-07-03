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
import com.example.nhom11.model.Person;
import com.example.nhom11.utils.Server;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String google = String.format("https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://%s:8080/SmartPhoneStoreOnline/login-google&response_type=code&client_id=660596394116-n5kd2jlllnt70f0al7r40ncjmvi9kum5.apps.googleusercontent.com&approval_prompt=force", Server.IP);
		String fb = String .format("https://www.facebook.com/dialog/oauth?client_id=2925420967573361&redirect_uri=http://%s:%d/SmartPhoneStoreOnline/login-facebook&scope=email", Server.IP, Server.PORT);
		
		req.setAttribute("google", google);
		req.setAttribute("fb", fb);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		AccountDAOTuan ad=new AccountDAO();
		Person person=ad.login(username, password);
		
		
		if(person==null || person.getAccount()==null || !person.getAccount().isLogin()) {	//Dang nhap that bai
			req.setAttribute("notification", "Login failed!!!");
			req.setAttribute("username", username);
			RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		}
		else {						//Dang nhap thanh cong
			HttpSession session=req.getSession();
			session.setAttribute("person", person);
			
			resp.sendRedirect(req.getContextPath()+"/");
		}
		
		
	}

}
