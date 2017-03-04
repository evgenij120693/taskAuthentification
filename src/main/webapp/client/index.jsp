<%@ page import="ru.svetozarov.models.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 25.02.2017
  Time: 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <%@include file="../include/header_client.jsp" %>
</head>
<body>
<div class="container">
    <h2>Личнае данные
        <a href="/taxi/client/edit" title="Редактировать">
            <img src="http://s1.iconbird.com/ico/2013/10/464/w512h5121380984696edit.png" width="25px;">
        </a>
    </h2>

    <% Client client = (Client) request.getAttribute("client");%>

    <div class="row col-md-6">
        <table class="table table-hover">
            <tr>
                <td>Логин</td>
                <td><%=client.getLogin()%>
                </td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><%=client.getPassword()%>
                </td>
            </tr>
            <tr>
                <td>ФИО</td>
                <td><%=client.getName()%>
                </td>
            </tr>
            <tr>

            <tr>
                <td>Пол</td>
                <td>
                    <%if (client.getSex().equals("m")) {%> Мужской  <%}%>
                    <%if (client.getSex().equals("w")) {%> Женский  <%}%>
                </td>
            </tr>
            <tr>
                <td>Номер телефона</td>
                <td><%=client.getPhone()%>
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td><%=client.getEmail()%>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
