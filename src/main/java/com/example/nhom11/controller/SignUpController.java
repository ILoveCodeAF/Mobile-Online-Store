package com.example.nhom11.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.nhom11.model.Account;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Role;
import com.example.nhom11.utils.EncodeUtil;
import com.example.nhom11.utils.SendMailUtil;
import com.example.nhom11.utils.Server;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/sign-up")
public class SignUpController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("sign-up.jsp").forward(req, resp);

//		Account account = new Account(0, username, password, null, null, Role.CUSTOMER);
//		Customer customer = new Customer(0, name, address, email, phone, dob, account);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		String dobString = req.getParameter("dob");
		Date dob = null;
		try {
			dob = format.parse(dobString);
		} catch (ParseException e) {
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// Lay 2 ngay sau
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5); // Gioi han 5 phut cho Token

		Date expiredTime = calendar.getTime();

		Account account = new Account(0, username, password, null, null, Role.CUSTOMER);
		Customer customer = new Customer(0, name, address, email, phone, dob, account);

		//Tao chuoi ma hoa cho thong tin dang ky cua nguoi dung
		String encodedCustomer = EncodeUtil.encode(new Gson().toJson(customer));
		String encodedExpiredTime = EncodeUtil.encode(Long.toString(expiredTime.getTime()));
		String code = encodedCustomer + "." + encodedExpiredTime;

		try {
			SendMailUtil.sendMailWithPlainText("Nhom 11 PTPM Huong DV Verification",
					"http://"+Server.IP+":8080" + req.getContextPath() + "/verify?code=" + code, email.trim());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		req.setAttribute("notification", "We have sent you a mail to "+customer.getEmail()+". Please use it to confirm your account ");
		req.getRequestDispatcher("notification.jsp").forward(req, resp);

	}

}
