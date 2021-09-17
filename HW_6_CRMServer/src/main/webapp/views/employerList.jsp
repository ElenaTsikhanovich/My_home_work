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
	<title>Список сотрудников</title>
</head>
<body>

<c:if test="${requestScope.employers != null}">
   <h1>Список сотрудников предприятия</h1>
   <c:forEach var="employer" items="${employers}" >
       <p style="color:black"><a href="./employer?id=${employer.getId()}">${employer.getName()}</a></p>
   </c:forEach>
</c:if>
<c:if test="${requestScope.page != 1}">
    <a href="./employer?limit=20&page=${requestScope.page-1}"><<</a>
</c:if>

<c:forEach begin="${page}" end="${page+10}" var="i">
<c:if test="${i<=pageCount}">
     <c:choose>
         <c:when test="${page eq i}">
            <td>${i}</td>
          </c:when>
          <c:otherwise>
             <td><a href="./employer?limit=20&page=${i}">${i}</a></td>
          </c:otherwise>
     </c:choose>
     </c:if>
</c:forEach>
<c:if test="${requestScope.page != pageCount}">
    <a href="./employer?limit=20&page=${requestScope.page+1}">>></a>
</c:if>

<form action="./employer" method="get">
    <input type="submit" value="назад">

</form>
</body>
</html>

