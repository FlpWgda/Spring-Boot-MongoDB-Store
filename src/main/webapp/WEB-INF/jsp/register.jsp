<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {offset: -20px;}
</style>
</head>
<body>

	<form:form action="saveUser" modelAttribute="uzytkownik2" method="POST">		
       		
		<c:if test = "${uzytkownik.rola eq 'admin'}">
			<h2>Dodaj konto pracownika</h2>
		</c:if>
		<c:if test = "${uzytkownik.rola ne 'admin'}">
			<h2>Dodaj konto </h2>
		</c:if>
		<table>
			<tbody>
				<tr>
					<td><label>Login</label></td>
					<td><form:input path="login" /> <c:if test = "${not empty exists }">
											        	<font color="red">Podany login istnieje w systemie</font>
											       	</c:if>	</td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><form:password path="haslo" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Stworz konto" /></td>
				</tr>
				
				
			</tbody>
		</table>
		
	</form:form>
	
</body>
</html>