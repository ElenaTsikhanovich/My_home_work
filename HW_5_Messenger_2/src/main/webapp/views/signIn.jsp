<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Авторизация пользователя</h2>

<p style="color:red">

<c:if test="${requestScope.info != null}">
<c:out value="${requestScope.info}" />
</c:if>
</p>


<h3>Введите логин и пароль</h3>
<form action="./signIn" method="post">
    <input type="text" name="login" placeholder="логин"><br/>
    <input type="password" name="password" placeholder="пароль"><br/>
    <input type="submit" value="войти"/>
</form>
<form action="./signUp" method="get">
    <input type="submit" value="зарегистрироваться"/>
</form>

<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>