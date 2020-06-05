<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Phone</title>
</head>
<body>
	<%@include file="/admin/admin-menu.jsp"%>
	<h1>Add new Phone</h1>
	<form action="<c:url value='/admin/add-phone' />" method="POST"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Name:</td>
				<td><input name="name" value="${phone.name }"
					required="required"></td>
			</tr>
			<tr>
				<td>Manufacturer:</td>
				<td><input name="manufacturer" required="required"
					value="${phone.manufacturer }"> (eg: Xiaomi, Samsung, ...)
				</td>
			</tr>
			<tr>
				<td>ROM:</td>
				<td><input name="rom" required="required" value="${phone.rom }"
					type="number" min="1"> (GB)</td>
			</tr>
			<tr>
				<td>RAM:</td>
				<td><input name="ram" required="required" value="${phone.ram }"
					type="number" min="1"> (GB)</td>
			</tr>
			<tr>
				<td>CPU:</td>
				<td><input name="cpu" required="required" value="${phone.cpu }">
					(eg: SnapDragon 625, ...)</td>
			</tr>
			<tr>
				<td>Front Camera:</td>
				<td><input name="frontCamera" required="required"
					value="${phone.frontCamera }" type="number" step="any" min="1">
					(MPixel)</td>
			</tr>
			<tr>
				<td>Behind Camera:</td>
				<td><input name="behindCamera" required="required"
					value="${phone.behindCamera }" type="number" step="any" min="1">
					(MPixel)</td>
			</tr>
			<tr>
				<td>OS:</td>
				<td><input name="os" required="required" value="${phone.os }">
					(eg: Android, IOS, ...)</td>
			</tr>
			<tr>
				<td>Battery Capacity:</td>
				<td><input name="battery" required="required"
					value="${phone.battery }" type="number" min="1"> (mAh)</td>
			</tr>
			<tr>
				<td>Image:</td>
				<td><input name="image" required="required" type="file">
					(JPG, PNG)</td>
			</tr>
			<tr>
				<td>Screen Size :</td>
				<td><input name="screen.size" required="required"
					value="${phone.screen.size }" type="number" step="any" min="1">
					(inch)</td>
			</tr>
			<tr>
				<td>Screen Resolution:</td>
				<td><input name="screen.resolution" required="required"
					value="${phone.screen.resolution }"> (eg: SD, HD, FHD,
					HD+, 2K, ...)</td>
			</tr>
			<tr>
				<td>Screen Technology:</td>
				<td><input name="screen.technology"
					value="${phone.screen.technology }"> (eg: IPS, ...)</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input name="price" required="required"
					value="${phone.price }" type="number" step="any" min="1">
					(USD)</td>
			</tr>
			<tr>
				<td><input type="submit" value="Add new Phone"></td>
			</tr>
		</table>
	</form>
</body>
</html>