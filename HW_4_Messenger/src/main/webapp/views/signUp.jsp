<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h2>Регистрация нового пользователя!</h2>
<p style="color:red">

<c:if test="${requestScope.login_used != null}">
<c:out value="${requestScope.login_used}" />
</c:if>
</p>


<form action="./signUp" method="post">
    <input type="text" name="login" placeholder="логин"><br/>
    <input type="password" name="password" placeholder="пароль"><br/>
    <input type="text" name="fio" placeholder="ФИО"><br/>
    <input type="date" name="birth" placeholder="дата рождения"><br/>
    <input type="submit" value="зарегистрироваться"/>
</form>

<form action="./" method="get">
    <input type="submit" value="назад">
</form>