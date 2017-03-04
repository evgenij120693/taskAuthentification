<%@ page import="ru.svetozarov.models.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 25.02.2017
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование клиента</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-6">
<h1>Редактирование клиента</h1>
<% Client client = (Client) request.getAttribute("client");%>

<form action="/taxi/admin/edit_client" name="form" method="post">
    <input type="hidden" name="id" value="<%=client.getId()%>">
    <table class="table table-hover">
        <tr>
            <td>Логин</td>
            <td><input type="text" required class="form-control" name="login" value="<%=client.getLogin()%>"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" required class="form-control" name="password" value="<%=client.getPassword()%>"></td>
        </tr>
        <tr>
            <td>ФИО</td>
            <td><input type="text" required class="form-control" name="name" value="<%=client.getName()%>"></td>
        </tr>
        <tr>

        <tr>
            <td>Пол</td>
            <td>
                <select class="form-control" name="sex">
                    <option value="m" <%if (client.getSex().equals("m")) {%> selected  <%}%> >Мужской</option>
                    <option value="w" <%if (client.getSex().equals("w")) {%> selected  <%}%> >Женский</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Номер телефона</td>
            <td><input type="text" class="form-control" name="phone" value="<%=client.getPhone()%>"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" class="form-control" name="email" value="<%=client.getEmail()%>"></td>
        </tr>
    </table>
    <input class="btn btn-primary" type="submit"  value="Редактировать">
</form>
</body>
</html>
