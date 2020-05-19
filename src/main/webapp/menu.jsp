<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <style type="text/css">
            .menu li{
                list-style: none;
                display: inline-block;
                margin-right: 30px;
            }
            a{
                text-decoration: none;
                color: #00cc33;
            }
            a:hover {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <ul class="menu" >
            <li><a href='<c:url value='/' />' >Home</a></li>
            <li><a href='<c:url value='/login' />' >Login</a></li>
            <li><a href='<c:url value='/sign-up' />' >Sign up</a></li>
            <li><a href='<c:url value='/logout' />' >Logout</a></li>		
        </ul>
    </body>
</html>