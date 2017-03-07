<%@ page import="ru.svetozarov.models.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 28.02.2017
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование профиля</title>

    <%@include file="../include/header_client.jsp" %>
</head>
<body>

<div class="container">
    <h1>Редактирование профиля</h1>
    <% Client client = (Client) request.getAttribute("client");%>
    <form action="/taxi/client/edit" name="form" method="post">
        <div class="row col-md-6">
            <table class="table table-hover">
                <tr>
                    <td>Логин</td>
                    <td><input type="text" class="form-control" readonly value="<%=client.getLogin()%>"></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" class="form-control" required name="password" value="32.,5/*/"></td>
                </tr>
                <tr>
                    <td>ФИО</td>
                    <td><input type="text" class="form-control" required name="name" value="<%=client.getName()%>"></td>
                </tr>
                <tr>

                <tr>
                    <td>Пол</td>
                    <td>
                        <select  class="form-control" name="sex">
                            <option value="m" <%if (client.getSex().equals("m")) {%> selected  <%}%> >Мужской</option>
                            <option value="w" <%if (client.getSex().equals("w")) {%> selected  <%}%> >Женский</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Номер телефона</td>
                    <td><input type="text" class="form-control" required name="phone" value="<%=client.getPhone()%>"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" class="form-control" name="email" value="<%=client.getEmail()%>"></td>
                </tr>
            </table>
            <input class="btn btn-primary" type="submit" value="Редактировать">
        </div>
    </form>
</div>


</body>
</html>
