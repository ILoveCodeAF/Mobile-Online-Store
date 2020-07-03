<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/static/css/menu.css' />">
<script src="<c:url value='/static/js/fontawesome.js' />"></script>
<script src="<c:url value='/static/js/jquery.js' />"></script>
<title>Insert title here</title>
</head>
<body>

	<section class="dau">
		<h4>Hello ${sessionScope.person.name }</h4>
		<div class="menu">
			<a class="item" href='<c:url value='/' />'>Home</a>
			<c:choose>
				<c:when test="${sessionScope.person.account.role=='CUSTOMER' }">
					<a class="item" href='<c:url value='/cart' />'>Cart</a>
					<a class="item" href="<c:url value='/order-history' />" >Order History</a>
				</c:when>
				<c:when test="${sessionScope.person.account.role=='ADMIN' }">
					<a class="item" href='<c:url value='/admin' />'>Admin</a>
				</c:when>
			</c:choose>

			<i id="logout" class="item logout fa fa-caret-down"
				aria-hidden="true">
				<div id="myDropdown" class="dropdown-content">
					<a href='<c:url value='/logout' />'>Logout</a>
				</div>
			</i>
		</div>

		<form class="search" action="<c:url value='/search-phone' />"
			method="GET">
			<input class="text" name="key" value="${key }"
				placeholder="Search Phone ..."> <input name="page" value="1"
				type="hidden"> <input class="submit" type="submit"
				value="Search">

		</form>
	</section>
	<script>
		$(document).ready(function() {
			$('#myDropdown').hide();
			$('#logout').click(function() {
				$('#myDropdown').toggle();
			});

		});
	</script>
</body>
</html>