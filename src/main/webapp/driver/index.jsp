<%@ page import="ru.svetozarov.models.pojo.Driver" %><%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 25.02.2017
  Time: 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
<%@include file="../include/header_driver.jsp" %>
<% Driver driver = (Driver) request.getAttribute("driver");
    int id_status = driver.getEntryStatus().getId();
    System.out.printf("id status " + id_status);%>


<div class="container">
    <h1>Личные данные 
        <a href="/taxi/driver/edit" title="Редактировать">
            <img src="http://s1.iconbird.com/ico/2013/10/464/w512h5121380984696edit.png" width="25px;">
        </a>
    </h1>

<div class="row col-md-6">
    <table class="table table-hover" >
        <tr>
            <td>Логин</td>
            <td><%=driver.getLogin()%>
            </td>
        </tr>
       
        <tr>
            <td>Имя</td>
            <td><%=driver.getFirstName()%>
            </td>

        </tr>
        <tr>
            <td>Фамилия</td>
            <td><%=driver.getLastName()%>
            </td>
        </tr>

        <tr>
            <td>Телефон</td>
            <td><%=driver.getPhoneNumber()%></td>
        </tr>

        <tr>
            <td>Рейтинг</td>
            <td><%=driver.getRating()%>
            </td>
        </tr>

        <tr>
            <td>Автомобиль</td>
            <td>
                <%=driver.getEntryAuto().getMarka()%> <%=driver.getEntryAuto().getModel()%>
            </td>
        </tr>


    </table>

<%--</div>--%>
    <%--<div class=" vcenter vcenter text-center col-md-offset-1 col-md-4">--%>
        <%--<div class="notice notice-warning">--%>
            <%--<strong>Статус: </strong> не на смене.--%>
            <%--<br>--%>
            <%--<button type="button" id="updateStatus" class="btn btn-success" value="ready">Начать смену</button>--%>
        <%--</div>--%>

    <%--</div>--%>
<%--</div>--%>

</body>
</html>
