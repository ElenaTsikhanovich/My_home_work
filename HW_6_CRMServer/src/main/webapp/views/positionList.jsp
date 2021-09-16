<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="model.Position" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Список должностей предприятия</title>
</head>
<body>
<c:if test="${requestScope.positions != null}">
   <h1>Список должностей предприятия</h1>
   <c:forEach var="position" items="${positions}" >
       <h3 style="color: black"><a href="./position?id=${position.getId()}">${position.getName()}</a></h3>
   </c:forEach>
</c:if>

<form action="./" method="get">
    <input type="submit" value="назад">
</form>
</body>
</html>
