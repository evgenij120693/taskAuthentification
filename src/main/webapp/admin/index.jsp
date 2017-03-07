<%@ page import="ru.svetozarov.models.pojo.Admin" %><%--
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
</head>
<body>
<%@include file="../include/header_admin.jsp" %>
<% Admin admin = (Admin) request.getAttribute("admin");%>
<div class="container">
    <h1>Личные данные
        <a href="/taxi/admin/edit_account" title="Редактировать">
            <img src="http://s1.iconbird.com/ico/2013/10/464/w512h5121380984696edit.png" width="25px;">
        </a>
    </h1>

    <div class="row col-md-6">
        <table class="table table-hover">

            <tr>
                <td>Логин</td>
                <td><%=admin.getLogin()%></td>
            </tr>

            <tr>
                <td>Имя</td>
                <td><%=admin.getName()%></td>
            </tr>
            <tr>

            <tr>
                <td>Email</td>
                <td><%=admin.getEmail()%>
                </td>
            </tr>
            <tr>
                <td>Отправлять сообщение при входе</td>
                <td>
                    <%if (admin.getSendEmailFlag() == 1) {%>Да<%}%>
                    <%if (admin.getSendEmailFlag() == 0) {%>Нет<%}%>
                </td>
            </tr>
        </table>


    </div>
</div>
</body>
</html>
