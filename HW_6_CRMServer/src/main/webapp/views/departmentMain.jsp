<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Department" %>



<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Отделы предприятия</title>
</head>
<body>
<h1>Регистрация нового отдела</h1>
       	 <form id="department" action="api/departments" method="post">
           <input type="text" name="name" placeholder="название отдела"><br/>
           <select name="parent.id" size="1">
                       <option selected="selected">Выберите руководящий отдел</option>
                       <option value="${-1}">Нет отдела</option>
                       <c:forEach var="department" items="${departments}" >
                       <option value="${department.getId()}">${department.getName()}</option>
                       </c:forEach>
                   </select>
           <input type="submit" class="button" value="внести в базу"/>
       </form>
<script type="text/javascript">
document.getElementById('department').addEventListener('submit', submitForm);

function submitForm(event){
	event.preventDefault();
	let formData = new FormData(event.target);

let obj = {};
formData.forEach(
	(value,key) =>{
		if(key.includes('.')){
		var partName = key.split(".");
			obj[partName[0]] = {};
			obj[partName[0]][partName[1]] = value;
		} else{
			obj[key] = value;
		}

	}
	);

let request = new Request(event.target.action, {
	method: 'post',
	body: JSON.stringify(obj),
	headers: {
		'Content-Type': 'application/json',
	},
});

fetch(request).then(
	function (response){
		console.log(response);
	},
	function(error){
		console.log(error);
	}
	);

console.log('Запрос отправляется');

}
</script>
 <h3 style="color:red">
     <c:if test="${requestScope.registration != null}">
       <c:out value="${requestScope.registration}" />
       </c:if>
       </h3>
<h1>Карточка отдела</h1>
<h3 style="color:red">
   <c:if test="${requestScope.exception != null}">
<c:out value="${requestScope.exception}" />
       </c:if>
       </h3>
       <form action="${pageContext.request.contextPath}/department/id" method="get">
           <input type="text" name="id" placeholder="id"><br/>
           <input type="submit" value="получить данные"/>
       </form>

<h1><a href="${pageContext.request.contextPath}/department/list">Посмотреть список всех отделов</a></h1>

 <form action="${pageContext.request.contextPath}/" method="get">
      <input type="submit" value="назад">
  </form>

</body>
</html>
