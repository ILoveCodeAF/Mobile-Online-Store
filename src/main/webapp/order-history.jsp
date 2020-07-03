<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<style type="text/css">
table{
	margin: 0 auto;
}

table a{
	text-decoration: none;
	color: #00cc33;
}

table a:hover{
	text-decoration: underline;
}

#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	margin 0 auto;
	width: 1000px;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	background-color: #00cc33;
	color: white;
}

</style>
</head>
<body>

	<%@ include file="/menu.jsp"%>

	<table id="customers">
		<thead>
			<tr>
				<th>Customer</th>
				<th>Order Date</th>
				<th>Total Price</th>
				<th>View Detail</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orders }" var="order">
				<tr>
					<td>${sessionScope.person.name }</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${order.date }" /></td>
					<td>${order.price } USD</td>
					<td><a href=" <c:url value='/order-detail?id=${order.id }' />">View
							Detail</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>