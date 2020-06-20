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
		
			
			<p> ${uzytkownik.login}, ${uzytkownik.rola} <input type="button" value="Wyloguj"
				onclick="window.location.href='/logout'; return false;"
			/> </p>
			<h2>Lista produktow w koszyku</h2>
			<br><br>
			<input type="button" value="Dodaj do koszyka"
				onclick="window.location.href='/produkt/list'; return false;"
			/> 
			&nbsp;
			<input type="button" value="Złóż zamówienie"
				onclick="window.location.href='/produkt/makeOrder'; return false;"
			/>			  
			<br><br>
			<table border="2">
				<tr>
					<th> Nazwa produktu</th>
					<th> Liczba sztuk</th>
					<th> Akcje</th>
				</tr>	
				
				<c:forEach var="zamowienieProdukt" items="${zamowienie.zamowienieProdukt}">
				
					<c:url var="updateLink" value="/produkt/showFormForOrderUpdate">
						<c:param name="produktZamowienieId" value="${zamowienieProdukt.id_produkt_zamowienie}" />
					</c:url>
					
					<c:url var="deleteLink" value="/produkt/deleteProduktZamowienie">
						<c:param name="produktZamowienieId" value="${zamowienieProdukt.id_produkt_zamowienie}" />
					</c:url>
					<tr>
						<td> ${zamowienieProdukt.id_produktu.nazwaP}</td>
						<td> ${zamowienieProdukt.ilosc }</td>
						<td> 
							<a href="${updateLink}">Zmien ilosc</a>
							|
							<a href="${deleteLink}"
							onclick="if (!(confirm('Czy na pewno chcesz usunac produkt z koszyka?'))) return false">Usun z koszyka</a>
						</td>
					</tr>
				</c:forEach>
			
			</table>
			<h2>Suma cen produktów w koszyku : <fmt:formatNumber type="number" maxFractionDigits="2" value="${cena}"/> zł</h2>
			<br>
			<input type="button" value="Powrót"
				onclick="window.location.href='/sessionUser'; return false;"
			/>    
		
		</div>
	
	</div>

</body>

</html>