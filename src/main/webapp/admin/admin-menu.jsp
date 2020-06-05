<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.menu li {
	list-style: none;
	display: inline-block;
	margin-right: 30px;
}

a {
	text-decoration: none;
	color: #00cc33;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<ul class="menu">
		<li><a href='<c:url value='/' />'>Home</a></li>		
		<li><a href='<c:url value='/admin' />'>Admin Home</a></li>
		<li><a href='<c:url value='/admin/search-phone' />'>Search Phone</a></li>
		<li><a href='<c:url value='/admin/add-phone' />'>Add Phone</a></li>
		<li><a href='<c:url value='/logout' />'>Logout</a></li>
	</ul>
</body>
</html>