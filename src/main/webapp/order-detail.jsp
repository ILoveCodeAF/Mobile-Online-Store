<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href='<c:url value='/static/css/cart.css' />'>
<script type="text/javascript"
	src='<c:url value='/static/js/fontawesome.js' />'></script>
<script type="text/javascript"
	src="<c:url value='/static/js/jquery.js' />"></script>
<title>Order Detail</title>
</head>
<body>
	<%@ include file="/menu.jsp"%>

	<section class="cart">

		<c:forEach var="phone" items="${order.cart.phones }">

			<div class="phone" id="${phone.phone.id }">
				<div class="parent img">
					<a href="<c:url value='/phone-detail?id=${phone.phone.id }' />"
						class="child"> <img
						src="<c:url value='${phone.phone.image }' />" alt="Phone Image">
					</a>
				</div>
				<div class="info parent">
					<ul class="child child-align-left">
						<li>${phone.phone.name}</li>
						<li>ROM: ${phone.phone.rom } GB</li>
						<li>RAM: ${phone.phone.ram } GB</li>
					</ul>
				</div>
				<div class="price parent">
					<p class="child">${phone.phone.price }USD</p>
					<div class="child">
						<span>${phone.quantity }</span>
					</div>
				</div>
			</div>

		</c:forEach>


		<div class="phone">
			<div class="parent img"></div>
			<div class="info parent">
				<p class="child" style="top: 50px;">Receiving Type</p>
				<p class="child" style="top: 100px;">Payment Type</p>
				<p class="child" style="top: 150px;">Total Price</p>
			</div>
			<div class="parent price">
				<p id="totalPrice" class="child" style="top: 50px;">${order.receivingType }</p>
				<p id="totalPrice" class="child" style="top: 100px;">${order.paymentType }</p>				
				<p id="totalPrice" class="child" style="top: 150px;">${order.price }
					USD</p>
			</div>
		</div>

	</section>
</body>
</html>