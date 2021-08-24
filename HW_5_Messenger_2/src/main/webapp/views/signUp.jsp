<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Регистраия</title>
</head>
<body>
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
<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>
</body>
</html>