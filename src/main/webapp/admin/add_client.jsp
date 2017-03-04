<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 25.02.2017
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление клиента</title>
    <%@include file="../include/header_admin.jsp" %>

</head>
<body>
<div class="container">
    <div class="row col-md-6">
        <h1>Добавление клиента</h1>
        <form action="/taxi/admin/add_client" name="form" method="post">
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
                    <td>ФИО</td>
                    <td><input type="text" required class="form-control" name="name" value=""></td>
                </tr>
                <tr>

                <tr>
                    <td>Пол</td>
                    <td>
                        <select class="form-control" name="sex">
                            <option value="m" selected>Мужской</option>
                            <option value="w">Женский</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Номер телефона</td>
                    <td><input type="text" class="form-control" name="phone" value=""></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" class="form-control" name="email" value=""></td>
                </tr>
            </table>
            <input class="btn btn-primary"  type="submit" value="Добавить">
        </form>

    </div>
</div>
</body>
</html>
