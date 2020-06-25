package com.example.nhom11.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.dao.CommentDAOTuan;
import com.example.nhom11.dao.impl.CommentDAOTuanImpl;
import com.example.nhom11.model.Comment;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Person;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Role;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/comment")
public class CommentController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Person person = (Person) session.getAttribute("person");
		
		//Khong phai Customer -> Khong cho comment
		if(person==null || person.getAccount().getRole()!=Role.CUSTOMER) {			
		}
		else {
			String content = req.getParameter("content");
			long phoneId = Long.parseLong(req.getParameter("phoneId"));
			Phone phone = new Phone();
			phone.setId(phoneId);
			Customer customer = (Customer) person;
			
			Comment comment = new Comment(0, content, null, customer, phone);
			
			CommentDAOTuan cd = new CommentDAOTuanImpl();
			comment = cd.add(comment);
			
			if(comment.getId()!=0) {	//Neu them thanh cong Comment vao DB
				resp.setContentType("application/json");
				String result = new Gson().toJson(comment, Comment.class);
				resp.getWriter().print(result);			
				System.out.println(result);
			}	
			
			
		}
	}
	
	

}
