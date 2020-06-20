<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ page
language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
%>
	<title> Basket</title>	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
		
						
			<h2>Zmiana hasła</h2>
			<p> Użytkownik ${uzytkownik.login} </p>
			<form action = "/savePassword" method = "post">
								<p> Podaj obecne hasło </p> <c:if test = "${not empty niepoprawneHaslo}">
        														<p style="color:red">Wpisano błędne hasło</p>
       														</c:if>
								
								<input type="password" name = "oldPassword"/>
								<p> Podaj nowe hasło </p>
        						<input type="password" name="newPassword"/>
								<input type="submit" value="Zmien hasło"/>
							</form>
			<br>
			<input type="button" value="Powrót"
				onclick="window.location.href='/sessionUser'; return false;"
			/>   
			
		
		</div>
	
	</div>

</body>

</html>