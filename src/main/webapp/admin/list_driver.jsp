<%@ page import="ru.svetozarov.models.pojo.Driver" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 25.02.2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список водителей</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-9">
        <h1>Список водителей
            <a href="/taxi/admin/add_driver" title="Добавить водителя">
                <img src="https://image.freepik.com/free-icon/no-translate-detected_318-34469.jpg"
                     width="25px;">
            </a>
        </h1>
        <% List<Driver> driver = (List<Driver>) request.getAttribute("list");
            System.out.println(driver.get(0).getEntryAuto().getModel());
        %>
        <table class="table table-hover">
            <tr>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Телефон</th>
                <th>Рейтинг</th>
                <th>Автомобиль</th>
                <!--<th>Статус</th>-->
            </tr>
            <c:forEach items="${list}" var="driver">
                <tr>
                    <td><c:out value="${driver.getLogin()}"></c:out></td>
                    <td><c:out value="${driver.getPassword()}"></c:out></td>
                    <td><c:out value="${driver.getFirstName()}"></c:out></td>
                    <td><c:out value="${driver.getLastName()}"></c:out></td>
                    <td><c:out value="${driver.getPhoneNumber()}"></c:out></td>
                    <td><c:out value="${driver.getRating()}"></c:out></td>
                    <td>
                            <c:out value="${driver.getEntryAuto().getMarka()}"></c:out>
                            <c:out value="${driver.getEntryAuto().getModel()}"></c:out>
                            <c:out value="${driver.getEntryAuto().getRegNumber()}"></c:out>
                   <!-- <td>
                        <c:out value="${driver.getEntryStatus().getName()}"></c:out>
                    </td>-->
                    <td><a href="/taxi/admin/edit_driver?id=<c:out value="${driver.id}"></c:out>">Редактировать</a>
                        <a href="/taxi/admin/delete_driver?id=<c:out value="${driver.getId()}"></c:out>">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
