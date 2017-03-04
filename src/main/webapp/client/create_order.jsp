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
    <%@include file="../include/header_client.jsp" %>

    <title>Заказ такси</title>
</head>
<body>
<div class="container">
    <h2>Заказ такси</h2>
    <div class="row col-md-6">
        <form action="/taxi/client/taxi" name="form" method="post">
            <input type="hidden" name="id" value="">
            <table class="table table-hover">
                <tr>
                    <td>Откуда</td>
                    <td><input type="text" class="form-control" name="punkt_a" value=""></td>
                </tr>
                <tr>
                    <td>Куда</td>
                    <td><input type="text" class="form-control" name="punkt_b" value=""></td>
                </tr>
                <tr id="tr_price" style="display:none;">
                    <td>Цена</td>
                    <td><input type="text" class="form-control" readonly name="price" id="price" value="0">
                    </td>
                </tr>
            </table>
            <input class="btn btn-primary" style="display:none; " id="submit" type="submit" value="Заказать">
        </form>
    </div>
</div>
</body>
</html>
