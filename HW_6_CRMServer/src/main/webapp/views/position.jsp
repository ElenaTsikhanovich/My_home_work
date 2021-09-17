<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Employer" %>
<%@page import="model.Position" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Карточка должности</title>
</head>
<body>
<h1>Карточка должности</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Должность</th>
    </tr>
        <tr>
            <td>${position.getId()}</td>
            <td width="200">${position.getName()}</td>
        </tr>
</table>

        <form action="./position" method="get">
              <input type="submit" value="назад">
          </form>

</body>
</html>