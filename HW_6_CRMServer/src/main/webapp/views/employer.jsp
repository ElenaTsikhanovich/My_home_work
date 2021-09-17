<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="model.Department" %>
<%@page import="model.Employer" %>
<%@page import="model.Position" %>



<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Карточка сотрудника</title>
</head>
<body>
<h1>Карточка сотрудника</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Зарплата</th>
        <th>Отдел</th>
        <th>Должность</th>
    </tr>
        <tr>
            <td>${employer.getId()}</td>
            <td width="200">${employer.getName()}</td>
            <td width="200"><fmt:formatNumber type = "number" value = "${employer.getSalary()}" /></td>
            <td width="200">${employer.getDepartment().getName()}</td>
            <td width="200">${employer.getPosition().getName()}</td>
        </tr>
</table>

<form action="./employer" method="get">
   <input type="submit" value="назад">
      </form>




</body>
</html>
