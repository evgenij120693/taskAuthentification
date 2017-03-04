<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.svetozarov.models.pojo.Driver" %><%--
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
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<%Driver driver = (Driver) request.getAttribute("driver");%>
<div class="container">
    <div class="row col-md-6">
<h2>Редактирование водителя</h2>
<form action="/taxi/admin/edit_driver" name="form" method="post">
    <input type="hidden" name="id" value="<%=driver.getId()%>">
    <table class="table table-hover">
        <tr>
            <td>Логин</td>
            <td><input type="text" class="form-control" name="login" value="<%=driver.getLogin()%>"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" class="form-control" name="password" value="<%=driver.getPassword()%>"></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" class="form-control" name="firstName" value="<%=driver.getFirstName()%>"></td>

        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" class="form-control" name="lastName" value="<%=driver.getLastName()%>"></td>
        </tr>

        <tr>
            <td>Телефон</td>
            <td><input type="text" class="form-control" name="phone" value="<%=driver.getPhoneNumber()%>"></td>
        </tr>

        <tr>
            <td>Рейтинг</td>
            <td><input type="text" class="form-control" name="rating" value="<%=driver.getRating()%>"></td>
        </tr>

        <tr>
            <td>Автомобиль</td>
            <td>
                <select class="form-control" name="auto">
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
        <!--<tr>
            <td>Статус</td>
            <td><input type="text"  name="status" value="<%=driver.getStatus()%>"></td>
        </tr>-->
    </table>
    <input class="btn btn-primary" type="submit" value="Сохранить изменения">
</form>
</body>
</html>
