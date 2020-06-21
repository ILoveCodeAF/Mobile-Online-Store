<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Delete Phone</title>
</head>
<body>
	<%@include file="/admin/admin-menu.jsp"%>
	<h1>Delete Phone</h1>
	<form action="<c:url value='/admin/delete-phone' />" method="POST">
		<input type="hidden" name="id" value="${phone.id }" >
		<input type="hidden" name="screen-id" value="${phone.screen.id }" >
		<input type="hidden" name="confirm" value="none" >
		<table>
			<tr>
				<td>Name:</td>
				<td>${phone.name }</td>
			</tr>
			<tr>
				<td>Manufacturer:</td>
				<td>${phone.manufacturer }</td>
			</tr>
			<tr>
				<td>ROM:</td>
				<td>${phone.rom } (GB)</td>
			</tr>
			<tr>
				<td>RAM:</td>
				<td>${phone.ram} (GB)</td>
			</tr>
			<tr>
				<td>CPU:</td>
				<td>${phone.cpu }</td>
			</tr>
			<tr>
				<td>Front Camera:</td>
				<td>${phone.frontCamera } (MPixel)</td>
			</tr>
			<tr>
				<td>Behind Camera:</td>
				<td>${phone.behindCamera } (MPixel)</td>
			</tr>
			<tr>
				<td>OS:</td>
				<td><${phone.os }</td>
			</tr>
			
			<tr>
				<td>Battery Capacity:</td>
				<td>${phone.battery } (mAh)</td>
			</tr>
            <tr>
                <td colspan="2">
                    <img id="img" name="img" alt="image" src="<c:url value='${phone.image }' />" style="max-height:500px;max-width:500px;">
                </td>
            </tr>
			<tr>
				<td>Screen Size :</td>
				<td>${phone.screen.size } (inch)</td>
			</tr>
			<tr>
				<td>Screen Resolution:</td>
				<td>${phone.screen.resolution }</td>
			</tr>
			
			<tr>
				<td>Screen Technology:</td>
				<td>${phone.screen.technology }</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>${phone.price } (USD)</td>
			</tr>
			<tr>
				<td><input type="submit" value="Delete Phone"></td>
			</tr>
		</table>
	</form>
</body>
</html>