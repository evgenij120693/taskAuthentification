<%@ page import="ru.svetozarov.models.pojo.Driver" %>
<%@ page import="ru.svetozarov.models.pojo.Auto" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование информации об автомобиле</title>
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<% Auto auto = (Auto) request.getAttribute("auto");%>
<div class="container">
    <div class="row col-md-6">
        <h2>Редактирование информации об автомобиле</h2>
        <form action="/taxi/admin/edit_auto" name="form" method="post">
            <input type="hidden" name="id" value="<%=auto.getId()%>">
            <table class="table table-hover">
                <tr>
                    <td>Марка</td>
                    <td><input type="text" class="form-control" required name="marka" value="<%=auto.getMarka()%>"></td>
                </tr>
                <tr>
                    <td>Модель</td>
                    <td><input type="text" class="form-control" name="model" value="<%=auto.getModel()%>"></td>
                </tr>
                <tr>
                    <td>Регистрационный номер</td>
                    <td><input type="text" class="form-control" required name="regNumber" value="<%=auto.getRegNumber()%>"></td>
                </tr>
                <tr>
                    <td>Цвет</td>
                    <td><input type="text" class="form-control" required name="color" value="<%=auto.getColor()%>"></td>
                </tr>
                <tr>


            </table>
            <input class="btn btn-primary" type="submit" value="Редактировать">
        </form>
    </div>
</div>
</body>
</html>
