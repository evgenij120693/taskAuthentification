<%@ page import="models.pojo.Admin" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 28.02.2017
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование аккаунта</title>
</head>
<body>
<% Admin admin = (Admin) request.getAttribute("admin");%>
<h1>Редактирование аккаунта</h1>
<form action="/taxi/admin/edit_account" name="form" method="post">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" disabled value="<%=admin.getLogin()%>"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="<%=admin.getPassword()%>"></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" name="name" value="<%=admin.getName()%>"></td>
        </tr>
        <tr>

        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="<%=admin.getEmail()%>"></td>
        </tr>
        <tr>
            <td>Отправлять сообщение при входе</td>
            <td><input type="checkbox" name="flag" <%if(admin.getSendEmailFlag() ==1){%>checked<%}%>></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit"  value="Сохранить">
</form>

</body>
</html>
