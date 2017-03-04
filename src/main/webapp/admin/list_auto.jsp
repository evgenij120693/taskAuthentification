<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список автомобилей</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-9">
        <h2>Список автомобилей
            <a href="/taxi/admin/add_auto" title="Добавить автомобиль">
                <img src="https://image.freepik.com/free-icon/no-translate-detected_318-34469.jpg"
                width="25px;">
            </a>
        </h2>
        <table class="table table-hover">
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
        
    </div>
</div>
</body>
</html>
