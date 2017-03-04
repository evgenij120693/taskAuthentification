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
    <%@include file="../include/header_admin.jsp" %>
</head>
<body>
<div class="container">
    <div class="row col-md-6">
        <h2>Добавление автомобиля</h2>
        <form action="/taxi/admin/add_auto" name="form" method="post">
            <input type="hidden" name="id" value="">
            <table class="table table-hover">
                <tr>
                    <td>Марка</td>
                    <td><input type="text" class="form-control" required name="marka" value=""></td>
                </tr>
                <tr>
                    <td>Модель</td>
                    <td><input type="text" class="form-control" name="model" value=""></td>
                </tr>
                <tr>
                    <td>Регистрационный номер</td>
                    <td><input type="text" class="form-control" required name="regNumber" value=""></td>
                </tr>
                <tr>
                    <td>Цвет</td>
                    <td><input type="text" class="form-control" required name="color" value=""></td>
                </tr>

            </table>
            <button type="submit" class="btn btn-primary" value="Добавить">Добавить</button>
        </form>

    </div>
</div>
</body>
</html>
