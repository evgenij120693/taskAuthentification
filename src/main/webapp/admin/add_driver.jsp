<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление водителя</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-6">
<h2>Добавление водителя</h2>
<form action="/taxi/admin/add_driver" name="form" method="post">
    <input type="hidden" name="id" value="">
    <table class="table table-hover">
        <tr>
            <td>Логин</td>
            <td><input type="text" required class="form-control" name="login" value=""></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" required class="form-control" name="password" value=""></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" required class="form-control" name="firstName" value=""></td>

        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" class="form-control" name="lastName" value=""></td>
        </tr>

        <tr>
            <td>Телефон</td>
            <td><input type="text" required class="form-control" name="phone" value=""></td>
        </tr>

        <tr>
            <td>Рейтинг</td>
            <td><input type="text" required class="form-control" name="rating" value=""></td>
        </tr>

        <tr>
            <td>Автомобиль</td>
            <td>
                <select class="form-control" name="auto">
                    <c:forEach items="${listAuto}" var="auto">
                        <option  value="<c:out value="${auto.getId()}"></c:out>">
                            <c:out value="${auto.getModel()}"></c:out>
                            <c:out value="${auto.getRegNumber()}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <!--<tr>
            <td>Статус</td>
            <td><input type="text" class="form-control" disabled name="status" value="Не готов"></td>
        </tr>-->
    </table>
    <input class="btn btn-primary" type="submit" value="Добавить">
</form>

</body>
</html>
