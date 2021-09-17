<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Department" %>
<%@page import="model.Employer" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Карточка отдела</title>
</head>
<body>
<h1>Карточка отдела</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Отдел</th>
        <th>Управление</th>
    </tr>
        <tr>
            <td>${department.getId()}</td>
            <td width="200">${department.getName()}</td>
            <td width="200">${department.getParent().getName()}</td>
        </tr>
</table>

  <form action="./department" method="get">
      <input type="submit" value="назад">
  </form>
</body>
</html>



