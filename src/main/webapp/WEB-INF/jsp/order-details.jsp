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
	<title> Szczegóły zamówienia</title>	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
		
			
			<p> ${uzytkownik.login}, ${uzytkownik.rola} <input type="button" value="Wyloguj"
				onclick="window.location.href='/logout'; return false;"
			/> </p>
			<h2>Lista produktow w zamówieniu</h2>
			<br>
			<table border="2">
				<tr>
					<th> Nazwa produktu</th>
					<th> Liczba sztuk</th>
				</tr>	
				
				<c:forEach var="zamowienieProdukt" items="${zamowienie.zamowienieProdukt}">
					<tr>
						<td> ${zamowienieProdukt.id_produktu.nazwaP}</td>
						<td> ${zamowienieProdukt.ilosc }</td>
					</tr>
				</c:forEach>
			
			</table>
			<h2>Suma cen produktów w zamówieniu : <fmt:formatNumber type="number" maxFractionDigits="2" value="${cena}"/> zł</h2>
			<br>
			<input type="button" value="Powrót"
				onclick="window.location.href='/sessionUser'; return false;"
			/>
		</div>
	
	</div>

</body>

</html>