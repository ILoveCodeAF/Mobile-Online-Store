<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/static/css/list-phone.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/css/pagination.css' />">
<title>Search Phone</title>

</head>
<body>
	<%@ include file="/menu.jsp"%>
	<section class="than">
		<c:choose>
			<c:when test="${phones.size()==0 }">
				<h4 style="color: red">No Result</h4>
			</c:when>
			<c:otherwise>
				<c:forEach var="phone" items="${phones }">
					<a href="<c:url value='/phone-detail?id=${phone.id }' />"
						class="phone">
						<div class="img">
							<img src="<c:url value='${phone.image }' />" alt="Phone Image">
						</div>
						<div class="detail">
							<p class="name">${phone.name }</p>
							<p class="memory">${phone.rom }GB/${phone.ram }GB</p>
							<p class="price">${phone.price }USD</p>
						</div>
					</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<div class="pagination">
			<div class="content">
				<a href="#">&laquo;</a>
				<c:forEach var="i" begin="1" end="${totalPage }">
					<c:choose>
						<c:when test="${page==i }">
							<a class="active"
								href="<c:url value='/search-phone?key= ${key }&page=${i }' />">${i }</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value='/search-phone?key= ${key }&page=${i }' />">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<a href="#">&raquo;</a>
			</div>

			<%-- 				<c:otherwise>
					<a href="#">&laquo;</a>
					<c:forEach var="i" begin="1" end="6">
						<a href="<c:url value='/search-phone?key= ${key }&page=${i }' />">${i }</a>
					</c:forEach>
					<a href="#">&raquo;</a>
				</c:otherwise> --%>
		</div>

	</section>
</body>
</html>