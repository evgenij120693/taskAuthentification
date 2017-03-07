<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String name = (String) request.getSession(false).getAttribute("name");%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap/notice.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>

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
                <li><a href="/taxi/admin/edit_account">Профиль</a></li>
                <li><a href="/taxi/admin/list_client">Список клиентов</a></li>
                <li><a href="/taxi/admin/list_driver">Список водителей</a></li>
                <li><a href="/taxi/admin/list_auto">Список автомобилей</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" class="">Привет, <%=name%></a></li>
                <li> <a href="/taxi/logout">Выйти</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

</body>
</html>