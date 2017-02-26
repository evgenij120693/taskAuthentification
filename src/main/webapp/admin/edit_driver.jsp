<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.pojo.Driver" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование водителя</title>
</head>
<body>
<%Driver driver = (Driver) request.getAttribute("driver");%>
<h1>Редактирование водителя</h1>
<form action="/taxi/admin/edit_driver" name="form" method="post">
    <input type="hidden" name="id" value="<%=driver.getId()%>">
    <table style="border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value="<%=driver.getLogin()%>"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="<%=driver.getPassword()%>"></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" name="firstName" value="<%=driver.getFirstName()%>"></td>

        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" name="lastName" value="<%=driver.getLastName()%>"></td>
        </tr>

        <tr>
            <td>Телефон</td>
            <td><input type="text" name="phone" value="<%=driver.getPhoneNumber()%>"></td>
        </tr>

        <tr>
            <td>Рейтинг</td>
            <td><input type="text" name="rating" value="<%=driver.getRating()%>"></td>
        </tr>

        <tr>
            <td>Автомобиль</td>
            <td>
                <select name="auto">
                    <c:forEach items="${listAuto}" var="auto">
                        <option value="<c:out value="${auto.getId()}"></c:out>"
                                <c:if test="${auto.getId() eq driver.getAuto()}">selected</c:if>
                        >
                            <c:out value="${auto.getModel()}"></c:out>
                            <c:out value="${auto.getRegNumber()}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статус</td>
            <td><input type="text"  name="status" value="<%=driver.getStatus()%>"></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit" value="Сохранить изменения">
</form>
</body>
</html>
