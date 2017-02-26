<%@ page import="models.pojo.Client" %><%--
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
</head>
<body>
<h1>Редактирование клиента</h1>
<% Client client = (Client) request.getAttribute("client");%>

<form action="/taxi/admin/edit_client" name="form" method="post">
    <input type="hidden" name="id" value="<%=client.getId()%>">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value="<%=client.getLogin()%>"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="<%=client.getPassword()%>"></td>
        </tr>
        <tr>
            <td>ФИО</td>
            <td><input type="text" name="name" value="<%=client.getName()%>"></td>
        </tr>
        <tr>

        <tr>
            <td>Пол</td>
            <td>
                <select name="sex">
                    <option value="m" <%if (client.getSex().equals("m")) {%> selected  <%}%> >Мужской</option>
                    <option value="w" <%if (client.getSex().equals("w")) {%> selected  <%}%> >Женский</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Номер телефона</td>
            <td><input type="text" name="phone" value="<%=client.getPhone()%>"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="<%=client.getEmail()%>"></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit"  value="Редактировать">
</form>
</body>
</html>
