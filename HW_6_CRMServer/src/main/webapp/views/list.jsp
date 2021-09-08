<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="model.Department" %>
<%@page import="model.Employer" %>
<%@page import="model.Position" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Списки и карточки</title>
</head>
<body>
<c:if test="${requestScope.employers != null}">
   <h1>Список сотрудников предприятия</h1>
   <c:forEach var="employer" items="${employers}" >
       <p style="color:black"><a href="./employer?id=${employer.getId()}">${employer.getName()}</a></p>
   </c:forEach>
</c:if>

<c:if test="${requestScope.departments != null}">
   <h1>Список отделов предприятия</h1>
   <c:forEach var="department" items="${departments}" >
       <h3 style="color: black"><a href="./department?id=${department.getId()}">${department.getName()}</a></h3>
   </c:forEach>
</c:if>

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








