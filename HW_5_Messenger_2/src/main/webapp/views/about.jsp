<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Об этом приложении</title>
</head>
<body>
<h2> Об этом приложении </h2>
<p>Приложение запущено: <%=request.getAttribute("startDate")%>
</p>
<p>Выбранный способ хранения данных:
<c:choose>
    <c:when test="${requestScope.storage == 'file'}">
       Файл
    </c:when>
    <c:otherwise>
         Оперативная память
    </c:otherwise>
</c:choose>
<form action="./user" method="get">
    <input type="submit" value="посмотреть информацию о пользователях">
</form>
<form action="./message" method="get">
    <input type="submit" value="перейти к сообщениям">
</form>
<form action="./" method="get">
    <input type="submit" value="выйти">
</form>
</body>
</html>