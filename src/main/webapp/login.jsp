<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<%@include file="include/header.jsp" %>
<body>
<link href="css/bootstrap/sign_in.css" rel="stylesheet">

<div class="container">

    <form class="form-signin" action="/taxi/login" method="post">
        <h2 class="form-signin-heading">Авторизуйтесь</h2>

        <label for="inputEmail" class="sr-only">Логин</label>
        <input type="text" name="login" id="inputEmail" class="form-control" placeholder="Логин" required="" autofocus="">
        <label for="inputPassword"   class="sr-only">Пароль</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div class="alert alert-danger " style="margin-top: 20px;">
            <%=error%>
        </div>
        <%}%>
    </form>


</div>

</body>
</html>
