package com.example.nhom11.filter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.nhom11.model.Person;
import com.example.nhom11.model.Role;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		HttpSession session =req.getSession();
		Person person = (Person) session.getAttribute("person");
		
		if(person==null || person.getAccount().getRole()!=Role.ADMIN) {	//Neu nguoi dung khong co quyen admin
			throw new AccessDeniedException("Unauthorized");
		}
		else {
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
