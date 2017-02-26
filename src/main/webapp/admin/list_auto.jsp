<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список автомобилей</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Марка</th>
        <th>Модель</th>
        <th>Регистрационный номер</th>
        <th>Цвет</th>
    </tr>
    <c:forEach items="${list}" var="auto">
        <tr>
            <td><c:out value="${auto.getMarka()}"></c:out></td>
            <td><c:out value="${auto.getModel()}"></c:out></td>
            <td><c:out value="${auto.getRegNumber()}"></c:out></td>
            <td><c:out value="${auto.getColor()}"></c:out></td>
            <td><a href="/taxi/admin/edit_auto?id=<c:out value="${auto.getId()}"></c:out>">Редактировать</a>
                <a href="/taxi/admin/delete_auto?id=<c:out value="${auto.getId()}"></c:out>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/taxi/admin/add_auto">Добавить автомобиль</a>
<h2><a href="/taxi/logout">Выйти из личного кабинета</a></h2>
</body>
</html>
