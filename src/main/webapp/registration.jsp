<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="include/header.jsp" %>
<body>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="row medium-4 large-3 alert callout">
    <%=error%>
</div>
<%}%>
<fieldset class="fieldset row medium-4 large-3 columns callout left">
    <legend>Регистрация</legend>

    <form action="/taxi/registration" name="form" method="post">
        Логин
        <input type="text" name="login" value="">
        Пароль
        <input type="password" name="password" value="">
        ФИО
        <input type="text" name="name" value="">
        Пол
        <select name="sex">
            <option value="m" selected>Мужской</option>
            <option value="w">Женский</option>
        </select>
        Номер телефона
        <input type="text" name="phone" value="">
        Email
        <input type="text" name="email" value="">
        <input type="submit" class="button" style="float: right; margin: 0;" value="Зарегистрироваться">
    </form>

</fieldset>
</body>
</html>
