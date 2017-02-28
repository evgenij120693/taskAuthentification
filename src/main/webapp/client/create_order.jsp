<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 28.02.2017
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="../js/jquery.js"></script>
    <script src="../js/client/calculation_price.js"></script>
    <title>Заказ такси</title>
</head>
<body>
<h1>Заказ такси</h1>
<form action="/taxi/client/taxi" name="form" method="post">
    <input type="hidden" name="id" value="">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Откуда</td>
            <td><input type="text" name="punkt_a" value=""></td>
        </tr>
        <tr>
            <td>Куда</td>
            <td><input type="text" name="punkt_b" value=""></td>
        </tr>
        <tr id="tr_price" style="display:none;">
            <td>Цена</td>
            <td><input type="text" readonly name="price" id="price" value="0">
                </td>
        </tr>
    </table>
    <input style="display:none; margin:10px;" id="submit" type="submit"  value="Заказать">
</form>

</body>
</html>
