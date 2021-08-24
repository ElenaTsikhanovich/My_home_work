<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List" %>
<%@page import="model.User" %>

<h2>Все зарегистрированные пользователи</h2>

<table border="2">
    <tr>
        <th>Имя пользователя</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Дата и время регистрации</th>
        <th>Дата рождения</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${user.getFio()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getDateOfRegistration()}</td>
            <td>${user.getBirth()}</td>
        </tr>
    </c:forEach>
</table>

<form action="./message" method="get">
    <input type="submit" value="перейти к сообщениям">
</form>

<form action="./" method="get">
    <input type="submit" value="выйти">
</form>

<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>