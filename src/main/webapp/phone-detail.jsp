<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/static/js/jquery' />"></script>
<link rel="stylesheet"
	href="<c:url value='/static/css/phone-detail.css' />">
<title>Phone Detail</title>
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<section class="than">
		<div class="img">
			<img src="<c:url value='${phone.image }' />" alt="">
		</div>
		<div class="thong-so-kt">
			<table>
				<tr style="line-height: 100px; font-size: x-large;">
					<td colspan="2">${phone.name }</td>
				</tr>
				<tr>
					<td>Manufacturer:</td>
					<td>${phone.manufacturer }</td>
				</tr>
				<tr>
					<td>Screen:</td>
					<td>${phone.screen.technology},${phone.screen.size}'',
						${phone.screen.resolution}</td>
				</tr>
				<tr>
					<td>OS:</td>
					<td>${phone.os }</td>
				</tr>
				<tr>
					<td>Front Camera:</td>
					<td>${phone.frontCamera }MP</td>
				</tr>
				<tr>
					<td>Behind Camera:</td>
					<td>${phone.behindCamera }MP</td>
				</tr>
				<tr>
					<td>CPU:</td>
					<td>${phone.cpu}</td>
				</tr>
				<tr>
					<td>ROM:</td>
					<td>${phone.rom}GB</td>
				</tr>
				<tr>
					<td>RAM:</td>
					<td>${phone.ram}GB</td>
				</tr>
				<tr>
					<td>Battery Capacity:</td>
					<td>${phone.battery}mAh</td>
				</tr>
				<tr>
					<td>Price:</td>
					<td style="color: rgb(228, 64, 64);">${phone.price} USD</td>
				</tr>
				<tr style="border-bottom: none;">
					<td colspan="2"><a
						href="<c:url value='/cart-add?phoneId=${phone.id }' />"
						class="button">Add to Cart</a></td>
				</tr>
			</table>
		</div>
	</section>

	<input id="content" name="content"></input>
	<button id="btnComment" onclick="doComment()">Comment</button>
	<section id="comment">
		<c:forEach items="${comments }" var="c" >
		
		<div>
			<span>${c.customer.name }</span>
			<span><fmt:formatDate pattern="dd/MM/yyyy" value="${c.date }"/></span>
			<p>${c.content}</p>
		</div>
		
		</c:forEach>
	</section>

	<script type="text/javascript">
		function doComment(){
				var url = $(location).attr('href');
				var id = url.substring(url.lastIndexOf('=')+1);
				
				$.ajax({
					url: 'comment', 
					type: 'POST', 
					dataType: 'json', 
					data: {
						'content': $('#content').val(),
						'phoneId': id
					},
					success: function(data){
						$('#comment').prepend('<div>'
								+'<span>'+data.customer.name+'</span>'
								+'<span>'+data.date+'</span>'
								+'<p>'+data.content+'</p>'
								+'<div>');
					}
				});			
			
		}
	</script>

</body>
</html>