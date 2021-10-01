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
<form action="${pageContext.request.contextPath}/search" method="get">
                 <input type="text" name="name" placeholder="имя"><br/>
                 <input type="text" name="salaryFrom" placeholder="зарплата от"><br/>
                 <input type="text" name="salaryTo" placeholder="зарплата до"><br/>
                 <input type="submit" value="искать"/>
             </form>

<c:if test="${requestScope.employers != null}">
   <h1>Список сотрудников предприятия</h1>
   <c:forEach var="employer" items="${employers}" >
       <p style="color:black"><a href="${pageContext.request.contextPath}/employer?id=${employer.getId()}">${employer.getName()}</a></p>
   </c:forEach>
</c:if>

<c:if test="${requestScope.page != null}">
<c:if test="${requestScope.page != 1}">
    <a href="${pageContext.request.contextPath}/employer?limit=20&page=${requestScope.page-1}"><<</a>
</c:if>
<c:forEach begin="${page}" end="${page+10}" var="i">
<c:if test="${i<=pageCount}">
     <c:choose>
         <c:when test="${page eq i}">
            <td>${i}</td>
          </c:when>
          <c:otherwise>
             <td><a href="${pageContext.request.contextPath}/employer?limit=20&page=${i}">${i}</a></td>
          </c:otherwise>
     </c:choose>
     </c:if>
</c:forEach>
<c:if test="${requestScope.page != pageCount}">
    <a href="./employer?limit=20&page=${requestScope.page+1}">>></a>
</c:if>
</c:if>

<form action="${pageContext.request.contextPath}/employer" method="get">
    <input type="submit" value="назад">
</form>

</body>
</html>

