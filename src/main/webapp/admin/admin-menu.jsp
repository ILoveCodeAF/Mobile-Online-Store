<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<ul class="menu">
		<li><a href="<c:url value='/' />">Home</a></li>
		<li><a href="<c:url value='/admin' />">Admin Home</a></li>
		<li><a href="<c:url value='/admin/search-phone' />">Search Phone</a></li>
		<li><a href="<c:url value='/admin/add-phone' />">Add Phone</a></li>
		<li><a href="<c:url value='/logout' />">Logout</a></li>
	</ul>