<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h2><%=(String)request.getAttribute("userName") %>, выберите адресата, введите сообщение
    и нажмите  <span style="color:blue">Отправить!</span></h2>


<p style="color:green">

<c:if test="${requestScope.addMessage != null}">
<c:out value="${requestScope.addMessage}" />
</c:if>
</p>

<form action="./message" method="post">
    <input type="text" name="recipient" placeholder="логин адресата"><br/>
    <textarea name="text" rows="10" cols="20"></textarea>
    <input type="submit" value="отправить"/>
</form>

<form action="./" method="get">
    <input type="submit" value="выйти">
</form>

<form action="./signIn" method="get">
    <input type="submit" value="назад">
</form>