<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Главная</title>
</head>
<body>
<h1>Добро пожаловать в Lena Messenger!</h1>
<h2>Чтобы общаться с другими пользователями, пожалуйста авторизуйтесь!</h2>
<form action="./signIn" method="get">
    <input type="submit" value="войти">
</form>
<form action="./signUp" method="get">
    <input type="submit" value="новый пользователь">
</form>
<form action="./about" method="get">
    <input type="submit" value="об этом приложении">
</form>
</body>
</html>

