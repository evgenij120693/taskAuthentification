<%@ page import="models.pojo.Driver" %>
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
</head>
<body>
<h1>Список водителей</h1>
<% List<Driver> driver = (List<Driver>) request.getAttribute("list");
    System.out.println(driver.get(0).getEntryAuto().getModel());
%>
<table border="2">
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Телефон</th>
        <th>Рейтинг</th>
        <th>Автомобиль</th>
        <th>Статус</th>
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
            <td>
                <c:out value="${driver.getEntryStatus().getName()}"></c:out>
            </td>
            <td><a href="/taxi/admin/edit_driver?id=<c:out value="${driver.id}"></c:out>">Редактировать</a>
                <a href="/taxi/admin/delete_driver?id=<c:out value="${driver.getId()}"></c:out>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/taxi/admin/add_driver">Добавить водителя</a>
<h2><a href="/taxi/logout">Выйти из личного кабинета</a></h2>
</body>
</html>
