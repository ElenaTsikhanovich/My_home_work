<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="model.Message" %>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Чат</title>
</head>
<body>
<h2>Ваши сообщения</h2>
<table border="2">
   <tr>
       <th>Отправитель</th>
       <th>Время отправления</th>
       <th>Текст</th>
   </tr>
     <c:forEach var="msg" items="${list}">
         <tr>
             <td>${msg.getSender()}</td>
             <td>${msg.getTime()}</td>
             <td>${msg.getText()}</td>
         </tr>
     </c:forEach>
</table>
<form action="./message" method="get">
        <input type="submit" value="написать сообщение"/>
</form>
<form action="./user" method="get">
    <input type="submit" value="посмотреть информацию о пльзователях">
</form>
<form action="./" method="get">
    <input type="submit" value="выйти">
</form>
<form action="./message" method="get">
    <input type="submit" value="назад">
</form>
<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>
</body>
</html>





