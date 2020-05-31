package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.CustomerDAOTuan;
import com.example.nhom11.dao.impl.CustomerDAOTuanImpl;
import com.example.nhom11.model.Account;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Role;
import com.example.nhom11.utils.RestFBUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

			JsonObject jo = new Gson().fromJson(u, JsonObject.class);
			Account a = new Account(0, null, null, null, jo.get("id").getAsString(), Role.CUSTOMER);

			Customer c = new Customer(0, jo.get("name").getAsString(), null, jo.get("email").getAsString(), null, null,
					a);

			// Them Customer vao DB
			CustomerDAOTuan cd = new CustomerDAOTuanImpl();
			long customerId = cd.checkIfFbAccountExist(c.getAccount().getFbId());
			if (customerId == 0) { 	// Customer chua co tai khoan trong he thong
				c = cd.add(c);		// Them Customer vao He thong
				if (c.getId() > 0) { // Them thanh cong
					// Them Customer vao Session
					HttpSession session = req.getSession();
					session.setAttribute("person", c);
					resp.sendRedirect(req.getContextPath() + "/");
				} else { // Them that bai
					req.setAttribute("notification", "Tài khoản đã tồn tại");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					;
				}

			} else { // Customer da co tai khoan trong he thong
				c.setId(customerId);
				HttpSession session = req.getSession();
				session.setAttribute("person", c);
				resp.sendRedirect(req.getContextPath() + "/");
			}

//			resp.setContentType("text/plain");
//			resp.setCharacterEncoding("UTF-8");			
//			resp.getWriter().print(c.toString());
//			System.out.println(c.toString());
		}

	}

}
