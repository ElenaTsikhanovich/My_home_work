<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Регистрация нового сотрудника</title>
</head>
<body>
<h2>Сотрудник  <%=(String)request.getAttribute("name")%>,
 успешно зарегистрирован под номером  <%=(String)request.getAttribute("id") %></h2>

               <h2>Теперь вы можете посмотреть карточку сотрудника<h2>
              <form action="./employer" method="get">
                          <input type="text" name="id" placeholder="id"><br/>
                          <input type="submit" value="получить данные сотрудника"/>
               </form>

<form action="./" method="get">
    <input type="submit" value="назад">
</form>
</body>
</html>

