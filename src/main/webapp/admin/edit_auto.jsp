<%@ page import="models.pojo.Driver" %>
<%@ page import="models.pojo.Auto" %><%--
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
</head>
<body>
<% Auto auto = (Auto) request.getAttribute("auto");%>
<h1>Редактирование информации об автомобиле</h1>
<form action="/taxi/admin/edit_auto" name="form" method="post">
    <input type="hidden" name="id" value="<%=auto.getId()%>">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Марка</td>
            <td><input type="text" name="marka" value="<%=auto.getMarka()%>"></td>
        </tr>
        <tr>
            <td>Модель</td>
            <td><input type="text" name="model" value="<%=auto.getModel()%>"></td>
        </tr>
        <tr>
            <td>Регистрационный номер</td>
            <td><input type="text" name="regNumber" value="<%=auto.getRegNumber()%>"></td>
        </tr>
        <tr>
            <td>Цвет</td>
            <td><input type="text" name="color" value="<%=auto.getColor()%>"></td>
        </tr>
        <tr>


    </table>
    <input style="margin:10px;" type="submit"  value="Редактировать">
</form>
</body>
</html>
