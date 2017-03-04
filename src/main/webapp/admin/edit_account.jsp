<%@ page import="ru.svetozarov.models.pojo.Admin" %><%--
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
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<% Admin admin = (Admin) request.getAttribute("admin");%>
<div class="container">
    <h2>Редактирование профиля</h2>
    <form action="/taxi/admin/edit_account" name="form" method="post">
        <div class="row col-md-6">
            <table class="table table-hover">
                <tr>
                    <td>Логин</td>
                    <td><input type="text" class="form-control" name="login" readonly value="<%=admin.getLogin()%>">
                    </td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="text" class="form-control" name="password" required
                               value="<%=admin.getPassword()%>"></td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td><input type="text" class="form-control" required name="name" value="<%=admin.getName()%>"></td>
                </tr>
                <tr>

                <tr>
                    <td>Email</td>
                    <td><input type="text" class="form-control" name="email" value="<%=admin.getEmail()%>"></td>
                </tr>
                <tr>
                    <td>Отправлять сообщение при входе</td>
                    <td><input class="check-box" type="checkbox" name="flag"
                               <%if(admin.getSendEmailFlag() ==1){%>checked<%}%>></td>
                </tr>
            </table>
            <input class="btn btn-primary" type="submit" value="Сохранить">
        </div>
    </form>
</div>


</body>
</html>
