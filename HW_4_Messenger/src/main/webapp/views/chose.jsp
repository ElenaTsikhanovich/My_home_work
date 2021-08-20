<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<h2><%=(String)request.getAttribute("userName") %>, теперь вы можете общаться!</h2>

<form action="./message" method="get">
        <input type="submit" value="написать сообщение"/>
</form>

<form action="./chats" method="get">
    <input type="submit" value="посмотреть ваши сообщения"/>
</form>

<form action="./" method="get">
    <input type="submit" value="выйти">
</form>