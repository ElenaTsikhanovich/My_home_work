<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="model.Department" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Список отделов предприятия</title>
</head>
<body>
<c:if test="${requestScope.departments != null}">
   <h1>Список отделов предприятия</h1>
   <c:forEach var="department" items="${departments}" >
       <h3 style="color: black"><a href="${pageContext.request.contextPath}/department?id=${department.getId()}">${department.getName()}</a></h3>
   </c:forEach>
</c:if>
<form action="${pageContext.request.contextPath}/department" method="get">
    <input type="submit" value="назад">
</form>
</body>
</html>
