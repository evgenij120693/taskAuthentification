<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Admin: Шмыга
  Date: 25.02.2017
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список клиентов</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-9">
        <h1>Список клиентов
            <a href="/taxi/admin/add_client" title="Добавить клиента">
                <img src="https://image.freepik.com/free-icon/no-translate-detected_318-34469.jpg"
                     width="25px;">
            </a>
        </h1>

        <table class="table table-hover">
            <tr>
                <th>Логин</th>
                <th>Имя</th>
                <th>Пол</th>
                <th>Телефон</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${list}" var="client">
                <tr>
                    <td><c:out value="${client.getLogin()}"></c:out></td>
                    <td><c:out value="${client.getName()}"></c:out></td>
                    <td>
                        <c:if test="${client.getSex() eq 'm'}">мужской</c:if>
                        <c:if test="${client.getSex() eq 'w'}">женский</c:if>
                    </td>
                    <td><c:out value="${client.getPhone()}"></c:out></td>
                    <td><c:out value="${client.getEmail()}"></c:out></td>
                    <td><a href="/taxi/admin/edit_client?id=<c:out value="${client.getId()}"></c:out>">edit</a>
                        <a href="/taxi/admin/delete_client?id=<c:out value="${client.getId()}"></c:out>">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
