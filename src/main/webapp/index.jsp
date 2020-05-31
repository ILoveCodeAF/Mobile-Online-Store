<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<%@ include file="/menu.jsp" %>
	<h1>Hello ${sessionScope.person.name }</h1>
</body>
</html>