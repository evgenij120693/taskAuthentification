<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 02.03.2017
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>История заказов</title>
    <%@include file="../include/header_client.jsp" %>
</head>
<body>
<div class="container">
    <h2>История заказов</h2>
    <div class="row col-md-12">
        <table class="table table-hover">
            <tr>
                <th>Дата регистрации</th>
                <th>Пукт отправления</th>
                <th>Пункт прибытия</th>
                <th>Цена</th>
                <th>Водитель</th>
                <th>Автомобиль</th>
                <th>Телефон</th>
                <th>Дата отправления</th>
                <th>Дата прибытия</th>
            </tr>
            <c:forEach items="${list}" var="order">
                <td>
                    <c:out value="${order.getDateRegistration()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getPunktA()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getPunktB()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getPrice()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getEntityDriver().getFirstName()}"></c:out>

                </td>
                <td>

                    <c:out value="${order.getEntityDriver().getEntryAuto().getMarka()}"></c:out>
                    <c:out value="${order.getEntityDriver().getEntryAuto().getModel()}"></c:out>
                </td>
                <td>

                    <c:out value="${order.getEntityDriver().getPhoneNumber()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getDateStart()}"></c:out>
                </td>
                <td>
                    <c:out value="${order.getDateEnd()}"></c:out>
                </td>
                </tr>
            </c:forEach>


        </table>
    </div>
</div>
</body>
</html>
