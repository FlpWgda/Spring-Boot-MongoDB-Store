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
<title>Insert title here</title>
<style>
	.error {color:red}
</style>
</head>
<body>
	<p> ${uzytkownik.login}, ${uzytkownik.rola} <input type="button" value="Wyloguj"
				onclick="window.location.href='/logout'; return false;"
			/> </p>
	<form:form action="saveProduktZamowienie" modelAttribute="zamowienieProdukt" method="POST">
		<form:hidden path="id_produkt_zamowienie" />
		<form:hidden path="id_produktu" />
		
		
		<table>
			<tbody>
				<tr>
						<td><label>Nazwa produktu</label></td>
						<td> ${zamowienieProdukt.id_produktu.nazwaP }</td>
				</tr>
				<tr>
						<td><label>Producent</label></td>
						<td> ${zamowienieProdukt.id_produktu.producent }</td>
				</tr>
				<tr>
						<td><label>Kategorie</label></td>
						<td> <c:forEach var="kat" items="${zamowienieProdukt.id_produktu.kategorie}">								
								${kat}<br>								
							 </c:forEach></td>
				</tr>
				<tr>
						<td><label>Opis</label></td>
						<td> ${zamowienieProdukt.id_produktu.opis }</td>
				</tr>
				<tr>
						<td><label>Liczba dostepnych sztuk</label></td>
						<td> ${zamowienieProdukt.id_produktu.liczbaSztuk }</td>
				</tr>
				<tr>
					<td><label>Liczba sztuk</label></td>
					<td><form:input path="ilosc" />
						<form:errors path="ilosc" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Dodaj" /></td>
				</tr>
				
				
			</tbody>
		</table>
		
	</form:form>
	
	<p>
		<a href="${pageContext.request.contextPath}/produkt/list">Powrot do listy</a> 
	</p>	
</body>
</html>