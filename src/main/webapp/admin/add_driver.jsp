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
</head>
<body>
<h1>Добавление водителч</h1>
<form action="/taxi/admin/add_driver" name="form" method="post">
    <input type="hidden" name="id" value="">
    <table style="border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value=""></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value=""></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" name="firstName" value=""></td>

        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" name="lastName" value=""></td>
        </tr>

        <tr>
            <td>Телефон</td>
            <td><input type="text" name="phone" value=""></td>
        </tr>

        <tr>
            <td>Рейтинг</td>
            <td><input type="text" name="rating" value=""></td>
        </tr>

        <tr>
            <td>Автомобиль</td>
            <td>
                <select name="auto">
                    <c:forEach items="${listAuto}" var="auto">
                        <option value="<c:out value="${auto.getId()}"></c:out>">
                            <c:out value="${auto.getModel()}"></c:out>
                            <c:out value="${auto.getRegNumber()}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статус</td>
            <td><input type="text" disabled name="status" value="Не готов"></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit" value="Добавить">
</form>

</body>
</html>
