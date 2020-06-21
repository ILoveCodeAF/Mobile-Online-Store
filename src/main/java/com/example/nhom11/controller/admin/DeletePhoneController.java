package com.example.nhom11.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Screen;

@WebServlet(value = {"/admin/delete-phone", "/admin/confirm-delete-phone"})
public class DeletePhoneController extends HttpServlet {

	private static final String UPLOAD_DIR = "/static/image/phone/";
	private static final long MAX_UPLOAD_SIZE = 1024 * 1024 * 2; // 2 MB

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------doget-------------------------"+req.getContextPath());
		String str_id = req.getParameter("id");
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		
		Phone phone = pd.getById(Integer.parseInt(str_id));
		req.setAttribute("phone", phone);
		req.getRequestDispatcher("/admin/delete-phone.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("===================do post");
		String str_id = req.getParameter("id");
		String confirm = req.getParameter("confirm");
		
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		Phone phone = pd.getById(Integer.parseInt(str_id));
		
		if(confirm.equals("none")) {
			req.setAttribute("phone", phone);
			req.getRequestDispatcher("/admin/confirm-delete-phone.jsp").forward(req, resp);
		} else if(confirm.equals("yes")) {
			boolean success = pd.delete(phone);
			
			if(!success) {	//Delete that bai
				req.setAttribute("phone", phone);
				PrintWriter out = resp.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Delete phone fail');");
				out.println("location='delete-phone.jsp'");
				out.println("</script>");
			}
			else {									//delete thanh cong ->
				System.out.println(req.getContextPath());
				
				PrintWriter out = resp.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Deleted phone successfully');");
				out.println(String.format("location='%s/admin/search-phone'", req.getContextPath()));
				out.println("</script>");
				
			}
		}
		PrintWriter out = resp.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Something went wrong!');");
		out.println(String.format("location='%s/admin/search-phone'", req.getContextPath()));
		out.println("</script>");
	}

}
