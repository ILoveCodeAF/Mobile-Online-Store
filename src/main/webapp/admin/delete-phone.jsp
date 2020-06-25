<%--
  Created by IntelliJ IDEA.
  User: VA Tuan
  Date: 25-Jun-20
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Phone</title>
    <style>
        .info{
            width: 1000px;
            height: 800px;
            position: relative;
        }
        .trai{
            width: 400px;
            text-align: center;
            position: absolute;
            left: 0px;
            top: 50%;
            transform: translate(0, -50%);

        }
        .trai img{
            max-width: 400px;
            max-height: 700px;
        }
        .phai{
            width: 580px;
            position: absolute;
            right: 0px;
            top: 50%;
            transform: translate(0, -50%);
        }
    </style>
</head>
<body>
    <%@ include file="/admin/admin-menu.jsp"%>
    <h1>Delete Phone</h1>
    <h4 style="color: red">${notification}</h4>
    <div class="info">
        <div class="trai" >
            <img alt="Image" src="<c:url value="${phone.image}" />" >
        </div>
        <div class="phai" >
            <table style="width: 1000px; width: inherit" >
                <tr>
                    <td>Name</td>
                    <td>${phone.name}</td>
                </tr>
                <tr>
                    <td>Manufacturer</td>
                    <td>${phone.manufacturer}</td>
                </tr>
                <tr>
                    <td>ROM</td>
                    <td>${phone.rom}</td>
                </tr>
                <tr>
                    <td>RAM</td>
                    <td>${phone.ram}</td>
                </tr>
                <tr>
                    <td>CPU</td>
                    <td>${phone.cpu}</td>
                </tr>
                <tr>
                    <td>Front Camera</td>
                    <td>${phone.frontCamera}</td>
                </tr>
                <tr>
                    <td>Behind Camera</td>
                    <td>${phone.behindCamera}</td>
                </tr>
                <tr>
                    <td>OS</td>
                    <td>${phone.os}</td>
                </tr>
                <tr>
                    <td>Battery Capacity</td>
                    <td>${phone.battery}</td>
                </tr>
                <tr>
                    <td>Screen Size</td>
                    <td>${phone.screen.size} inch</td>
                </tr>
                <tr>
                    <td>Screen Resolution</td>
                    <td>${phone.screen.resolution}</td>
                </tr>
                <tr>
                    <td>Screen Technology</td>
                    <td>${phone.screen.technology}</td>
                </tr>
                <tr>
                    <td><form action="<c:url value='/admin/delete-phone' />" method="POST" >
                        <input type="hidden" name="id" value="${phone.id}" >
                        <input type="hidden" name="screenId" value="${phone.screen.id}" >
                        <input type="submit" value="Delete" >
                    </form></td>
                </tr>
            </table>
        </div>
    </div>

</body>
</html>
