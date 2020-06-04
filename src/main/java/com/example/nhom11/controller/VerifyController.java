package com.example.nhom11.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.CustomerDAOTuan;
import com.example.nhom11.dao.impl.CustomerDAOTuanImpl;
import com.example.nhom11.model.Customer;
import com.example.nhom11.utils.EncodeUtil;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/verify")
public class VerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String code = req.getParameter("code");
		String jsonCode = code.substring(0, code.indexOf("."));
		String timeCode = code.substring(code.lastIndexOf(".") + 1);

		String customerJson = EncodeUtil.decode(jsonCode);
		Customer customer = new Gson().fromJson(customerJson, Customer.class);
		long expiredTime = Long.parseLong(EncodeUtil.decode(timeCode));

//		PrintWriter out=resp.getWriter();
//		resp.setCharacterEncoding("UTF-8");
		if (new Date().getTime() > expiredTime) { // Token da het han
			req.setAttribute("notification", "Token has expired");
			req.getRequestDispatcher("notification.jsp").forward(req, resp);
		} else { // Token van con gia tri

						//Token sai dinh dang
			if (customer.getAccount().getUsername() == null || customer.getAccount().getUsername().isEmpty()) {
				req.setAttribute("notification", "Token is wrong");
				req.getRequestDispatcher("notification.jsp").forward(req, resp);
			} else {	//Token dung dinh dang
				CustomerDAOTuan cd = new CustomerDAOTuanImpl();
				customer = cd.add(customer); // Them customer vao DB
				if (customer.getId() <= 0) { // Them khong thanh cong
					req.setAttribute("notification", "Username have existed in System. Please select another username");
					req.getRequestDispatcher("notification").forward(req, resp);
				} else { // Them thanh cong
					HttpSession session = req.getSession();
					session.setAttribute("person", customer);
					resp.sendRedirect(req.getContextPath() + "/");
				}
			}

		}

	}

}
