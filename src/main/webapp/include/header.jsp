<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><% System.out.println(request.getRequestURI());%></title>
    <meta name="viewport" content="initial-scale=1.0, width=device-width">
    <link rel="stylesheet" href="css/foundation.css">
    <link rel="stylesheet" href="css/app.css">

    <script type="text/javascript" src="js/vendor/jquery.js"></script>
    <script type="text/javascript" src="js/vendor/foundation.js"></script>
</head>
<body>
<header id="page-header">
    <div class="top-bar" id="realEstateMenu">
        <div class="top-bar-left">
            <ul class="menu accordion-menu" data-responsive-menu="accordion" role="tablist"
                aria-multiselectable="true" data-accordionmenu="5oqnp2-accordionmenu"
                data-responsivemenu="1jclc5-responsivemenu">
                <li class="menu-text" role="menuitem">
                    <a href="/taxi/" class="header"
                       style=" color: #fefefe;
                       text-shadow: 0 0 3px black;
                       line-height: 0; font-size: 20px;">
                        Taxi Online
                    </a></li>
            </ul>
        </div>
        <div class="top-bar-right">
            <ul class="menu">
                <li><a href="/taxi/registration">Регистрация</a></li>
                <li><a class="button" href="/taxi/login">Войти</a></li>
            </ul>
        </div>
    </div>
</header>
<br>
</body>
</html>