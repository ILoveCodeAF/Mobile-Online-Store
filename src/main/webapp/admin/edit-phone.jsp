<%--
  Created by IntelliJ IDEA.
  User: VA Tuan
  Date: 24-Jun-20
  Time: 11:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value='/static/js/jquery.js' />"></script>
    <title>Edit Phone</title>
</head>
<body>
<%@include file="/admin/admin-menu.jsp" %>
<h1>Edit Phone</h1>
<h4 style="color: red">${notification}</h4>
<form action="<c:url value='/admin/edit-phone' />" method="POST"
      enctype="multipart/form-data">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="hidden" name="id" value="${phone.id }"><input name="name" value="${phone.name }"
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
                       type="number" min="1"> (GB)
            </td>
        </tr>
        <tr>
            <td>RAM:</td>
            <td><input name="ram" required="required" value="${phone.ram }"
                       type="number" min="1"> (GB)
            </td>
        </tr>
        <tr>
            <td>CPU:</td>
            <td><input name="cpu" required="required" value="${phone.cpu }">
                (eg: SnapDragon 625, ...)
            </td>
        </tr>
        <tr>
            <td>Front Camera:</td>
            <td><input name="frontCamera" required="required"
                       value="${phone.frontCamera }" type="number" step="any" min="1">
                (MPixel)
            </td>
        </tr>
        <tr>
            <td>Behind Camera:</td>
            <td><input name="behindCamera" required="required"
                       value="${phone.behindCamera }" type="number" step="any" min="1">
                (MPixel)
            </td>
        </tr>
        <tr>
            <td>OS:</td>
            <td><input name="os" required="required" value="${phone.os }">
                (eg: Android, IOS, ...)
            </td>
        </tr>
        <tr>
            <td>Battery Capacity:</td>
            <td><input name="battery" required="required"
                       value="${phone.battery }" type="number" min="1"> (mAh)
            </td>
        </tr>
        <tr>
            <td>Image:</td>
            <td><input id="image" name="image" type="file">
                (JPG, PNG)
            </td>
        </tr>
        <script type="text/javascript">
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#img').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#image").change(function () {
                readURL(this);
            });
        </script>
        <tr>
            <td colspan="2">
                <img id="img" name="img" alt="image" src="<c:url value='${phone.image }' />"
                     style="max-height:500px;max-width:500px;">
            </td>
        </tr>
        <tr>
            <td>Screen Size :</td>
            <td><input type="hidden" name="screenId" value="${phone.screen.id }">
                <input name="screen.size" required="required"
                       value="${phone.screen.size }" type="number" step="any" min="1">
                (inch)
            </td>
        </tr>
        <tr>
            <td>Screen Resolution:</td>
            <td><input name="screen.resolution" required="required"
                       value="${phone.screen.resolution }"> (eg: SD, HD, FHD,
                HD+, 2K, ...)
            </td>
        </tr>
        <tr>
            <td>Screen Technology:</td>
            <td><input name="screen.technology"
                       value="${phone.screen.technology }"> (eg: IPS, ...)
            </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input name="price" required="required"
                       value="${phone.price }" type="number" step="any" min="1">
                (USD)
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Edit"></td>
        </tr>
    </table>
</form>
</body>
</html>
