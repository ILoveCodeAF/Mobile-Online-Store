package com.example.nhom11.filter;

import java.io.IOException;
import java.net.UnknownServiceException;

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

@WebFilter(urlPatterns = {"/cart.jsp", "/cart", "/cart-add"})
public class CartFilter implements Filter {

    public CartFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	//Chi cho Customer try cap vao trang Cart
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpSession session=req.getSession();
		Person p=(Person) session.getAttribute("person");
		if(p==null || p.getAccount().getRole()!=Role.CUSTOMER) {	//Khong phai Customer
			throw new UnknownServiceException("Not a Customer");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
