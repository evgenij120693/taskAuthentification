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
    <c:url var="loginUrl" value="/j_spring_security_check"/>
    <form class="form-signin" action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Авторизуйтесь</h2>

        <label for="inputEmail" class="sr-only">Логин</label>
        <input type="text" name="j_username" id="inputEmail" class="form-control" placeholder="Логин" required="" autofocus="">
        <label for="inputPassword"   class="sr-only">Пароль</label>
        <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Пароль" required="">
        <input id="remember_me"
                   name="_spring_security_remember_me"
                   type="checkbox"/> <!-- Флажок "запомнить меня" -->
            <label for="remember_me"
                   class="inline">Remember me</label>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <%
            String error = (String) request.getParameter("error");
            System.out.println(error);
            if (error != null) {
        %>
        <div class="alert alert-danger " style="margin-top: 20px;">
            Неверный логин/пароль
        </div>
        <%}%>
    </form>


</div>

</body>
</html>
