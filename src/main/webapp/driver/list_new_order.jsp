<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 02.03.2017
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список доступных заявок</title>
    <%@include file="../include/header_driver.jsp" %>
</head>
<body>
<div class="container">
    <h2>Список доступных заявок</h2>

    <div class="row col-md-10">
        <table class="table table-hover">
            <tr>
                <th>Имя клиента</th>
                <th>Дата регистрации</th>
                <th>Пукт отправления</th>
                <th>Пункт прибытия</th>
                <th>Цена</th>
            </tr>
<c:forEach items="${list}" var="list">
            <tr>
                <td>
                    <c:out value="${list.getEntityClient().getName()}"></c:out>
                </td>
                <td>
                    <c:out value="${list.getDateRegistration()}"></c:out>
                </td>
                <td>
                    <c:out value="${list.getPunktA()}"></c:out>
                </td>
                <td>
                    <c:out value="${list.getPunktB()}"></c:out>
                </td>
                <td>
                    <c:out value="${list.getPrice()}"></c:out>
                </td>
                <td>
                    <a href="/taxi/driver/order_execute?id=<c:out value="${list.getId()}"></c:out>">Назначить заявку</a>
                </td>
            </tr>
</c:forEach>
        </table>

    </div>
</div>
</body>
</html>
