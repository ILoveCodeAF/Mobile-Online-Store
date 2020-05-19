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
	<%@ include file="/menu.jsp"%>
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
				<td><a
					href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8080/SmartPhoneStoreOnline/login-google&response_type=code&client_id=660596394116-n5kd2jlllnt70f0al7r40ncjmvi9kum5.apps.googleusercontent.com&approval_prompt=force">
						Login with Google</a></td>
				<td><a
					href="https://www.facebook.com/dialog/oauth?client_id=2925420967573361&redirect_uri=http://localhost:8080/SmartPhoneStoreOnline/login-facebook&scope=email">
						Login with Facebook</a></td>
			</tr>			
		</table>
	</form>
</body>
</html>