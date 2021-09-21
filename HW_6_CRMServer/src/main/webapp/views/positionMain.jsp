<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Должности предприятия</title>
</head>
<body>
<h1>Карточка должности</h1>
<h3 style="color:red">
   <c:if test="${requestScope.exception != null}">
<c:out value="${requestScope.exception}" />
       </c:if>
       </h3>
       <form action="${pageContext.request.contextPath}/position" method="get">
           <input type="text" name="id" placeholder="id"><br/>
           <input type="submit" value="получить данные"/>
       </form>

<h1><a href="${pageContext.request.contextPath}/position?list=1">Посмотреть список всех должностей</a></h1>
<form action="./" method="get">
   <input type="submit" value="назад">
      </form>

</body>
</html>