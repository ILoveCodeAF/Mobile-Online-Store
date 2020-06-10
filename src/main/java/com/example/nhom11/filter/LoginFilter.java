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

import com.example.nhom11.model.Person;

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
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();		
		String ignorePath1 = req.getContextPath()+"/login";
		String ignorePath2 = req.getContextPath()+"/sign-up";
		String ignorePath3 = req.getContextPath()+"/logout";
		String ignorePath4 = req.getContextPath()+"/notification";
		String ignorePath5 = req.getContextPath()+"/verify";
		
		if(!url.startsWith(ignorePath1) 
				&& !url.startsWith(ignorePath2)
				&& !url.startsWith(ignorePath3) 
				&& !url.startsWith(ignorePath4)
				&& !url.startsWith(ignorePath5)
				) { 	//Khong phai trang login 
											//-> Kiem tra User Login hay chua
			HttpSession session=req.getSession();
			Person p=(Person) session.getAttribute("person");
			if(p==null) {	//Chua login				
				resp.sendRedirect(req.getContextPath()+"/login");
			}
			else {
			}
		}
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
