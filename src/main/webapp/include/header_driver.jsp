<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = (String) request.getSession(false).getAttribute("name");%>
<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/tabicon.png" type="image/png">
    <link href="//localhost:8080/taxi/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="//localhost:8080/taxi/css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
    <link href="//localhost:8080/taxi/css/bootstrap/notice.css" rel="stylesheet">
    <link href="//localhost:8080/taxi/css/app.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="//localhost:8080/taxi/js/driver.js"></script>
    <script src="//localhost:8080/taxi/js/bootstrap/bootstrap.min.js"></script>

</head>
<body>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/taxi">Taxi online</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/taxi/driver">Профиль</a></li>
                <li><a href="/taxi/driver/list_done_order">Выполненные заявки</a></li>
                <li><a href="/taxi/driver/list_new_order">Текущие заявки</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>
                    <%--<img src="http://iconizer.net/files/Human_o2/orig/emblem-people.png" width="25px;">--%>
                    <%=name%></a></li>
                <c:url var="logoutUrl" value="/j_spring_security_logout"/>
                <li> <a href="${logoutUrl}">Выйти</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

</body>
</html>