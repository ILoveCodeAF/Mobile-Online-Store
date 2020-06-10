<%@page import="com.example.nhom11.model.Phone"%>
<%@page import="java.util.List"%>
<%@page import="com.example.nhom11.dao.impl.PhoneDAOTuanImpl"%>
<%@page import="com.example.nhom11.dao.PhoneDAOTuan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/static/css/list-phone.css' />">
<title>Home Page</title>
</head>
<body>

	<% 
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		List<Phone>  phones=pd.getNewestPhones(10);
	%>

	<%@ include file="/menu.jsp"%>
	<section class="than">
		<% for(Phone p:phones){ %>
			<a href="<c:url value='/phone-detail?id=' /><%= Long.toString(p.getId()) %>"
				class="phone">
				<div class="img">
					<img src="<c:url value='<%= p.getImage() %>' />" alt="Phone Image">
				</div>
				<div class="detail">
					<p class="name"><%= p.getName() %></p>
					<p class="memory"><%= p.getRom() %>GB/<%= p.getRam() %>GB</p>
					<p class="price"><%= p.getPrice() %> USD</p>
				</div>
			</a>
		<% } %>
	</section>
</body>
</html>