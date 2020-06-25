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
<title>Your Cart</title>
</head>
<body>

	<%@ include file="/menu.jsp"%>

	<section class="cart">

		<c:forEach var="phone" items="${sessionScope.cart.phones }">

			<div class="phone" id="${phone.phone.id }">
				<div class="parent img">
					<a href="<c:url value='/phone-detail?id=${phone.phone.id }' />"
						class="child"> <img
						src="<c:url value='${phone.phone.image }' />" alt="Phone Image">
					</a>
					<button value="${phone.phone.id }"
						onclick="deletePhone(${phone.phone.id})" type="button"
						class="link transprent child">
						<i class="fa fa-times" aria-hidden="true"></i> Delete
					</button>
				</div>
				<div class="info parent">
					<ul class="child child-align-left">
						<li>${phone.phone.name}</li>
						<li>ROM: ${phone.phone.rom } GB</li>
						<li>RAM: ${phone.phone.ram } GB</li>
					</ul>
				</div>
				<div class="price parent">
					<p class="child">${phone.phone.price } USD</p>
					<div class="child">
						<button onclick="updateCart(${phone.phone.id}, 1)" class="link transprent common-color" type="button">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button>
						<span>${phone.quantity }</span>
						<button onclick="updateCart(${phone.phone.id}, 2)" class="link transprent common-color" type="button">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</div>

		</c:forEach>


		<div class="phone">
			<div class="parent img"></div>
			<div class="info parent">
				<p class="child" style="top: 50px;">Promotion</p>
				<p class="child" style="top: 100px;">Total Price</p>
			</div>
			<div class="price parent">
				<p id="promotion" class="child" style="top: 50px;">0</p>
				<p id="totalPrice" class="child" style="top: 100px;">${price }
					USD</p>
			</div>
		</div>
		<button class="order link" type="button">Order</button>
	</section>

	<script>
	
		function deletePhone(id){
			var select = '#'+id;
			$.ajax({
				   url: 'cart-delete?phoneId='+id,
				   success: function(data) {
						$('#totalPrice').text(data+" USD");
						$(select).remove();
				   },
				   type: 'GET'
			});				
		}
		
		function updateCart(id, tag){
			var select = '#'+id+' span:last';			
			
			
			$.ajax({
				   url: 'cart-update?phoneId='+id+'&tag='+tag,
				   dataType: 'json', 
				   success: function(data) {
						$(select).text(data.quantity);
						$('#totalPrice').text(data.price + ' USD');
				   },
				   type: 'GET'
			});
		}
		
	
	</script>

</body>
</html>