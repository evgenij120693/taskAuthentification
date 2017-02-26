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
    <script src="http://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="/taxi/js/add_client.js" type="text/javascript"></script>
</head>
<body>
<h1>Добавление клиента</h1>
<form action="/taxi/admin/add_client" name="form" method="post">
    <input type="hidden" name="id" value="">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value=""></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value=""></td>
        </tr>
        <tr>
            <td>ФИО</td>
            <td><input type="text" name="name" value=""></td>
        </tr>
        <tr>

        <tr>
            <td>Пол</td>
            <td>
                <select name="sex">
                    <option value="m"  selected  >Мужской</option>
                    <option value="w" >Женский</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Номер телефона</td>
            <td><input type="text" name="phone" value=""></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value=""></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit"  value="Добавить">
</form>
</body>
</html>
