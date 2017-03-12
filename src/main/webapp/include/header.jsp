
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link rel="shortcut icon" href="image/tabicon.png" type="image/png">

    <spring:url value="/css/bootstrap/bootstrap.css" var="bootstrap"/>
    <spring:url value="/css/bootstrap/bootstrap-responsive.css" var="bootstrap_responsive"/>
    <spring:url value="/css/app.css" var="app"/>
    <title><% %></title>
    <link type="text/css" href="${bootstrap}" rel="stylesheet"/>
    <link type="text/css" href="${bootstrap_responsive}" rel="stylesheet"/>
    <link type="text/css" href="${app}" rel="stylesheet"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>

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
                <li class="active"><a href="/taxi/">Главная</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/taxi/login" class="">Войти</a></li>
                <li> <a href="/taxi/registration" class="">Зарегистрироваться</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

</body>
