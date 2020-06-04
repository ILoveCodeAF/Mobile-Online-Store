<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<a href="<c:url value='/login' />" >Login</a>
	<h4 style="color: red">${notification }</h4>
	<form action="<c:url value='/login'/> " method="POST">
		<table>
			<tr>
				<td>Username:</td>
				<td><input name="username" value="${username }"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
			<tr>
				<td><a href="<c:url value='/sign-up' />">Sign up</a></td>
				<td><a href="${google }"> Login with Google</a></td>
				<td><a href="${fb }"> Login with Facebook</a></td>
			</tr>
		</table>
	</form>
</body>
</html>