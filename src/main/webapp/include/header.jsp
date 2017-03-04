
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><% %></title>
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>

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
</html>