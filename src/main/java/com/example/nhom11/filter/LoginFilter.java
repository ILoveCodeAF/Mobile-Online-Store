package com.example.nhom11.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nhom11.model.Account;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;		
		HttpServletResponse resp=(HttpServletResponse) response;
		
		String url=req.getRequestURI();		
		String ignorePath1 = req.getContextPath()+"/login";
		String ignorePath2 = req.getContextPath()+"/sign-up";
		
		if(!url.startsWith(ignorePath1) && !url.startsWith(ignorePath2)) { 	//Khong phai trang login 
											//-> Kiem tra User Login hay chua
			HttpSession sesison=req.getSession();
			Account account=(Account) sesison.getAttribute("account");
			if(account==null || !account.isLogin()) {	//Chua login				
				resp.sendRedirect(req.getContextPath()+"/login");
			}
		}
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}