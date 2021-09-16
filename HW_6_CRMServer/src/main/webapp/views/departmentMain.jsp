<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Отделы предприятия</title>
</head>
<body>
<h1>Карточка отдела</h1>
       <form action="./department" method="get">
           <input type="text" name="id" placeholder="id"><br/>
           <input type="submit" value="получить данные"/>
       </form>

<h1><a href="./department?list=1">Посмотреть список всех отделов</a></h1>


</body>
</html>