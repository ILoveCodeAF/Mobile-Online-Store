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
						<button onclick="updateCart(${phone.phone.id}, 1)"
							class="link transprent common-color" type="button">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button>
						<span>${phone.quantity }</span>
						<button onclick="updateCart(${phone.phone.id}, 2)"
							class="link transprent common-color" type="button">
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
			<div class="parent price">
				<p id="promotion" class="child" style="top: 50px;">0</p>
				<p id="totalPrice" class="child" style="top: 100px;">${price }
					USD</p>
			</div>
		</div>
		<form class="form" action="order" method="POST" id="orderForm">
			<table>
				<tr>
					<td>Receiving Type:</td>
					<td><select onchange="receivingTypeChange()"
						id="receivingType" name="receivingType">
							<option value="">SELECT</option>
							<c:forEach var="receivingType" items="${receivingTypes }">
								<option value="${receivingType }">${receivingType }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Payment Type:</td>
					<td><select id="paymentType" name="paymentType">
							<option value="">SELECT</option>
							<c:forEach var="paymentType" items="${paymentTypes }">
								<option value="${paymentType }">${paymentType }</option>
							</c:forEach>
					</select></td>
				</tr>

			</table>
			<table id="shipment">
			</table>
			<input class="order link" type="button" value="Order"
				onclick="submitForm()" />
		</form>

	</section>

	<script>
	
		function receivingTypeChange(){
			var value = $("#receivingType").val();
			if(value=="SHIPPING"){
				var content = "";
				content += "<tr>"
					+"<td>Address: </td>"
					+"<td><input id='address' name='address' ></td>"
					+"</tr>"
					+"<tr>"
					+"<td>Phone: </td>"
					+"<td><input id='phone' name='phone' ></td>"
					+"</tr>"
					;
				$("#shipment").html(content);
			}
			else{
				$("#shipment").html("");
			}
			
		}
	
		function submitForm(){
			
			if($("#receivingType").val()=="" || $("#paymentType").val()==""){
				alert("Please fill out all information");
			}
			else{
				if($("#receivingType").val()=="SHIPPING"){
					var phone = $("#phone").val().trim();
					var address = $("#address").val().trim();
					var regex = new RegExp("0[0-9]{9,10}");
					
					if(address.length<10 || !regex.test(phone) || phone.length>11){
						alert("Please fill out all information");
					}
					else{
						$("#orderForm").submit();
					}
					
				}
				else{
					$("#orderForm").submit();
				}
				
			}
			
			
		}
	
		function deletePhone(id){
			var select = '#'+id;
			$.ajax({
				   url: 'cart-delete?phoneId='+id,
					datatype: 'json', 
				   success: function(data) {
						$('#totalPrice').text(data.price+" USD");
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