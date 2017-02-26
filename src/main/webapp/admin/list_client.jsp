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
</head>
<body>
<h1>Список клиентов</h1>

<table border="2">
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Имя </th>
        <th>Пол</th>
        <th>Телефон</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${list}" var="client">
        <tr>
            <td><c:out value="${client.getLogin()}"></c:out></td>
            <td><c:out value="${client.getPassword()}"></c:out></td>
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
<a href="/taxi/admin/add_client">Добавить клиента</a>
<h2><a href="/taxi/logout">Выйти из личного кабинета</a></h2>
</body>
</html>
