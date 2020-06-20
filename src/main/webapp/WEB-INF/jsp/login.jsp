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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {offset: -20px;}
</style>
</head>
<body>

	<form:form action="logUser" modelAttribute="uzytkownik" method="POST">
				
	
		<table>
			<tbody>
				<tr>
					<td><label>Login</label></td>
					<td><form:input path="login" /><c:if test = "${not empty loginNieIstnieje}">
        														<font color="red">Podany użytkownik nie istnieje w systemie</font>
       														</c:if></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><form:password path="haslo" /><c:if test = "${not empty niepoprawneHaslo}">
        														<font color="red">Wpisano błędny login lub hasło</font>
       														</c:if></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Zaloguj" /></td>
				</tr>
				
				
			</tbody>
		</table>
		
	</form:form>
	
</body>
</html>