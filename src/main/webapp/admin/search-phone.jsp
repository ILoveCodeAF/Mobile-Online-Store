<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Phone</title>

<style type="text/css">
table {
	border-collapse: collapse;
	margin: 0 auto;
	width: 1000px;
}

table td {
	text-align: center;
}

a {
	text-decoration: none;
	color: #00cc33;
}

a:hover {
	text-decoration: underline;
}
img{
width: 100px;
}
</style>

</head>
<body>
	<%@ include file="/admin/admin-menu.jsp" %>
	<h1>Search Phone</h1>
	<form action="<c:url value='/admin/search-phone' />" method="POST" >
		<input placeholder="Enter Phone name or Manufacturer" name="key" required="required" value="${key }" >
		<input type="submit" value="Search" >
	</form>
	
	<br><hr><br>

	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Manufacturer</th>
				<th>ROM</th>
				<th>RAM</th>
				<th>CPU</th>
				<th>OS</th>
				<th>Screen</th>
				<th>Image</th>
				<th>Price</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${phones }" var="phone" >
				<tr>
				<td>${phone.name }</td>
				<td>${phone.manufacturer }</td>
				<td>${phone.rom }</td>
				<td>${phone.ram }</td>
				<td>${phone.cpu }</td>
				<td>${phone.os }</td>
				<td>${phone.screen.size } (inch)<br>${phone.screen.resolution }<br>${phone.screen.technology }</td>
				<td><img alt="image" src="<c:url value='${phone.image }' />"></td>
				<td>${phone.price }</td>
				<td><a href="${pageContext.request.contextPath}/admin/edit-phone?id=${phone.id }" >Edit</a></td>
				<td><a href="delete-phone?id=${phone.id }" >Delete</a></td>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>