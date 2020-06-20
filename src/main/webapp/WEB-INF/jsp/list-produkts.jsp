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
	<title> List Products</title>	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Lista produktów sklepu</h2>
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
		
			<input type="button" value="Dodaj produkt"
				onclick="window.location.href='showFormForAdd'; return false;"
			/>
			&nbsp;
			<input type="button" value="Powrót"
				onclick="window.location.href='/sessionUser'; return false;"
			/>  
			
			<br><br>
			
			<table border="2">
				<tr>
					<th> Nazwa uzytkownika</th>
					<th> Producent</th>
					<th> Kategorie</th>
					<th> Liczba sztuk</th>
					<th> Cena</th>
					<th></th>
				</tr>	
				
				<c:forEach var="tempProdukt" items="${produkts}">
				
					<c:url var="updateLink" value="/produkt/showFormForUpdate">
						<c:param name="produktId" value="${tempProdukt.idP}" />
					</c:url>
					
					<c:url var="deleteLink" value="/produkt/delete">
						<c:param name="produktId" value="${tempProdukt.idP}" />
					</c:url>
					
					<c:url var="detailsLink" value="/produkt/details">
						<c:param name="produktId" value="${tempProdukt.idP}" />
					</c:url>
					<tr>
						<td> ${tempProdukt.nazwaP }</td>
						<td> ${tempProdukt.producent }</td>
						<td> <c:forEach var="kat" items="${tempProdukt.kategorie}">
						
								
								
								${kat}<br>
								
								
							 </c:forEach></td>
						 
						<td> ${tempProdukt.liczbaSztuk }</td>
						<td> ${tempProdukt.cena } zł</td>
						<td><c:if test = "${(uzytkownik.rola eq 'admin') || (uzytkownik.rola eq 'pracownik')}">	 
							<a href="${updateLink}">Modyfikuj</a>
							|
							</c:if>
							<a href="${deleteLink}"
							onclick="if (!(confirm('Czy na pewno chcesz usunac produkt?'))) return false">Usun</a>
							|
							<a href="${detailsLink}">Szczegóły produktu</a>
							|
							<form action = "/produkt/addToBasket" method = "post">
								<input type="hidden" name = "produktId" value="${tempProdukt.idP}"/>
        						<input type="number" name="ilosc" value=""/>
								<input type="submit" value="Zamow"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			
			</table>
		
		
		</div>
	
	</div>

</body>

</html>