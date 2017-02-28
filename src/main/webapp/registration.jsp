<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 26.02.2017
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="container">
    <div class=" row col-md-6">
        <form class="form-horizontal" action="/taxi/registration" method="post">

            <fieldset>
                <legend>Регистрация</legend>
                <div class="form-group">
                    <label for="login" class="control-label col-xs-2" style="padding-right: 0px;">Логин</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" name="login" id="login" placeholder="Логин">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="control-label col-xs-2" style="padding-right: 0px;">Пароль</label>
                    <div class="col-xs-10">
                        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Пароль">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="control-label col-xs-2" style="padding-right: 0px;">ФИО</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="ФИО">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sex" class="control-label col-xs-2" style="padding-right: 0px;">Пол</label>
                    <div class="col-xs-10">
                        <select name="sex" id="sex" class="form-control">
                            <option value="m" selected>Мужской</option>
                            <option value="w">Женский</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="control-label col-xs-2 " style="padding-right: 0px;">Телефон</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="Телефон">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="control-label col-xs-2" style="padding-right: 0px;">Email</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" name="email" id="email" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
