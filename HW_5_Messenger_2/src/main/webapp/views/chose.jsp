<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Меню</title>
</head>
<body>
<h2><%=(String)request.getAttribute("userName") %>, теперь вы можете общаться!</h2>
<form action="./message" method="get">
        <input type="submit" value="написать сообщение"/>
</form>
<form action="./chats" method="get">
    <input type="submit" value="посмотреть ваши сообщения"/>
</form>
<form action="./user" method="get">
    <input type="submit" value="посмотреть информацию о пользователях">
</form>
<form action="./" method="get">
    <input type="submit" value="выйти">
</form>
<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>
</body>
</html>