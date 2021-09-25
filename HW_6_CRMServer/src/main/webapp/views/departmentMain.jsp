<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Отделы предприятия</title>
</head>
<body>
<h1>Регистрация нового отдела</h1>
       	 <form action="${pageContext.request.contextPath}/department" method="post">
           <input type="text" name="name" placeholder="название отдела"><br/>
           <select name="parent" size="1">
                       <option selected="selected">Выберите руководящий отдел</option>
                       <option value="${-1}">Нет отдела</option>
                       <c:forEach var="department" items="${departments}" >
                       <option value="${department.getId()}">${department.getName()}</option>
                       </c:forEach>
                   </select>
           <input type="submit" value="внести в базу"/>
       </form>

 <h3 style="color:red">
     <c:if test="${requestScope.registration != null}">
       <c:out value="${requestScope.registration}" />
       </c:if>
       </h3>
<h1>Карточка отдела</h1>
<h3 style="color:red">
   <c:if test="${requestScope.exception != null}">
<c:out value="${requestScope.exception}" />
       </c:if>
       </h3>
       <form action="${pageContext.request.contextPath}/department" method="get">
           <input type="text" name="id" placeholder="id"><br/>
           <input type="submit" value="получить данные"/>
       </form>

<h1><a href="${pageContext.request.contextPath}/department?list=1">Посмотреть список всех отделов</a></h1>

 <form action="${pageContext.request.contextPath}/" method="get">
      <input type="submit" value="назад">
  </form>

</body>
</html>
