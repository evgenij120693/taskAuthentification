<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление автомобиля</title>
</head>
<body>
<h1>Добавление автомобиля</h1>
<form action="/taxi/admin/add_auto" name="form" method="post">
    <input type="hidden" name="id" value="">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Марка</td>
            <td><input type="text" name="marka" value=""></td>
        </tr>
        <tr>
            <td>Модель</td>
            <td><input type="text" name="model" value=""></td>
        </tr>
        <tr>
            <td>Регистрационный номер</td>
            <td><input type="text" name="regNumber" value=""></td>
        </tr>
        <tr>
            <td>Цвет</td>
            <td><input type="text" name="color" value=""></td>
        </tr>

    </table>
    <input style="margin:10px;" type="submit"  value="Добавить">
</form>

</body>
</html>
