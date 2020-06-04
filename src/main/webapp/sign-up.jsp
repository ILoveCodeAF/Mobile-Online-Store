<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>

	<h1>Sign Up</h1>
	<form action="<c:url value="/sign-up"/>" method="POST">
		<table>
			<tr>
				<td>Name:</td>
				<td><input name="name" required="required"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input name="address"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" type="email" required="required"></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input name="phone"></td>
			</tr>
			<tr>
				<td>Date of Birth:</td>
				<td><input name="dob" type="date"></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><input name="username" required="required"
					pattern="[a-zA-Z0-9]{4,20}"> <span>(From 4 to 20
						characters, just include number and letter)</span></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" required="required"
					pattern="[a-zA-Z0-9]{4,20}"> <span>(From 4 to 20
						characters, just include number and letter)</span></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sign Up"></td>
			</tr>

		</table>
	</form>

</body>
</html>