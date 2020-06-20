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
		
		<h2>Szczegóły produktu</h2>
		<table>
			<tbody>
				<tr>
					<td><label>Nazwa:</label></td>
					<td><label>${produkt.nazwaP }</label></td>
				</tr>
				<tr>
					<td><label>Liczba sztuk:</label></td>
					<td><label>${produkt.liczbaSztuk }</label></td>
				</tr>
				<tr>
					<td><label>Cena: </label></td>
					<td><label>${produkt.cena } </label></td>
				</tr>
				<tr>
					<td><label>Producent</label></td>
					<td><label>${produkt.producent } </label></td>				
				</tr>
				<tr>
					<td><label>Kategorie</label></td>
					<td><c:forEach var="kat" items="${produkt.kategorie}">
						${kat}<br>
						</c:forEach></td>					
				</tr>
				<tr>
					<td><label>Opis</label></td>
					<td><label>${produkt.opis } </label></td>				
				</tr>
				
				
			</tbody>
		</table>
	
	<p>
		<a href="${pageContext.request.contextPath}/produkt/list">Powrot do listy</a> 
	</p>	
</body>
</html>