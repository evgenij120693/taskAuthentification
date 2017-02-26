<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<%@include file="include/header.jsp" %>
<body>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="row medium-4 large-3 alert callout">
    <%=error%>
</div>
<%}%>


<div class="row medium-4 large-3 primary columns callout left">
    <form action="/taxi/login" method="post">
        <label>Логин:</label>
        <input type="text" name="login" placeholder="Логин" value="">
        <label>Пароль:</label>
        <input type="password" name="password" placeholder="Пароль" value="">
        <input type="submit" class="button" style="float: right;" value="Войти">
    </form>

</div>
</body>
</html>
