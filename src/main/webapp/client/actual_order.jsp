<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 03.03.2017
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статус текущей заявки</title>
    <%@include file="../include/header_client.jsp" %>
</head>
<body>

<div class="container">
    <h2>Текущий заказ</h2>
    <div class="row col-md-10">
        <table class="table table-hover">
            <tr>
                <th>Дата регистрации</th>
                <th>Пукт отправления</th>
                <th>Пункт прибытия</th>
                <th>Цена</th>
                <th>Водитель</th>
                <th>Автомобиль</th>
                <th>Телефон</th>
            </tr>
            <tr>

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
                    <c:choose>
                        <c:when test="${order.getEntityDriver() != null}">
                            <c:out value="${order.getEntityDriver().getFirstName()}"></c:out>
                        </c:when>
                        <c:when test="${order.getEntityDriver() == null}">
                            Не назначен
                        </c:when>
                    </c:choose>

                </td>
                <td>
                    <c:choose>
                        <c:when test="${order.getEntityDriver() != null}">
                            <c:out value="${list.getEntityDriver().getEntryAuto().getMarka()}"></c:out>
                            <c:out value="${list.getEntityDriver().getEntryAuto().getModel()}"></c:out>
                        </c:when>
                        <c:when test="${order.getEntityDriver() == null}">
                            Не назначен
                        </c:when>
                    </c:choose>

                </td>
                <td>
                    <c:choose>
                        <c:when test="${order.getEntityDriver() != null}">
                            <c:out value="${list.getEntityDriver().getPhoneNumber()}"></c:out>
                        </c:when>
                        <c:when test="${order.getEntityDriver() == null}">
                            Не назначен
                        </c:when>
                    </c:choose>

                </td>
                <td>
                    <a class="btn btn-danger"
                       href="/taxi/client/cancel_order?id=<c:out value="${order.getId()}"></c:out>"
                    >
                        Отменить завку
                    </a>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
