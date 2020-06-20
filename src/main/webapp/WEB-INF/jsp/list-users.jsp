<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<%@ page
language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
%>
	<title> List Userss</title>	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Lista użytkowników</h2>
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
			<input type="button" value="Powrót"
				onclick="window.location.href='/sessionUser'; return false;"
			/>  
			
			<br><br>
			
			<table border="2">
				<tr>
					<th> Login uzytkownika</th>
					<th> Uprawnienia</th>
				</tr>	
				
				<c:forEach var="tempUser" items="${uzytkownicy}">
					
					<tr>
						<td>	${tempUser.login}	</td>
						<td> 	${tempUser.rola } </td>
					</tr>
				</c:forEach>
			
			</table>
		
		
		</div>
	
	</div>

</body>

</html>