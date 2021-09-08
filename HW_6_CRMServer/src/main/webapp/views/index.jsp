<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Главная</title>
</head>
<body>
       <h1>Регистрация нового сотрудника</h1>
       	 <form action="./employer" method="post">
           <input type="text" name="name" placeholder="имя"><br/>
           <input type="text" name="salary" placeholder="сумма"><br/>
           <select name="department" size="1">
                       <option selected="selected">Выберите отдел</option>
                       <option value="11">Отдел управления</option>
                       <option value="12">Отдел товарооборота</option>
                       <option value="13">Отдел продаж</option>
                       <option value="14">Отдел закупок</option>
                       <option value="15">Аналитический отдел</option>
                  </select>
           <select name="position" size="1">
                       <option selected="selected">Выберите должность</option>
                       <option value="1">Генеральный директор</option>
                       <option value="2">Заместитель генерального директора</option>
                       <option value="3">Директор отдела</option>
                       <option value="4">Заведующий отделом</option>
                       <option value="5">Администратор</option>
                       <option value="6">специалист 1-й категории</option>
                       <option value="7">специалист 2-й категории</option>
                       <option value="8">Бухгалтер</option>
                       <option value="9">Юрист</option>
                       <option value="10">Секретарь</option>
                  </select>
           <input type="submit" value="внести в базу"/>
       </form>



       <h1>Карточка сотрудника</h1>
       <form action="./employer" method="get">
           <input type="text" name="id" placeholder="id"><br/>
           <input type="submit" value="получить данные сотрудника"/>
       </form>

       <h1>Посмотреть списки и карточки</h1>
       <form action="./list" method="get">
       <select name="list" size="1">
            <option selected="selected">Выберите список</option>
            <option value="employers">Сотрудники</option>
            <option value="departments">Отделы</option>
            <option value="positions">Должности</option>
            <input type="submit" value="выбрать"/>
       </select>
       </form>


</body>
</html>

