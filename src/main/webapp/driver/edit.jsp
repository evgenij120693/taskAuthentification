<%@ page import="ru.svetozarov.models.pojo.Driver" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 28.02.2017
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование профиля</title>
</head>
<%@include file="../include/header_driver.jsp" %>
<body>

<div class="container">
    <h2>Редактирование профиля</h2>
    <% Driver driver = (Driver) request.getAttribute("driver");
        int id_status = driver.getEntryStatus().getId();
        System.out.printf("id status " + id_status);%>
    <form action="/taxi/driver/edit" name="form" method="post">
        <div class="row col-md-6">
            <table class="table table-hover">
                <tr>
                    <td>Логин</td>
                    <td><input class="form-control" type="text"  readonly value="<%=driver.getLogin()%>"></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input class="form-control" required type="text" name="password" value="<%=driver.getPassword()%>"></td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td><input class="form-control" required type="text" name="firstName" value="<%=driver.getFirstName()%>"></td>

                </tr>
                <tr>
                    <td>Фамилия</td>
                    <td><input class="form-control"  type="text" name="lastName" value="<%=driver.getLastName()%>"></td>
                </tr>

                <tr>
                    <td>Телефон</td>
                    <td><input class="form-control" required type="text" name="phone" value="<%=driver.getPhoneNumber()%>"></td>
                </tr>

                <%--<tr>--%>
                    <%--<td>Рейтинг</td>--%>
                    <%--<td><input class="form-control"readonly type="text" name="rating" value="<%=driver.getRating()%>"></td>--%>
                <%--</tr>--%>

                <%--<tr>--%>
                    <%--<td>Автомобиль</td>--%>
                    <%--<td>--%>
                        <%--<input class="form-control" type="text" readonly name="rating"--%>
                               <%--value="<%=driver.getEntryAuto().getMarka()%> <%=driver.getEntryAuto().getModel()%>">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td>Статус</td>--%>
                <%--<td>--%>
                <%--<select name="status">--%>
                <%--<c:forEach items="${statusList}" var="stat">--%>
                <%--<option value="<c:out value="${status.getId()}"></c:out>"--%>
                <%--<c:if test="${stat.getId() eq id_status}"> selected </c:if>--%>
                <%-->--%>
                <%--<c:out value="${stat.getName()}"></c:out>--%>
                <%--</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</td>--%>
                <%--</tr>--%>

            </table>
            <input class="btn btn-primary"  type="submit" value="Сохранить изменения">
        </div>
    </form>
</div>
</body>
</html>
